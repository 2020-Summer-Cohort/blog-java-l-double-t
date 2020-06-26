package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class HomeController {
    private TagStorage tagStorage;
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;

    public HomeController(TagStorage tagStorage, CategoryStorage categoryStorage, AuthorStorage authorStorage) {
        this.tagStorage = tagStorage;
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
    }

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("tags",tagStorage.findAllTags());
        model.addAttribute("categories",categoryStorage.findAllCategories());
        model.addAttribute("authors",authorStorage.findAllAuthors());
        return "home-template";
    }
}
