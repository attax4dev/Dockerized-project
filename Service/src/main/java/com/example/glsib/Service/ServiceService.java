package com.example.glsib.Service;

import com.example.glsib.Entite.Service;
import com.example.glsib.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceService  {

    @Autowired
    ServiceRepository serviceRepository;

    public Service addService(Service s1){
        Service savedService = serviceRepository.save(s1);
        return savedService;
    }
    public Service deleteService(Long id){
        Optional<Service>  service = serviceRepository.findById(id);
        if(service.isPresent()){
            return service.get();
        }else
        {
            return null;
        }
    }
    public List<Service> gettAllService(){
        return serviceRepository.findAll();
    }

}
