package dev.struchkov.example.hibernate.nbfe.no_fix_set;

import dev.struchkov.example.hibernate.nbfe.no_fix_set.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class NoFixSetMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Blog");
        final EntityManager entityManager = emf.createEntityManager();

        final long startTime = System.currentTimeMillis();
        final List<Post> posts = entityManager.createQuery(
                        """
                                SELECT p
                                FROM Post p
                                LEFT JOIN FETCH p.comments
                                LEFT JOIN FETCH p.tags
                                WHERE p.id BETWEEN :minId AND :maxId
                                """, Post.class
                )
                .setParameter("minId", 1L)
                .setParameter("maxId", 50L)
                .getResultList();

        final long finishTime = System.currentTimeMillis();
        System.out.println("Performance: " + (startTime - finishTime));
    }

}
