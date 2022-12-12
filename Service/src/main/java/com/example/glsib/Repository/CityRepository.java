package com.example.glsib.Repository;

import com.example.glsib.Entite.City;
import com.example.glsib.Entite.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
