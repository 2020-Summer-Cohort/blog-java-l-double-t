package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.repositories.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CategoryControllerTest {
    private CategoryStorage mockCategoryStorage;
    private CategoryController underTest;
    private Model model;

    @BeforeEach
    void setUp(){
        mockCategoryStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(mockCategoryStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void showSingleCategoryAsksCategoryStorageForSports(){
        String templateName = underTest.showSingleCategory("Sports", model);
        assertThat(templateName).isEqualTo("category-template");
    }


}
