package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.entities.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryStorageTest {
    private CategoryRepository categoryRepo;
    private CategoryStorage underTest;

    @BeforeEach
    void setUp(){
        categoryRepo = mock(CategoryRepository.class);
        underTest = new CategoryStorage(categoryRepo);
    }

    @Test
    public void shouldFindByCategoryName(){
        when(categoryRepo.findByName("Sports")).thenReturn(Optional.of(new Category("Sports")));
        Category result = underTest.findCategoryByName("Sports");
        assertThat(result.getCategoryName()).isEqualTo("Sports");
    }

    @Test
    public void shouldBeAbleToAddCategory(){
        Category testCategory = new Category("Sports");
        underTest.addCategory(testCategory);
        verify(categoryRepo).save(testCategory);
    }
}
