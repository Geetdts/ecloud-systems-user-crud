package com.geethanjana.User.service.domain.service;

import com.geethanjana.User.service.application.request.UserCreateRequest;
import com.geethanjana.User.service.application.request.UserUpdateRequest;
import com.geethanjana.User.service.domain.dto.UserDto;

import java.util.List;

public interface IUserService {

    /**
     * Create a new user.
     *
     * @param userCreateRequest - Contains the details of the user to be created.
     * @return - A UserDto containing the created user's details.
     */
    UserDto create(UserCreateRequest userCreateRequest);

    /**
     * Update an existing user.
     *
     * @param userUpdateRequest - Contains the user's update details.
     * @return - The updated User entity.
     */
    UserDto updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * Retrieve a user by ID.
     *
     * @param id - The ID of the user to be retrieved.
     * @return - The User entity corresponding to the provided ID.
     */
    UserDto getUserById(Long id);

    /**
     * Delete a user by ID.
     *
     * @param id - The ID of the user to be deleted.
     */
    void deleteUser(Long id);

    List<UserDto> getAllUsers();
}
