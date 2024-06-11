package com.group21.novel_crawler.service.impl;

import com.group21.novel_crawler.common.PageableData;
import com.group21.novel_crawler.entity.ChapterNovel;
import com.group21.novel_crawler.entity.HeaderData;
import com.group21.novel_crawler.entity.HomeData;
import com.group21.novel_crawler.entity.Novel;
import com.group21.novel_crawler.service.NovelPdfExporter;
import com.group21.novel_crawler.service.NovelScraper;
import com.group21.novel_crawler.service.NovelService;
import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl implements NovelService {

    private final NovelScraper novelScraper;
    private final NovelPdfExporter novelPdfExporter;

    public NovelServiceImpl(NovelScraper novelScraper, NovelPdfExporter novelPdfExporter) {
        this.novelScraper = novelScraper;
        this.novelPdfExporter = novelPdfExporter;
    }
    @Override
    public HomeData getHomeData() {
        return novelScraper.getHomeData();
    }

    @Override
    public HeaderData getHeaderData() {
        return novelScraper.getHeaderData();
    }

    @Override
    public PageableData<Novel> getNovelByType(String type, int page) {
        return novelScraper.getNovelByType(type, page);
    }

    @Override
    public PageableData<Novel> getNovelByGenre(String genre, int page) {
        return novelScraper.getNovelByGenre(genre, page);
    }

    @Override
    public PageableData<Novel> searchNovel(String searchText, int page) {
        return novelScraper.searchNovel(searchText, page);
    }

    @Override
    public PageableData<Novel> getNovelDetails(String novelName, int page) {
        return novelScraper.getNovelDetails(novelName, page);
    }

    @Override
    public ChapterNovel getChapterNovel(String novelName, int chapterNumber) {
        return novelScraper.getChapterNovel(novelName, chapterNumber);
    }

    @Override
    public byte[] exportChapterToPdf(String novelName, int chapterNumber) {
        return novelPdfExporter.exportChapterToPdf(novelName, chapterNumber);
    }
}