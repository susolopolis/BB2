package Entities;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idusuario;
    private String username;
    private String password;
    private Timestamp creationDate;
    private String userCode;
    private boolean admin;

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


    public String getUserCode() {
        return userCode;
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

    public void setUserCode(String userCode){
        this.userCode = userCode;
    }

    public void setAdmin(){
        admin = true;
    }

    public void setRegularUser(){
        admin = false;
    }
}
