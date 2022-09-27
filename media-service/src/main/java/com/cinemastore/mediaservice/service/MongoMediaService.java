package com.cinemastore.mediaservice.service;

import com.cinemastore.mediaservice.dto.RequestMediaDto;
import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.entity.Media;
import com.cinemastore.mediaservice.exception.ImageConvertingException;
import com.cinemastore.mediaservice.exception.NoSuchMediaException;
import com.cinemastore.mediaservice.mapper.MediaMapper;
import com.cinemastore.mediaservice.repository.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MongoMediaService implements MediaService {

    private final MediaRepository mediaRepository;

    private final MediaMapper mediaMapper;

    private final Logger logger = LoggerFactory.getLogger(MongoMediaService.class);

    @Autowired
    public MongoMediaService(MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    public ResponseMediaDTO save(RequestMediaDto requestMediaDto) throws ImageConvertingException {
        try {
            return mediaMapper.mediaToResponseMediaDto
                    (mediaRepository.save
                            (mediaMapper.reqMediaDtoToMedia(requestMediaDto))
                    );
        } catch (IOException e) {
            logger.error("A problem during image {} converting", requestMediaDto.getTitle());
            throw new ImageConvertingException();
        }
    }

    @Transactional(readOnly = true)
    public ResponseMediaDTO findById(String id) throws NoSuchMediaException {
        return mediaRepository.findById(id)
                .map(mediaMapper::mediaToResponseMediaDto)
                .orElseThrow(() -> {
                    logger.warn("Media with id {} not found", id);
                    return new NoSuchMediaException();
                });
    }

    public void deleteById(String id) throws NoSuchMediaException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> mediaRepository.deleteById(it.getId()));
    }

    @Transactional(readOnly = true)
    public List<ResponseMediaDTO> findAll() {
        return mediaRepository.findAll().stream()
                .map(mediaMapper::mediaToResponseMediaDto)
                .collect(Collectors.toList());
    }

    public ResponseMediaDTO updateById(String id, RequestMediaDto requestMediaDto) throws ImageConvertingException, NoSuchMediaException {
        Optional<Media> maybeMediaById = mediaRepository.findById(id);
        if (maybeMediaById.isPresent()) {
            Media media = mediaMapper.updateMediaFromReqMediaDto(maybeMediaById.get(), requestMediaDto);
            media.setId(id);
            return mediaMapper.mediaToResponseMediaDto(mediaRepository.save(media));
        } else {
            throw new NoSuchMediaException();
        }
    }
}
