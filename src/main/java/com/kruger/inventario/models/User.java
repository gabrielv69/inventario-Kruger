package com.kruger.inventario.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "userCredentials")
@Data //Eliminamos el código de getters y setters
public class User  implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Autogeneramos el ID
    private Long idUser;
    private String username;
    private String password;
    private boolean rol; //Administrador o empleado
    @OneToOne//Un usuario está asociada a una persona y viceversa
    @JoinColumn (name="idPerson")
    private Person person;



}
