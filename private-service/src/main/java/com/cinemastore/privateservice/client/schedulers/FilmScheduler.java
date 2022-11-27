package com.cinemastore.privateservice.client.schedulers;

import com.cinemastore.privateservice.client.FilmClient;
import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.service.implementation.BookServiceImpl;
import com.cinemastore.privateservice.service.implementation.FilmServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class FilmScheduler {

    private final FilmClient filmClient;

    private final FilmServiceImpl filmService;

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public FilmScheduler(FilmClient filmClient,
                         FilmServiceImpl filmService) {
        this.filmClient = filmClient;
        this.filmService = filmService;
    }

    @Scheduled(cron = "0 30 * * * MON-FRI")
    public void getBestFilmTitlesAndSaveNewFilms() {
        filmClient.getTitles().forEach(filmInfo -> {
            FilmRequestDto film = filmClient.getFilm(filmInfo.getId().substring(7, 16));
            if (filmService.findBy(FilmFilter.builder()
                    .title(film.getTitle())
                    .build(), 0, 1).isEmpty()) {
                filmService.save(film);
                logger.info("A new film '{}' have been saved", film.getTitle());
            }
        });
    }
}
