package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.client.MediaServiceClient;
import com.cinemastore.privateservice.criteria.SeriesFilter;
import com.cinemastore.privateservice.criteria.SeriesSpecificationBuilder;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.SeriesResponseDto;
import com.cinemastore.privateservice.entity.Series;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.CountryMapper;
import com.cinemastore.privateservice.mapper.GenreMapper;
import com.cinemastore.privateservice.mapper.PersonMapper;
import com.cinemastore.privateservice.mapper.SeriesMapper;
import com.cinemastore.privateservice.mapper.StudioMapper;
import com.cinemastore.privateservice.repository.SeriesRepository;
import com.cinemastore.privateservice.service.SeriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    private final SeriesMapper seriesMapper;

    private final SeriesSpecificationBuilder seriesSpecificationBuilder;

    private final MediaServiceClient client;

    private final Logger logger = LoggerFactory.getLogger(SeriesServiceImpl.class);

    public SeriesServiceImpl(SeriesRepository seriesRepository,
                             SeriesMapper seriesMapper,
                             GenreMapper genreMapper,
                             CountryMapper countryMapper,
                             PersonMapper personMapper,
                             StudioMapper studioMapper,
                             SeriesSpecificationBuilder seriesSpecificationBuilder,
                             MediaServiceClient client) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
        this.seriesSpecificationBuilder = seriesSpecificationBuilder;
        this.client = client;
    }

    @Override
    @Transactional
    public SeriesResponseDto save(SeriesRequestDto entity) {
        return seriesMapper.entityToResponseDto(
                seriesRepository.save(seriesMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    public SeriesResponseDto findById(Long id) throws NoSuchContentException {
        return seriesRepository.findById(id)
                .map(seriesMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Series with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    public SeriesResponseDto findById(Long id, String imageId) throws NoSuchContentException {
        return seriesRepository.findById(id)
                .map(it -> {
                    SeriesResponseDto entity = seriesMapper.entityToResponseDto(it);
                    entity.setImage(client.findById(imageId));
                    return entity;
                })
                .orElseThrow(() -> {
                    logger.warn("Series with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> seriesRepository.deleteById(it.getId()));
    }

    @Override
    @Transactional
    public SeriesResponseDto updateById(Long id, SeriesRequestDto entity) throws NoSuchContentException {
        Optional<Series> maybeSeriesById = seriesRepository.findById(id);
        if (maybeSeriesById.isPresent()) {
            Series series = seriesMapper.updateEntityFromRequestDto(maybeSeriesById.get(), entity);
            series.setId(id);
            return seriesMapper.entityToResponseDto(seriesRepository.save(series));
        } else {
            throw new NoSuchContentException();
        }
    }

    public List<SeriesResponseDto> findBy(SeriesFilter seriesFilter, Integer page, Integer size) {
        return seriesRepository.findAll(seriesSpecificationBuilder.getSpecification(seriesFilter), PageRequest.of(page, size))
                .stream()
                .map(it -> {
                    SeriesResponseDto entity = seriesMapper.entityToResponseDto(it);
                    if (seriesFilter.getImageId() != null) {
                        entity.setImage(client.findById(seriesFilter.getImageId()));
                    }
                    return entity;
                })
                .collect(Collectors.toList());
    }
}
