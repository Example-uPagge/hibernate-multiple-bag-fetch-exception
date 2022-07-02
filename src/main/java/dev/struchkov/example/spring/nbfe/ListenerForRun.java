package dev.struchkov.example.spring.nbfe;

import dev.struchkov.example.spring.nbfe.domain.Post;
import dev.struchkov.example.spring.nbfe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListenerForRun implements ApplicationRunner {

    private final PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<Post> allWithCommentsAndTags = postService.findAllWithCommentsAndTags(1, 50);
        System.out.println();
    }

}
