package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.entity.CatalogProduct;
import com.bstbackend.bstbackend.entity.CatalogSubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogSubcategoryRepository extends JpaRepository<CatalogSubCategory, Long> {
    List<CatalogSubCategory> findAllByCatalogCategory_Flag(String slug);

    @Query("select c from CatalogProduct c where c.catalogCategory.flag like :slug")
    List<CatalogSubCategory> findAllByCatalogCategorySlug(@Param("slug") String slug);

    CatalogSubCategory findByFlag(String flag);

    @Query("select distinct cs from CatalogSubCategory cs right join CatalogProduct cp ON cp.catalogCategory.id = cs.id where lower(cp.name) like :query")
    Page<CatalogSubCategory> findByQuery(@Param("query") String query, Pageable pageable);

    @Query("select c.flag from CatalogSubCategory c")
    List<String> categorySitemapRequest();
}
