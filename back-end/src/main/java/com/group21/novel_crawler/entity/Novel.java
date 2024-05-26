package com.group21.novel_crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Novel {
    private String title;
    private String author;
    private String coverUrl;

    private String description;

    private String status;

    private List<String> chapters;

    private List<String> genres;
}

