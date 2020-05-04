package com.search.api.repository;

import com.search.api.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, String> {
    @EntityGraph(attributePaths = "country")
    @Query("select c from City c join c.country t " +
            "where lower(c.name) like concat('%',lower(:cityName),'%') " +
            "and (:countryName is null or lower(t.name)= lower(:countryName))")
    Page<City> findCityNameAndAndCountryName(@Param("cityName") String cityName,
                                             @Param("countryName") String countryName,
                                             Pageable pageable);
}
