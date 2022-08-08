package com.kruger.inventario.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "record")
@Data //Eliminamos el código de getters y setters
public class Record implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Autogeneramos el ID
    private Long idRecord;
    private String birthDate; //TODO: tipo date
    private String addres;
    private int phone;
    private boolean vaccinated; // SI o No
    private String vaccine;
    private String vaccineDate; //TODO: tipo date
    private int doses;
   @OneToOne //Una record está asociado a una persona y viceversa
   @JoinColumn (name="idPerson")
   private Person person;

}
