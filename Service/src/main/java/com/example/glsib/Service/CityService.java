package com.example.glsib.Service;

import com.example.glsib.Entite.City;
import com.example.glsib.Entite.Comment;
import com.example.glsib.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CityService {

@Autowired
    CityRepository cityRepository;

    public City addCity(City s1){
        City savedCity = cityRepository.save(s1);
        return savedCity;
    }
    public City deleteCity(Long id){
        Optional<City> city = cityRepository.findById(id);
        if(city.isPresent()){
            return city.get();
        }else
        {
            return null;
        }
    }
    public List<City> getAllCity(){
        return  cityRepository.findAll();

    }
}
