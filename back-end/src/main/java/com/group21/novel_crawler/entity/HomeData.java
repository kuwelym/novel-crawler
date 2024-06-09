package com.group21.novel_crawler.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HomeData {
    private List<Novel> hotNovelList;
    private List<Novel> newNovelList;
    private List<Genre> novelGenres;
}
