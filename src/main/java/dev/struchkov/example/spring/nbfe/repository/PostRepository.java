package dev.struchkov.example.spring.nbfe.repository;

import dev.struchkov.example.spring.nbfe.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        select distinct p
        from Post p
        left join fetch p.comments
        where p.id between :minId and :maxId
        """)
    List<Post> findAllWithComments(
            @Param("minId") long minId,
            @Param("maxId") long maxId
    );

    @Query("""
        select distinct p
        from Post p
        left join fetch p.tags
        where p.id between :minId and :maxId
        """)
    List<Post> findAllWithTags(
            @Param("minId") long minId,
            @Param("maxId") long maxId
    );

}
