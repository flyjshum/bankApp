package com.example.bankapp.dtos;

import com.example.bankapp.entities.RoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Schema(description = "ДТО пользователя")

public class UserDto {
  // @Schema(description = "Это id пользователя", example = "2")
  //  private Long id;

    @Schema(description = "Имя пользователя", example = "maryPoppins")
    private String username;

    @Schema(description = "Пароль пользователя", example = "Qwerty123")
    private String password;

 @Schema(description = "Роль пользователя", example = "ROLE_USER")

 private String roleName;

}
