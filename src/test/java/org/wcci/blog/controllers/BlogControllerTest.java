package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class BlogControllerTest {
    private BlogStorage mockBlogStorage;
    private TagStorage mockTagStorage;
    private BlogController underTest;
    private Model model;

    @BeforeEach
    void setUp(){
        mockBlogStorage = mock(BlogStorage.class);
        mockTagStorage = mock(TagStorage.class);
        underTest = new BlogController(mockBlogStorage, mockTagStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void showBlogShouldShowSingleBlog(){
        String templateName = underTest.showSingleBlog("Blog Title", model);
        assertThat(templateName).isEqualTo("blog-template");
    }

}
