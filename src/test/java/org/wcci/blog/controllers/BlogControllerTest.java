package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class BlogControllerTest {
    private BlogStorage mockBlogStorage;
    private TagStorage mockTagStorage;
    private BlogController underTest;
    private AuthorStorage mockAuthorStorage;
    private CategoryStorage mockCategoryStorage;
    private Model model;

    @BeforeEach
    void setUp(){
        mockBlogStorage = mock(BlogStorage.class);
        mockTagStorage = mock(TagStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockCategoryStorage = mock(CategoryStorage.class);
        underTest = new BlogController(mockBlogStorage, mockTagStorage, mockCategoryStorage, mockAuthorStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void showBlogShouldShowSingleBlog(){
        String templateName = underTest.showSingleBlog("Blog Title", model);
        assertThat(templateName).isEqualTo("blog-template");
    }

    @Test
    public void addBlogShouldShowAddBlogTemplate(){
        String templateName = underTest.addABlog(model);
        assertThat(templateName).isEqualTo("add-blog-template");
    }

}
