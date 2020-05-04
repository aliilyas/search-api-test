package com.search.api.repository;

import com.search.api.model.City;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository repository;

    @Test
    void findByCityName() {
        Function<List<City>, Boolean> exactMatch = (cities) -> cities.stream()
                        .filter(c -> c.getName().equals("Prague"))
                        .findAny()
                        .isPresent();
        //when
        Page<City> foundCities = repository.findCityNameAndAndCountryName("Prague", null, null);
        //then
        assertThat(foundCities.getContent(), is(not(empty())));
        assertThat(exactMatch.apply(foundCities.getContent()), is(true));

        //when
        Page<City> match = repository.findCityNameAndAndCountryName("Pra", null, null);
        //then
        assertThat(match.getTotalElements() > foundCities.getTotalElements(), is(true));
        assertThat(exactMatch.apply(match.getContent()), is(true));
    }

    @Test
    void findByCityNameAndCountryName() {
        //when
        Page<City> foundCities = repository.findCityNameAndAndCountryName("Riga", "Latvia", null);
        //then
        assertThat(foundCities.getContent(), hasSize(1));
        assertThat(foundCities.getContent().get(0).getName(), is("Riga"));

        //when
        foundCities = repository.findCityNameAndAndCountryName("Ri", "Latvia", null);
        //then
        assertThat(foundCities.getContent(), hasSize(greaterThan(1)));
    }

    @Test
    void findByCountryName() {
        //when
        Page<City> foundCities = repository.findCityNameAndAndCountryName("", "Vatican", null);
        //then
        assertThat(foundCities.getContent(), hasSize(1));

        //when
        foundCities = repository.findCityNameAndAndCountryName("", "Latvia", null);
        //then
        assertThat(foundCities.getContent(), hasSize(118));
    }
}
