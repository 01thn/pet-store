package com.cinemastore.apigateway.controller;

import com.cinemastore.apigateway.client.MediaClient;
import com.cinemastore.apigateway.controller.helpers.MediaEndpoints;
import com.cinemastore.apigateway.dto.MediaResponseDTO;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaClient client;

    public MediaController(MediaClient client) {
        this.client = client;
    }

    @PostMapping(value = MediaEndpoints.ADD, consumes = "multipart/form-data")
    public ResponseEntity<MediaResponseDTO> saveNew(@RequestParam String title,
                                                    @RequestBody MultipartFile image) {
        return client.saveNew(title, image);
    }

    // TODO: 13.10.2022 headers?
    @GetMapping(MediaEndpoints.GET)
    public ResponseEntity<byte[]> getMedia(@PathVariable String id) {
        return client.getMedia(id);
    }

    @DeleteMapping(MediaEndpoints.DELETE)
    public ResponseEntity<HttpStatus> deleteMedia(@PathVariable String id) {
        return client.deleteMedia(id);
    }

    @GetMapping(MediaEndpoints.FIND_ALL)
    public ResponseEntity<List<MediaResponseDTO>> findAllMedia() {
        return client.findAllMedia();
    }

    @PutMapping(value = MediaEndpoints.UPDATE, consumes = "multipart/form-data")
    public ResponseEntity<MediaResponseDTO> updateMedia(@PathVariable String id,
                                                        @RequestParam String title,
                                                        @RequestBody(required = false) MultipartFile image) {
        return client.updateMedia(id, title, image);
    }
}
