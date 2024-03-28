package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.entity.MainCatalogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogCategoryRepository extends JpaRepository<CatalogCategory, Long> {
    CatalogCategory findByFlag(String flag);

    List<CatalogCategory> findAllByMainCatalogCategory_Slug(String slug);

    @Query("select c.flag from CatalogCategory c")
    List<String> categorySitemapRequest();
}
