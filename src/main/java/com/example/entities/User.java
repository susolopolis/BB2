package com.example.entities;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idusuario;

    @Column(unique=true)
    private String username;

    private String password;
    private Timestamp creationDate;

    @Column(unique=true)
    private String email;
    private boolean admin;

    public Long getId() {
        return idusuario;
    }

    public void setId(Long idusuario) {
        this.idusuario = idusuario;
    }

    //GETTERS


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public Timestamp getCreationDate() {
        return creationDate;
    }


    public String getEmail() {
        return email;
    }


    public boolean getAdmin(){
        return admin;
    }

    //SETTERS

    public void setUsername(String nombreUsuario){
        this.username = nombreUsuario;
    }

    public void setPassword(String contrasena){
        this.password = contrasena;
    }

    public void setCreationDate(){
        creationDate = new Timestamp(System.currentTimeMillis());
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAdmin(){
        admin = true;
    }

    public void setRegularUser(){
        admin = false;
    }
}
