package com.example.backend.api.dto;

import com.example.backend.api.entity.Page;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageDto {

    private Integer id;
    private String title;
    private String context;
    private List<PageSummary> breadcrumbs;
    private List<PageSummary> subPages;

    public PageDto(Page detail, List<Page> breadCrumb, List<Page> subPage) {
        this.id = detail.getId();
        this.title = detail.getTitle();
        this.context = detail.getContext();

        breadcrumbs = new LinkedList<>();
        for (Page p : breadCrumb) {
            breadcrumbs.add(new PageSummary(p.getId(), p.getTitle()));
        }

        subPages = new LinkedList<>();
        for (Page p : subPage) {
            subPages.add(new PageSummary(p.getId(), p.getTitle()));
        }
    }

}


