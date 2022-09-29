package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.client.MediaServiceClient;
import com.cinemastore.privateservice.criteria.FilmFilter;
import com.cinemastore.privateservice.criteria.FilmSpecificationBuilder;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.entity.Film;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.FilmMapper;
import com.cinemastore.privateservice.repository.FilmRepository;
import com.cinemastore.privateservice.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    private final FilmSpecificationBuilder filmSpecificationBuilder;

    private final MediaServiceClient client;

    private final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    public FilmServiceImpl(FilmRepository filmRepository,
                           FilmMapper filmMapper,
                           FilmSpecificationBuilder filmSpecificationBuilder,
                           MediaServiceClient client) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
        this.filmSpecificationBuilder = filmSpecificationBuilder;
        this.client = client;
    }

    @Override
    public FilmResponseDto save(FilmRequestDto entity) {
        return filmMapper.entityToResponseDto(
                filmRepository.save(filmMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    public FilmResponseDto findById(Long id) throws NoSuchContentException {
        return filmRepository.findById(id)
                .map(filmMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Film with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public FilmResponseDto findById(Long id, String imageId) throws NoSuchContentException {
        return filmRepository.findById(id)
                .map(it -> {
                    FilmResponseDto entity = filmMapper.entityToResponseDto(it);
                    entity.setImage(client.findById(imageId));
                    return entity;
                })
                .orElseThrow(() -> {
                    logger.warn("Film with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteById(Long id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> filmRepository.deleteById(it.getId()));
    }

    @Override
    public FilmResponseDto updateById(Long id, FilmRequestDto entity) throws NoSuchContentException {
        Optional<Film> maybeFilmById = filmRepository.findById(id);
        if (maybeFilmById.isPresent()) {
            Film film = filmMapper.updateEntityFromRequestDto(maybeFilmById.get(), entity);
            film.setId(id);
            return filmMapper.entityToResponseDto(filmRepository.save(film));
        } else {
            throw new NoSuchContentException();
        }
    }

    public List<FilmResponseDto> findBy(FilmFilter filmFilter, Integer page, Integer size) {
        return filmRepository.findAll(filmSpecificationBuilder.getSpecification(filmFilter), PageRequest.of(page, size))
                .stream()
                .map(it -> {
                    FilmResponseDto entity = filmMapper.entityToResponseDto(it);
                    if (filmFilter.getImageId() != null) {
                        entity.setImage(client.findById(filmFilter.getImageId()));
                    }
                    return entity;
                })
                .collect(Collectors.toList());
    }
}
