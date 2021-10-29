package com.developer.springframework.msscbeerservice.web.controller;

import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
Create By Rohit on 25-10-2021
 */
@RequestMapping("/api/v1/beer/")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerByID(@PathVariable("beerId") UUID beerId) {

        //todo imp
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {

        //todo imp
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {

        //todo imp
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
