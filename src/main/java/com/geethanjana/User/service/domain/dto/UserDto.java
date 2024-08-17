package com.geethanjana.User.service.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;
    private String username;
    private boolean active;
    private Date createdAt;



}
