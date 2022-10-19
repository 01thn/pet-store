package com.cinemastore.apigateway.client;

import com.cinemastore.apigateway.criteria.FilmFilter;
import com.cinemastore.apigateway.dto.FilmRequestDto;
import com.cinemastore.apigateway.dto.FilmResponseDto;
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

@FeignClient(name = "private-film", url = "http://localhost:8082")
@Primary
public interface FilmClient {

    @RequestMapping(method = RequestMethod.POST, value = "/film/add")
    ResponseEntity<FilmResponseDto> save(@Valid @RequestBody FilmRequestDto requestDto);

    @RequestMapping(method = RequestMethod.GET, value = "/film/get/id/{id}")
    ResponseEntity<FilmResponseDto> getById(@PathVariable Long id, @RequestParam String imageId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/film/delete/id/{id}")
    ResponseEntity<HttpStatus> deleteById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/film/find-by")
    ResponseEntity<List<FilmResponseDto>> findBy(@RequestBody FilmFilter filmFilter,
                                                 @RequestParam Integer page,
                                                 @RequestParam Integer size);
}
