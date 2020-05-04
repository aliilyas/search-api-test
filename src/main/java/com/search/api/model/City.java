package com.search.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "city", indexes = {
        @Index(name = "city_index_id", columnList = "id"),
        @Index(name = "city_index_name", columnList = "name")})
@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue
    private String id;
    @NotNull
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
