package com.example.backend.api.dao.mapper;

import com.example.backend.api.dto.PageDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PageSummaryMapper implements RowMapper<PageDetails.PageSummary> {

    @Override
    public PageDetails.PageSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
        PageDetails.PageSummary pageSummary = new PageDetails.PageSummary();
        pageSummary.setId(rs.getLong("id"));
        pageSummary.setTitle(rs.getString("title"));
        return pageSummary;
    }

}
