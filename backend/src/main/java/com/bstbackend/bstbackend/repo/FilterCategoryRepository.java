package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.FilterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterCategoryRepository extends JpaRepository<FilterCategory, Long> {
    List<FilterCategory> findAllByCatalogCategory_Id(Long id);
}
