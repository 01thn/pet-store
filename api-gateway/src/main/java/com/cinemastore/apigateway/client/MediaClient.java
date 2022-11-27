package com.cinemastore.apigateway.client;

import com.cinemastore.apigateway.dto.MediaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "media", url = "http://localhost:8081")
@Primary
public interface MediaClient {

    @RequestMapping(method = RequestMethod.POST, value = "/media/add", consumes = "multipart/form-data")
    ResponseEntity<MediaResponseDTO> saveNew(@RequestParam String title,
                                             @RequestBody MultipartFile image);

    @RequestMapping(method = RequestMethod.GET, value = "/media/get/{id}")
    ResponseEntity<byte[]> getMedia(@PathVariable String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/media/delete/{id}")
    ResponseEntity<HttpStatus> deleteMedia(@PathVariable String id);

    @RequestMapping(method = RequestMethod.GET, value = "/media/find-all")
    ResponseEntity<List<MediaResponseDTO>> findAllMedia();

    @RequestMapping(method = RequestMethod.PUT, value = "/media/update/{id}", consumes = "multipart/form-data")
    ResponseEntity<MediaResponseDTO> updateMedia(@PathVariable String id,
                                                 @RequestParam String title,
                                                 @RequestBody(required = false) MultipartFile image);
}
