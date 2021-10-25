package com.developer.springframework.msscbeerservice.web.model;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
* Created by Rohit on 25-10-2021
 */
public class BeerPagedList extends PageImpl<BeerDto>
{
    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
