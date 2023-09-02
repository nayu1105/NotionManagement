package com.example.backend.api.entity;

import java.util.List;

class Page {
    private long id;
    private String title;
    private String context;
    private Page parent;
    private List<Page> subPages;
}