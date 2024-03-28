package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.FilterCategoryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterCategoryOptionRepository extends JpaRepository<FilterCategoryOption, Long> {
}
