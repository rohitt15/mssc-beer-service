package com.developer.springframework.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/*
* Created by Rohit on 25-10-2021
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
private UUID idl;
private Integer version;

private OffsetDateTime createdDate;
private OffsetDateTime lastModifiedDate;

private String beerName;
private BeerStyleEnum beerStyle;

private Long upc;

private BigDecimal price;

private Integer quantityOrder;
}

