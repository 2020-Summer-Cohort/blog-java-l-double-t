package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
