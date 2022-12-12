package com.example.glsib.Repository;

import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
