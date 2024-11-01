package com.inncontrol.employees.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Employee {

    // Getters y Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Enumerated(EnumType.STRING)
    private EmployeeGender genero;

    @Column(length = 50, nullable = false, unique = true)
    private String correo;

    @Column(length = 9, nullable = false)
    private String telefono;

    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    @PrePersist
    public void prePersist() {
        this.fechaContratacion = LocalDate.now();
    }

}
