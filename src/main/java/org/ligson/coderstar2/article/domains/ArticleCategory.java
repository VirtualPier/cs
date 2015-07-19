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

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
