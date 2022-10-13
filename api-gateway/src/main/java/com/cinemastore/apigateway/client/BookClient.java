package com.cinemastore.apigateway.client;

import com.cinemastore.apigateway.criteria.BookFilter;
import com.cinemastore.apigateway.dto.BookRequestDto;
import com.cinemastore.apigateway.dto.BookResponseDto;
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

@FeignClient(name = "private-book", url = "http://localhost:8082")
@Primary
public interface BookClient {

    @RequestMapping(method = RequestMethod.POST, value = "/book/add")
    ResponseEntity<BookResponseDto> save(@Valid @RequestBody BookRequestDto requestDto);

    @RequestMapping(method = RequestMethod.GET, value = "/book/get/id/{id}")
    ResponseEntity<BookResponseDto> getById(@PathVariable Long id, @RequestParam String imageId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/book/delete/id/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/book/find-by")
    ResponseEntity<List<BookResponseDto>> findBy(@RequestBody BookFilter bookFilter,
                                                 @RequestParam Integer page,
                                                 @RequestParam Integer size);
}
