package com.cinemastore.authservice.aop;

import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.NoSuchUserException;
import com.cinemastore.authservice.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatcherController {

    @ExceptionHandler({NoSuchUserException.class, NoSuchRoleException.class})
    public final ResponseEntity<String> noSuchCatcher(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<String> userExistsCatcher(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
