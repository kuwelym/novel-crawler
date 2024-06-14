package com.group21.novel_crawler;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.*;
import com.group21.novel_crawler.exception.InternalServerErrorException;
import com.group21.novel_crawler.service.NovelScraper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the NovelScraper service.
 * It tests the service's methods by mocking the service and verifying the results.
 */
class NovelScraperUnitTest {

    @Mock
    private NovelScraper novelScraper;

    private NovelScraper directNovelScraper;

    /**
     * This method sets up the test environment before each test.
     * It initializes the mocked and direct instances of NovelScraper.
     */
    @BeforeEach
    void setUp() {
        novelScraper = mock(NovelScraper.class);
        directNovelScraper = new NovelScraper();
    }

    /**
     * This test verifies the getHeaderData method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getHeaderData()
     */
    @Test
    void testGetHeaderData() {
        // Prepare test data
        Genre genre1 = Genre.builder().name("Fantasy").url("/genre/fantasy").build();
        Genre genre2 = Genre.builder().name("Adventure").url("/genre/adventure").build();
        HeaderData headerData = new HeaderData();
        headerData.setNovelGenres(List.of(genre1, genre2));
        headerData.setNovelTypes(List.of(genre1));


        // Mock the service method
        when(novelScraper.getHeaderData()).thenReturn(headerData);

        // Perform the test
        HeaderData result = novelScraper.getHeaderData();

        // Assert the result
        assertNotNull(result);
        assertNotNull(result.getNovelGenres());
        assertFalse(result.getNovelGenres().isEmpty());
        assertEquals(2, result.getNovelGenres().size());
        assertEquals("Fantasy", result.getNovelGenres().get(0).getName());

        assertNotNull(result.getNovelTypes());
        assertFalse(result.getNovelTypes().isEmpty());
        assertEquals(1, result.getNovelTypes().size());
        assertEquals("Fantasy", result.getNovelTypes().get(0).getName());

        // Verify the method was called
        verify(novelScraper, times(1)).getHeaderData();
    }

    /**
     * This test verifies the getHomeData method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getHomeData()
     */
    @Test
    void testGetHomeData() {
        // Prepare test data
        Novel novel1 = new Novel(); // Assuming Novel has a no-arg constructor
        Novel novel2 = new Novel();
        Genre genre1 = Genre.builder().name("Fantasy").url("/genre/fantasy").build();
        HomeData homeData = new HomeData();
        homeData.setHotNovelList(List.of(novel1));
        homeData.setNewNovelList(List.of(novel1, novel2));
        homeData.setNovelGenres(List.of(genre1));


        // Mock the service method
        when(novelScraper.getHomeData()).thenReturn(homeData);

        // Perform the test
        HomeData result = novelScraper.getHomeData();

        // Assert the result
        assertNotNull(result);
        assertNotNull(result.getHotNovelList());
        assertFalse(result.getHotNovelList().isEmpty());
        assertEquals(1, result.getHotNovelList().size());

        assertNotNull(result.getNewNovelList());
        assertFalse(result.getNewNovelList().isEmpty());
        assertEquals(2, result.getNewNovelList().size());

        assertNotNull(result.getNovelGenres());
        assertFalse(result.getNovelGenres().isEmpty());
        assertEquals(1, result.getNovelGenres().size());
        assertEquals("Fantasy", result.getNovelGenres().get(0).getName());

        // Verify the method was called
        verify(novelScraper, times(1)).getHomeData();
    }


    /**
     * This test verifies the getNovelByType method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getNovelByType(String, int)
     */
    @Test
    void testGetNovelByType() {
        PageableData<Novel> novels = new PageableData<>();
        novels.setContent(List.of(new Novel(), new Novel()));

        when(novelScraper.getNovelByType("truyen-moi", 1)).thenReturn(novels);

        PageableData<Novel> result = novelScraper.getNovelByType("truyen-moi", 1);
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(2, result.getContent().size());
    }

