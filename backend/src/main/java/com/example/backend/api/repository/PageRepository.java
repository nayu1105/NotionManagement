package com.example.backend.api.repository;

import com.example.backend.api.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {

    @Query(value = "WITH RECURSIVE p3 (id, title, context, parent_id) AS\n" +
            "            (\n" +
            "            SELECT p1.id, p1.title, p1.context, p1.parent_id\n" +
            "            FROM page p1\n" +
            "            WHERE p1.id = ?\n" +
            "            \n" +
            "            UNION ALL\n" +
            "            \n" +
            "            SELECT p2.id, p2.title, p2.context, p2.parent_id\n" +
            "            FROM page p2\n" +
            "            INNER JOIN p3 ON p2.id = p3.parent_id\n" +
            "            )\n" +
            "            \n" +
            "SELECT * FROM p3 ORDER BY id", nativeQuery = true)
    List<Page> findPageAndParent(Long id);

    @Query(value = "SELECT * FROM page WHERE page.parent_id = ?", nativeQuery = true)
    List<Page> findSubPage(Long id);
}
