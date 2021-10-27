package com.developer.springframework.msscbeerservice.repositories;

import com.developer.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/*
Created by Rohit on 27-10-2021
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
