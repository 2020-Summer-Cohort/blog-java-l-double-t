package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TagStorageTest {
    private TagRepository tagRepo;
    private TagStorage underTest;

    @BeforeEach
    void setUp(){
        tagRepo = mock(TagRepository.class);
        underTest = new TagStorage(tagRepo);
    }

    @Test
    public void shouldFindTagByName(){
        when(tagRepo.findTagByTagName("sports")).thenReturn(Optional.of(new Tag("sports")));
        Tag result = underTest.findTagByTagName("sports");
        assertThat(result.getTagName()).isEqualTo("sports");
    }

    @Test
    public void shouldBeAbleToAddATag(){
        Tag testTag = new Tag("sports");
        underTest.addTag(testTag);
        verify(tagRepo).save(testTag);
    }

}
