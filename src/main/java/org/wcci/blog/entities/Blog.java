package org.wcci.blog.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String blogTitle;
    private String blogBody;
    @ManyToOne
    private Author author;
    private String publishDate;
    @ManyToOne
    private Category category;
    @ManyToMany
    private Collection<Tag> tags;

    protected Blog(){}

    public Blog(String blogTitle, String blogBody, Author author, String publishDate, Category category, Tag... tags) {
        this.blogTitle = blogTitle;
        this.blogBody = blogBody;
        this.author = author;
        this.publishDate = publishDate;
        this.category = category;
        this.tags = new ArrayList<>(Arrays.asList(tags));
          }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public Author getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return publishDate == blog.publishDate &&
                Objects.equals(id, blog.id) &&
                Objects.equals(blogTitle, blog.blogTitle) &&
                Objects.equals(blogBody, blog.blogBody) &&
                Objects.equals(author, blog.author) &&
                Objects.equals(category, blog.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogTitle, blogBody, author, publishDate, category);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogBody='" + blogBody + '\'' +
                ", author=" + author +
                ", publishDate=" + publishDate +
                ", category=" + category +
                '}';
    }

    public void addTag(Tag tagName) {
        tags.add(tagName);
    }
}
