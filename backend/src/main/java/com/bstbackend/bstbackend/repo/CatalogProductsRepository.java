package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import com.bstbackend.bstbackend.entity.CatalogCategory;
import com.bstbackend.bstbackend.entity.CatalogProduct;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogProductsRepository extends JpaRepository<CatalogProduct, Long> {
    List<CatalogProduct> findAllByCatalogCategory_Id(Long catalogCategoryId);


    @Query("select c from CatalogProduct c where LOWER(c.name) LIKE :query and c.catalogCategory.id = :categoryId order by c.id")
    Page<CatalogProduct> getAllByQuery(@Param("categoryId") Long categoryId, Pageable pageable, @Param("query") String query);

    @Query("select c from CatalogProduct c where c.catalogCategory.id = :categoryId order by c.id")
    Page<CatalogProduct> getAllByQuery(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("select c from CatalogProduct c where c.catalogCategory.flag like :slug order by c.id")
    Page<CatalogProduct> getAllByQuery(@Param("slug") String slug, Pageable pageable);

    @Query("select c from CatalogProduct c where c.catalogCategory.flag like :slug and lower(c.name) like :query order by c.id")
    Page<CatalogProduct> getAllByQuery(@Param("slug") String slug, Pageable pageable, @Param("query") String query);

    @Query("select c from CatalogProduct c where c.catalogCategory.id = :categoryId order by random() limit 5")
    List<CatalogProduct> getRandomByQuery(@Param("categoryId") Long categoryId);

    Optional<CatalogProduct> findBySlug(String slug);

    @Query("select count(c) from CatalogProduct c where c.catalogCategory.id = :categoryId")
    Long countAll(@Param("categoryId") Long categoryId);
}
