package com.example.backend.api.service;

import com.example.backend.api.dto.PageDetails;
import com.example.backend.api.entity.Page;
import com.example.backend.api.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public PageDetails findPage(Long id) {
        List<Page> pageAndParent = pageRepository.findPageAndParent(id);
        List<Page> subPage = pageRepository.findSubPage(id);
        int lastIndex = pageAndParent.size() - 1;

        Page current = pageAndParent.get(lastIndex);
        PageDetails pageDetails = PageDetails.builder()
                .id(current.getId()).title(current.getTitle()).context(current.getContext())
                .breadcrumbs(pageAndParent.subList(0, lastIndex)).subPages(subPage).build();
        return pageDetails;
    }
}
