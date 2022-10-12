package com.cinemastore.privateservice.client;

import com.cinemastore.privateservice.client.info.FilmInfo;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "film-client", url = "https://imdb8.p.rapidapi.com")
public interface FilmClient {

    String header1 = "X-RapidAPI-Key=a0ae981adamsh978ee4c4eb1d8e2p136bcfjsn2e7e0d2ff95d";

    String header2 = "X-RapidAPI-Host=imdb8.p.rapidapi.com";

    @RequestMapping(method = RequestMethod.GET, value = "title/get-top-rated-movies",
            headers = {header1, header2},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<FilmInfo> getTitles();

    @RequestMapping(method = RequestMethod.GET, value = "title/get-details",
            headers = {header1, header2},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    FilmRequestDto getFilm(@RequestParam String tconst);
}
