package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        return (List) postRepository.findAll();
    }

    public void create(Post post) {
        post.setCreated(Calendar.getInstance());
        postRepository.save(post);
    }

    public void edit(Post post) {
        post.setCreated(Calendar.getInstance());
        postRepository.save(post);
    }
}
