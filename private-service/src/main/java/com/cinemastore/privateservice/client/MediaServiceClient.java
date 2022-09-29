package com.cinemastore.privateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "media-service", url = "http://localhost:4000")
public interface MediaServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/media/get/{id}", consumes = MediaType.IMAGE_PNG_VALUE)
    byte[] findById(@PathVariable String id);
}
