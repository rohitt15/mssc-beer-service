package com.developer.springframework.msscbeerservice.repositories;

import com.developer.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
Created by Rohit on 27-10-2021
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
