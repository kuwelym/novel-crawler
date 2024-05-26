package com.group21.novel_crawler.controller;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.common.ResponseObject;
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

    @GetMapping
    public ResponseEntity<ResponseObject> getAllNovels(
            @RequestParam(defaultValue = "truyen-hot") String filter,
            @RequestParam(defaultValue = "1") int page
    ) {
        PageableData<Novel> pageableData = novelService.getAllNovels(filter, page);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all novels successfully.")
                        .data(pageableData)
                        .build()
        );
    }

}

