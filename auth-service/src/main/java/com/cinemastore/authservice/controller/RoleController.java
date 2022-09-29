package com.cinemastore.authservice.controller;

import com.cinemastore.authservice.dto.RoleRequestDto;
import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.UserAlreadyExistsException;
import com.cinemastore.authservice.service.RoleService;
import com.cinemastore.authservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        return new ResponseEntity<>(roleService.save(roleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleResponseDto> getById(@PathVariable Integer id) throws NoSuchRoleException {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchRoleException {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<RoleResponseDto> updateById(@PathVariable Integer id, @Valid @RequestBody RoleRequestDto roleRequestDto) throws NoSuchRoleException {
        return new ResponseEntity<>(roleService.updateById(id, roleRequestDto), HttpStatus.OK);
    }
}
