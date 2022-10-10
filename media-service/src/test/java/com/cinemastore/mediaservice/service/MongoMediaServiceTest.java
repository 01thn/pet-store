package com.cinemastore.mediaservice.service;

import com.cinemastore.mediaservice.dto.RequestMediaDto;
import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.entity.Media;
import com.cinemastore.mediaservice.exception.ImageConvertingException;
import com.cinemastore.mediaservice.kafka.ProducerService;
import com.cinemastore.mediaservice.mapper.MediaMapper;
import com.cinemastore.mediaservice.repository.MediaRepository;
import org.bson.types.Binary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MongoMediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private MediaMapper mediaMapper;

    @Mock
    private ProducerService producerService;

    @InjectMocks
    private MongoMediaService mongoMediaService;

    @Test
    void save() throws ImageConvertingException, IOException {
        var request = RequestMediaDto.builder()
                .title("test")
                .build();

        var entity = new Media("1", "test", new Binary(new byte[0]));

        var response = ResponseMediaDTO.builder()
                .id("1")
                .title("test")
                .image(new Binary(new byte[0]))
                .build();

        doReturn(entity).when(mediaMapper).reqMediaDtoToMedia(request);

        doReturn(entity)
                .when(mediaRepository).save(entity);

        doReturn(response).when(mediaMapper).mediaToResponseMediaDto(entity);

        ResponseMediaDTO actual = mongoMediaService.save(request);

        Assertions.assertEquals(response.getId(), actual.getId());

        verify(producerService).produceSavingSuccess(any(String.class));

    }
}