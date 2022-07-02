package dev.struchkov.example.spring.nbfe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "POSTCOMMENT")
public class PostComment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    private String review;

}

