package DTO;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserDTO implements Serializable{

    private Long idusuario;
    private String username;
    private String password;
    private Timestamp creationDate;
    private String userCode;
    private boolean admin;

}
