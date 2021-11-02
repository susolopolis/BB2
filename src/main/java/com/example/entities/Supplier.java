package com.example.entities;

import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)   //Agregar one to may para item
    private Long idsupplier;
    private String name;
    private String country;

    public void setName(String name){
        this.name = name;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
