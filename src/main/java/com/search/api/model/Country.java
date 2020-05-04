package com.search.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "country", indexes = {
        @Index(name = "country_index_id", columnList = "id"),
        @Index(name = "country_index_name", columnList = "name")})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","cities"})
public class Country {
    @Id
    @GeneratedValue
    private String id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private Long population;
    @NotNull
    private String currency;
    @NotNull
    private String phoneCode;
    @OneToMany(mappedBy="country", fetch = FetchType.LAZY)
    private List<City> cities;
}
