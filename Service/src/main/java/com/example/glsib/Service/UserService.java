package com.example.glsib.Service;

import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.User;
import com.example.glsib.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailSenderService mailSending;



    public List<User> getAllUser() {
        return  userRepository.findAll();
    }


    public User getUserById (Long idProvider){
        return  userRepository.findById(idProvider).orElseThrow(()-> new IllegalArgumentException("Provider ID not Found"));
    }
    public User deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else
        {
            return null;
        }
    }



    public void validInscription(Long id) {
        Optional<User> user=userRepository.findById(id);
        User user1=user.get();
        String Newligne = System.getProperty("line.separator");
        String url = "http://localhost:4200/auth/verification/" +user1.getToken();
        String body = "Welcom to our platform \n  use this link to verify your account is :" + Newligne + url;
        if(user.isPresent()) {

            user1.setIsVerified(true);
            this.userRepository.save(user1);
            try {
                mailSending.send(user1.getEmail(), "Welcome Provaider", body);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
