package org.ligson.coderstar2.question.service.impl;

import org.ligson.coderstar2.question.ask.dao.AskDao;
import org.ligson.coderstar2.question.domains.*;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
import org.ligson.coderstar2.question.questiontag.dao.QuestionTagDao;
import org.ligson.coderstar2.question.rate.dao.RateDao;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ligson on 2015/7/17.
 */
public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    private QuestionTagDao questionTagDao;

    private QuestionCategoryDao questionCategoryDao;

    private CategoryDao categoryDao;

    private AskDao askDao;

    private RateDao rateDao;

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public QuestionTagDao getQuestionTagDao() {
        return questionTagDao;
    }

    public void setQuestionTagDao(QuestionTagDao questionTagDao) {
        this.questionTagDao = questionTagDao;
    }

    public QuestionCategoryDao getQuestionCategoryDao() {
        return questionCategoryDao;
    }

    public void setQuestionCategoryDao(QuestionCategoryDao questionCategoryDao) {
        this.questionCategoryDao = questionCategoryDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void setAskDao(AskDao askDao) {
        this.askDao = askDao;
    }

    public RateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
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
        Map<String, Object> result = new HashMap<>();
        if (ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                Question question = questionDao.findBy("id",ids[i]);
                if (question != null) {
                    if (deleteTagByQuestion(question)) {
                        if (deleteCategoryByQuestion(question) && deleteAskByQuestion(question)) {
                            //searchService.deleteQuestionById(question.id)
                            questionDao.delete(question);
                            result.put("success", true);
                            result.put("msg", "问题删除成功!");
                        }
                    }

                } else {
                    result.put("success", false);
                    result.put("msg", "问题删除失败!");
                }
            }
        } else {
            result.put("success", false);
            result.put("msg", "问题删除失败!");
        }
        return result;
    }

    @Override
    public boolean deleteTagByQuestion(Question question) {
        boolean isFlag = true;
        if (question != null) {
            List<QuestionTag> tagList = questionTagDao.findAllBy("question", question);
            if (tagList != null && tagList.size() > 0) {
                for (int i = 0; i < tagList.size(); i++) {
                    questionTagDao.delete(tagList.get(i));
                }
            }
        }
        return isFlag;
    }

    @Override
    public boolean deleteCategoryByQuestion(Question question) {
        boolean isFlag = true;
        if (question!=null) {
            List<Category> list = categoryDao.findAllBy("question", question);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Category category = list.get(i);
                    category.setQuestionNum(category.getQuestionNum() - 1);
                    categoryDao.saveOrUpdate(category);
                }
            }
        }
        return isFlag;
    }

    @Override
    public boolean deleteAskByQuestion(Question question) {
        boolean isFlag = true;
        if (question!=null) {
            List<Ask> list =askDao.findAllBy("question", question);
            if (list!=null && list.size() > 0) {
                for(int i=0;i<list.size();i++){
                    if (deleteRateByAsk(list.get(i))) {
                        try {
                            askDao.delete(list.get(i));
                        } catch (Exception e) {
                            isFlag = false;
                        }
                    }
                }
            }
        }
        return isFlag;
    }

    @Override
    public boolean deleteRateByAsk(Ask ask) {
        boolean isFlag = true;
        if (ask != null) {
            List<Rate> list = rateDao.findAllBy("ask", ask);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        rateDao.delete(list.get(i));
                    } catch (Exception E) {
                        isFlag = false;
                    }
                }
            }
        }
        return isFlag;
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




    @Override
    public Question findQuestionById(long id) {
        return questionDao.getById(id);
    }
}
