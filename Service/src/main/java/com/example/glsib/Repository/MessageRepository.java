package com.example.glsib.Repository;


import com.example.glsib.Entite.FeedBack;
import com.example.glsib.Entite.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
