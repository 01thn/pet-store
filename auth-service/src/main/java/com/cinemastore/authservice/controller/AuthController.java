package com.cinemastore.authservice.controller;

import com.cinemastore.authservice.controller.helpers.AuthEndpoints;
import com.cinemastore.authservice.dto.UserAuthDto;
import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.NoSuchUserException;
import com.cinemastore.authservice.exception.UserAlreadyExistsException;
import com.cinemastore.authservice.jwt.JwtTokenProvider;
import com.cinemastore.authservice.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(AuthEndpoints.AUTH)
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;


    public AuthController(UserServiceImpl userServiceImpl,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(AuthEndpoints.SIGN_UP)
    public ResponseEntity<UserResponseDto> registry(@Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchRoleException, UserAlreadyExistsException {
        if (userServiceImpl.userExistsByUsername(userRequestDto.getUsername())) {
            throw new UserAlreadyExistsException("User with such username already exists");
        }
        if (userServiceImpl.userExistsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with such email already exists");
        }
        return new ResponseEntity<>(userServiceImpl.save(userRequestDto), HttpStatus.CREATED);
    }

    @PostMapping(AuthEndpoints.SIGN_IN)
    public ResponseEntity<Map<Object, Object>> login(@RequestBody UserAuthDto authDto) throws NoSuchUserException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        UserResponseDto user = userServiceImpl.findByUsername(authDto.getUsername());
        String token = jwtTokenProvider.generateToken(authDto.getUsername(), user.getRole());
        return new ResponseEntity<>(new HashMap<>() {{
            put("username", authDto.getUsername());
            put("token", token);
        }}, HttpStatus.OK);
    }
}
