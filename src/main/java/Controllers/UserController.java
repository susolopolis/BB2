package Controllers;
import DTO.UserDTO;
import Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Repositories.UserRepository;

import java.sql.Timestamp;

@RestController
public class UserController {

    UserRepository usuarioRepository;

    @Autowired
    public UserController(UserRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/createUser/{username}/{password}")
    public void createUser(@PathVariable ("username") String Username, @PathVariable("password") String Password ){
        String username = Username;
        String password = Password;
        String userCode ="";

        User user= new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserCode(userCode);
        user.setCreationDate();
        user.setRegularUser();

        usuarioRepository.save(user);
    }

    @GetMapping("/login/{username}/{password}")
    public void login(@PathVariable ("username") String Username, @PathVariable("password") String Password ){
        String username = Username;
        String password = Password;

        User user = usuarioRepository.findByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password)){

            }
        }
    }

}
