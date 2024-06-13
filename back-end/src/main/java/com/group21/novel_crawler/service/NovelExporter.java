package com.group21.novel_crawler.service;

import com.group21.novel_crawler.exception.InternalServerErrorException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import lombok.Getter;
import lombok.Setter;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class NovelExporter {

    public static final String BASE_URL = "https://truyenfull.vn";

    public byte[] exportChapterToPdf(String novelName, int chapterNumber) {
        try {
            ChapterDetails chapterDetails = fetchChapterDetails(novelName, chapterNumber);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            createPdf(outputStream, chapterDetails);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public ChapterDetails fetchChapterDetails(String novelName, int chapterNumber) throws Exception {
        String url = String.format("%s/%s/chuong-%d", BASE_URL, novelName, chapterNumber);
        Document doc = Jsoup.connect(url).get();

        ChapterDetails chapterDetails = new ChapterDetails();
        chapterDetails.setNovelTitle(fetchText(doc, "a.truyen-title"));
        chapterDetails.setChapterTitle(fetchText(doc, "h2 a.chapter-title"));

        Element chapterContentDiv = doc.selectFirst("div#chapter-c");
        if (chapterContentDiv != null) {
            chapterContentDiv.select(".ads-responsive").remove();
            chapterDetails.setChapterContent(chapterContentDiv.text());
        }

        return chapterDetails;
    }

    private String fetchText(Document doc, String cssQuery) {
        Element element = doc.selectFirst(cssQuery);
        return element != null ? element.text() : "";
    }

    private void createPdf(ByteArrayOutputStream outputStream, ChapterDetails chapterDetails) {
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        PdfFont font = loadFont();

        com.itextpdf.layout.Document pdfDoc = new com.itextpdf.layout.Document(pdfDocument);
        pdfDoc.setFont(font);

        addTitle(pdfDoc, chapterDetails.getNovelTitle(), font, 18);
        addTitle(pdfDoc, chapterDetails.getChapterTitle(), font, 15);
        addContent(pdfDoc, chapterDetails.getChapterContent(), font);

        pdfDoc.close();
    }

    public byte[] exportChapterToEpub(String novelName, int chapterNumber) {
        try {
            ChapterDetails chapterDetails = fetchChapterDetails(novelName, chapterNumber);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            createEpub(outputStream, chapterDetails);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private void createEpub(ByteArrayOutputStream outputStream, ChapterDetails chapterDetails) throws Exception {
        Book book = new Book();
        Metadata metadata = book.getMetadata();
        metadata.addTitle(chapterDetails.getNovelTitle());
        metadata.addTitle(chapterDetails.getChapterTitle());

        String htmlContent = "<html><head><title>" + chapterDetails.getChapterTitle() + "</title></head>"
                + "<body>" + chapterDetails.getChapterContent() + "</body></html>";
        book.addSection(chapterDetails.getChapterTitle(), new Resource(htmlContent.getBytes(), "chapter1.html"));

        EpubWriter epubWriter = new EpubWriter();
        epubWriter.write(book, outputStream);
    }

    public byte[] exportChapterToTxt(String novelName, int chapterNumber) {
        try {
            ChapterDetails chapterDetails = fetchChapterDetails(novelName, chapterNumber);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            createTxt(outputStream, chapterDetails);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private void createTxt(ByteArrayOutputStream outputStream, ChapterDetails chapterDetails) throws Exception {
        try (Writer writer = new OutputStreamWriter(outputStream)) {
            writer.write(chapterDetails.getNovelTitle() + "\n");
            writer.write(chapterDetails.getChapterTitle() + "\n");
            writer.write(chapterDetails.getChapterContent());

        }
    }

    private PdfFont loadFont() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("times.ttf")) {
            if (is == null) {
                throw new InternalServerErrorException("Font file not found: times.ttf");
            }
            byte[] fontBytes = is.readAllBytes();
            return PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H, true);
        } catch (IOException e) {
            throw new InternalServerErrorException("Failed to load font file: " + e.getMessage());
        }
    }


    private void addTitle(com.itextpdf.layout.Document pdfDoc, String text, PdfFont font, float fontSize) {
        Text titleText = new Text(text).setFont(font).setBold().setFontSize(fontSize);
        Paragraph titleParagraph = new Paragraph(titleText).setTextAlignment(TextAlignment.CENTER);
        pdfDoc.add(titleParagraph);
    }

    private void addContent(com.itextpdf.layout.Document pdfDoc, String text, PdfFont font) {
        Paragraph contentParagraph = new Paragraph(text).setFont(font).setFontSize((float) 12);
        pdfDoc.add(contentParagraph);
    }

    @Setter
    @Getter
    public static class ChapterDetails {
        private String novelTitle;
        private String chapterTitle;
        private String chapterContent;

    }
}
