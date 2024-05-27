package com.group21.novel_crawler.service;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.ChapterNovel;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.exception.InternalServerErrorException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NovelService {

    public PageableData<Novel> getAllNovels(String filter, int page) {
        PageableData<Novel> pageableData = new PageableData<Novel>();
        List<Novel> novelList = new ArrayList<>();

        try {
            String baseUrl = "https://truyenfull.vn/danh-sach/";
            String url = String.format("%s%s/trang-%d", baseUrl, filter, page);

            Document doc = Jsoup.connect(url).get();

            // Select the element <div class="list-truyen">
            Element listTruyenDiv = doc.selectFirst("div.list-truyen");

            // Select all elements with class equals to "row"
            if (listTruyenDiv != null) {
                Elements rowElements = listTruyenDiv.select("div.row");

                for (Element row : rowElements) {
                    Novel newNovel = new Novel();

                    // Get cover URL from novel
                    Element coverElement = row.selectFirst("div[data-classname='cover']");
                    if (coverElement != null) {
                        String coverUrl = coverElement.attr("data-image");
                        newNovel.setCoverUrl(coverUrl);
                    }

                    // Get title from novel
                    Element titleElement = row.selectFirst("div.col-xs-7 h3.truyen-title a");
                    if (titleElement != null) {
                        String title = titleElement.text();
                        newNovel.setTitle(title);
                    }

                    // Get author from novel
                    Element authorElement = row.selectFirst("div.col-xs-7 span.author");
                    if (authorElement != null) {
                        String author = authorElement.text();
                        newNovel.setAuthor(author);
                    }

                    // Add newNovel to novelList
                    novelList.add(newNovel);
                }
            }

            // Get total pages
            Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");

            // If the pagination element is found
            if (paginationUl != null) {
                // Select all <a> elements inside
                Elements pageLinks = paginationUl.select("a");

                // If there is at least one <a> element
                if (!pageLinks.isEmpty()) {
                    // Get the second last element
                    Element lastPageLink = pageLinks.get(pageLinks.size() - 2);

                    // Get the value of the href attribute from this element
                    String lastPageHref = lastPageLink.attr("href");

                    // Extract the number of pages from the URL
                    String[] parts = lastPageHref.split("/");
                    String totalPagesStr = parts[parts.length - 1];
                    totalPagesStr = totalPagesStr.replace("trang-", "").replace("/", "");
                    int totalPages = Integer.parseInt(totalPagesStr);

                    // Set total pages
                    pageableData.setTotalPages(totalPages);
                }

                pageableData.setContent(novelList);
                pageableData.setTotalElements(novelList.size());
            }
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return pageableData;
    }

    public PageableData<Novel> searchNovel(String searchText, int page) {
        PageableData<Novel> pageableData = new PageableData<Novel>();
        List<Novel> novelList = new ArrayList<>();

        try {
            String baseUrl = "https://truyenfull.vn/tim-kiem/";
            String url = String.format("%s?tukhoa=%s&page=%s", baseUrl, searchText, page);

            Document doc = Jsoup.connect(url).get();

            // Select the element <div class="list-truyen">
            Element listTruyenDiv = doc.selectFirst("div.list-truyen");

            // Select all elements with class equals to "row"
            if (listTruyenDiv != null) {
                Elements rowElements = listTruyenDiv.select("div.row");

                for (Element row : rowElements) {
                    Novel newNovel = new Novel();

                    // Get cover URL from novel
                    Element coverElement = row.selectFirst("div[data-classname='cover']");
                    if (coverElement != null) {
                        String coverUrl = coverElement.attr("data-image");
                        newNovel.setCoverUrl(coverUrl);
                    }

                    // Get title from novel
                    Element titleElement = row.selectFirst("div.col-xs-7 h3.truyen-title a");
                    if (titleElement != null) {
                        String title = titleElement.text();
                        newNovel.setTitle(title);
                    }

                    // Get author from novel
                    Element authorElement = row.selectFirst("div.col-xs-7 span.author");
                    if (authorElement != null) {
                        String author = authorElement.text();
                        newNovel.setAuthor(author);
                    }

                    // Add newNovel to novelList
                    novelList.add(newNovel);
                }
            }

            // Get totalPages
            Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");

            // If the pagination element is found
            if (paginationUl != null) {
                // Select all <a> elements inside
                Elements pageLinks = paginationUl.select("a");

                // If there is at least one <a> element
                if (!pageLinks.isEmpty()) {
                    // Get the second last element
                    Element lastPageLink = pageLinks.get(pageLinks.size() - 2);

                    // Get the value of the href attribute from this element
                    String lastPageHref = lastPageLink.attr("href");

                    int totalPages = 1;
                    if (lastPageHref.contains("page=")) {
                        String[] parts = lastPageHref.split("page=");
                        String totalPagesStr = parts[1].split("&")[0];
                        totalPages = Integer.parseInt(totalPagesStr);
                    } else {
                        String[] parts = lastPageHref.split("/");
                        String totalPagesStr = parts[parts.length - 1].replace("trang-", "").replace("/", "");
                        totalPages = Integer.parseInt(totalPagesStr);
                    }

                    // Set total pages
                    pageableData.setTotalPages(totalPages);
                }
            }

            pageableData.setContent(novelList);
            pageableData.setTotalElements(novelList.size());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return pageableData;
    }

    public PageableData<Novel> getNovelDetail(String novelName, int page) {
        String baseUrl = "https://truyenfull.vn";
        Novel novel = new Novel();
        PageableData<Novel> pageableData = new PageableData<Novel>();

        try {
            String url = String.format("%s/%s/trang-%d/#list-chapter", baseUrl, novelName, page);
            Document doc = Jsoup.connect(url).get();

            Element novelInfoDiv = doc.selectFirst("div.col-xs-12.col-info-desc");
            if (novelInfoDiv != null) {
                // Set novel title
                String title = novelInfoDiv.selectFirst("h3.title").text();
                novel.setTitle(title);

                // Set cover URL
                Element imgElement = novelInfoDiv.selectFirst("div.book img");
                if (imgElement != null) {
                    String coverUrl = imgElement.attr("src");
                    novel.setCoverUrl(coverUrl);
                }

                // Set author
                Element authorElement = novelInfoDiv.selectFirst("div.info-holder div.info a[href~=tac-gia]");
                if (authorElement != null) {
                    String author = authorElement.text();
                    novel.setAuthor(author);
                }

                // Set genres
                Elements genreElements = novelInfoDiv.select("div.info-holder div.info a[href~=the-loai]");
                List<String> genres = new ArrayList<>();
                for (Element genreElement : genreElements) {
                    genres.add(genreElement.text());
                }

                novel.setGenres(genres);

                // Set status
                Element statusElement = novelInfoDiv.selectFirst("div.info-holder div.info span.text-success");
                if (statusElement != null) {
                    String status = statusElement.text();
                    novel.setStatus(status);
                }

                // Set description
                Element descriptionElement = novelInfoDiv.selectFirst("div.desc div.desc-text");
                if (descriptionElement != null) {
                    novel.setDescription(descriptionElement.toString());
                }
            }

            // Get chapter list
            Element listChapterDiv = doc.selectFirst("#list-chapter");
            if (listChapterDiv != null) {
                // Get the list of <li> elements
                Elements chapterElements = listChapterDiv.select("ul.list-chapter li");
                List<String> chapters = new ArrayList<>();
                for (Element chapterElement : chapterElements) {
                    // Extract information about the chapter number and title
                    String chapterTitle = chapterElement.selectFirst("a").text();
                    chapters.add(chapterTitle);
                }

                // Save chapter list to novel object
                novel.setChapters(chapters);
            }

            // Get total pages of chapter list
            Element paginationUl = doc.selectFirst("ul.pagination.pagination-sm");

            // Get the last <li> element in the <ul>
            Elements liElements = paginationUl.select("li");
            Element lastPageLi = liElements.get(liElements.size() - 2);

            String lastPageHref = lastPageLi.selectFirst("a").attr("href");

            // Extract the page number from the href attribute
            String lastPageNumber = lastPageHref.replaceAll("^.*trang-(\\d+).*$", "$1");

            // Extract the page number from the text
            int totalPages = Integer.parseInt(lastPageNumber);

            pageableData.setContent(Collections.singletonList(novel));
            pageableData.setTotalPages(totalPages);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return pageableData;
    }

    public ChapterNovel getChapterNovel(String novelName, int chapterNumber) {
        String baseUrl = "https://truyenfull.vn";
        ChapterNovel chapterNovel = new ChapterNovel();
        try {
            String url = String.format("%s/%s/chuong-%d", baseUrl, novelName, chapterNumber);
            Document doc = Jsoup.connect(url).get();

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
    }

    public byte[] exportChapterToPdf(String novelName, int chapterNumber) {
        String baseUrl = "https://truyenfull.vn";
        try {
            String url = String.format("%s/%s/chuong-%d", baseUrl, novelName, chapterNumber);
            Document doc = Jsoup.connect(url).get();

            // Get novel title
            String novelTitle = "";
            Element novelTitleElement = doc.selectFirst("a.truyen-title");
            if (novelTitleElement != null) {
                novelTitle = novelTitleElement.text();
            }

            // Get chapter title
            String chapterTitle = "";
            Element chapterTitleElement = doc.selectFirst("h2 a.chapter-title");
            if (chapterTitleElement != null) {
                chapterTitle = chapterTitleElement.text();
            }

            // Get chapter content
            String chapterContent = "";
            Element chapterContentDiv = doc.selectFirst("div#chapter-c");
            if (chapterContentDiv != null) {
                chapterContentDiv.select(".ads-responsive").remove();
                chapterContent = chapterContentDiv.text();
            }

            // Export to PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Load the font supporting Vietnamese characters
            String fontPath = "src/main/resources/times.ttf";
            byte[] fontBytes = Files.readAllBytes(Paths.get(fontPath));
            PdfFont font = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H, true);

            com.itextpdf.layout.Document pdfDoc = new com.itextpdf.layout.Document(pdfDocument);

            // Set the font for paragraphs
            pdfDoc.setFont(font);

            // Add novel title
            Text novelTitleText = new Text(novelTitle).setFont(font).setBold().setFontSize(18);
            Paragraph novelTitleParagraph = new Paragraph(novelTitleText).setTextAlignment(TextAlignment.CENTER);
            pdfDoc.add(novelTitleParagraph);

            // Add chapter title
            Text chapterTitleText = new Text(chapterTitle).setFont(font).setBold().setFontSize(15);
            Paragraph chapterTitleParagraph = new Paragraph(chapterTitleText).setTextAlignment(TextAlignment.CENTER);
            pdfDoc.add(chapterTitleParagraph);

            // Add chapter content
            Paragraph chapterContentParagraph = new Paragraph(chapterContent).setFont(font).setFontSize(12);
            pdfDoc.add(chapterContentParagraph);

            pdfDoc.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}