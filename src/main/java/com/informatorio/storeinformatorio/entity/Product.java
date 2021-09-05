package com.informatorio.storeinformatorio.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    @Size(min = 6, max = 200)
    private String name;

    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    @Positive(message = "El precio debe ser mayor a 0")
    @NotNull(message = "Debe ingresar el precio del producto")
    private Double price;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String content;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaAlta;

    private String status;

    private Boolean published;


}
