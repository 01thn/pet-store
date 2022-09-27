package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.CountryResponseDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Country;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.CountryMapper;
import com.cinemastore.privateservice.mapper.FilmMapper;
import com.cinemastore.privateservice.mapper.SeriesMapper;
import com.cinemastore.privateservice.repository.CountryRepository;
import com.cinemastore.privateservice.service.CountryService;
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
public class CountryServiceImpl implements CountryService {
    public final CountryRepository countryRepository;

    public final CountryMapper countryMapper;

    public final FilmMapper filmMapper;

    public final SeriesMapper seriesMapper;

    private final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    public CountryServiceImpl(CountryRepository countryRepository,
                              CountryMapper countryMapper,
                              FilmMapper filmMapper,
                              SeriesMapper seriesMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.filmMapper = filmMapper;
        this.seriesMapper = seriesMapper;
    }

    @Override
    public CountryResponseDto save(CountryRequestDto entity) {
        return countryMapper.entityToResponseDto(
                countryRepository.save(countryMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CountryResponseDto findById(Integer id) throws NoSuchContentException {
        return countryRepository.findById(id)
                .map(countryMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Country with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public CountryResponseDto findByTitle(String title) throws NoSuchContentException {
        return countryRepository.findByTitle(title)
                .map(countryMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Country with title {} not found", title);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteByTitle(String title) throws NoSuchContentException {
        Optional.ofNullable(findByTitle(title))
                .ifPresent(it -> countryRepository.deleteByTitle(it.getTitle()));
    }

    @Override
    public void deleteById(Integer id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> countryRepository.deleteById(it.getId()));
    }

    @Override
    public CountryResponseDto updateById(Integer id, CountryRequestDto entity) throws NoSuchContentException {
        Optional<Country> maybeCountryById = countryRepository.findById(id);
        if (maybeCountryById.isPresent()) {
            Country country = countryMapper.updateEntityFromRequestDto(maybeCountryById.get(), entity);
            country.setId(id);
            return countryMapper.entityToResponseDto(countryRepository.save(country));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryResponseDto> findAllByFilmsIn(FilmRequestDto... films) {
        return countryRepository.findAllByFilmsIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(countryMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryResponseDto> findAllBySeriesIn(SeriesRequestDto... series) {
        return countryRepository.findAllBySeriesIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(countryMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
