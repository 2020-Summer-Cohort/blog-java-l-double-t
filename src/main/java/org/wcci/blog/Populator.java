package org.wcci.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.BlogRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.TagRepository;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    BlogRepository blogRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    TagRepository tagRepo;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category("Sports");
        Category category2 = new Category("Cars");
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        Author trey = new Author("Trey Grace");
        Author lT = new Author("LT Humphries");
        authorRepo.save(trey);
        authorRepo.save(lT);
        Tag tag = new Tag("cool story");
        tagRepo.save(tag);
        Blog blog1 = new Blog("Dak Prescott Signed his Franchise Tag","Dallas Cowboys finally signed his franchise tag. He is now set to play the 2020 season. Let's hope they come to a long term deal agreement.",trey,"2020-06-24" ,category1);
        Blog blog2 = new Blog("Trucks are cool","Trucks are cool. They can do a lot. And are helpful for a ton of projects.",trey,"2020-06-25",category2, tag);
        Blog blog3 = new Blog("Cadillacs ratings", "Cadillacs are great reliable cars that are smooth to drive and durable. great for a spin on any sunny day!",lT,"2020-06-25",category2, tag);
        Blog blog4 = new Blog("Braxton miller career", "What is he doing these day? Can anyone tell me what is going on with him these days", lT, "2020-06-25",category1);
        blogRepo.save(blog1);
        blogRepo.save(blog2);
        blogRepo.save(blog3);
        blogRepo.save(blog4);
    }
}
