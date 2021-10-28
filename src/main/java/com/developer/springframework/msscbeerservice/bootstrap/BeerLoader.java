package com.developer.springframework.msscbeerservice.bootstrap;

import com.developer.springframework.msscbeerservice.domain.Beer;
import com.developer.springframework.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

/*
Created by Rohit on 27-10-2021
 */
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().
                    beerName("Mongo Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(0631234200036L)
                    .price(new BigDecimal("12.95"))
                    .build()
            );
            beerRepository.save(Beer.builder().
                    beerName("Galaxy")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(0631234200036L)
                    .price(new BigDecimal("15.95"))
                    .build()
            );
        }
        System.out.println("Loaded Beers: " +beerRepository.count());
    }
}
