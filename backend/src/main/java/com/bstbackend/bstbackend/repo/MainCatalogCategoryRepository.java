package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.MainCatalogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCatalogCategoryRepository extends JpaRepository<MainCatalogCategory, Long> {
    MainCatalogCategory findBySlug(String slug);


}
