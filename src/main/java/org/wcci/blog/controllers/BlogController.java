package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.storage.BlogStorage;

import java.time.LocalDate;

@Controller
public class BlogController {
    private BlogStorage blogStorage;

    public BlogController(BlogStorage blogStorage) {
        this.blogStorage = blogStorage;
    }

    @GetMapping("blog/{blogTitle}")
    public String showSingleBlog(@PathVariable String blogTitle, Model model) {
        model.addAttribute("blog",blogStorage.findBlogByTitle(blogTitle));
        return "blog-template";
    }
}
