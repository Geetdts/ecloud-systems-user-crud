package com.geethanjana.User.service.application.controller;

import com.geethanjana.User.service.application.request.UserCreateRequest;
import com.geethanjana.User.service.application.request.UserUpdateRequest;
import com.geethanjana.User.service.application.response.ObjectResponse;
import com.geethanjana.User.service.application.util.SystemMessage;
import com.geethanjana.User.service.domain.dto.UserDto;
import com.geethanjana.User.service.domain.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for managing User-related operations.
 */
@CrossOrigin
@Tag(name = "User", description = "User APIs.")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * Create a new User / Register user.
     *
     * @param userCreateRequest - Contains the details of the user to be created.
     * @return - A ResponseEntity containing the created user's details.
     */
    @Operation(summary = "Create new User / Register user")
    @PostMapping
    public ResponseEntity<ObjectResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = iUserService.create(userCreateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(userDto, HttpStatus.CREATED.value(), SystemMessage.CREATE),
                HttpStatus.CREATED);
    }

    /**
     * Update User.
     *
     * @param userUpdateRequest - Contains the user's update details.
     * @return - A ResponseEntity containing the updated user details.
     */
    @Operation(summary = "Update user")
    @PutMapping()
    public ResponseEntity<ObjectResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserDto updatedUser = iUserService.updateUser(userUpdateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(updatedUser, HttpStatus.OK.value(), SystemMessage.UPDATE),
                HttpStatus.OK);
    }

    /**
     * Retrieve a list of all Users.
     *
     * @return - A ResponseEntity containing the list of all users.
     */
    @Operation(summary = "Get all Users")
    @GetMapping
    public ResponseEntity<ObjectResponse> getAllUsers() {
        List<UserDto> users = iUserService.getAllUsers();
        return new ResponseEntity<>(new ObjectResponse<>(users, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }
    /**
     * Retrieve a User by ID.
     *
     * @param id - The ID of the user to be retrieved.
     * @return - A ResponseEntity containing the user details.
     */
    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getUserById(@PathVariable Long id) {
        UserDto userDto = iUserService.getUserById(id);
        return new ResponseEntity<>(new ObjectResponse<>(userDto, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    /**
     * Delete a User by ID.
     *
     * @param id - The ID of the user to be deleted.
     * @return - A ResponseEntity with a status of NO_CONTENT.
     */
    @Operation(summary = "Delete User by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
        return new ResponseEntity<>(new ObjectResponse<>(null, HttpStatus.OK.value(), SystemMessage.DELETE),
                HttpStatus.OK);
    }


}
