package org.wcci.blog.integrationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.entities.Author;
import org.wcci.blog.entities.Blog;
import org.wcci.blog.entities.Category;
import org.wcci.blog.entities.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.BlogRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private BlogRepository blogRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void categoryShouldHaveMultipleBlogs() {
        Category testCategory = new Category("Sports Blogs");
        categoryRepo.save(testCategory);
        Author testAuthor = new Author("John Doe");
        authorRepo.save(testAuthor);
        Blog testBlog1 = new Blog("Sport Blog", "Stuff about sports", testAuthor, 25062020, testCategory);
        blogRepo.save(testBlog1);
        Blog testBlog2 = new Blog("Movie Blog", "Stuff about a movie", testAuthor, 25062020, testCategory);
        blogRepo.save(testBlog2);

        entityManager.flush();
        entityManager.clear();

        Optional<Category> retrievedCategoryOptional = categoryRepo.findById(testCategory.getId());
        Category retrievedCategory = retrievedCategoryOptional.get();

        assertThat(retrievedCategory).isEqualTo(testCategory);
        assertThat(retrievedCategory.getBlogs()).containsExactlyInAnyOrder(testBlog1, testBlog2);
    }

    @Test
    public void authorCanHaveMultipleBlogs() {
        Author testAuthor1 = new Author("John Doe");
        authorRepo.save(testAuthor1);
        Category testCategory = new Category("Sports Blogs");
        categoryRepo.save(testCategory);
        Blog testBlog1 = new Blog("Sport Blog", "Stuff about sports", testAuthor1, 25062020, testCategory);
        blogRepo.save(testBlog1);
        Blog testBlog2 = new Blog("Movie Blog", "Stuff about a movie", testAuthor1, 22062020, testCategory);
        blogRepo.save(testBlog2);
        Blog testBlog3 = new Blog("Book Blog", "Stuff about a book", testAuthor1, 02062020, testCategory);
        blogRepo.save(testBlog3);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> retrievedAuthorOptional = authorRepo.findById(testAuthor1.getId());
        Author retrievedAuthor = retrievedAuthorOptional.get();

        assertThat(retrievedAuthor).isEqualTo(testAuthor1);
        assertThat(retrievedAuthor.getBlogs()).containsExactlyInAnyOrder(testBlog1, testBlog2, testBlog3);
    }

    @Test
    public void tagsShouldHaveMultipleBlogs(){
        Tag testTag1 = new Tag("fun");
        tagRepo.save(testTag1);
        Tag testTag2 = new Tag("cool");
        tagRepo.save(testTag2);
        Author testAuthor1 = new Author("John Doe");
        authorRepo.save(testAuthor1);
        Category testCategory = new Category("Sports Blogs");
        categoryRepo.save(testCategory);
        Blog testBlog1 = new Blog("Sport Blog", "Stuff about sports", testAuthor1, 25062020, testCategory,testTag1);
        blogRepo.save(testBlog1);
        Blog testBlog2 = new Blog("Movie Blog", "Stuff about a movie", testAuthor1, 22062020, testCategory, testTag2);
        blogRepo.save(testBlog2);
        Blog testBlog3 = new Blog("Book Blog", "Stuff about a book", testAuthor1, 02062020, testCategory, testTag1, testTag2);
        blogRepo.save(testBlog3);

        entityManager.flush();
        entityManager.clear();

        Tag retrievedTag1 = tagRepo.findById(testTag1.getId()).get();
        assertThat(retrievedTag1).isEqualTo(testTag1);
        assertThat(retrievedTag1.getBlogs()).containsExactlyInAnyOrder(testBlog1,testBlog3);
        Tag retrievedTag2 = tagRepo.findById(testTag2.getId()).get();
        assertThat(retrievedTag2).isEqualTo(testTag2);
        assertThat(retrievedTag2.getBlogs()).containsExactlyInAnyOrder(testBlog2, testBlog3);
    }
}
