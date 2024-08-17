package com.geethanjana.User.service.domain.dto.convertor;

import com.geethanjana.User.service.application.request.UserCreateRequest;
import com.geethanjana.User.service.application.request.UserUpdateRequest;
import com.geethanjana.User.service.domain.dto.UserDto;
import com.geethanjana.User.service.external.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Converts a UserCreateRequest object to a User entity.
     *
     * @param userCreateRequest the UserCreateRequest object containing user details.
     * @return the User entity created from the request object, with an encoded password and creation date set.
     */
    public User convertCreateRequestToEntity(UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassWord()));
        user.setCreatedAt(new Date());
        return user;
    }

    /**
     * Converts a UserUpdateRequest object to an updated User entity.
     *
     * @param userUpdateRequest the UserUpdateRequest object containing updated user details.
     * @param existingUser      the existing User entity to be updated.
     * @return the updated User entity with the new details.
     */
    public User convertUpdateRequestToEntity(UserUpdateRequest userUpdateRequest, User existingUser) {

        existingUser.setPassword(passwordEncoder.encode(userUpdateRequest.getPassWord()));
        existingUser.setActive(userUpdateRequest.isActive());
        existingUser.setUsername(userUpdateRequest.getUsername());
        return existingUser;
    }


    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert.
     * @return the UserDto object created from the User entity.
     */
    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
