package dev.struchkov.example.spring.nbfe.service;

import dev.struchkov.example.spring.nbfe.domain.Post;
import dev.struchkov.example.spring.nbfe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<Post> findAllWithCommentsAndTags(long minId, long maxId) {

        final List<Post> posts = postRepository.findAllWithComments(
                minId,
                maxId
        );

        return !posts.isEmpty() ?
                postRepository.findAllWithTags(
                        minId,
                        maxId
                ) :
                posts;
    }

}
