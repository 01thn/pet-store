package com.cinemastore.authservice.service;

import com.cinemastore.authservice.dto.RoleRequestDto;
import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.entity.Role;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.mapper.RoleMapper;
import com.cinemastore.authservice.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository,
                       RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleResponseDto save(RoleRequestDto roleRequestDto){
        return roleMapper.entityToResponseDto(
                roleRepository.save
                        (roleMapper.requestDtoToEntity(roleRequestDto))
        );
    }

    public RoleResponseDto findById(Integer id) throws NoSuchRoleException {
        return roleRepository.findById(id)
                .map(roleMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("Role with id {} not found", id);
                    return new NoSuchRoleException();
                });
    }

    public void deleteById(Integer id) throws NoSuchRoleException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> roleRepository.deleteById(it.getId()));
    }

    public RoleResponseDto updateById(Integer id, RoleRequestDto entity) throws NoSuchRoleException {
        Optional<Role> maybePublisherById = roleRepository.findById(id);
        if (maybePublisherById.isPresent()) {
            Role publisher = roleMapper.updateEntityFromRequestDto(maybePublisherById.get(), entity);
            publisher.setId(id);
            return roleMapper.entityToResponseDto(roleRepository.save(publisher));
        } else {
            throw new NoSuchRoleException();
        }
    }
}
