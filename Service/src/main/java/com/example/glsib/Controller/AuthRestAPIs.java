package com.example.glsib.Controller;
import com.example.glsib.Entite.Role;
import com.example.glsib.Entite.User;
import com.example.glsib.Repository.RoleRepository;
import com.example.glsib.Repository.UserRepository;
import com.example.glsib.Service.MailSenderService;
import com.example.glsib.dtos.RoleName;
import com.example.glsib.jwt.JwtProvider;
import com.example.glsib.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MailSenderService mailSending;

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
     @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user1)   {
        if(userRepository.existsByUsername(user1.getUsername())) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if(userRepository.existsByEmail(user1.getEmail())) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        User user = new User(user1.getName(),user1.getUsername(),user1.getEmail(),passwordEncoder.encode(user1.getPassword()),false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user.setRoles(roles);
        user.setIsVerified(false);
        User suser= userRepository.save(user);
        if(suser != null ) {
            String Newligne = System.getProperty("line.separator");
            String url = "http://localhost:4200/auth/verification/" + suser.getToken();
            String body = "Welcom to our platform \n  use this link to verify your account is :" + Newligne + url;
            try {
                mailSending.send(user.getEmail(), "Welcome", body);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }




    @RequestMapping(value = "/signupprovaider", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> registerProvaider(@Validated @RequestBody User user1)   {
        if(userRepository.existsByUsername(user1.getUsername())) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if(userRepository.existsByEmail(user1.getEmail())) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        User user = new User(user1.getName(),user1.getUsername(),user1.getEmail(),passwordEncoder.encode(user1.getPassword()),false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_PROVIDER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user.setRoles(roles);
        user.setIsVerified(false);
        User suser= userRepository.save(user);
        if(suser != null ) {
           // String Newligne = System.getProperty("line.separator");
           // String url = "http://localhost:4200/auth/verification/" + suser.getToken()    + Newligne + url;
            String body = "merci de votre inscription  \n vous attendre l'activation de administrateur :" ;
            try {
                mailSending.send(user.getEmail(), "Welcome", body);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }









    @RequestMapping(value="/getisverified/{token}",method = RequestMethod.GET)
    public ResponseEntity getisverified(@PathVariable String token){
        User user= userRepository.findByToken(token);
        if(user != null){
            user.setIsVerified(true);
            userRepository.save(user);
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }


    @RequestMapping(value = "/signupadmin", method = RequestMethod.POST)
    public ResponseEntity<User> registerAdmin(@Validated @RequestBody User user)  {
        if(userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User user1 = new User(user.getName(), user.getUsername(),
                user.getEmail(),passwordEncoder.encode(user.getPassword()),false);
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
        user1.setRoles(roles);
        userRepository.save(user1);
        return new ResponseEntity<User>(user1, HttpStatus.OK);
    }


}
