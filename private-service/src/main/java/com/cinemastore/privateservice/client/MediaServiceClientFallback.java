package com.cinemastore.privateservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MediaServiceClientFallback implements MediaServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaServiceClientFallback.class);

    @Override
    public byte[] findById(String id) {
        LOGGER.error("Error during getting image with id: {}", id);
        return new byte[0];
    }
}
