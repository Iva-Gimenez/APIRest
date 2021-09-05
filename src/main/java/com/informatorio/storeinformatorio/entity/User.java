package com.informatorio.storeinformatorio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "TBL_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idUser;

    @Size(min = 3, max = 12, message = "El nombre debe contener más de 3 caracteres y un máximo 12")
    private String name;

    @Size(min = 3, max = 12, message = "El nombre debe contener más de 3 caracteres y un máximo 12")
    private String lastName;

    @Column(unique = true)
    @Size(min = 11, max = 35, message = "La dirección de email debe contener más de 11 hasta 35 caracteres")
    private String email;

    @JsonIgnore
    private String password;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaAlta;

    @Size(min = 3, max = 35, message = "Mínimo 3 caracteres y máximo 35")
    private String cityName;

    @Size(min = 3, max = 35, message = "Mínimo 3 caracteres y máximo 35")
    private String stateName;

    @Size(min = 3, max = 35, message = "Mínimo 3 caracteres y máximo 50")
    private String country;

    private String status;



}
