package com.inncontrol.employees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {

    // Getters y Setters
    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String apellido;

    @NotBlank
    @Pattern(regexp = "^[MF]$", message = "El género solo puede ser 'M' o 'F'")
    private String genero;

    @Email(message = "El correo debe ser válido y contener '@'")
    @NotBlank
    @Size(max = 50)
    private String correo;

    @NotBlank
    @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

}
