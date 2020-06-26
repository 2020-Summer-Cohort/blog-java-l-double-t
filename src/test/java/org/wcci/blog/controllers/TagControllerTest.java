package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.blog.storage.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TagControllerTest {
    private TagStorage mockTagStorage;
    private TagController underTest;
    private Model model;

    @BeforeEach
    void setUp(){
        mockTagStorage = mock(TagStorage.class);
        underTest = new TagController(mockTagStorage);
        model = Mockito.mock(Model.class);
    }

    @Test
    public void showAllTagsShouldShowAllTags(){
        String templateName = underTest.showAllTags(model);
        assertThat(templateName).isEqualTo("tags-template");
    }

    @Test
    public void showSingleTagShouldShowSingleTag(){
        String templateName = underTest.showASingleTag("cool story", model);
        assertThat(templateName).isEqualTo("tag-template");
    }
}
