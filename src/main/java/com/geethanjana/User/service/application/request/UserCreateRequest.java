package com.geethanjana.User.service.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    private String username;
    @NotNull
    private String passWord;
    @NotNull
    private boolean active;
}
