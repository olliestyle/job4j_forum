package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String create() {
        return "post";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        postService.create(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Post post) {
        postService.edit(post);
        return "redirect:/";
    }
}
