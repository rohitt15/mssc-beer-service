package com.developer.springframework.msscbeerservice.services;

import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import java.util.UUID;

/*
Created by Rohit on 10-11-21
 */
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
