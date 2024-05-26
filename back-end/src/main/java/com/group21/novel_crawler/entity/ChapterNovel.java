package com.group21.novel_crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterNovel {
    private String novelTitle;

    private String chapterTitle;

    private String chapterContent;
}
