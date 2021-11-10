package com.developer.springframework.msscbeerservice.web.controller;

import com.developer.springframework.msscbeerservice.web.model.BeerDto;
import com.developer.springframework.msscbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Modified by Rohit on 02-11-21
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https",uriHost = "dev.springframework",uriPort = 80)
@WebMvcTest
class BeerControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getBeerByID() throws Exception {
        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                      /*here we are adding the request parameter and it will not going to affect the
                        controller class
                       */
                        .param("iscold", "yes")
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(document("v1/beer", pathParameters(
                                parameterWithName("beerId").description("UUId of desired beer to go")),
                        requestParameters(parameterWithName("iscold").description("Beer is cold")),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                        fieldWithPath("version").description("Version number"),
                        fieldWithPath("createdDate").description("Date Created"),
                        fieldWithPath("lastModifiedDate").description("Date Updated"),
                        fieldWithPath("beerName").description("Beer Name"),
                        fieldWithPath("beerStyle").description("Beer Style"),
                        fieldWithPath("upc").description("UPC of Beer"),
                        fieldWithPath("price").description("Price of Beer"),
                       fieldWithPath("quantityOrder").description("Quantity On hand"))
                        ));

    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        ConstrainedFields fields=new ConstrainedFields(BeerDto.class);
        mockMvc.perform(post("/api/v1/beer/").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer/",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer"),
                                fields.withPath("beerStyle").description("Style of the Beer"),
                                fields.withPath("upc").description("UPC of the Beer"),
                                fields.withPath("price").description("Price of the Beer"),
                                fields.withPath("quantityOrder").description("Quantity on the hand")
                        )));
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandling(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintVoilation -> {
            errors.add(constraintVoilation.getPropertyPath() + " : " + constraintVoilation.getMessage());

        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(123456789123L)
                .build();
    }
    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}