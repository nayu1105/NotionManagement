package com.example.backend.api.dao.mapper;

import com.example.backend.api.dto.PageDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PageDetailsMapper implements RowMapper<PageDetails> {

    @Override
    public PageDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        PageDetails pageDetails = new PageDetails();
        pageDetails.setId(rs.getLong("id"));
        pageDetails.setTitle(rs.getString("title"));
        pageDetails.setContext(rs.getString("context"));
        return pageDetails;
    }

}
