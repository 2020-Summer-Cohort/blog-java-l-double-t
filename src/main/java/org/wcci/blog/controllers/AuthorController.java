package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.AuthorStorage;

@Controller
public class AuthorController {
    private AuthorStorage authorStorage;

    public AuthorController(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    @GetMapping("authors")
    public String showAllAuthors(Model model) {
        model.addAttribute("authors", authorStorage.findAllAuthors());
        return "authors-template";
    }

    @GetMapping("/authors/{authorName}")
    public String showSingleAuthor(@PathVariable String authorName, Model model) {
        model.addAttribute("author",authorStorage.findAuthorByName(authorName));
        return "redirect:/authors";
    }

    @PostMapping("/addAuthor")
    public String addAnAuthor(String name){
        if(authorStorage.authorExists(name)){
            return "authors-template";
        }
        Author authorToAdd = new Author(name);
        authorStorage.addAuthor(authorToAdd);
        return "redirect:/authors";
    }
}
