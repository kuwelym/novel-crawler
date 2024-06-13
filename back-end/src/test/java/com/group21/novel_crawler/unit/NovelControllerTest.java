package com.group21.novel_crawler.unit;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.common.ResponseObject;
import com.group21.novel_crawler.common.SearchObject;
import com.group21.novel_crawler.service.NovelService;
import com.group21.novel_crawler.controller.NovelController;
import com.group21.novel_crawler.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class NovelControllerTest {

    @InjectMocks
    NovelController novelController;

    @Mock
    NovelService novelService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getHomeDataReturnsSuccessfully() {
        HomeData homeData = new HomeData();
        when(novelService.getHomeData()).thenReturn(homeData);

        ResponseEntity<ResponseObject> response = novelController.getHomeData();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(homeData, response.getBody().getData());
    }

    @Test
    public void getHeaderDataReturnsSuccessfully() {
        HeaderData headerData = new HeaderData();
        when(novelService.getHeaderData()).thenReturn(headerData);

        ResponseEntity<ResponseObject> response = novelController.getHeaderData();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(headerData, response.getBody().getData());
    }

    @Test
    public void getNovelByTypeReturnsSuccessfully() {
        PageableData<Novel> pageableData = new PageableData<>();
        when(novelService.getNovelByType("truyen-hot", 1)).thenReturn(pageableData);

        ResponseEntity<ResponseObject> response = novelController.getNovelByType("truyen-hot", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageableData, response.getBody().getData());
    }

    @Test
    public void getNovelByGenreReturnsSuccessfully() {
        PageableData<Novel> pageableData = new PageableData<>();
        when(novelService.getNovelByGenre("tien-hiep", 1)).thenReturn(pageableData);

        ResponseEntity<ResponseObject> response = novelController.getNovelByGenre("tien-hiep", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageableData, response.getBody().getData());
    }

    @Test
    public void searchNovelReturnsSuccessfully() {
        PageableData<Novel> pageableData = new PageableData<>();
        SearchObject searchObject =
                SearchObject.builder()
                        .searchText("searchText")
                        .page(1)
                        .build();
        when(novelService.searchNovel(searchObject.getSearchText(), searchObject.getPage())).thenReturn(pageableData);

        ResponseEntity<ResponseObject> response = novelController.searchNovel(searchObject);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageableData, response.getBody().getData());
    }

    @Test
    public void getNovelDetailReturnsSuccessfully() {
        PageableData<Novel> pageableData = new PageableData<>();
        when(novelService.getNovelDetails("novelName", 1)).thenReturn(pageableData);

        ResponseEntity<ResponseObject> response = novelController.getNovelDetail("novelName", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageableData, response.getBody().getData());
    }

    @Test
    public void getChapterNovelReturnsSuccessfully() {
        ChapterNovel chapterNovel = new ChapterNovel();
        when(novelService.getChapterNovel("novelName", 1)).thenReturn(chapterNovel);

        ResponseEntity<ResponseObject> response = novelController.getChapterNovel("novelName", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(chapterNovel, response.getBody().getData());
    }

    @Test
    public void getHomeDataReturnsError() {
        when(novelService.getHomeData()).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getHomeData());
    }

    @Test
    public void getHeaderDataReturnsError() {
        when(novelService.getHeaderData()).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getHeaderData());
    }

    @Test
    public void getNovelByTypeReturnsError() {
        when(novelService.getNovelByType("truyen-hot", 1)).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getNovelByType("truyen-hot", 1));
    }

    @Test
    public void getNovelByGenreReturnsError() {
        when(novelService.getNovelByGenre("tien-hiep", 1)).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getNovelByGenre("tien-hiep", 1));
    }

    @Test
    public void searchNovelReturnsError() {
        SearchObject searchObject =
                SearchObject.builder()
                        .searchText("searchText")
                        .page(1)
                        .build();
        when(novelService.searchNovel(searchObject.getSearchText(), searchObject.getPage())).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.searchNovel(searchObject));
    }

    @Test
    public void getNovelDetailReturnsError() {
        when(novelService.getNovelDetails("novelName", 1)).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getNovelDetail("novelName", 1));
    }

    @Test
    public void getChapterNovelReturnsError() {
        when(novelService.getChapterNovel("novelName", 1)).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> novelController.getChapterNovel("novelName", 1));
    }
}