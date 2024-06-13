package com.group21.novel_crawler.service;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.*;

public interface NovelService {
    HeaderData getHeaderData();

    HomeData getHomeData();

    PageableData<Novel> getNovelByType(String type, int page);

    PageableData<Novel> getNovelByGenre(String genre, int page);

    PageableData<Novel> searchNovel(String searchText, int page);

    PageableData<Novel> getNovelDetails(String novelName, int page);

    ChapterNovel getChapterNovel(String serverName ,String novelName, int chapterNumber);

    byte[] exportChapterToPdf(String novelName, int chapterNumber);

    byte[] exportChapterToEpub(String novelName, int chapterNumber);

    byte[] exportChapterToTxt(String novelName, int chapterNumber);
}