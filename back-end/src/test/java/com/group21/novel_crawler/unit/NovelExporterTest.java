package com.group21.novel_crawler.unit;

import com.group21.novel_crawler.controller.NovelExportController;
import com.group21.novel_crawler.service.NovelExporter;
import com.group21.novel_crawler.service.NovelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class NovelExporterTest {

    @InjectMocks
    NovelExportController novelExportController;

    @Mock
    NovelExporter novelExporter;

    @Mock
    NovelService novelService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidNovelName() throws Exception {
        String invalidNovelName = "invalid-novel-name";
        int chapterNumber = 1;

        doThrow(new Exception()).when(novelExporter).fetchChapterDetails(invalidNovelName, chapterNumber);

        assertThrows(Exception.class, () -> novelExporter.fetchChapterDetails(invalidNovelName, chapterNumber));
    }

    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidChapterNumber() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int invalidChapterNumber = -1;

        doThrow(new Exception()).when(novelExporter).fetchChapterDetails(novelName, invalidChapterNumber);

        assertThrows(Exception.class, () -> novelExporter.fetchChapterDetails(novelName, invalidChapterNumber));
    }

    @Test
    public void exportChapterToPdfReturnsSuccessfully() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToPdf(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterPdf(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }

    @Test
    public void exportChapterToEpubReturnsSuccessfully() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToEpub(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterEpub(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }

    @Test
    public void exportChapterToTxtReturnsSuccessfully() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToTxt(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterTxt(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }
}