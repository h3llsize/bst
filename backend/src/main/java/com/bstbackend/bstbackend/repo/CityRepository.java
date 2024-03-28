package com.bstbackend.bstbackend.repo;

import com.bstbackend.bstbackend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
