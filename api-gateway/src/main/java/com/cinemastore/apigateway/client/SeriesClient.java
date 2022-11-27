package com.cinemastore.apigateway.client;

import com.cinemastore.apigateway.criteria.BookFilter;
import com.cinemastore.apigateway.criteria.SeriesFilter;
import com.cinemastore.apigateway.dto.BookRequestDto;
import com.cinemastore.apigateway.dto.BookResponseDto;
import com.cinemastore.apigateway.dto.SeriesRequestDto;
import com.cinemastore.apigateway.dto.SeriesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "private-series", url = "http://localhost:8082")
@Primary
public interface SeriesClient {

    @RequestMapping(method = RequestMethod.POST, value = "/series/add")
    ResponseEntity<SeriesResponseDto> save(@Valid @RequestBody SeriesRequestDto requestDto);

    @RequestMapping(method = RequestMethod.GET, value = "/series/get/id/{id}")
    ResponseEntity<SeriesResponseDto> getById(@PathVariable Long id, @RequestParam String imageId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/series/delete/id/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/series/find-by")
    ResponseEntity<List<SeriesResponseDto>> findBy(@RequestBody SeriesFilter seriesFilter,
                                                 @RequestParam Integer page,
                                                 @RequestParam Integer size);
}
