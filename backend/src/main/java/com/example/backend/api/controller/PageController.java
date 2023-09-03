package com.example.backend.api.controller;

import com.example.backend.api.dto.PageDetails;
import com.example.backend.api.service.PageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/page/{page-id}")
    public ResponseEntity<PageDetails> getPage(@PathVariable("page-id") Long id) {
        PageDetails page = pageService.findPage(id);
        return ResponseEntity.ok(page);
    }
}
