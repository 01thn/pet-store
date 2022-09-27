package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.dto.StudioResponseDto;
import com.cinemastore.privateservice.entity.Studio;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.FilmMapper;
import com.cinemastore.privateservice.mapper.SeriesMapper;
import com.cinemastore.privateservice.mapper.StudioMapper;
import com.cinemastore.privateservice.repository.StudioRepository;
import com.cinemastore.privateservice.service.StudioService;
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
public class StudioServiceImpl implements StudioService {
    public final StudioRepository studioRepository;

    public final StudioMapper studioMapper;

    public final FilmMapper filmMapper;

    public final SeriesMapper seriesMapper;

    private final Logger logger = LoggerFactory.getLogger(StudioServiceImpl.class);

    public StudioServiceImpl(StudioRepository studioRepository,
                             StudioMapper studioMapper,
                             FilmMapper filmMapper,
                             SeriesMapper seriesMapper) {
        this.studioRepository = studioRepository;
        this.studioMapper = studioMapper;
        this.filmMapper = filmMapper;
        this.seriesMapper = seriesMapper;
    }

    @Override
    public StudioResponseDto save(StudioRequestDto entity) {
        return studioMapper.entityToResponseDto(
                studioRepository.save(studioMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public StudioResponseDto findById(Integer id) throws NoSuchContentException {
        return studioRepository.findById(id)
                .map(studioMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Studio with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public StudioResponseDto findByTitle(String title) throws NoSuchContentException {
        return studioRepository.findByTitle(title)
                .map(studioMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Studio with title {} not found", title);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteByTitle(String title) throws NoSuchContentException {
        Optional.ofNullable(findByTitle(title))
                .ifPresent(it -> studioRepository.deleteByTitle(it.getTitle()));
    }

    @Override
    public void deleteById(Integer id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> studioRepository.deleteById(it.getId()));
    }

    @Override
    public StudioResponseDto updateById(Integer id, StudioRequestDto entity) throws NoSuchContentException {
        Optional<Studio> maybeStudioById = studioRepository.findById(id);
        if (maybeStudioById.isPresent()) {
            Studio studio = studioMapper.updateEntityFromRequestDto(maybeStudioById.get(), entity);
            studio.setId(id);
            return studioMapper.entityToResponseDto(studioRepository.save(studio));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudioResponseDto> findAllByFilmsIn(FilmRequestDto... films) {
        return studioRepository.findAllByFilmsIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(studioMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudioResponseDto> findAllBySeriesIn(SeriesRequestDto... series) {
        return studioRepository.findAllBySeriesIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(studioMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
