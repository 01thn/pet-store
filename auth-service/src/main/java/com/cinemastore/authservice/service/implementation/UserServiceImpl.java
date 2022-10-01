package com.cinemastore.authservice.service.implementation;

import com.cinemastore.authservice.dto.UserRequestDto;
import com.cinemastore.authservice.dto.UserResponseDto;
import com.cinemastore.authservice.entity.User;
import com.cinemastore.authservice.exception.NoSuchRoleException;
import com.cinemastore.authservice.exception.NoSuchUserException;
import com.cinemastore.authservice.mapper.RoleMapper;
import com.cinemastore.authservice.mapper.UserMapper;
import com.cinemastore.authservice.repository.RoleRepository;
import com.cinemastore.authservice.repository.UserRepository;
import com.cinemastore.authservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final RoleServiceImpl roleServiceImpl;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
//                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           RoleMapper roleMapper,
                           RoleServiceImpl roleServiceImpl) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleServiceImpl = roleServiceImpl;
    }

    public UserResponseDto save(UserRequestDto userRequestDto) throws NoSuchRoleException {
        User user = userMapper.requestDtoToEntity(userRequestDto);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            user.setRole(roleMapper.responseDtoToEntity
                    (roleServiceImpl.findById
                            (userRequestDto.getRole().getId()))
            );
        } catch (NoSuchRoleException e) {
            throw new NoSuchRoleException("Role doesn't exist");
        }
        return userMapper.entityToResponseDto(userRepository.save(user));
    }

    public UserResponseDto findById(UUID id) throws NoSuchUserException {
        return userRepository.findById(id)
                .map(userMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("User with id {} not found", id);
                    return new NoSuchUserException();
                });
    }

    public UserResponseDto findByUsername(String username) throws NoSuchUserException {
        return userRepository.findByUsername(username)
                .map(userMapper::entityToResponseDto)
                .orElseThrow(() -> {
                    logger.warn("User with username {} not found", username);
                    return new NoSuchUserException();
                });
    }

    public Boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteById(UUID id) throws NoSuchUserException {
        Optional.ofNullable(findById(id))
                .ifPresent(it -> userRepository.deleteById(it.getId()));
    }

    public UserResponseDto updateById(UUID id, UserRequestDto entity) throws NoSuchUserException {
        Optional<User> maybeUserById = userRepository.findById(id);
        if (maybeUserById.isPresent()) {
            User user = userMapper.updateEntityFromRequestDto(maybeUserById.get(), entity);
            user.setId(id);
            return userMapper.entityToResponseDto(userRepository.save(user));
        } else {
            throw new NoSuchUserException();
        }
    }
}
