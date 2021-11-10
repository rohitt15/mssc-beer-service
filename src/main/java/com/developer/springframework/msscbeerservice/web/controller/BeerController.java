package com.developer.springframework.msscbeerservice.web.controller;

import com.developer.springframework.msscbeerservice.services.BeerService;
import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
Create By Rohit on 25-10-2021
Modified By Rohit on 10-11-2021
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer/")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerByID(@PathVariable("beerId") UUID beerId) {
        return  new ResponseEntity<>(beerService.getById(beerId),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody  @Validated BeerDto beerDto) {
        return new ResponseEntity<>( beerService.updateBeer(beerId,beerDto),HttpStatus.NO_CONTENT);
    }

}
