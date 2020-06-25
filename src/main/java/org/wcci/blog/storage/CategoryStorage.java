package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.entities.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

@Service
public class CategoryStorage {
    private CategoryRepository categoryRepo;

    public CategoryStorage(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public CategoryRepository getCategoryRepo() {
        return categoryRepo;
    }

    public Category findCategoryByName(String categoryName) {
        return categoryRepo.findByName(categoryName).get();
    }

    public void addCategory(Category categoryName) {
        categoryRepo.save(categoryName);
    }
}
