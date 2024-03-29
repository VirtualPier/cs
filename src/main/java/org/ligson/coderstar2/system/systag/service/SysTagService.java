package org.ligson.coderstar2.system.systag.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleTag;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionTag;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/20.
 */
public interface SysTagService {
    public ArticleTag addArticleTag(User creator, Article article, String tagName);

    public QuestionTag addQuestionTag(User creator, Question question, String tagName);

    public List<List> hotsTag(int max);

    public List<SysTag> findByArticle(Article article);

    public List<SysTag> findUserGoodTag(User user, int max);

    public void deleteTagByArticle(Article article);

    public void deleteTagByQuestion(Question question);

    public List<SysTag> questionHotTags(int max);
}
