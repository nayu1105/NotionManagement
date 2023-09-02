package com.example.backend.api.dao;

import com.example.backend.api.dao.mapper.PageDetailsMapper;
import com.example.backend.api.dao.mapper.PageSummaryMapper;
import com.example.backend.api.dto.PageDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PageDao {

    private final JdbcTemplate jdbcTemplate;
    private final PageDetailsMapper pageDetailsMapper;
    private final PageSummaryMapper pageSummaryMapper;

    public PageDetails getPageDetails(long id) {
        String detailsQuery = "SELECT id, title, context FROM `page` WHERE id = ?";
        PageDetails pageDetails = jdbcTemplate.queryForObject(detailsQuery, pageDetailsMapper, id);
        assert pageDetails != null;

        String breadCrumbsQuery = " WITH RECURSIVE breadcrumbs AS (\n" +
                "     SELECT id, title, parent_id, 1 as depth\n" +
                "     FROM `page`\n" +
                "     WHERE id = ?\n" +
                "     UNION ALL\n" +
                "     SELECT p.id, p.title, p.parent_id, b.depth + 1\n" +
                "     FROM `page` p\n" +
                "              INNER JOIN breadcrumbs b ON p.id = b.parent_id\n" +
                " )\n" +
                " SELECT id, title FROM breadcrumbs ORDER BY depth DESC";
        List<PageDetails.PageSummary> breadCrumbs = jdbcTemplate.query(breadCrumbsQuery, pageSummaryMapper, id);
        pageDetails.setBreadcrumbs(breadCrumbs);

        String subpagesQuery = "SELECT id, title FROM `page` WHERE parent_id = ?";
        List<PageDetails.PageSummary> subpages = jdbcTemplate.query(subpagesQuery, pageSummaryMapper, id);
        pageDetails.setSubPages(subpages);

        return pageDetails;
    }
}
