package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
