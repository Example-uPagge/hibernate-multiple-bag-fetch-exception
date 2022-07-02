package dev.struchkov.example.hibernate.nbfe.problem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
