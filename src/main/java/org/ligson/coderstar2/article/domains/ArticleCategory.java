package org.ligson.coderstar2.article.domains;

import org.ligson.coderstar2.system.domains.Category;

import javax.persistence.*;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory {
    private long id;

    private long articleId;

    private long categoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, name = "article_id")
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
