package com.cinemastore.mediaservice.controller.helpers;

import com.cinemastore.mediaservice.exception.ImageConvertingException;
import com.cinemastore.mediaservice.exception.NoSuchMediaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatcherController {

    @ExceptionHandler({NoSuchMediaException.class})
    public final ResponseEntity<HttpStatus> noSuchMediaCatcher() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ImageConvertingException.class})
    public final ResponseEntity<HttpStatus> imageConvertExceptionCatcher() {
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
