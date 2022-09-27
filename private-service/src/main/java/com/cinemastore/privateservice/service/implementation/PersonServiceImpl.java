package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.CountryRequestDto;
import com.cinemastore.privateservice.dto.FilmRequestDto;
import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PersonResponseDto;
import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.SeriesRequestDto;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.CountryMapper;
import com.cinemastore.privateservice.mapper.FilmMapper;
import com.cinemastore.privateservice.mapper.PersonMapper;
import com.cinemastore.privateservice.mapper.PositionMapper;
import com.cinemastore.privateservice.mapper.SeriesMapper;
import com.cinemastore.privateservice.repository.PersonRepository;
import com.cinemastore.privateservice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PositionMapper positionMapper;

    private final CountryMapper countryMapper;

    private final FilmMapper filmMapper;

    private final SeriesMapper seriesMapper;

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper,
                             PositionMapper positionMapper,
                             CountryMapper countryMapper,
                             FilmMapper filmMapper,
                             SeriesMapper seriesMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.positionMapper = positionMapper;
        this.countryMapper = countryMapper;
        this.filmMapper = filmMapper;
        this.seriesMapper = seriesMapper;
    }

    @Override
    public PersonResponseDto save(PersonRequestDto entity) {
        return personMapper.entityToResponseDto(
                personRepository.save(personMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponseDto findById(Long id) throws NoSuchContentException {
        return personRepository.findById(id)
                .map(personMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Person with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteById(Long id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> personRepository.deleteById(it.getId()));
    }

    @Override
    public PersonResponseDto updateById(Long id, PersonRequestDto entity) throws NoSuchContentException {
        Optional<Person> maybePersonById = personRepository.findById(id);
        if (maybePersonById.isPresent()) {
            Person person = personMapper.updateEntityFromRequestDto(maybePersonById.get(), entity);
            person.setId(id);
            return personMapper.entityToResponseDto(personRepository.save(person));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByFirstName(String firstName) {
        return personRepository.findAllByFirstName(firstName)
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByLastName(String lastName) {
        return personRepository.findAllByLastName(lastName)
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByDateOfBirth(LocalDate dateOfBirth) {
        return personRepository.findAllByDateOfBirth(dateOfBirth)
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByDateOfDeath(LocalDate dateOfDeath) {
        return personRepository.findAllByDateOfDeath(dateOfDeath)
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByPositionsIn(PositionReqDto... roles) {
        return personRepository.findAllByPositionsIn(Arrays.stream(roles)
                        .map(positionMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByCountriesIn(CountryRequestDto... countries) {
        return personRepository.findAllByCountriesIn(Arrays.stream(countries)
                        .map(countryMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByFilmsAuthorIn(FilmRequestDto... films) {
        return personRepository.findAllByFilmsAuthorIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByFilmsOperatorIn(FilmRequestDto... films) {
        return personRepository.findAllByFilmsOperatorIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllByFilmsActorIn(FilmRequestDto... films) {
        return personRepository.findAllByFilmsActorIn(Arrays.stream(films)
                        .map(filmMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllBySeriesAuthorIn(SeriesRequestDto... series) {
        return personRepository.findAllBySeriesAuthorIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllBySeriesOperatorIn(SeriesRequestDto... series) {
        return personRepository.findAllBySeriesOperatorIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAllBySeriesActorIn(SeriesRequestDto... series) {
        return personRepository.findAllBySeriesActorIn(Arrays.stream(series)
                        .map(seriesMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(personMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
