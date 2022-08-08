package com.kruger.inventario.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Data //Eliminamos el código de getters y setters
@Entity  @Table (name= "person")
public class Person  implements Serializable {
    @Id  @GeneratedValue(strategy = GenerationType.AUTO) //Autogeneramos el ID
    private Long idPerson;
    //@NotNull
    private String names;
   // @NotNull
    private String surnames;
   // @NotNull  @Digits(message="Cedula incorrecta",fraction = 0,integer = 10)  @Column(unique=true)
    private long CI;
    //@Email  @NotNull
    private String email;
}
