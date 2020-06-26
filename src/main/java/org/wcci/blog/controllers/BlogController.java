package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
public class BlogController {
    private BlogStorage blogStorage;
    private TagStorage tagStorage;

    public BlogController(BlogStorage blogStorage, TagStorage tagStorage) {
        this.blogStorage = blogStorage;
        this.tagStorage = tagStorage;
    }

    @GetMapping("blog/{blogTitle}")
    public String showSingleBlog(@PathVariable String blogTitle, Model model) {
        model.addAttribute("blog", blogStorage.findBlogByBlogTitle(blogTitle));
        return "blog-template";
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
