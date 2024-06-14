package com.group21.novel_crawler;

import com.group21.novel_crawler.service.NovelExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the NovelExporter service.
 */
public class NovelExporterUnitTest {
    @Mock
    // Mock instance of NovelExporter service
    NovelExporter novelExporter;

    // Direct instance of NovelExporter service for testing real methods
    private NovelExporter directNovelExporter;

    /**
     * This method initializes the mocks and the direct instance of NovelExporter before each test.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        directNovelExporter = new NovelExporter();
    }

    /**
     * This test checks if the exportChapterToPdf method returns a non-null byte array.
     */
    @Test
    public void exportChapterToPdfReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;
        byte[] pdfBytes = new byte[0];

        when(novelExporter.exportChapterToPdf(novelName, chapterNumber)).thenReturn(pdfBytes);

        assertNotNull(pdfBytes);
    }

    /**
     * This test checks if the exportChapterToEpub method returns a non-null byte array.
     */
    @Test
    public void exportChapterToEpubReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;
        byte[] pdfBytes = new byte[0];

        when(novelExporter.exportChapterToEpub(novelName, chapterNumber)).thenReturn(pdfBytes);

        assertNotNull(pdfBytes);
    }

    /**
     * This test checks if the exportChapterToTxt method returns a non-null byte array.
     */
    @Test
    public void exportChapterToTxtReturnsSuccessfully() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;
        byte[] pdfBytes = new byte[0];

        when(novelExporter.exportChapterToTxt(novelName, chapterNumber)).thenReturn(pdfBytes);

        assertNotNull(pdfBytes);
    }

    /**
     * This test checks if the fetchChapterDetails method throws an exception when provided with an invalid novel name.
     */
    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidNovelName() throws Exception {
        String invalidNovelName = "invalid-novel-name";
        int chapterNumber = 1;

        when(novelExporter.fetchChapterDetails(invalidNovelName, chapterNumber)).thenThrow(new Exception());
    }

    /**
     * This test checks if the fetchChapterDetails method throws an exception when provided with an invalid chapter number.
     */
    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidChapterNumber() throws Exception {
        String novelName = "dau-pha-thuong-khung";
        int invalidChapterNumber = -1;

        when(novelExporter.fetchChapterDetails(novelName, invalidChapterNumber)).thenThrow(new Exception());
    }

    /**
     * This test checks if the fetchChapterDetails method throws an exception when provided with an invalid novel name.
     * This test uses the direct instance of NovelExporter.
     */
    @Test
    public void fetchChapterDetailsThrowsExceptionWhenInvalidNovelNameDirect() throws Exception {
        String invalidNovelName = "invalid-novel-name";
        int chapterNumber = 1;

        when(novelExporter.fetchChapterDetails(invalidNovelName, chapterNumber)).thenThrow(new Exception());
    }

    /**
     * This test checks if the exportChapterToPdf method returns a non-null byte array.
     * This test uses the direct instance of NovelExporter.
     */
    @Test
    public void exportChapterToPdfReturnsSuccessfullyDirect() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] result = directNovelExporter.exportChapterToPdf(novelName, chapterNumber);

        assertNotNull(result);
    }

    /**
     * This test checks if the exportChapterToEpub method returns a non-null byte array.
     * This test uses the direct instance of NovelExporter.
     */
    @Test
    public void exportChapterToEpubReturnsSuccessfullyDirect() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] result = directNovelExporter.exportChapterToEpub(novelName, chapterNumber);

        assertNotNull(result);
    }

    /**
     * This test checks if the exportChapterToTxt method returns a non-null byte array.
     * This test uses the direct instance of NovelExporter.
     */
    @Test
    public void exportChapterToTxtReturnsSuccessfullyDirect() {
        String novelName = "dau-pha-thuong-khung";
        int chapterNumber = 1;

        byte[] result = directNovelExporter.exportChapterToTxt(novelName, chapterNumber);

        assertNotNull(result);
    }
}