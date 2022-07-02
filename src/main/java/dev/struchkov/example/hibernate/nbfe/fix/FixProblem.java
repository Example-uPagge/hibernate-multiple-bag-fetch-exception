package dev.struchkov.example.hibernate.nbfe.fix;

import dev.struchkov.example.hibernate.nbfe.fix.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.annotations.QueryHints;

import java.util.List;

public class FixProblem {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Blog");
        final EntityManager entityManager = emf.createEntityManager();

        final long startTime = System.currentTimeMillis();
        List<Post> posts = entityManager.createQuery("""
                        select distinct p
                        from Post p
                        left join fetch p.comments
                        where p.id between :minId and :maxId""", Post.class)
                .setParameter("minId", 1L)
                .setParameter("maxId", 50L)
                .setHint("PASS_DISTINCT_THROUGH", false)
                .getResultList();

        posts = entityManager.createQuery("""
                        select distinct p
                        from Post p
                        left join fetch p.tags t
                        where p in :posts""", Post.class)
                .setParameter("posts", posts)
                .setHint("PASS_DISTINCT_THROUGH", false)
                .getResultList();

        final long finishTime = System.currentTimeMillis();
        System.out.println("Performance: " + (finishTime - startTime));
    }

}
