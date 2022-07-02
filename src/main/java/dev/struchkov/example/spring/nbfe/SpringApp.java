package dev.struchkov.example.spring.nbfe;

import dev.struchkov.example.spring.nbfe.domain.Post;
import dev.struchkov.example.spring.nbfe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringApp {

    private final PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }

    @EventListener
    public void run(ContextStartedEvent event) {
        final long start = System.currentTimeMillis();
        final List<Post> allWithCommentsAndTags = postRepository.findAllWithCommentsAndTags(1, 50);
        final long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

}
