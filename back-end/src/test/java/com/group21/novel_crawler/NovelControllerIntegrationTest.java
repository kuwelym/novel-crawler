package com.group21.novel_crawler;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.ChapterNovel;
import com.group21.novel_crawler.entity.HeaderData;
import com.group21.novel_crawler.entity.HomeData;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.service.NovelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the NovelController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NovelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NovelService novelService;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the getHomeData endpoint.
     * @throws Exception if any error occurs during the test
     * @see NovelService#getHomeData()
     */
    @Test
    public void testGetHomeData() throws Exception {
        HomeData homeData = new HomeData();

        when(novelService.getHomeData()).thenReturn(homeData);

        mockMvc.perform(get("/api/novels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get home data successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the getHeaderData endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testGetHeaderData() throws Exception {
        HeaderData headerData = new HeaderData();

        when(novelService.getHeaderData()).thenReturn(headerData);

        mockMvc.perform(get("/api/novels/header"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get header data successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the getNovelByType endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testGetNovelByType() throws Exception {
        PageableData<Novel> pageableData = new PageableData<>();

        when(novelService.getNovelByType(anyString(), anyInt())).thenReturn(pageableData);

        mockMvc.perform(get("/api/novels/types")
                        .param("type", "truyen-hot")
                        .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get all novels successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the getNovelByGenre endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testGetNovelByGenre() throws Exception {
        PageableData<Novel> pageableData = new PageableData<>();

        when(novelService.getNovelByGenre(anyString(), anyInt())).thenReturn(pageableData);

        mockMvc.perform(get("/api/novels/genres")
                        .param("genre", "tien-hiep")
                        .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get all novels successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the searchNovel endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testSearchNovel() throws Exception {
        PageableData<Novel> pageableData = new PageableData<>();

        when(novelService.searchNovel(anyString(), anyInt())).thenReturn(pageableData);

        mockMvc.perform(post("/api/novels/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\": \"novel name\", \"page\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Search novel by search text successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the getNovelDetail endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testGetNovelDetail() throws Exception {
        PageableData<Novel> pageableData = new PageableData<>();

        when(novelService.getNovelDetails(anyString(), anyInt())).thenReturn(pageableData);

        mockMvc.perform(get("/api/novels/detail")
                        .param("novelName", "novel-name")
                        .param("page", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get novel details successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    /**
     * Test the getChapterNovel endpoint.
     * @throws Exception if any error occurs during the test
     */
    @Test
    public void testGetChapterNovel() throws Exception {
        ChapterNovel chapterNovel = new ChapterNovel();

        when(novelService.getChapterNovel(anyString(), anyString(), anyInt())).thenReturn(chapterNovel);

        mockMvc.perform(get("/api/novels/chapter")
                        .param("serverName", "server-name")
                        .param("novelName", "novel-name")
                        .param("chapterNumber", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Get chapter novel successfully."))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}