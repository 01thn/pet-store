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
public class UserRequestDto {

    private UUID id;

    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    @Length(min = 6, message = "The field must be at least 6 characters")
    private String password;

    @NotBlank
    private String email;

    private String phone;

    @NotNull
    private RoleRequestDto role;

    @NotNull
    private Boolean isEnabled;
}
