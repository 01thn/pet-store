package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.PublisherRequestDto;
import com.cinemastore.privateservice.dto.PublisherResponseDto;
import com.cinemastore.privateservice.entity.Publisher;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.BookMapper;
import com.cinemastore.privateservice.mapper.PublisherMapper;
import com.cinemastore.privateservice.repository.PublisherRepository;
import com.cinemastore.privateservice.service.PublisherService;
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
public class PublisherServiceImpl implements PublisherService {
    public final PublisherRepository publisherRepository;

    public final PublisherMapper publisherMapper;

    public final BookMapper bookMapper;

    private final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);

    public PublisherServiceImpl(PublisherRepository publisherRepository,
                                PublisherMapper publisherMapper,
                                BookMapper bookMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public PublisherResponseDto save(PublisherRequestDto entity) {
        return publisherMapper.entityToResponseDto(
                publisherRepository.save(publisherMapper.requestDtoToEntity(entity))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PublisherResponseDto findById(Integer id) throws NoSuchContentException {
        return publisherRepository.findById(id)
                .map(publisherMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Publisher with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public PublisherResponseDto findByTitle(String title) throws NoSuchContentException {
        return publisherRepository.findByTitle(title)
                .map(publisherMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Publisher with title {} not found", title);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteByTitle(String title) throws NoSuchContentException {
        Optional.ofNullable(findByTitle(title))
                .ifPresent(it -> publisherRepository.deleteByTitle(it.getTitle()));
    }

    @Override
    public void deleteById(Integer id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> publisherRepository.deleteById(it.getId()));
    }

    @Override
    public PublisherResponseDto updateById(Integer id, PublisherRequestDto entity) throws NoSuchContentException {
        Optional<Publisher> maybePublisherById = publisherRepository.findById(id);
        if (maybePublisherById.isPresent()) {
            Publisher publisher = publisherMapper.updateEntityFromRequestDto(maybePublisherById.get(), entity);
            publisher.setId(id);
            return publisherMapper.entityToResponseDto(publisherRepository.save(publisher));
        } else {
            throw new NoSuchContentException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublisherResponseDto> findAllByBooksIn(BookRequestDto... books) {
        return publisherRepository.findAllByBooksIn(Arrays.stream(books)
                        .map(bookMapper::requestDtoToEntity)
                        .collect(Collectors.toSet()))
                .stream()
                .map(publisherMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
