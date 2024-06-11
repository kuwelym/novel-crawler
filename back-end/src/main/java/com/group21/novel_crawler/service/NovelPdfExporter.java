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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class NovelPdfExporter {

    private static final String BASE_URL = "https://truyenfull.vn";
    private static final String FONT_PATH = "src/main/resources/times.ttf";

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

    private ChapterDetails fetchChapterDetails(String novelName, int chapterNumber) throws Exception {
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

    private void createPdf(ByteArrayOutputStream outputStream, ChapterDetails chapterDetails) throws Exception {
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

    private PdfFont loadFont() throws Exception {
        byte[] fontBytes = Files.readAllBytes(Paths.get(NovelPdfExporter.FONT_PATH));
        return PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H, true);
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
    private static class ChapterDetails {
        private String novelTitle;
        private String chapterTitle;
        private String chapterContent;

    }
}
