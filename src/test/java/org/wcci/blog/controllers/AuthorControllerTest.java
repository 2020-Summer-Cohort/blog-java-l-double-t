package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.storage.AuthorStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AuthorControllerTest {
    private AuthorStorage mockAuthorStorage;
    private AuthorController underTest;
    private Model model;

    @BeforeEach
    void setUp(){
        mockAuthorStorage = mock(AuthorStorage.class);
        underTest = new AuthorController(mockAuthorStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void showAllAuthorsShouldShowAllAuthors(){
        String templateName = underTest.showAllAuthors(model);
        assertThat(templateName).isEqualTo("authors-template");
    }

    @Test
    public void showASingleAuthorShowSingleAuthor(){
        String templateName = underTest.showSingleAuthor("Trey Grace", model);
        assertThat(templateName).isEqualTo("author-template");
    }
}
