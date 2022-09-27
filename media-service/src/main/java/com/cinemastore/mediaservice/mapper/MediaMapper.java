package com.cinemastore.mediaservice.mapper;

import com.cinemastore.mediaservice.dto.RequestMediaDto;
import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.entity.Media;
import com.cinemastore.mediaservice.exception.ImageConvertingException;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    ResponseMediaDTO mediaToResponseMediaDto(Media media);

    Media respMediaDtoToMedia(ResponseMediaDTO responseMediaDTO);

    @Named("updateMediaFromReqMediaDto")
    default Media updateMediaFromReqMediaDto(@MappingTarget Media media, RequestMediaDto requestMediaDto) throws ImageConvertingException {
        if (requestMediaDto == null) {
            return media;
        }

        if (requestMediaDto.getTitle() != null) {
            media.setTitle(requestMediaDto.getTitle());
        }

        if (requestMediaDto.getImage() != null) {
            try {
                media.setImage(new Binary(BsonBinarySubType.BINARY, requestMediaDto.getImage().getBytes()));
            } catch (IOException e) {
                throw new ImageConvertingException();
            }
        }
        return media;
    }

    @Named("fromReqMediaDtoToMedia")
    default Media reqMediaDtoToMedia(RequestMediaDto requestMediaDto) throws IOException {
        if (requestMediaDto == null) {
            return null;
        }

        Media media = new Media();
        media.setTitle(requestMediaDto.getTitle());
        media.setImage(new Binary(BsonBinarySubType.BINARY, requestMediaDto.getImage().getBytes()));

        return media;
    }
}
