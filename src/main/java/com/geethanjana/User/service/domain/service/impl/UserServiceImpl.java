package com.geethanjana.User.service.domain.service.impl;

import com.geethanjana.User.service.application.exception.ResourceNotFoundException;
import com.geethanjana.User.service.application.request.UserCreateRequest;
import com.geethanjana.User.service.application.request.UserUpdateRequest;
import com.geethanjana.User.service.domain.dto.UserDto;
import com.geethanjana.User.service.domain.dto.convertor.UserMapper;
import com.geethanjana.User.service.domain.service.IUserService;
import com.geethanjana.User.service.external.entity.User;
import com.geethanjana.User.service.external.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation class responsible for managing User-related operations.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {

        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        User user = userMapper.convertCreateRequestToEntity(userCreateRequest);
        User savedUser = userRepository.save(user);
        return userMapper.convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userUpdateRequest.getId()));

        // Check if the username is being updated
        if (!user.getUsername().equals(userUpdateRequest.getUsername())) {
            // Check if the new username already exists for a different user
            if (userRepository.existsByUsernameAndIdNot(userUpdateRequest.getUsername(), user.getId())) {
                throw new IllegalArgumentException("Username already exists. Please choose a different username.");
            }
        }
        User updateduser = userMapper.convertUpdateRequestToEntity(userUpdateRequest, user);
        return userMapper.convertToDto(userRepository.save(updateduser));
    }


    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return userMapper.convertToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

}
