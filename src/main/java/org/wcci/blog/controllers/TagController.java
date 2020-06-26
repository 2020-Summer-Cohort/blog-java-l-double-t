package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wcci.blog.storage.TagStorage;

@Controller
public class TagController {
    private TagStorage tagStorage;

    public TagController(TagStorage tagStorage) {
        this.tagStorage = tagStorage;
    }

    @GetMapping("tags")
    public String showAllTags(Model model) {
        model.addAttribute("tags",tagStorage.findAllTags());
        return "tags-template";
    }

    @GetMapping("tags/{tagName}")
    public String showASingleTag(@PathVariable String tagName, Model model) {
        model.addAttribute("tag", tagStorage.findTagByTagName(tagName));
        return "tag-template";
    }

}
