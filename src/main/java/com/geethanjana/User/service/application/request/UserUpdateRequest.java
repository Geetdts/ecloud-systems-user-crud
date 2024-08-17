package com.geethanjana.User.service.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String passWord;
    @NotNull
    private boolean active;

}
