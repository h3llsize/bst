package com.bstbackend.bstbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filter_category")
@Getter
@Setter
public class FilterCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "filterCategory", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<FilterCategoryOption> options = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "catalog_category_id")
    private CatalogSubCategory catalogCategory;

    public FilterCategoryOption getCategoryByName(String name) {
        for (FilterCategoryOption filterCategoryOptionDTO : options) {
            if(filterCategoryOptionDTO.getName().equals(name))
                return filterCategoryOptionDTO;
        }
        return null;
    }
}
