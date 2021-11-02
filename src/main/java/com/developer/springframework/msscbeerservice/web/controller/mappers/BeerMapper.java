package com.developer.springframework.msscbeerservice.web.controller.mappers;

import com.developer.springframework.msscbeerservice.domain.Beer;
import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/*
Created by Rohit on 02-11-21
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerDto(Beer beer);
    Beer beer(BeerDto beerDto);
}
