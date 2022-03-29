package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(Post.of(posts.size(), "Продаю машину ладу 01.", "Когда-нибудь"));
    }

    public List<Post> getAll() {
        return posts;
    }

    public void create(Post post) {
        post.setId(posts.size());
        post.setCreated(Calendar.getInstance());
        posts.add(post);
    }

    public void edit(Post post) {
        post.setCreated(Calendar.getInstance());
        posts.set(post.getId(), post);
    }
}
