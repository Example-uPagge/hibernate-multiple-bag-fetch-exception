package dev.struchkov.example.spring.nbfe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PostComment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    private String review;

}

