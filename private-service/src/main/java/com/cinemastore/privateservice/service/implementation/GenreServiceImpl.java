package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.GenreResponseDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.BookMapper;
import com.cinemastore.privateservice.mapper.FilmMapper;
import com.cinemastore.privateservice.mapper.GenreMapper;
import com.cinemastore.privateservice.mapper.SeriesMapper;
import com.cinemastore.privateservice.repository.GenreRepository;
import com.cinemastore.privateservice.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {
    public final GenreRepository genreRepository;

    public final GenreMapper genreMapper;

    public final BookMapper bookMapper;

    public final FilmMapper filmMapper;

    public final SeriesMapper seriesMapper;

    private final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    public GenreServiceImpl(GenreRepository genreRepository,
                            GenreMapper genreMapper,
                            BookMapper bookMapper,
                            FilmMapper filmMapper,
                            SeriesMapper seriesMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
        this.bookMapper = bookMapper;
        this.filmMapper = filmMapper;
        this.seriesMapper = seriesMapper;
    }

    @Override
    public GenreResponseDto save(GenreRequestDto entity) {
        return genreMapper.entityToResponseDto(
                genreRepository.save(genreMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GenreResponseDto findById(Integer id) throws NoSuchContentException {
        return genreRepository.findById(id)
                .map(genreMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Genre with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public GenreResponseDto findByTitle(String title) throws NoSuchContentException {
        return genreRepository.findByTitle(title)
                .map(genreMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Genre with title {} not found", title);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteByTitle(String title) throws NoSuchContentException {
        Optional.ofNullable(findByTitle(title))
                .ifPresent(it -> genreRepository.deleteByTitle(it.getTitle()));
    }

    @Override
    public void deleteById(Integer id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> genreRepository.deleteById(it.getId()));
    }

    @Override
    public GenreResponseDto updateById(Integer id, GenreRequestDto entity) throws NoSuchContentException {
        Optional<Genre> maybeGenreById = genreRepository.findById(id);
        if (maybeGenreById.isPresent()) {
            Genre genre = genreMapper.updateEntityFromRequestDto(maybeGenreById.get(), entity);
            genre.setId(id);
            return genreMapper.entityToResponseDto(genreRepository.save(genre));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreResponseDto> findAllByBooksIn(BookRequestDto... books) {
        return genreRepository.findAllByBooksIn(Arrays.stream(books)
                        .map(bookMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(genreMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreResponseDto> findAllByFilmsIn(FilmRequestDto... films) {
        return genreRepository.findAllByFilmsIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(genreMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreResponseDto> findAllBySeriesIn(SeriesRequestDto... series) {
        return genreRepository.findAllBySeriesIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(genreMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
