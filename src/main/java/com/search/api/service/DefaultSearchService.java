package com.search.api.service;

import com.search.api.model.City;
import com.search.api.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultSearchService implements SearchService {
    private final CityRepository cityRepository;

    @Override
    public Page<City> search(String countryName, String cityName, Pageable pageable) {
        return cityRepository.findCityNameAndAndCountryName(cityName, countryName, pageable);
    }
}
