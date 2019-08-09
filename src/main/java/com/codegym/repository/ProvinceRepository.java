package com.codegym.repository;

import com.codegym.model.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProvinceRepository extends PagingAndSortingRepository<Province, Long> {

    @Query("SELECT p FROM Province p WHERE p.name LIKE :name")
    Iterable<Province> findByName(@Param("name") String name);
}
