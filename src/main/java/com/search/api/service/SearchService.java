package com.search.api.service;

import com.search.api.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<City> search(String countryName, String cityName, Pageable pageable);
}
