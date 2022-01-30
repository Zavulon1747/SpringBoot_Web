package com.example.spring_project_boot_first_web.controller;

import com.example.spring_project_boot_first_web.models.Post;
import com.example.spring_project_boot_first_web.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MyController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/ourProducts")
    public String ourProducts(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "ourProducts";
    }

    @GetMapping("/ourProducts/{id}")
    public String fullDescription(@PathVariable long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/ourProducts";
        }
        Optional<Post> post = postRepository.findById(id);
        model.addAttribute("kek",post.get());
        return "/fullDescription";
    }

    @GetMapping("/ourProducts/add")
    public String ourProductsAdd(Model model) {
        return "ourProducts-add";
    }

    @PostMapping("/ourProducts/add")
    public String ourProductsPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/ourProducts";
    }

    @PostMapping("/ourProducts/{id}/delete")
    public String ourProductPostDelete(@PathVariable long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/ourProducts";
    }

    @GetMapping("/ourProducts/{id}/edit")
    public String ourProductPostEdit(@PathVariable long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/ourProducts";
        }
        Optional<Post> post = postRepository.findById(id);
        model.addAttribute("kek",post.get());
        return "product-edit";
    }

    @PostMapping("/ourProducts/{id}/edit")
    public String ourProductsPostUpdate(@PathVariable long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/ourProducts";
    }

    @GetMapping("/supporting")
    public String support(Model model) {
        model.addAttribute("title", "Support page");
        return "supporting";
    }





}
