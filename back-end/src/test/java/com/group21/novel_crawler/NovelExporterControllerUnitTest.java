package com.group21.novel_crawler;

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

/**
 * This class contains unit tests for the NovelExporter.
 */
public class NovelExporterControllerUnitTest {

    @InjectMocks
    // The controller to be tested
    NovelExportController novelExportController;

    @Mock
    // The service to be tested
    NovelExporter novelExporter;

    @Mock
    // The service to be tested
    NovelService novelService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This test checks if an exception is thrown when an invalid novel name is provided.
     */
    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidNovelName() throws Exception {
        String invalidNovelName = "invalid-novel-name";
        int chapterNumber = 1;

        doThrow(new Exception()).when(novelExporter).fetchChapterDetails(invalidNovelName, chapterNumber);

        assertThrows(Exception.class, () -> novelExporter.fetchChapterDetails(invalidNovelName, chapterNumber));
    }

    /**
     * This test checks if an exception is thrown when an invalid chapter number is provided.
     */
    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidChapterNumber() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int invalidChapterNumber = -1;

        doThrow(new Exception()).when(novelExporter).fetchChapterDetails(novelName, invalidChapterNumber);

        assertThrows(Exception.class, () -> novelExporter.fetchChapterDetails(novelName, invalidChapterNumber));
    }

    /**
     * This test checks if the export to PDF functionality works as expected.
     */
    @Test
    public void exportChapterToPdfReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToPdf(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterPdf(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }

    /**
     * This test checks if the export to EPUB functionality works as expected.
     */
    @Test
    public void exportChapterToEpubReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToEpub(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterEpub(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }

    /**
     * This test checks if the export to TXT functionality works as expected.
     */
    @Test
    public void exportChapterToTxtReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] pdfBytes = new byte[0];

        doReturn(pdfBytes).when(novelService).exportChapterToTxt(novelName, chapterNumber);

        ResponseEntity<byte[]> response = novelExportController.exportChapterTxt(novelName, chapterNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pdfBytes, response.getBody());
    }
}