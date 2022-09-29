package com.cinemastore.authservice.controller;

import com.cinemastore.authservice.dto.UserAuthDto;
import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.NoSuchUserException;
import com.cinemastore.authservice.exception.UserAlreadyExistsException;
import com.cinemastore.authservice.jwt.JWTTokenProvider;
import com.cinemastore.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTTokenProvider jwtTokenProvider;


    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> registry(@Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchRoleException, UserAlreadyExistsException {
        if (userService.userExistsByUsername(userRequestDto.getUsername())) {
            throw new UserAlreadyExistsException("User with such username already exists");
        }
        if (userService.userExistsByEmail(userRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("User with such email already exists");
        }
        return new ResponseEntity<>(userService.save(userRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody UserAuthDto authDto) throws NoSuchUserException {

        String username = authDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authDto.getPassword()));
        UserResponseDto user = userService.findByUsername(username);

        String token = jwtTokenProvider.generateToken(username, user.getRole());

        Map<Object, Object> resp = new HashMap<>();
        resp.put("username", username);
        resp.put("token", token);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
