package com.example.controller;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import static javax.swing.JOptionPane.showMessageDialog;

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
        user.setPassword(password);
        user.setEmail(Email);
        user.setCreationDate();
        user.setRegularUser();


        usuarioRepository.save(user);
        model.addAttribute("userName",user.getUsername());
        return "User";
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String Username, @RequestParam("password") String Password, ModelMap model) {
        String User = Username;
        String password = Password;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = usuarioRepository.findByUsername(User);
        if (user == null) {
            user = usuarioRepository.findByEmail(User);
        }
        if (user != null) {
            if (password.equals(user.getPassword())) {
                model.addAttribute("user", user);
                model.addAttribute("userName",User);
            }
            else{
                showMessageDialog(null, "Incorrect Password");
            }
        }
        return "User";
    }

}
