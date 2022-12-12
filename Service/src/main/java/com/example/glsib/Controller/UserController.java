package com.example.glsib.Controller;

import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.User;
import com.example.glsib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/api/user")
@RestController
public class UserController {
@Autowired
    UserService userService;
    @GetMapping("/getUser/{idUser}")

    public User getUserById(@PathVariable Long idUser){
        return userService.getUserById(idUser);
    }


    @GetMapping("/list-User")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> ListUser() {return userService.getAllUser();}
    @DeleteMapping("/delete-user/{iduser}")
    public void deleteuser(@PathVariable("iduser") Long iduser) {
        userService.deleteUser(iduser);
    }

    @PutMapping("/validate-user/{iduser}")
    public void validInscription(@PathVariable("iduser") Long iduser) {
        userService.validInscription(iduser);
    }
}
