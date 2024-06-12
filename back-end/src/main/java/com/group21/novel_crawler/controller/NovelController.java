package com.group21.novel_crawler.controller;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.common.ResponseObject;
import com.group21.novel_crawler.common.SearchObject;
import com.group21.novel_crawler.entity.ChapterNovel;
import com.group21.novel_crawler.entity.HeaderData;
import com.group21.novel_crawler.entity.HomeData;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/novels")
@CrossOrigin
public class NovelController {

    @Autowired
    private NovelService novelService;

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

    @GetMapping("/chapter")
    public ResponseEntity<ResponseObject> getChapterNovel(
            @RequestParam(defaultValue = "") String novelName,
            @RequestParam(defaultValue = "1") int chapterNumber
    ) {
        ChapterNovel chapterNovel = novelService.getChapterNovel(novelName, chapterNumber);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get chapter novel successfully.")
                        .data(chapterNovel)
                        .build()
        );
    }
}

