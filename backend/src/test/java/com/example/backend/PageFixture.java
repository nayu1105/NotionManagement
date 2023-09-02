package com.example.backend;

import com.example.backend.api.dto.PageDetails;

public class PageFixture {
    public static PageDetails createPageDetails() {
        PageDetails pageDetails = new PageDetails();
        pageDetails.setId(1L);
        pageDetails.setTitle("Test Title");
        pageDetails.setContext("Test Context");
        return pageDetails;
    }

    public static PageDetails.PageSummary createPageSummary() {
        PageDetails.PageSummary pageSummary = new PageDetails.PageSummary();
        pageSummary.setId(1L);
        pageSummary.setTitle("Test Title");
        return pageSummary;
    }
}
