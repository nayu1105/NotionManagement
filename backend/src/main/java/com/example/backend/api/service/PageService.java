package com.example.backend.api.service;

import com.example.backend.api.dao.PageDao;
import com.example.backend.api.dto.PageDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageDao pageDao;

    public PageDetails fetchOne(long id) {
        return pageDao.getPageDetails(id);
    }

}
