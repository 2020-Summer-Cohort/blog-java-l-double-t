package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.entities.Category;
import org.wcci.blog.storage.BlogStorage;
import org.wcci.blog.storage.repositories.BlogRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BlogStorageTest {
    private BlogRepository blogRepo;
    private BlogStorage underTest;

    @BeforeEach
    void setUp() {
        blogRepo = mock(BlogRepository.class);
        underTest = new BlogStorage(blogRepo);

    }

    @Test
    public void shouldFindBlogByTitle() {
        Author testAuthor = mock(Author.class);
        Category testCategory = mock(Category.class);
        when(blogRepo.findByBlogTitle("Title")).thenReturn(Optional.of(new Blog("Title", "", testAuthor, "56", testCategory)));
        Blog result = underTest.findBlogByTitle("Title");
        assertThat(result.getBlogTitle()).isEqualTo("Title");
    }

    @Test
    public void shouldAddABlog() {
        Author testAuthor = mock(Author.class);
        Category testCategory = mock(Category.class);
        Blog testBlog = new Blog("Title","",testAuthor,"52564",testCategory);
        underTest.addBlog(testBlog);
        verify(blogRepo).save(testBlog);
    }
}
