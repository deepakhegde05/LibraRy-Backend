package com.backend.lib.Dto;

import com.backend.lib.Enum.Role;
import lombok.Data;

@Data
public class AdminDto {
    private String name;

    private String email;

    private String password;

    private String designation;

    private String address;

    private Role role;
}
