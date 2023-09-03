package com.example.backend.api.dto;

import com.example.backend.api.entity.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PageDetails {
    private long id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;

    @Builder
    public PageDetails(long id, String title, String context, List<Page> breadcrumbs, List<Page> subPages) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.breadcrumbs = pageToDTO(breadcrumbs);
        this.subPages = pageToDTO(subPages);
    }

    private List<PageSummary> pageToDTO(List<Page> pages) {
        return pages.stream()
                .map(page -> PageSummary.builder().id(page.getId()).title(page.getTitle()).build()).collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    @Builder
    private static class PageSummary {
        private long id;
        private String title;
    }
}


