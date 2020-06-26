package org.wcci.blog.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class HomeControllerTest {
    private AuthorStorage mockAuthorStorage;
    private CategoryStorage mockCategoryStorage;
    private TagStorage mockTagStorage;
    private HomeController underTest;
    private Model model;

    @Test
    public void homeControllerShouldReturnHomeTemplateWhenAsked(){
        mockAuthorStorage = mock(AuthorStorage.class);
        mockCategoryStorage = mock(CategoryStorage.class);
        mockTagStorage = mock(TagStorage.class);
        underTest = new HomeController(mockTagStorage, mockCategoryStorage, mockAuthorStorage);
        model = Mockito.mock(Model.class);
        String templateName = underTest.showHomePage(model);
        assertThat(templateName).isEqualTo("home-template");
    }
}