    /**
     * This test verifies the getNovelByGenre method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getNovelByGenre(String, int)
     */
    @Test
    void testGetNovelByGenre() {
        PageableData<Novel> novels = new PageableData<>();
        novels.setContent(List.of(new Novel(), new Novel()));

        when(novelScraper.getNovelByGenre("tien-hiep", 1)).thenReturn(novels);

        PageableData<Novel> result = novelScraper.getNovelByGenre("tien-hiep", 1);
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(2, result.getContent().size());
    }

    /**
     * This test verifies the searchNovel method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#searchNovel(String, int)
     */
    @Test
    void testSearchNovel() {
        PageableData<Novel> novels = new PageableData<>();
        novels.setContent(List.of(new Novel(), new Novel()));

        when(novelScraper.searchNovel("dau-pha-thuong-khung", 1)).thenReturn(novels);

        PageableData<Novel> result = novelScraper.searchNovel("dau-pha-thuong-khung", 1);
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(2, result.getContent().size());
    }

    /**
     * This test verifies the getNovelDetails method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getNovelDetails(String, int)
     */
    @Test
    void testGetNovelDetails() {
        PageableData<Novel> novelDetails = new PageableData<>();
        novelDetails.setContent(List.of(new Novel(), new Novel()));

        when(novelScraper.getNovelDetails("dau-pha-thuong-khung", 1)).thenReturn(novelDetails);

        PageableData<Novel> result = novelScraper.getNovelDetails("dau-pha-thuong-khung", 1);
        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(2, result.getContent().size());
    }

    /**
     * This test verifies the getChapterNovel method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getChapterNovel(String, String, int)
     */
    @Test
    void testGetNovelByTypeInvalidType() {
        when(novelScraper.getNovelByType("invalid-type", 1)).thenReturn(new PageableData<>());
        System.out.println(novelScraper.getNovelByType("invalid-type", 1));
        PageableData<Novel> result = novelScraper.getNovelByType("invalid-type", 1);
        assertNotNull(result);
        assertNull(result.getContent());
    }

    /**
     * This test verifies the getNovelByGenre method of the NovelScraper service.
     * It prepares test data, mocks the service method, performs the test, asserts the result, and verifies the method call.
     * @see NovelScraper#getNovelByGenre(String, int)
     */
    @Test
    void testGetNovelByGenreInvalidGenre() {
        when(novelScraper.getNovelByGenre("invalid-genre", 1)).thenReturn(new PageableData<>());

        PageableData<Novel> result = novelScraper.getNovelByGenre("invalid-genre", 1);
        assertNotNull(result);
        assertNull(result.getContent());
    }

    @Test
    void testSearchNovelNoResults() {
        when(novelScraper.searchNovel("non-existent-novel", 1)).thenReturn(new PageableData<>());

        PageableData<Novel> result = novelScraper.searchNovel("non-existent-novel", 1);
        assertNotNull(result);
        assertNull(result.getContent());
    }

    @Test
    void testGetNovelDetailsNoResults() {
        when(novelScraper.getNovelDetails("non-existent-novel", 1)).thenReturn(new PageableData<>());

        PageableData<Novel> result = novelScraper.getNovelDetails("non-existent-novel", 1);
        assertNotNull(result);
        assertNull(result.getContent());
    }

    // Direct NovelScraper tests
    @Test
    void testDirectGetHeaderData() {
        HeaderData result = directNovelScraper.getHeaderData();
        assertNotNull(result);

        assertNotNull(result.getNovelGenres());
        assertFalse(result.getNovelGenres().isEmpty());

        assertNotNull(result.getNovelTypes());
        assertFalse(result.getNovelTypes().isEmpty());
    }

    @Test
    void testDirectGetHomeData() {
        HomeData result = directNovelScraper.getHomeData();
        assertNotNull(result);

        assertNotNull(result.getHotNovelList());
        assertFalse(result.getHotNovelList().isEmpty());

        assertNotNull(result.getNewNovelList());
        assertFalse(result.getNewNovelList().isEmpty());

        assertNotNull(result.getNovelGenres());
        assertFalse(result.getNovelGenres().isEmpty());
    }

