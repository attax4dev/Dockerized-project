package com.example.glsib.Controller;

import com.example.glsib.Entite.City;
import com.example.glsib.Entite.Comment;
import com.example.glsib.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/city")
@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/list-city")
    @PreAuthorize("hasRole('USER')")
    public List<City> ListCity() {return cityService.getAllCity();}

    @DeleteMapping("/delete-city/{idcity}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletecity(@PathVariable("idLevel") Long idcity) {
        cityService.deleteCity(idcity);
    }

    @PostMapping("/add-city")
    @PreAuthorize("hasRole('ADMIN')")
    public City addCity(@RequestBody @Validated City c1) {
        return cityService.addCity(c1);
    }
}
