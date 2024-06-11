package com.group21.novel_crawler.service;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.*;
import com.group21.novel_crawler.exception.InternalServerErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Component
public class NovelScraper {

    private static final String BASE_URL = "https://truyenfull.vn";

    private <T> T executeWithExceptionHandling(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private Document getDocument(String url) {
        return executeWithExceptionHandling(() -> {
            try {
                return Jsoup.connect(url).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Genre extractGenre(Element item) {
        Genre genre = new Genre();
        genre.setName(item.text());
        genre.setUrl(item.attr("href").split("/")[4]);
        return genre;
    }

    private Novel extractNovel(Element element) {
        Novel novel = new Novel();
        novel.setTitle(Objects.requireNonNull(element.selectFirst("h3[itemprop='name']")).text());
        novel.setCoverUrl(Objects.requireNonNull(element.selectFirst("img[itemprop='image']")).attr("src"));
        novel.setNameUrl(Objects.requireNonNull(element.selectFirst("a")).attr("href").split("/")[3]);
        novel.setIsFull(element.selectFirst("span.full-label") != null);
        return novel;
    }

    private List<Genre> getGenresFromElements(Elements elements) {
        List<Genre> genres = new ArrayList<>();
        for (Element item : elements) {
            genres.add(extractGenre(item));
        }
        return genres;
    }

    public HeaderData getHeaderData() {
        return executeWithExceptionHandling(() -> {
            Document doc = getDocument(BASE_URL);

            Elements novelTypeElements = doc.select(".navbar-nav > .dropdown > .dropdown-menu > li > a");
            Elements novelGenreElements = doc.select(".navbar-nav > .dropdown > .dropdown-menu.multi-column a");

            List<Genre> types = getGenresFromElements(novelTypeElements).subList(0, novelTypeElements.size() - 4);
            List<Genre> genres = getGenresFromElements(novelGenreElements);

            HeaderData headerData = new HeaderData();
            headerData.setNovelTypes(types);
            headerData.setNovelGenres(genres);

            return headerData;
        });
    }

    private List<Novel> extractHotNovels(Document doc) {
        List<Novel> hotNovelList = new ArrayList<>();
        Element indexIntroDiv = doc.selectFirst("div.index-intro");
        if (indexIntroDiv != null) {
            Elements hotNovelElements = indexIntroDiv.select("div.item");
            for (Element element : hotNovelElements) {
                Novel novel = extractNovel(element);
                hotNovelList.add(novel);
            }
        }
        return hotNovelList;
    }

    private List<Novel> extractNewNovels(Document doc) {
        List<Novel> newNovelList = new ArrayList<>();
        Element listNewDiv = doc.selectFirst("div.list.list-truyen.list-new.col-xs-12.col-sm-12.col-md-8.col-truyen-main");
        if (listNewDiv != null) {
            Elements newNovelElements = listNewDiv.select("div.row");
            for (Element element : newNovelElements) {
                Novel novel = Novel.builder()
                        .title(Objects.requireNonNull(element.selectFirst("h3[itemprop='name']")).text())
                        .nameUrl(Objects.requireNonNull(element.selectFirst("h3[itemprop='name'] a")).attr("href").split("/")[3])
                        .newestChapter(Objects.requireNonNull(element.selectFirst("div.text-info a")).text().split(" ")[1])
                        .timeOfNewEstChapter(Objects.requireNonNull(element.selectFirst("div.col-time")).text())
                        .build();

                Elements genreElements = element.select("a[itemprop='genre']");
                List<Genre> genres = getGenresFromElements(genreElements);
                novel.setGenres(genres);

                newNovelList.add(novel);
            }
        }
        return newNovelList;
    }

    private List<Genre> extractGenres(Document doc) {
        List<Genre> novelGenres = new ArrayList<>();
        Element listCatDiv = doc.selectFirst("div.list.list-truyen.list-cat.col-xs-12");
        if (listCatDiv != null) {
            Elements genreElements = listCatDiv.select("div.col-xs-6 a");
            for (Element element : genreElements) {
                Genre genre = new Genre();
                genre.setName(element.text());
                genre.setUrl(element.attr("href").split("/")[4]);
                novelGenres.add(genre);
            }
        }
        return novelGenres;
    }

    public HomeData getHomeData() {
        return executeWithExceptionHandling(() -> {
            HomeData homeData = new HomeData();
            List<Novel> hotNovelList = extractHotNovels(getDocument(BASE_URL));
            List<Novel> newNovelList = extractNewNovels(getDocument(BASE_URL));
            List<Genre> novelGenres = extractGenres(getDocument(BASE_URL));

            homeData.setHotNovelList(hotNovelList);
            homeData.setNewNovelList(newNovelList);
            homeData.setNovelGenres(novelGenres);

            return homeData;
        });
    }

    public PageableData<Novel> getNovelByType(String type, int page) {
        return executeWithExceptionHandling(() -> {
            PageableData<Novel> pageableData = new PageableData<>();
            List<Novel> novelList = new ArrayList<>();

            String url = String.format("%s/danh-sach/%s/trang-%d", BASE_URL, type, page);
            Document doc = getDocument(url);

            // Process novel list
            processNovelList(doc, novelList);

            // Process pagination
            processPagination(doc, pageableData);

            pageableData.setContent(novelList);
            pageableData.setTotalElements(novelList.size());

            return pageableData;
        });
    }

    private void processNovelList(Document doc, List<Novel> novelList) {
        Element listTruyenDiv = doc.selectFirst("div.list-truyen");

        if (listTruyenDiv != null) {
            Elements rowElements = listTruyenDiv.select("div.row");
            rowElements.removeIf(element -> "the-loai-show-ads".equals(element.id()));

            for (Element row : rowElements) {
                Novel newNovel = new Novel();

                populateNovelFields(newNovel, row);

                novelList.add(newNovel);
            }
        }
    }

    private void populateNovelFields(Novel novel, Element row) {
        // Get cover URL from novel
        Element coverElement = row.selectFirst("div[data-classname='cover']");
        if (coverElement != null) {
            novel.setCoverUrl(coverElement.attr("data-image"));
        }
        // Get title from novel
        Element titleElement = row.selectFirst("div.col-xs-7 h3.truyen-title a");
        if (titleElement != null) {
            novel.setTitle(titleElement.text());
            novel.setNameUrl(titleElement.attr("href").split("/")[3]);
        }
        // Get author from novel
        Element authorElement = row.selectFirst("div.col-xs-7 span.author");
        if (authorElement != null) {
            novel.setAuthor(authorElement.text());
        }
        // Get the newest chapter from novel
        Element chapterElement = row.selectFirst("div.text-info a");
        if (chapterElement != null) {
            if (chapterElement.text().split(" ").length == 1) {
                novel.setNewestChapter("0");
            } else {
                String newestChapter = chapterElement.text().split(" ")[1];
                novel.setNewestChapter(newestChapter);
            }
        }
    }

    private void processPagination(Document doc, PageableData<Novel> pageableData) {
        Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");

        if (paginationUl != null) {
            Elements pageLinks = paginationUl.select("a");

            if (!pageLinks.isEmpty()) {
                int totalPages = getTotalPages(pageLinks);
                pageableData.setTotalPages(totalPages);
            }
        }
    }

    public PageableData<Novel> getNovelByGenre(String genre, int page) {
        return executeWithExceptionHandling(() -> {

            PageableData<Novel> pageableData = new PageableData<>();
            List<Novel> novelList = new ArrayList<>();

            String url = String.format("%s/the-loai/%s/trang-%d", BASE_URL, genre, page);
            Document doc = getDocument(url);


            processNovelList(doc, novelList);

            // Get total pages
            processPagination(doc, pageableData);

            pageableData.setContent(novelList);
            pageableData.setTotalElements(novelList.size());

            return pageableData;
        });
    }

    private static int getTotalPages(Elements pageLinks) {
        Element lastPageLink = pageLinks.get(pageLinks.size() - 2);
        String lastPageHref = lastPageLink.attr("href");

        if (lastPageHref.contains("page=")) {
            return extractTotalPagesFromQueryParam(lastPageHref);
        } else {
            return extractTotalPagesFromUrl(lastPageHref);
        }
    }

    private static int extractTotalPagesFromQueryParam(String lastPageHref) {
        String[] parts = lastPageHref.split("page=");
        String totalPagesStr = parts[1].split("&")[0];
        return Integer.parseInt(totalPagesStr);
    }

    private static int extractTotalPagesFromUrl(String lastPageHref) {
        String[] parts = lastPageHref.split("/");
        String totalPagesStr = parts[parts.length - 1].replace("trang-", "").replace("/", "");
        return Integer.parseInt(totalPagesStr);
    }

    public PageableData<Novel> searchNovel(String searchText, int page) {
        return executeWithExceptionHandling(() -> {

            PageableData<Novel> pageableData = new PageableData<>();
            List<Novel> novelList = new ArrayList<>();

            String url = String.format("%s/tim-kiem/?tukhoa=%s&page=%s", BASE_URL, searchText, page);

            Document doc = getDocument(url);

            processNovelList(doc, novelList);

            // Get totalPages
            processPagination(doc, pageableData);

            pageableData.setContent(novelList);
            pageableData.setTotalElements(novelList.size());


            return pageableData;
        });
    }

    public PageableData<Novel> getNovelDetails(String novelName, int page) {
        return executeWithExceptionHandling(() -> {
            String url = String.format("%s/%s/trang-%d/#list-chapter", BASE_URL, novelName, page);
            Document doc = getDocument(url);

            Novel novel = extractNovelDetails(doc);
            novel.setChapters(extractChapterList(doc));
            int totalPages = extractTotalPages(doc);

            PageableData<Novel> pageableData = new PageableData<>();
            pageableData.setContent(Collections.singletonList(novel));
            pageableData.setTotalPages(totalPages);

            return pageableData;
        });
    }

    private Novel extractNovelDetails(Document doc) {
        Novel novel = new Novel();

        Element novelInfoDiv = doc.selectFirst("div.col-xs-12.col-info-desc");
        if (novelInfoDiv != null) {
            novel.setTitle(Objects.requireNonNull(novelInfoDiv.selectFirst("h3.title")).text());

            Element imgElement = novelInfoDiv.selectFirst("div.book img");
            if (imgElement != null) {
                novel.setCoverUrl(imgElement.attr("src"));
            }

            Element authorElement = novelInfoDiv.selectFirst("div.info-holder div.info a[href~=tac-gia]");
            if (authorElement != null) {
                novel.setAuthor(authorElement.text());
            }

            Elements genreElements = novelInfoDiv.select("div.info-holder div.info a[href~=the-loai]");
            List<Genre> genres = new ArrayList<>();
            for (Element genreElement : genreElements) {
                Genre genre = new Genre();
                genre.setUrl(genreElement.attr("href").split("/")[4]);
                genre.setName(genreElement.text());
                genres.add(genre);
            }
            novel.setGenres(genres);

            Element statusElement = novelInfoDiv.selectFirst("div.info-holder div.info span.text-success");
            if (statusElement != null) {
                novel.setStatus(statusElement.text());
            }

            Element descriptionElement = novelInfoDiv.selectFirst("div.desc div.desc-text");
            if (descriptionElement != null) {
                novel.setDescription(descriptionElement.toString());
            }
        }

        return novel;
    }

    private List<String> extractChapterList(Document doc) {
        List<String> chapters = new ArrayList<>();
        Element listChapterDiv = doc.selectFirst("#list-chapter");
        if (listChapterDiv != null) {
            Elements chapterElements = listChapterDiv.select("ul.list-chapter li");
            for (Element chapterElement : chapterElements) {
                chapters.add(Objects.requireNonNull(chapterElement.selectFirst("a")).text());
            }
        }
        return chapters;
    }

    private int extractTotalPages(Document doc) {
        Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");
        int totalPages = 1;

        if (paginationUl != null) {
            Elements liElements = paginationUl.select("li");
            Element activeLiElement = paginationUl.selectFirst("li.active");

            if (activeLiElement == liElements.get(liElements.size() - 2)) {
                String lastPageNumber = Objects.requireNonNull(paginationUl.selectFirst("li.active")).text().split(" ")[0];
                totalPages = Integer.parseInt(lastPageNumber);
            } else {
                Element lastPageLi = liElements.get(liElements.size() - 2);
                String lastPageHref = Objects.requireNonNull(lastPageLi.selectFirst("a")).attr("href");
                String lastPageNumber = lastPageHref.replaceAll("^.*trang-(\\d+).*$", "$1");
                totalPages = Integer.parseInt(lastPageNumber);
            }
        }

        return totalPages;
    }


    public ChapterNovel getChapterNovel(String novelName, int chapterNumber) {
        return executeWithExceptionHandling(() -> {

            ChapterNovel chapterNovel = new ChapterNovel();
            try {
                String url = String.format("%s/%s/chuong-%d", BASE_URL, novelName, chapterNumber);
                Document doc = getDocument(url);

                // Get title
                Element novelTitleElement = doc.selectFirst("a.truyen-title");
                if (novelTitleElement != null) {
                    String novelTitle = novelTitleElement.text();
                    chapterNovel.setNovelTitle(novelTitle);
                }

                // Get chapter number
                Element chapterTitleElement = doc.selectFirst("h2 a.chapter-title");
                if (chapterTitleElement != null) {
                    String chapterTitle = chapterTitleElement.text();
                    chapterNovel.setChapterTitle(chapterTitle);
                }

                // Get content of the chapter novel
                Element chapterContentDiv = doc.selectFirst("div#chapter-c");
                if (chapterContentDiv != null) {
                    chapterContentDiv.select(".ads-responsive").remove();
                    String chapterContent = chapterContentDiv.html();
                    chapterNovel.setChapterContent(chapterContent);
                }
            } catch (Exception e) {
                throw new InternalServerErrorException(e.getMessage());
            }

            return chapterNovel;
        });
    }
}
