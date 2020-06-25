package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.entities.Blog;

import java.util.Optional;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    Optional<Blog> findByBlogTitle(String blogTitle);
}
