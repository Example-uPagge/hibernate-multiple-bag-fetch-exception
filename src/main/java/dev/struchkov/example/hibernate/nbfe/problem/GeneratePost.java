package dev.struchkov.example.hibernate.nbfe.problem;

import dev.struchkov.example.hibernate.nbfe.problem.domain.Post;
import dev.struchkov.example.hibernate.nbfe.problem.domain.PostComment;
import dev.struchkov.example.hibernate.nbfe.problem.domain.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class GeneratePost {

    public static void main(String[] args) {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Blog");
        final EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        for (long j = 0; j < 20; j++) {
            final Tag tag = new Tag();
            tag.setId(j);
            tag.setName("Tag " + j);
            entityManager.merge(tag);
        }

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        final List<Tag> tags = entityManager.createQuery("SELECT t FROM Tag t where t.id IN :id", Tag.class)
                .setParameter("id", LongStream.range(0, 19).boxed().collect(Collectors.toSet()))
                .getResultList();

        for (int i = 0; i < 50; i++) {
            final Post post = new Post();
            post.setTitle("Пост " + i);

            for (int j = 0; j < 1000; j++) {
                final PostComment postComment = new PostComment();
                postComment.setReview("Комментарий " + j);
                post.addComment(postComment);
            }

            tags.forEach(post::addTag);

            entityManager.merge(post);
        }

        entityManager.getTransaction().commit();

    }

}
