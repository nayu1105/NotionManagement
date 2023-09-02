package com.example.backend.api.dao;

import com.example.backend.PageFixture;
import com.example.backend.api.dao.mapper.PageDetailsMapper;
import com.example.backend.api.dao.mapper.PageSummaryMapper;
import com.example.backend.api.dto.PageDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PageDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PageDetailsMapper pageDetailsMapper;

    @Mock
    private PageSummaryMapper pageSummaryMapper;

    @InjectMocks
    private PageDao pageDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPageDetails() {
        // given
        PageDetails pageDetails = PageFixture.createPageDetails();
        when(jdbcTemplate.queryForObject(anyString(), eq(pageDetailsMapper), anyLong())).thenReturn(pageDetails);
        PageDetails.PageSummary pageSummary = PageFixture.createPageSummary();
        when(jdbcTemplate.query(anyString(), eq(pageSummaryMapper), anyLong())).thenReturn(Collections.singletonList(pageSummary));

        // when
        PageDetails result = pageDao.getPageDetails(1L);

        // Assert
        assertEquals(pageDetails.getId(), result.getId());
        assertEquals(pageDetails.getTitle(), result.getTitle());
        assertEquals(pageDetails.getContext(), result.getContext());
        assertEquals(pageSummary.getId(), result.getBreadcrumbs().size());
        assertEquals(pageSummary.getId(), result.getSubPages().size());
    }
}
