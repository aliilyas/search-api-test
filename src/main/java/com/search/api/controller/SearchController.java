package com.search.api.controller;

import com.search.api.model.City;
import com.search.api.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Tag(name = "Search", description = "Search City API")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "Search city by name or all cities inside Country", description = "Returns list of found cities", tags = {"city"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "search results",
            content = @Content(schema = @Schema(implementation = City.class)))})
    @GetMapping(value = {"/{compositeSearch}"})
    public Page<City> search(@Parameter(description = "City name + 'optional' country name (example=Ri+Latvia).", required = true)
                             @PathVariable String compositeSearch,
                             @PageableDefault(sort = "name", value = 50) Pageable pageable) {
        String[] cityAndCountry = Arrays.copyOf(compositeSearch.split("\\+"),2);
        log.info("search(country={},city={})", cityAndCountry[1], cityAndCountry[0]);
        return searchService.search(cityAndCountry[1], cityAndCountry[0], pageable);
    }
}
