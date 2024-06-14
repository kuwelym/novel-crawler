package com.group21.novel_crawler.controller;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.common.ResponseObject;
import com.group21.novel_crawler.common.SearchObject;
import com.group21.novel_crawler.entity.ChapterNovel;
import com.group21.novel_crawler.entity.HeaderData;
import com.group21.novel_crawler.entity.HomeData;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.service.NovelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to novels.
 */
@RestController
@RequestMapping("/api/novels")
@CrossOrigin
public class NovelController {

    private final NovelService novelService;

    /**
     * Constructor for NovelController.
     *
     * @param novelService the service to handle novel-related operations
     */
    public NovelController(NovelService novelService) {
        this.novelService = novelService;
    }

    /**
     * Endpoint to get home data.
     *
     * @return ResponseEntity with the home data
     */
    @GetMapping()
    public ResponseEntity<ResponseObject> getHomeData() {
        HomeData homeData = novelService.getHomeData();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get home data successfully.")
                        .data(homeData)
                        .build()
        );
    }

    /**
     * Endpoint to get header data.
     *
     * @return ResponseEntity with the header data
     */
    @GetMapping("/header")
    public ResponseEntity<ResponseObject> getHeaderData() {
        HeaderData headerData = novelService.getHeaderData();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get header data successfully.")
                        .data(headerData)
                        .build()
        );
    }

    /**
     * Endpoint to get novels by type.
     *
     * @param type the type of the novel
     * @param page the page number
     * @return ResponseEntity with the novels of the given type
     */
    @GetMapping("/types")
    public ResponseEntity<ResponseObject> getNovelByType(
            @RequestParam(defaultValue = "truyen-hot") String type,
            @RequestParam(defaultValue = "1") int page
    ) {
        PageableData<Novel> pageableData = novelService.getNovelByType(type, page);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all novels successfully.")
                        .data(pageableData)
                        .build()
        );
    }

    /**
     * Endpoint to get novels by genre.
     *
     * @param genre the genre of the novel
     * @param page the page number
     * @return ResponseEntity with the novels of the given genre
     */
    @GetMapping("/genres")
    public ResponseEntity<ResponseObject> getNovelByGenre(
            @RequestParam(defaultValue = "tien-hiep") String genre,
            @RequestParam(defaultValue = "1") int page
    ) {
        PageableData<Novel> pageableData = novelService.getNovelByGenre(genre, page);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all novels successfully.")
                        .data(pageableData)
                        .build()
        );
    }

    /**
     * Endpoint to search for novels.
     *
     * @param searchObject the object containing the search text and page number
     * @return ResponseEntity with the search results
     */
    @PostMapping("/search")
    public ResponseEntity<ResponseObject> searchNovel(@RequestBody SearchObject searchObject) {
        PageableData<Novel> pageableData = novelService.searchNovel(searchObject.getSearchText(), searchObject.getPage());
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Search novel by search text successfully.")
                        .data(pageableData)
                        .build()
        );
    }

    /**
     * Endpoint to get novel details.
     *
     * @param novelName the name of the novel
     * @param page the page number
     * @return ResponseEntity with the novel details
     */
    @GetMapping("/detail")
    public ResponseEntity<ResponseObject> getNovelDetail(
            @RequestParam(defaultValue = "") String novelName,
            @RequestParam(defaultValue = "1") int page
    ) {
        PageableData<Novel> pageableData = novelService.getNovelDetails(novelName, page);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get novel details successfully.")
                        .data(pageableData)
                        .build()
        );
    }

    /**
     * Endpoint to get a novel chapter.
     *
     * @param serverName the server name
     * @param novelName the name of the novel
     * @param chapterNumber the chapter number
     * @return ResponseEntity with the novel chapter
     */
    @GetMapping("/chapter")
    public ResponseEntity<ResponseObject> getChapterNovel(
            @RequestParam(defaultValue = "") String serverName,
            @RequestParam(defaultValue = "") String novelName,
            @RequestParam(defaultValue = "1") int chapterNumber
    ) {
        ChapterNovel chapterNovel = novelService.getChapterNovel(serverName, novelName, chapterNumber);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get chapter novel successfully.")
                        .data(chapterNovel)
                        .build()
        );
    }
}