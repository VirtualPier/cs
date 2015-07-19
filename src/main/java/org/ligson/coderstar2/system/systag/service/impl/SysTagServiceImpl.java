package org.ligson.coderstar2.system.systag.service.impl;

import org.ligson.coderstar2.article.articletag.dao.ArticleTagDao;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.ArticleTag;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.QuestionTag;
import org.ligson.coderstar2.question.questiontag.dao.QuestionTagDao;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/20.
 */
public class SysTagServiceImpl implements SysTagService {
    private SysTagDao sysTagDao;
    private ArticleTagDao articleTagDao;
    private QuestionTagDao questionTagDao;

    public SysTagDao getSysTagDao() {
        return sysTagDao;
    }

    public void setSysTagDao(SysTagDao sysTagDao) {
        this.sysTagDao = sysTagDao;
    }

    public ArticleTagDao getArticleTagDao() {
        return articleTagDao;
    }

    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    public QuestionTagDao getQuestionTagDao() {
        return questionTagDao;
    }

    public void setQuestionTagDao(QuestionTagDao questionTagDao) {
        this.questionTagDao = questionTagDao;
    }

    @Override
    public ArticleTag addArticleTag(User creator, Article article, String tagName) {
        SysTag sysTag = sysTagDao.findBy("name", tagName);
        if (sysTag == null) {
            sysTag = new SysTag();
            sysTag.setCreator(creator);
            sysTag.setName(tagName);
        } else {
            sysTag.setArticleNum(sysTag.getQuestionNum() + 1);
        }
        sysTagDao.saveOrUpdate(sysTag);
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticle(article);
        articleTag.setTag(sysTag);
        articleTagDao.saveOrUpdate(articleTag);
        return articleTag;
    }

    @Override
    public QuestionTag addQuestionTag(User creator, Question question, String tagName) {
        SysTag sysTag = sysTagDao.findBy("name", tagName);
        if (sysTag == null) {
            sysTag = new SysTag();
            sysTag.setCreator(creator);
            sysTag.setName(tagName);
        } else {
            sysTag.setArticleNum(sysTag.getQuestionNum() + 1);
        }
        sysTagDao.saveOrUpdate(sysTag);
        QuestionTag questionTag = new QuestionTag();
        questionTag.setQuestion(question);
        questionTag.setTag(sysTag);
        questionTagDao.saveOrUpdate(questionTag);
        return questionTag;
    }

    @Override
    public List<List> hotsTag(int max) {
        return sysTagDao.findHotTag(max);
    }

    @Override
    public List<SysTag> findByArticle(Article article) {
        return sysTagDao.findAllByArticle(article);
    }
}
