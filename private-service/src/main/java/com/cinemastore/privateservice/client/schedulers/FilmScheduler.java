package com.cinemastore.privateservice.client.schedulers;

import com.cinemastore.privateservice.client.FilmClient;
import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.service.implementation.FilmServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FilmScheduler {

    private final FilmClient filmClient;

    private final FilmServiceImpl filmService;

    public FilmScheduler(FilmClient filmClient,
                         FilmServiceImpl filmService) {
        this.filmClient = filmClient;
        this.filmService = filmService;
    }

    @Scheduled(fixedRate = 1800000)
    public void getBestFilmTitlesAndSaveNewFilms() {
        filmClient.getTitles().forEach(filmInfo -> {
            FilmRequestDto film = filmClient.getFilm(filmInfo.getId().substring(7, 16));
            if (filmService.findBy(FilmFilter.builder()
                    .title(film.getTitle())
                    .build(), 0, 1).isEmpty()) {
                filmService.save(film);
            }
        });
    }
}
