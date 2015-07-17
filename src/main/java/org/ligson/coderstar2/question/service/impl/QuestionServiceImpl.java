package org.ligson.coderstar2.question.service.impl;

import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Map createQuestion(User user, String title, String description, String tags, long languageId, double money) {
        return null;
    }

    @Override
    public Map createLanguage(Map params) {
        return null;
    }

    @Override
    public Map modifyLanguage(Map params) {
        return null;
    }

    @Override
    public Map deleteLanguage(Map params) {
        return null;
    }

    @Override
    public Map searchQuestion(String sort, String order, long offset, long max, String tagName, long languageId, String title) {
        return null;
    }

    @Override
    public Map saveAsk(long questionId, String content) {
        return null;
    }

    @Override
    public Map rateAsk(long askId, String upOrDown) {
        return null;
    }

    @Override
    public Map deleteQuestion(String id) {
        return null;
    }

    @Override
    public boolean deleteTagByQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteLanguageByQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteAskByQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteRateByAsk(Ask ask) {
        return false;
    }

    @Override
    public Map modifyDescription(long questionId, String description) {
        return null;
    }

    @Override
    public Map attentionQuestion(User user, long questionId) {
        return null;
    }

    @Override
    public Map searchRelatedQuestion(int max, int offset, long questionId) {
        return null;
    }

    @Override
    public Map<String, Object> questionList(int offset, int max) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Question> questions = questionDao.list(offset, max);
        int total = questionDao.countAll();
        result.put("rows", questions);
        result.put("total", total);
        return result;
    }
}
