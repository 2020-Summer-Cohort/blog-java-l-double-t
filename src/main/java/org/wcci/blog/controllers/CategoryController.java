package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.repositories.CategoryRepository;

@Controller
public class CategoryController {
    private CategoryStorage categoryStorage;

    public CategoryController(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    @GetMapping("categories")
    public String showAllCategories( Model model) {
        model.addAttribute("categories",categoryStorage.findAllCategories());
        return "categories-template";
    }

    @GetMapping("category/{categoryName}")
    public String showSingleCategory(@PathVariable String categoryName, Model model) {
        model.addAttribute("category", categoryStorage.findCategoryByName(categoryName));
        return "category-template";
    }

    @PostMapping("/addCategory")
    public String
}