    @Test
    void testDirectGetNovelByType() {
        // Assuming "truyen-moi" is a valid type and page 1 has data
        PageableData<Novel> novels = directNovelScraper.getNovelByType("truyen-moi", 1);

        assertNotNull(novels);
        assertFalse(novels.getContent().isEmpty());
    }

    @Test
    void testDirectGetNovelByGenre() {
        // Assuming "tien-hiep" is a valid genre and page 1 has data
        PageableData<Novel> novels = directNovelScraper.getNovelByGenre("tien-hiep", 1);

        assertNotNull(novels);
        assertFalse(novels.getContent().isEmpty());
    }

    @Test
    void testDirectSearchNovel() {
        // Assuming "dau-pha-thuong-khung" is a valid search term and page 1 has data
        PageableData<Novel> novels = directNovelScraper.searchNovel("dau-pha-thuong-khung", 1);

        assertNotNull(novels);
        assertFalse(novels.getContent().isEmpty());
    }

    @Test
    void testDirectSearchNovel2() {
        // Assuming "dau-pha-thuong-khung" is a valid search term and page 1 has data
        PageableData<Novel> novels = directNovelScraper.searchNovel("anh-yeu-em-la-that-truoc-dau-phay-la-dua", 1);

        assertNotNull(novels);
        assertFalse(novels.getContent().isEmpty());
    }

    @Test
    void testDirectGetNovelDetails() {
        // Assuming "dau-pha-thuong-khung" is a valid novel ID and page 1 has data
        PageableData<Novel> novelDetails = directNovelScraper.getNovelDetails("dau-pha-thuong-khung", 1);

        assertNotNull(novelDetails);
        assertFalse(novelDetails.getContent().isEmpty());
    }

    @Test
    void testDirectGetNovelChapter() {
        // Assuming "dau-pha-thuong-khung" is a valid novel ID and page 1 has data
        ChapterNovel chapterNovel = directNovelScraper.getChapterNovel("serverName","dau-pha-thuong-khung", 1);

        assertNotNull(chapterNovel);
        assertFalse(chapterNovel.getNovelTitle().isEmpty());
        assertFalse(chapterNovel.getChapterTitle().isEmpty());
        assertFalse(chapterNovel.getChapterContent().isEmpty());

    }

    @Test
    void testDirectGetNovelByTypeInvalidType() {
        String invalidType = "invalid-type";
        int page = 1;
        InternalServerErrorException exception = assertThrows(InternalServerErrorException.class,
                () -> directNovelScraper.getNovelByType(invalidType, page));

        assertEquals("HTTP error fetching URL. Status=404, URL=[https://truyenfull.vn/danh-sach/invalid-type/trang-1/]",
                exception.getMessage());
    }

    @Test
    void testDirectGetNovelByGenreInvalidGenre() {
        String invalidGenre = "invalid-genre";
        int page = 1;
        InternalServerErrorException exception = assertThrows(InternalServerErrorException.class,
                () -> directNovelScraper.getNovelByGenre(invalidGenre, page));

        assertEquals("HTTP error fetching URL. Status=404, URL=[https://truyenfull.vn/the-loai/invalid-genre/trang-1/]",
                exception.getMessage());
    }

    @Test
    void testDirectSearchNovelNoResults() {
        PageableData<Novel> result = directNovelScraper.searchNovel("non-existent-novel", 1);
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void testDirectGetNovelDetailsInvalidNovel() {
        String novelName = "invalid-novel";
        int page = 1;

        InternalServerErrorException exception = assertThrows(InternalServerErrorException.class,
                () -> directNovelScraper.getNovelDetails(novelName, page));

        assertEquals("HTTP error fetching URL. Status=404, URL=[https://truyenfull.vn/invalid-novel/trang-1/#list-chapter]",
                exception.getMessage());
    }
}