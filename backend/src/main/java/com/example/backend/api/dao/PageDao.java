package com.example.backend.api.dao;

import com.example.backend.api.entity.Page;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageDao {

    Page findDetail(Integer id);

    List<Page> findSubPage(Integer parentId);

    List<Page> findBreadCrumb(Integer id);
}