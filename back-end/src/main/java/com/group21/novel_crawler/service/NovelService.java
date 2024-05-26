package com.group21.novel_crawler.service;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.exception.InternalServerErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NovelService {

    private static final String BASE_URL = "https://truyenfull.vn";

    public PageableData<Novel> getAllNovels(String filter, int page) {
        String url = String.format("%s/danh-sach/%s/trang-%d", BASE_URL, filter, page);
        return fetchNovels(url);
    }

    public PageableData<Novel> searchNovel(String searchText, int page) {
        String url = String.format("%s/tim-kiem?tukhoa=%s&page=%d", BASE_URL, searchText, page);
        return fetchNovels(url);
    }

    private PageableData<Novel> fetchNovels(String url) {
        PageableData<Novel> pageableData = new PageableData<>();
        List<Novel> novelList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Element listTruyenDiv = doc.selectFirst("div.list-truyen");
            if (listTruyenDiv != null) {
                Elements rowElements = listTruyenDiv.select("div.row");
                for (Element row : rowElements) {
                    Novel newNovel = extractNovelDetails(row);
                    novelList.add(newNovel);
                }
            }

            int totalPages = extractTotalPages(doc);
            pageableData.setTotalPages(totalPages);
            pageableData.setContent(novelList);
            pageableData.setTotalElements(novelList.size());

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return pageableData;
    }

    private Novel extractNovelDetails(Element row) {
        Novel novel = new Novel();

        Element coverElement = row.selectFirst("div[data-classname='cover']");
        if (coverElement != null) {
            String coverUrl = coverElement.attr("data-image");
            novel.setCoverUrl(coverUrl);
        }

        Element titleElement = row.selectFirst("div.col-xs-7 h3.truyen-title a");
        if (titleElement != null) {
            String title = titleElement.text();
            novel.setTitle(title);
        }

        Element authorElement = row.selectFirst("div.col-xs-7 span.author");
        if (authorElement != null) {
            String author = authorElement.text();
            novel.setAuthor(author);
        }

        return novel;
    }

    private int extractTotalPages(Document doc) {
        Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");
        if (paginationUl != null) {
            Elements pageLinks = paginationUl.select("a");
            if (!pageLinks.isEmpty()) {
                Element lastPageLink = pageLinks.get(pageLinks.size() - 2);
                String lastPageHref = lastPageLink.attr("href");

                if (lastPageHref.contains("page=")) {
                    String[] parts = lastPageHref.split("page=");
                    String totalPagesStr = parts[1].split("&")[0];
                    return Integer.parseInt(totalPagesStr);
                } else {
                    String[] parts = lastPageHref.split("/");
                    String totalPagesStr = parts[parts.length - 1].replace("trang-", "").replace("/", "");
                    return Integer.parseInt(totalPagesStr);
                }
            }
        }
        return 1;
    }

}
