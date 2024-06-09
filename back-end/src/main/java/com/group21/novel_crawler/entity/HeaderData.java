package com.group21.novel_crawler.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HeaderData {
    private List<Genre> novelGenres;
    private List<Genre> novelTypes;
}
