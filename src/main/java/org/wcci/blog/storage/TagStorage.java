package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

@Service
public class TagStorage {
    private TagRepository tagRepo;

    public TagStorage(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    public TagRepository getTagRepo() {
        return tagRepo;
    }

    public Tag findTagByName(String tagName) {
        return tagRepo.findByTagName(tagName).get();
    }

    public void addTag(Tag tagToAdd) {
        tagRepo.save(tagToAdd);
    }
}
