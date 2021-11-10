package com.developer.springframework.msscbeerservice.services;

import com.developer.springframework.msscbeerservice.domain.Beer;
import com.developer.springframework.msscbeerservice.repositories.BeerRepository;
import com.developer.springframework.msscbeerservice.web.controller.NotFoundException;
import com.developer.springframework.msscbeerservice.web.controller.mappers.BeerMapper;
import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*
Created By Rohit on 10-11-2021
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImp implements BeerService{
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).
                orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer=beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beer.getBeerStyle());
        beer.setPrice(beer.getPrice());
        beer.setUpc(beer.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
