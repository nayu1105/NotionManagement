package com.example.backend.api.controller;

import com.example.backend.api.dto.PageDto;
import com.example.backend.api.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;

    @GetMapping("/{id}")
    public  ResponseEntity<PageDto> findPage(@PathVariable Integer id){
        PageDto pageDto = pageService.findPage(id);
//        log.info("pageDto - {}", pageDto);
        return ResponseEntity.ok().body(pageDto);
    }
}
