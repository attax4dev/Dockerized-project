package com.example.glsib.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String[] PUBLIC_ENDPOINTS={
            "/api/provider/list-provider",
            "/api/provider/add-provider",
            "/api/auth/signup",
            "/api/auth/signupprovaider",
            "/api/service/list-Service",
            "/api/service/delete-Service/{idService}",
            "/api/service/add-Service",
            "/api/service/list-Service",
             "/api/city/list-city",
            "/api/city/add-city",
            "/api/user/list-User",
            "/api/user/validate-user/{iduser}",
            "/api/user/delete-user/{iduser}",
            "/api/user/getUser/{idUser}",
            "/v2/api-docs",
            "/configuration/ui",
            "/ -resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/index.html",
            "/swagger-resources",
            "/configuration/security",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   http
        .cors().and().csrf().disable()
        .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
        .authorizeRequests()
           .antMatchers(PUBLIC_ENDPOINTS).permitAll()
              .anyRequest().authenticated()
           .and()
   .httpBasic();
}
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
