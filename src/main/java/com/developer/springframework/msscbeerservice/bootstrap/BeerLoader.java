package com.developer.springframework.msscbeerservice.bootstrap;

import com.developer.springframework.msscbeerservice.domain.Beer;
import com.developer.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/*
Created By Rohit on 27-10-2021
Modified By Rohit on 10-11-2021
 */
@Component
public class BeerLoader implements CommandLineRunner {
    public static final String BEER_1_UPC="123123123123L";
    public static final String BEER_2_UPC="234234234234L";
    public static final String BEER_3_UPC="345345345345L";
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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build()
            );
            beerRepository.save(Beer.builder().
                    beerName("Galaxy")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("15.95"))
                    .build()
            );
            beerRepository.save(Beer.builder().
                    beerName("No Hammers On the bar")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(20)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("15.95"))
                    .build()
            );
        }
        System.out.println("Loaded Beers: " +beerRepository.count());
    }
}
