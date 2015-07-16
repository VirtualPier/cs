package org.ligson.coderstar2.article.domains;

import javax.persistence.*;

/**
 * Created by Ruby on 2015/7/16.
 */
@Entity
@Table(name="article_language")
public class ArticleLanguage {
    private long id;

    private Article article;

    private Language language;

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

    @Column(name = "language_id")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
