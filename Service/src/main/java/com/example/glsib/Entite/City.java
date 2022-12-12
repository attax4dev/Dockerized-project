package com.example.glsib.Entite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
  /*  @OneToMany( fetch = FetchType.LAZY , cascade= CascadeType.ALL)
    @JsonIgnoreProperties
    private List<User> user;*/
}
