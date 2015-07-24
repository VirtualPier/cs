package org.ligson.coderstar2.system.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.question.domains.Question;

import java.util.List;

/**
 * Created by ligson on 2015/7/23.
 */
public interface FullTextSearchService {
    public void indexArticle(Article article);

    public void indexQuestion(Question question);

    public void removeArticle(Article article);

    public void removeQuestion(Question question);

    public void updateArticle(Article article);

    public void updateQuestion(Question question);

    public List<Question> relatedQuestion(Question question, int max);

    public List<Article> relatedArticle(Article question, int max);

    public List<String> hotQuestionKey(String key, int max);

    public List<String> hotArticleKey(String key, int max);


}
