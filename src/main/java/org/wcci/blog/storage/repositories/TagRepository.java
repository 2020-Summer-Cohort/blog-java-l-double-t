package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
