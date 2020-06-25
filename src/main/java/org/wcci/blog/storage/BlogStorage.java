package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.storage.repositories.BlogRepository;

@Service
public class BlogStorage {
    private BlogRepository blogRepo;

    public BlogStorage(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    public Blog findBlogByTitle(String blogTitle) {
        return blogRepo.findByBlogTitle(blogTitle).get();
    }

    public BlogRepository getBlogRepo() {
        return blogRepo;
    }

    public void addBlog(Blog testBlog) {
        blogRepo.save(testBlog);
    }
}
