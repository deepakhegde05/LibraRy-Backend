package com.backend.lib.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private String uname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobile;
}
