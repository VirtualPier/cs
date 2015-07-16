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

    private Article article;

    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "article_id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(name = "category_id")
    public Category getLanguage() {
        return category;
    }

    public void setLanguage(Category category) {
        this.category = category;
    }
}
