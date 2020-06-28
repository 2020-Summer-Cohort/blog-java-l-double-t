package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class BlogController {
    private BlogStorage blogStorage;
    private TagStorage tagStorage;
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;

    public BlogController(BlogStorage blogStorage, TagStorage tagStorage, CategoryStorage categoryStorage, AuthorStorage authorStorage) {
        this.blogStorage = blogStorage;
        this.tagStorage = tagStorage;
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
    }

    @GetMapping("blog/{blogTitle}")
    public String showSingleBlog(@PathVariable String blogTitle, Model model) {
        model.addAttribute("blog", blogStorage.findBlogByBlogTitle(blogTitle));
        return "blog-template";
    }

    @GetMapping("addablog")
    public String addABlog(Model model) {
        model.addAttribute("categories",categoryStorage.findAllCategories());
        model.addAttribute("authors", authorStorage.findAllAuthors());
        return "add-blog-template";
    }

    @PostMapping("/blog/addablogpost")
    public String addABlogPost(String blogTitle, String blogBody, String name, String publishDate, String categoryName){
        Author authorToBlog = authorStorage.findAuthorByName(name);
        Category categoryToBlog = categoryStorage.findCategoryByName(categoryName);
        Blog blogToAdd = new Blog(blogTitle,blogBody,authorToBlog,publishDate,categoryToBlog);
        blogStorage.addBlog(blogToAdd);
        return "redirect:/blog/" + blogTitle;
    }

    @PostMapping("/blog/addTag")
    public String addATagToBlog(String tagName, String blogTitle) {
        if (tagStorage.tagExists(tagName)) {
            Tag tagToAdd = tagStorage.findTagByTagName(tagName);
            Blog blogToUpdate = blogStorage.findBlogByBlogTitle(blogTitle);
            blogToUpdate.addTag(tagToAdd);
            blogStorage.addBlog(blogToUpdate);
            return "redirect:/blog/" + blogTitle;
        }
        Tag tagToAdd = new Tag(tagName);
        tagStorage.addTag(tagToAdd);
        Blog blogToUpdate = blogStorage.findBlogByBlogTitle(blogTitle);
        blogToUpdate.addTag(tagToAdd);
        blogStorage.addBlog(blogToUpdate);
        return "redirect:/blog/" + blogTitle;
    }
}
