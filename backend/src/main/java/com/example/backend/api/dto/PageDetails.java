package com.example.backend.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDetails {
    private long id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PageSummary {
        private long id;
        private String title;
    }
}


