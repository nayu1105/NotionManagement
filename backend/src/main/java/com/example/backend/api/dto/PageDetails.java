package com.example.backend.api.dto;

import java.util.List;

public class PageDetails {
    private long id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;

    private class PageSummary {
        private long id;
        private String title;
    }
}


