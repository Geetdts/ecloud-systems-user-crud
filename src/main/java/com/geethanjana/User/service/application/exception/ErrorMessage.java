package com.geethanjana.User.service.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int status;

    private Date timestamp;

    private String message;

    private String description;
}
