package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

@Service
public class AuthorStorage {
    private AuthorRepository authorRepo;

    public AuthorStorage(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
   }

    public AuthorRepository getAuthorRepo() {
        return authorRepo;
    }

    public Author findAuthorByName(String name) {
        return authorRepo.findByName(name).get();
    }

    public void addAuthor(Author testAuthor) {
        authorRepo.save(testAuthor);
    }
}
