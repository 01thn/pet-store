package com.cinemastore.mediaservice.service;

import com.cinemastore.mediaservice.dto.RequestMediaDto;
import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.exception.ImageConvertingException;
import com.cinemastore.mediaservice.exception.NoSuchMediaException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface MediaService {

    /**
     *
     * @param requestMediaDto for saving
     * @return saved ResponseMediaDTO
     * @throws ImageConvertingException if there are some problems due to image converting
     */
    ResponseMediaDTO save(RequestMediaDto requestMediaDto) throws ImageConvertingException;

    /**
     *
     * @param id of Media for searching
     * @return ResponseMediaDTO
     * @throws NoSuchMediaException if there's no media with such id
     */
    ResponseMediaDTO findById(String id) throws NoSuchMediaException;

    /**
     *
     * @param id of Media for deleting
     * @throws NoSuchMediaException if there's no media with such id
     */
    void deleteById(String id) throws NoSuchMediaException;

    /**
     *
     * @return list of all ResponseMediaDTO
     */
    List<ResponseMediaDTO> findAll();

    /**
     *
     * @param id for updating media
     * @param requestMediaDto with new data
     * @return updated ResponseMediaDTO
     * @throws ImageConvertingException if there are some problems due to image converting in the requestMediaDto
     * @throws NoSuchMediaException if there's no media with such id
     */
    ResponseMediaDTO updateById(String id, RequestMediaDto requestMediaDto) throws ImageConvertingException, NoSuchMediaException;
}
