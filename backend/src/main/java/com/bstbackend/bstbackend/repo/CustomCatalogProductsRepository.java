package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.dtos.FilterCategoryRequestDTO;
import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import com.bstbackend.bstbackend.entity.CatalogProduct;
import com.bstbackend.bstbackend.entity.FilterCategory;
import com.bstbackend.bstbackend.entity.FilterCategoryOption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomCatalogProductsRepository extends SimpleJpaRepository<CatalogProduct, Long> implements JpaRepository<CatalogProduct, Long> {

    private final EntityManager entityManager;

    public CustomCatalogProductsRepository(EntityManager entityManager) {
        super(CatalogProduct.class, entityManager);
        this.entityManager = entityManager;
    }

    public Page<CatalogProduct> findBySearchRequest(ProductListSearchRequest searchRequest, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CatalogProduct> criteriaQuery = criteriaBuilder.createQuery(CatalogProduct.class);
        Root<CatalogProduct> productRoot = criteriaQuery.from(CatalogProduct.class);

        // Добавляем алиасы для атрибутов
        productRoot.alias("p");

        // Добавляем алиас для атрибута categoryOptions
        Join<CatalogProduct, FilterCategoryOption> filterOptionJoin = productRoot.join("categoryOptions");
        filterOptionJoin.alias("fo");

        // Добавляем алиас для атрибута filterCategory
        Join<FilterCategoryOption, FilterCategory> filterCategoryJoin = filterOptionJoin.join("filterCategory");
        filterCategoryJoin.alias("fc");

        List<Predicate> predicates = buildPredicates(searchRequest, productRoot, criteriaBuilder);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Создаем запрос для выборки данных
        TypedQuery<CatalogProduct> typedQuery = entityManager.createQuery(criteriaQuery);

        // Применяем пагинацию
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        // Получаем результаты
        List<CatalogProduct> resultList = typedQuery.getResultList();

        // Создаем запрос для подсчета общего количества записей
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CatalogProduct> countRoot = countQuery.from(CatalogProduct.class);

        // Добавляем алиасы для атрибутов в запросе на подсчет
        countRoot.alias("p_count");
        Join<CatalogProduct, FilterCategoryOption> filterOptionJoinCount = countRoot.join("categoryOptions");
        filterOptionJoinCount.alias("fo_count");
        Join<FilterCategoryOption, FilterCategory> filterCategoryJoinCount = filterOptionJoinCount.join("filterCategory");
        filterCategoryJoinCount.alias("fc_count");

        countQuery.select(criteriaBuilder.count(countRoot)).where(predicates.toArray(new Predicate[0]));

        // Выполняем запрос на подсчет общего количества записей
        Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        // Создаем объект PageImpl с результатами и общим количеством
        return new PageImpl<>(resultList, pageable, totalCount);
    }

    private List<Predicate> buildPredicates(ProductListSearchRequest searchRequest, Root<CatalogProduct> productRoot, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchRequest.getSlug() != null) {
            predicates.add(criteriaBuilder.equal(productRoot.get("slug"), searchRequest.getSlug()));
        }

        if (searchRequest.getFilters() != null) {
            for (FilterCategoryRequestDTO filter : searchRequest.getFilters()) {
                List<Long> optionIds = filter.getOptionsId();

                predicates.add(criteriaBuilder.and(
                        productRoot.join("categoryOptions").get("id").in(optionIds)
                ));
            }
        }

        return predicates;
    }
}
