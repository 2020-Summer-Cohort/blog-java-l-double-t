package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthorStorageTest {
    private AuthorRepository authorRepo;
    private AuthorStorage underTest;

    @BeforeEach
    void SetUp(){
        authorRepo = mock(AuthorRepository.class);
        underTest = new AuthorStorage(authorRepo);
    }

    @Test
    public void shouldFindAuthorByName(){
        when(authorRepo.findByName("John Doe")).thenReturn(Optional.of(new Author("John Doe")));
        Author result = underTest.findAuthorByName("John Doe");
        assertThat(result.getName()).isEqualTo("John Doe");
    }

    @Test
    public void shouldAddAnAuthor(){
        Author testAuthor = new Author("John Doe");
        underTest.addAuthor(testAuthor);
        verify(authorRepo).save(testAuthor);
    }
}
