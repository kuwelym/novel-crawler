package com.group21.novel_crawler.controller;

import com.group21.novel_crawler.service.NovelService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/novels/export")
public class NovelExportController {

    private final NovelService novelService;
    public NovelExportController(NovelService novelService) {
        this.novelService = novelService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportChapterPdf(
            @RequestParam String novelName,
            @RequestParam(defaultValue = "1") int chapterNumber
    ) {
        byte[] pdfBytes = novelService.exportChapterToPdf(novelName, chapterNumber);
        return createResponseEntity(pdfBytes, novelName, chapterNumber, "pdf", MediaType.APPLICATION_PDF);
    }

    @GetMapping("/epub")
    public ResponseEntity<byte[]> exportChapterEpub(
            @RequestParam String novelName,
            @RequestParam(defaultValue = "1") int chapterNumber
    ) {
        byte[] epubBytes = novelService.exportChapterToEpub(novelName, chapterNumber);
        return createResponseEntity(epubBytes, novelName, chapterNumber, "epub", MediaType.parseMediaType("application/epub+zip"));
    }

    @GetMapping("/txt")
    public ResponseEntity<byte[]> exportChapterTxt(
            @RequestParam String novelName,
            @RequestParam(defaultValue = "1") int chapterNumber
    ) {
        byte[] txtBytes = novelService.exportChapterToTxt(novelName, chapterNumber);
        return createResponseEntity(txtBytes, novelName, chapterNumber, "txt", MediaType.TEXT_PLAIN);
    }

    private ResponseEntity<byte[]> createResponseEntity(byte[] fileBytes, String novelName, int chapterNumber, String extension, MediaType mediaType) {
        String fileName = String.format("%s chapter-%d.%s", novelName, chapterNumber, extension);

        // Set the appropriate HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileBytes);
    }
}
