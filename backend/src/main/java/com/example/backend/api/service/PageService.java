package com.example.backend.api.service;

import com.example.backend.api.dao.PageDao;
import com.example.backend.api.dto.PageDto;
import com.example.backend.api.entity.Page;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    private final PageDao pageDao;

    public PageDto findPage(Integer id) {
        Page detail = pageDao.findDetail(id);
        List<Page> breadCrumb = pageDao.findBreadCrumb(detail.getParent_id());
        List<Page> subPage = pageDao.findSubPage(id);
        return new PageDto(detail, breadCrumb, subPage);
    }

}
