package com.cinemastore.privateservice.service.implementation;

import com.cinemastore.privateservice.client.MediaServiceClient;
import com.cinemastore.privateservice.criteria.BookFilter;
import com.cinemastore.privateservice.criteria.BookSpecificationBuilder;
import com.cinemastore.privateservice.dto.BookRequestDto;
import com.cinemastore.privateservice.dto.BookResponseDto;
import com.cinemastore.privateservice.dto.FilmResponseDto;
import com.cinemastore.privateservice.dto.GenreRequestDto;
import com.cinemastore.privateservice.dto.PersonRequestDto;
import com.cinemastore.privateservice.dto.PublisherRequestDto;
import com.cinemastore.privateservice.entity.Book;
import com.cinemastore.privateservice.entity.Genre;
import com.cinemastore.privateservice.entity.Person;
import com.cinemastore.privateservice.entity.Publisher;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.mapper.BookMapper;
import com.cinemastore.privateservice.mapper.GenreMapper;
import com.cinemastore.privateservice.mapper.PersonMapper;
import com.cinemastore.privateservice.mapper.PublisherMapper;
import com.cinemastore.privateservice.repository.BookRepository;
import com.cinemastore.privateservice.service.BookService;
import com.cinemastore.privateservice.service.GenreService;
import com.cinemastore.privateservice.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final GenreMapper genreMapper;

    private final PublisherMapper publisherMapper;

    private final PersonMapper personMapper;

    private final GenreService genreService;

    private final PublisherService publisherService;

    private final PersonServiceImpl personService;

    private final BookSpecificationBuilder bookSpecificationBuilder;

    private final MediaServiceClient client;

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           GenreMapper genreMapper,
                           PublisherMapper publisherMapper,
                           PersonMapper personMapper,
                           GenreService genreService,
                           PublisherService publisherService,
                           PersonServiceImpl personService,
                           BookSpecificationBuilder bookSpecificationBuilder,
                           MediaServiceClient client) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.genreMapper = genreMapper;
        this.publisherMapper = publisherMapper;
        this.personMapper = personMapper;
        this.genreService = genreService;
        this.publisherService = publisherService;
        this.personService = personService;
        this.bookSpecificationBuilder = bookSpecificationBuilder;
        this.client = client;
    }

    @Override
    public BookResponseDto save(BookRequestDto entity) throws NoSuchContentException {
        Set<Genre> genres = new HashSet<>();
        Set<Publisher> publishers = new HashSet<>();
        Set<Person> authors = new HashSet<>();

        for (GenreRequestDto genre : entity.getGenres()) {
            try {
                genres.add(genreMapper.responseDtoToEntity
                        (genreService.findByTitle(genre.getTitle())));
            } catch (NoSuchContentException e) {
                throw new NoSuchContentException("Genre " + genre.getTitle() + " doesn't exist");
            }
        }

        for (PublisherRequestDto publisher : entity.getPublishers()) {
            try {
                publishers.add(publisherMapper.responseDtoToEntity
                        (publisherService.findByTitle(publisher.getTitle())));
            } catch (NoSuchContentException e) {
                throw new NoSuchContentException("Publisher " + publisher.getTitle() + " doesn't exist");
            }
        }

        for (PersonRequestDto author : entity.getAuthors()) {
            try {
                authors.add(personMapper.responseDtoToEntity
                        (personService.findById(author.getId())));
            } catch (NoSuchContentException e) {
                throw new NoSuchContentException("Author with id " + author.getId() + " doesn't exist");
            }
        }

        Book book = bookMapper.requestDtoToEntity(entity);
        book.setGenres(genres);
        book.setPublishers(publishers);
        book.setAuthors(authors);
        return bookMapper.entityToResponseDto(bookRepository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDto findById(Long id) throws NoSuchContentException {
        return bookRepository.findById(id)
                .map(bookMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Book with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDto findById(Long id, String imageId) throws NoSuchContentException {
        return bookRepository.findById(id)
                .map(it -> {
                    BookResponseDto entity = bookMapper.entityToResponseDto(it);
                    entity.setImage(client.findById(imageId));
                    return entity;
                })
                .orElseThrow(() -> {
                    logger.warn("Book with id {} not found", id);
                    return new NoSuchContentException();
                });
    }

    @Override
    public void deleteById(Long id) throws NoSuchContentException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> bookRepository.deleteById(it.getId()));
    }

    public List<BookResponseDto> findBy(BookFilter bookFilter, Integer page, Integer size) {
        return bookRepository.findAll(bookSpecificationBuilder.getSpecification(bookFilter), PageRequest.of(page, size))
                .stream()
                .map(it -> {
                    BookResponseDto entity = bookMapper.entityToResponseDto(it);
                    if (bookFilter.getImageId() != null) {
                        entity.setImage(client.findById(bookFilter.getImageId()));
                    }
                    return entity;})
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto updateById(Long id, BookRequestDto entity) throws NoSuchContentException {
        Optional<Book> maybeBookById = bookRepository.findById(id);
        if (maybeBookById.isPresent()) {
            Book book = bookMapper.updateEntityFromRequestDto(maybeBookById.get(), entity);
            book.setId(id);
            return bookMapper.entityToResponseDto(bookRepository.save(book));
        } else {
            throw new NoSuchContentException();
        }
    }
}
