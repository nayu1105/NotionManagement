package com.example.backend.api.controller;

import com.example.backend.api.dto.PageDetails;
import com.example.backend.api.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/{id}")
    public ResponseEntity<PageDetails> fetchOne(
            @PathVariable long id
    ) {
        PageDetails pageDetails = pageService.fetchOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(pageDetails);
    }
}
