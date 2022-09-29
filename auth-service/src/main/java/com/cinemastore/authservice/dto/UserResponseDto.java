package com.cinemastore.authservice.dto;

import com.cinemastore.authservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private RoleResponseDto role;

    private Boolean isEnabled;
}
