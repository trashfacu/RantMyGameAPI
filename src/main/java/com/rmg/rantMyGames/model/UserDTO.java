package com.rmg.rantMyGames.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {
    private Integer id;

    @NotBlank(message = "El nombre de usuario es requerido")
    private String username;
    @Email(message = "El correo debe tener un formato valido")
    private String email;
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;
}
