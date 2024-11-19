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

    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String apellido;

    @NotBlank
    @Size(max = 1)
    private String genero;

    @NotBlank
    @Size(max = 50)
    private String correo;

    @Pattern(regexp = "^[0-9]{9}$")
    @NotBlank
    @Size(max = 9)
    private Long telefono;

}
