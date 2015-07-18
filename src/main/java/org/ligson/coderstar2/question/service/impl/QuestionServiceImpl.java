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
    public Map<String, Object> createQuestion(User user, String title, String description, String tags, long[] languageIds, double money) {
        return null;
    }

    @Override
    public Map<String, Object> createLanguage(Map params) {
        return null;
    }

    @Override
    public Map<String, Object> modifyLanguage(Map params) {
        return null;
    }

    @Override
    public Map<String, Object> deleteLanguage(Map params) {
        return null;
    }

    @Override
    public Map<String, Object> searchQuestion(String sort, String order, long offset, long max, String tagName, long languageId, String title) {
        return null;
    }

    @Override
    public Map<String, Object> saveAsk(long questionId, String content) {
        return null;
    }

    @Override
    public Map<String, Object> rateAsk(long askId, String upOrDown) {
        return null;
    }

    @Override
    public Map<String, Object> deleteQuestion(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Question question = questionDao.getById(ids[i]);
            deleteTagByQuestion(question);
            deleteCategoryByQuestion(question);
            deleteAskByQuestion(question);
            questionDao.delete(question);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public boolean deleteTagByQuestion(Question question) {
        return false;
    }

    @Override
    public boolean deleteCategoryByQuestion(Question question) {
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
    public Map<String, Object> modifyDescription(long questionId, String description) {
        return null;
    }

    @Override
    public Map<String, Object> attentionQuestion(User user, long questionId) {
        return null;
    }

    @Override
    public Map<String, Object> searchRelatedQuestion(int max, int offset, long questionId) {
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

    /***
     * 前台搜索
     *
     * @param hasDeal 是否已解决
     * @param sort    排序字段可是:createDate,viewNum,money,replyNum,attentionNum
     * @param max     每页大小
     * @param offset  开始位置
     * @return 格式:[success:true/false,questionList:questionList,total:total]
     */
    @Override
    public Map<String, Object> searchQuestion(boolean hasDeal, String sort, int max, int offset) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(questionDao.getById(10));
        List<Question> questionList = questionDao.findByRightAskIsNullOrderBy(hasDeal, sort, max, offset);
        int total = questionDao.countByRightAskIsNullOrderBy(hasDeal, sort);
        result.put("questionList", questionList);
        result.put("total", total);
        return result;
    }
}
