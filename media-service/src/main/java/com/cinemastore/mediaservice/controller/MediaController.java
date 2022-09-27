package com.cinemastore.mediaservice.controller;

import com.cinemastore.mediaservice.controller.helpers.Endpoints;
import com.cinemastore.mediaservice.dto.RequestMediaDto;
import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.exception.ImageConvertingException;
import com.cinemastore.mediaservice.exception.NoSuchMediaException;
import com.cinemastore.mediaservice.mapper.MediaMapper;
import com.cinemastore.mediaservice.service.MediaService;
import com.cinemastore.mediaservice.service.MongoMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(Endpoints.MEDIA)
public class MediaController {
    private final MediaService mongoMediaService;

    @Autowired
    public MediaController(MongoMediaService mongoMediaService) {
        this.mongoMediaService = mongoMediaService;
    }

    @PostMapping(value = Endpoints.ADD, consumes = "multipart/form-data")
    public ResponseEntity<ResponseMediaDTO> saveNew(@RequestParam String title,
                                                    @RequestBody MultipartFile image) throws ImageConvertingException {
        return new ResponseEntity<>(mongoMediaService.save(new RequestMediaDto(title, image)), HttpStatus.CREATED);
    }

    @GetMapping(Endpoints.GET)
    public ResponseEntity<byte[]> getMedia(@PathVariable String id) throws NoSuchMediaException {
        ResponseMediaDTO content = mongoMediaService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .header("Content-Id", content.getId())
                .header("Content-Title", content.getTitle())
                .contentLength(content.getImage().length())
                .body(content.getImage().getData());
    }

    @DeleteMapping(Endpoints.DELETE)
    public ResponseEntity<HttpStatus> deleteMedia(@PathVariable String id) throws NoSuchMediaException {
        mongoMediaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: only for admin or moderator
    @GetMapping(Endpoints.FIND_ALL)
    public ResponseEntity<List<ResponseMediaDTO>> findAllMedia() {
        return new ResponseEntity<>(mongoMediaService.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = Endpoints.UPDATE, consumes = "multipart/form-data")
    public ResponseEntity<ResponseMediaDTO> updateMedia(@PathVariable String id,
                                                        @RequestParam String title,
                                                        @RequestBody(required = false) MultipartFile image) throws ImageConvertingException, NoSuchMediaException {
       return new ResponseEntity<>(mongoMediaService.updateById(id, new RequestMediaDto(title, image)), HttpStatus.OK);
    }
}
