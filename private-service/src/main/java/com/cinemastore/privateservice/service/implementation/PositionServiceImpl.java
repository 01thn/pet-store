package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PositionReqDto;
import com.cinemastore.privateservice.dto.PositionRespDto;
import com.cinemastore.privateservice.entity.Position;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.PersonMapper;
import com.cinemastore.privateservice.mapper.PositionMapper;
import com.cinemastore.privateservice.repository.PositionRepository;
import com.cinemastore.privateservice.service.PositionService;
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
public class PositionServiceImpl implements PositionService {
    public final PositionRepository positionRepository;

    public final PositionMapper positionMapper;

    public final PersonMapper personMapper;

    private final Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    public PositionServiceImpl(PositionRepository positionRepository,
                               PositionMapper positionMapper,
                               PersonMapper personMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
        this.personMapper = personMapper;
    }

    @Override
    public PositionRespDto save(PositionReqDto entity) {
        return positionMapper.entityToResponseDto(
                positionRepository.save(positionMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PositionRespDto findById(Integer id) throws NoSuchContentException {
        return positionRepository.findById(id)
                .map(positionMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Role with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public PositionRespDto findByTitle(String title) throws NoSuchContentException {
        return positionRepository.findByTitle(title)
                .map(positionMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Role with title {} not found", title);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteByTitle(String title) throws NoSuchContentException {
        Optional.ofNullable(findByTitle(title))
                .ifPresent(it -> positionRepository.deleteByTitle(it.getTitle()));
    }

    @Override
    public void deleteById(Integer id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> positionRepository.deleteById(it.getId()));
    }

    @Override
    public PositionRespDto updateById(Integer id, PositionReqDto entity) throws NoSuchContentException {
        Optional<Position> maybeRoleById = positionRepository.findById(id);
        if (maybeRoleById.isPresent()) {
            Position position = positionMapper.updateEntityFromRequestDto(maybeRoleById.get(), entity);
            position.setId(id);
            return positionMapper.entityToResponseDto(positionRepository.save(position));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionRespDto> findAllByPersonsIn(PersonRequestDto... persons) {
        return positionRepository.findAllByPersonsIn(Arrays.stream(persons)
                        .map(personMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(positionMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
