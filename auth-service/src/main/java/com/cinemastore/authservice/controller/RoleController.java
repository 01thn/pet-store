package com.cinemastore.authservice.controller;

import com.cinemastore.authservice.controller.helpers.RoleEndpoints;
import com.cinemastore.authservice.dto.RoleRequestDto;
import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.service.implementation.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(RoleEndpoints.ROLE)
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @PostMapping(RoleEndpoints.ADD)
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        return new ResponseEntity<>(roleServiceImpl.save(roleRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(RoleEndpoints.GET_BY_ID)
    public ResponseEntity<RoleResponseDto> getById(@PathVariable Integer id) throws NoSuchRoleException {
        return new ResponseEntity<>(roleServiceImpl.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(RoleEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) throws NoSuchRoleException {
        roleServiceImpl.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(RoleEndpoints.UPDATE_BY_ID)
    public ResponseEntity<RoleResponseDto> updateById(@PathVariable Integer id, @Valid @RequestBody RoleRequestDto roleRequestDto) throws NoSuchRoleException {
        return new ResponseEntity<>(roleServiceImpl.updateById(id, roleRequestDto), HttpStatus.OK);
    }
}
