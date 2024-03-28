package com.bstbackend.bstbackend.service;

import com.bstbackend.bstbackend.dtos.CatalogProductDTO;
import com.bstbackend.bstbackend.dtos.CatalogProductWithOptionsDTO;
import com.bstbackend.bstbackend.dtos.FilterCategoryRequestDTO;
import com.bstbackend.bstbackend.dtos.ProductListSearchRequest;
import com.bstbackend.bstbackend.dtos.mapper.CatalogMapper;
import com.bstbackend.bstbackend.entity.CatalogProduct;
import com.bstbackend.bstbackend.entity.FilterCategory;
import com.bstbackend.bstbackend.entity.FilterCategoryOption;
import com.bstbackend.bstbackend.repo.CatalogProductsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ProductService {

    private final CityService cityService;
    private EntityManager entityManager;

    private final CatalogProductsRepository productsRepository;

    private final CatalogMapper catalogMapper;

    private final int PAGE_SIZE = 5;

    public ProductService(CatalogProductsRepository productsRepository, CatalogMapper catalogMapper, EntityManager entityManager, CityService cityService) {
        this.productsRepository = productsRepository;
        this.catalogMapper = catalogMapper;
        this.entityManager = entityManager;
        this.cityService = cityService;
    }

    public List<CatalogProductDTO> listProductsByCatalogId(Long id) {
        return productsRepository.findAllByCatalogCategory_Id(id).stream().map(catalogMapper::toDto).toList();
    }

    public Page<CatalogProductDTO> listProducts(ProductListSearchRequest productListSearchRequest) {
        if(productListSearchRequest.getFilters() != null)
            return findAllByRequest(productListSearchRequest);

        if(productListSearchRequest.getQuery() == null || productListSearchRequest.getQuery().isEmpty())
            return productsRepository.getAllByQuery(productListSearchRequest.getCategoryId(), Pageable.ofSize(PAGE_SIZE)
                    .withPage(productListSearchRequest.getPage())).map(catalogMapper::toDto);

        return productsRepository.getAllByQuery(productListSearchRequest.getCategoryId(),
                Pageable.ofSize(PAGE_SIZE).withPage(Math.toIntExact(productListSearchRequest.getPage())),
                "%" + productListSearchRequest.getQueryNonNull().toLowerCase(Locale.ROOT) + "%"
                ).map(catalogMapper::toDto);

    }

    public CatalogProductWithOptionsDTO getProductsBySlug(String slug, String cityPattern) {
        return productsRepository.findBySlug(slug).map(c -> catalogMapper.withOptionsDTO(c, cityService.getDeclinsionCityName(cityPattern))).orElse(null);
    }

    public List<CatalogProductDTO> randomList(Long id) {
        return productsRepository.getRandomByQuery(id).stream().map(catalogMapper::toDto).toList();
    }

    public CatalogProductDTO getProductById(Long id) {
        return productsRepository.findById(id).map(catalogMapper::toDto).orElse(null);
    }

    public Page<CatalogProductDTO> findAllByRequest(ProductListSearchRequest productListSearchRequest) {
        return buildQuery(productListSearchRequest, Pageable.ofSize(PAGE_SIZE).withPage(productListSearchRequest.getPage())).map(catalogMapper::toDto);
    }
    public Page<CatalogProduct> buildQuery(ProductListSearchRequest searchRequest, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CatalogProduct> criteriaQuery = criteriaBuilder.createQuery(CatalogProduct.class);
        Root<CatalogProduct> productRoot = criteriaQuery.from(CatalogProduct.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchRequest.getCategoryId() != null) {
            predicates.add(criteriaBuilder.equal(productRoot.get("catalogCategory").get("id"), searchRequest.getCategoryId()));
        }

        for (FilterCategoryRequestDTO filter : searchRequest.getFilters()) {
            List<Long> optionIds = filter.getOptionsId();

            Join<CatalogProduct, FilterCategoryOption> filterOptionJoin = productRoot.join("categoryOptions");

            Predicate subqueryPredicate = criteriaBuilder.and(
                    filterOptionJoin.get("id").in(optionIds)
            );

            predicates.add(subqueryPredicate);
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        criteriaQuery.where(finalPredicate);

        TypedQuery<CatalogProduct> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(), pageable, countTotalResults(searchRequest));
    }

    private long countTotalResults(ProductListSearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CatalogProduct> productRoot = countQuery.from(CatalogProduct.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchRequest.getCategoryId() != null) {
            predicates.add(criteriaBuilder.equal(productRoot.get("catalogCategory").get("id"), searchRequest.getCategoryId()));
        }

        for (FilterCategoryRequestDTO filter : searchRequest.getFilters()) {
            List<Long> optionIds = filter.getOptionsId();

            Join<CatalogProduct, FilterCategoryOption> filterOptionJoin = productRoot.join("categoryOptions");

            Predicate subqueryPredicate = criteriaBuilder.and(
                    filterOptionJoin.get("id").in(optionIds)
            );

            predicates.add(subqueryPredicate);
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        countQuery.where(finalPredicate);

        countQuery.select(criteriaBuilder.countDistinct(productRoot));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
