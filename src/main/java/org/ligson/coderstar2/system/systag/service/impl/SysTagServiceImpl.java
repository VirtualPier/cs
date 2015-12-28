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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
        List<ArticleTag> articleTags = articleTagDao.findAllByArticle(article);
        boolean isExist = false;
        ArticleTag articleTag = null;
        for (ArticleTag tag : articleTags) {
            if ((tag.getTag().getId() == sysTag.getId()) && (tag.getArticle().getId() == article.getId())) {
                articleTag = tag;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            articleTag = new ArticleTag();
            articleTag.setArticle(article);
            articleTag.setTag(sysTag);
            articleTagDao.saveOrUpdate(articleTag);
        }
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

        List<QuestionTag> questionList = questionTagDao.findAllByQuestion(question);
        boolean isExist = false;
        QuestionTag questionTag1 = null;
        for (QuestionTag questionTag : questionList) {
            if ((questionTag.getQuestion().getId() == question.getId()) && (sysTag.getId() == questionTag.getTag().getId())) {
                isExist = true;
                questionTag1 = questionTag;
                break;
            }
        }
        if (!isExist) {
            questionTag1 = new QuestionTag();
            questionTag1.setQuestion(question);
            questionTag1.setTag(sysTag);
            questionTagDao.saveOrUpdate(questionTag1);
        }
        return questionTag1;
    }

    @Override
    public List<List> hotsTag(int max) {
        return sysTagDao.findHotTag(max);
    }

    @Override
    public List<SysTag> findByArticle(Article article) {
        return sysTagDao.findAllByArticle(article);
    }

    @Override
    public List<SysTag> findUserGoodTag(User user, int max) {
        //dianzi
        return sysTagDao.findAllByCreator(user, max);
    }

    @Override
    public void deleteTagByArticle(Article article) {
        List<ArticleTag> articleTags = articleTagDao.findAllByArticle(article);
        for (ArticleTag articleTag : articleTags) {
            articleTagDao.delete(articleTag);
        }

    }

    @Override
    public void deleteTagByQuestion(Question question) {
        List<QuestionTag> questionTags = questionTagDao.findAllByQuestion(question);
        for (QuestionTag questionTag : questionTags) {
            questionTagDao.delete(questionTag);
        }
    }

    @Override
    public List<SysTag> questionHotTags(int max) {
        return sysTagDao.findQuestionHotTags(max);
    }

    @Override
    public Set<QuestionTag> findByQuestion(Question question) {
        return null;
    }
}
