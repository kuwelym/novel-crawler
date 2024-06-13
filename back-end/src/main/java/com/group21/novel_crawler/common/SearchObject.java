package com.group21.novel_crawler.common;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchObject {
    String searchText;
    int page;
}

