package com.example.controller;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/user")

@Controller
public class UserController {


    @Autowired
    UserRepository usuarioRepository;

    @PostMapping("/createUser")
    public String createUser(@RequestParam("username") String Username, @RequestParam("password") String Password, @RequestParam("email") String email,  ModelMap model){
        String username = Username;
        String password = Password;
        String Email =email;

        User user= new User();
        user.setUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setPassword(encoder.encode(password));
        user.setEmail(Email);
        user.setCreationDate();
        user.setRegularUser();


        usuarioRepository.save(user);
        model.addAttribute("userName",user.getUsername());
        model.addAttribute("message","User created!");
        return "User";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String Username, @RequestParam("password") String Password, ModelMap model) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = usuarioRepository.findByUsername(Username);
        if (user == null) {
            user = usuarioRepository.findByEmail(Username);
        }
        if (user != null) {
            if (Password.equals(user.getPassword())) {
                model.addAttribute("userName",Username);
            }
            else{
                model.addAttribute("message","Incorrect Password!");
                return "login";
            }
        }
        else{
            model.addAttribute("message","Username or Email don´t found!");
            return "login";
        }
        return "User";
    }

}
