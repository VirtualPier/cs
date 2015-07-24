package org.ligson.coderstar2.question.service.impl;

import org.apache.log4j.Logger;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.ask.dao.AskDao;
import org.ligson.coderstar2.question.attentionquestion.dao.AttentionQuestionDao;
import org.ligson.coderstar2.question.domains.*;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.question.questioncategory.dao.QuestionCategoryDao;
import org.ligson.coderstar2.question.questiontag.dao.QuestionTagDao;
import org.ligson.coderstar2.question.rate.dao.RateDao;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.service.FullTextSearchService;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.dao.UserDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.*;

/**
 * Created by ligson on 2015/7/17.
 */
public class QuestionServiceImpl implements QuestionService {
    private static Logger logger = Logger.getLogger(QuestionServiceImpl.class);
    private QuestionDao questionDao;

    private QuestionTagDao questionTagDao;

    private QuestionCategoryDao questionCategoryDao;

    private CategoryDao categoryDao;

    private AskDao askDao;

    private RateDao rateDao;
    private UserDao userDao;
    private CategoryService categoryService;

    private AttentionQuestionDao attentionQuestionDao;
    private SysTagDao sysTagDao;
    private PayService payService;
    private SysTagService sysTagService;
    private FullTextSearchService fullTextSearchService;

    public FullTextSearchService getFullTextSearchService() {
        return fullTextSearchService;
    }

    public void setFullTextSearchService(FullTextSearchService fullTextSearchService) {
        this.fullTextSearchService = fullTextSearchService;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        QuestionServiceImpl.logger = logger;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public SysTagService getSysTagService() {
        return sysTagService;
    }

    public void setSysTagService(SysTagService sysTagService) {
        this.sysTagService = sysTagService;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public SysTagDao getSysTagDao() {
        return sysTagDao;
    }

    public void setSysTagDao(SysTagDao sysTagDao) {
        this.sysTagDao = sysTagDao;
    }

    public AskDao getAskDao() {
        return askDao;
    }

    public AttentionQuestionDao getAttentionQuestionDao() {
        return attentionQuestionDao;
    }

    public void setAttentionQuestionDao(AttentionQuestionDao attentionQuestionDao) {
        this.attentionQuestionDao = attentionQuestionDao;
    }

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
    public Map<String, Object> createQuestion(long id, User user, String title, String description, String[] tags, long[] languageIds, double money) {

        user = userDao.getById(user.getId());

        Map<String, Object> result = new HashMap<>();

        if (money > 0) {
            if (user.getBalance() < money) {
                result.put("success", false);
                result.put("msg", "您的余额不足，请先充值后再提问！");
                return result;
            }
        }

        Question question = null;
        if (id >= 0) {
            question = findQuestionById(id);
        }
        if (question == null) {
            question = new Question();
        }

        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user);
        question.setMoney(money);
        questionDao.saveOrUpdate(question);

        for (long categoryId : languageIds) {
            categoryService.addQuestionToCategory(question, categoryId);
        }

        if (money > 0) {
            Map<String, Object> result2 = payService.trade(user, 1, question.getId(), money, true);
            boolean success = (boolean) result2.get("success");
            if (!success) {
                return result2;
            }
        }
        if (id >= 0) {
            sysTagService.deleteTagByQuestion(question);
        }
        for (String tag : tags) {
            sysTagService.addQuestionTag(user, question, tag);
        }
        if (id == -1) {
            user.setQuestionNum(user.getQuestionNum() + 1);
        }
        userDao.saveOrUpdate(user);
        result.put("success", true);
        result.put("question", question);
        return result;
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
    public Map<String, Object> searchQuestion(String title, long tagId, long categoryId, long max, long offset, String sort, String order) {
        return questionDao.searchQuestion(title, tagId, categoryId, max, offset, sort, order);
    }

    @Override
    public Map<String, Object> saveAsk(User user, long questionId, String content) {
        Question question = questionDao.getById(questionId);
        Ask ask = new Ask();
        ask.setContent(content);
        ask.setQuestion(question);
        ask.setUser(user);
        askDao.add(ask);
        Map<String, Object> map = new HashMap<>();
        map.put("ask", ask);
        map.put("success", true);
        return map;
    }

    @Override
    public Map<String, Object> rateAsk(User user, long askId, String upOrDown) {
        Map<String, Object> result = new HashMap<>();
        Ask ask = askDao.getById(askId);
        Rate rate = rateDao.findByAskAndUser(ask, user);
        if (rate == null) {
            if ("up".equals(upOrDown)) {
                ask.setSupportNum(ask.getSupportNum() + 1);
            } else {
                ask.setOpposeNum(ask.getOpposeNum() + 1);
            }
            askDao.saveOrUpdate(ask);
            rate = new Rate();
            rate.setAsk(ask);
            rate.setUser(user);
            rate.setSupport("up".equals(upOrDown));
            rateDao.saveOrUpdate(rate);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "您已经评价过了!");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteQuestion(long[] ids) {
        Map<String, Object> result = new HashMap<>();
        if (ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                //通过sql方式直接删除
                boolean flag = false;
                String msg = "";
                try {
                    questionDao.execuRemoveSql(ids);
                    flag = true;
                    msg = "文章删除成功";
                } catch (Exception e) {
                    logger.error("删除提问时出错了，异常为：" + e.getMessage());
                    msg = "删除文章失败，请再次尝试。";
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
            List<QuestionTag> tagList = questionTagDao.findAllByQuestion(question);
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
        if (question != null) {
            List<Category> list = categoryDao.findAllByQuestion(question);
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
        if (question != null) {
            List<Ask> list = askDao.findAllByQuestion(question);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
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
            List<Rate> list = rateDao.findAllByAsk(ask);
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
        Map<String, Object> result = new HashMap();
        Question question = questionDao.getById(questionId);
        int count = attentionQuestionDao.countByUserAndQuestion(user, question);
        if (count > 0) {
            result.put("success", false);
            result.put("msg", "已经关注");
        } else {
            AttentionQuestion attentionQuestion = new AttentionQuestion();
            attentionQuestion.setUser(user);
            attentionQuestion.setQuestion(question);
            attentionQuestionDao.saveOrUpdate(attentionQuestion);

            question.setAttentionNum(question.getAttentionNum() + 1);
            questionDao.saveOrUpdate(question);
            result.put("msg", "关注成功!");
            result.put("success", true);
        }
        return result;
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
    public Map<String, Object> searchQuestion(long categoryId, boolean hasDeal, String sort, int max, int offset) {
        Map<String, Object> result = new HashMap<>();
        List<Question> questionList = questionDao.findByRightAskIsNullAndCategoryIdOrderBy(hasDeal, sort, categoryId, max, offset);
        int total = questionDao.countByRightAskIsNullAndCategoryIdOrderBy(hasDeal, sort, categoryId);
        result.put("questionList", questionList);
        result.put("total", total);
        return result;
    }


    @Override
    public Question findQuestionById(long id) {
        return questionDao.getById(id);
    }

    @Override
    public void viewQuestion(Question question) {
        question.setViewNum(question.getViewNum() + 1);
        questionDao.saveOrUpdate(question);
    }

    @Override
    public boolean isAttentionQuestion(User user, Question question) {
        int count = attentionQuestionDao.countByUserAndQuestion(user, question);
        return count > 0;
    }

    @Override
    public List<SysTag> findQuestionTagList(Question question) {
        return sysTagDao.findAllByQuestion(question);
    }

    @Override
    public List<Ask> findQuestionAskList(Question question, String askSort) {
        return askDao.findAllByQuestionOrder(question, askSort);
    }

    @Override
    public Map<String, Object> selectRightAsk(long askId) {
        Map<String, Object> result = new HashMap<>();
        Ask ask = askDao.getById(askId);
        Question question = ask.getQuestion();
        if (question.getRightAsk() != null) {
            result.put("success", false);
            result.put("msg", "已经选择最佳答案");
        } else {
            User user = question.getCreator();
            if (user == ask.getUser()) {
                result.put("success", false);
                result.put("msg", "不能选择自己的答案!");
            }
            if (question.getMoney() > 0) {
                Map<String, Object> isOk = payService.transfer(user, ask.getUser(), question.getMoney(), 1, question.getId());
                boolean success = (boolean) isOk.get("success");
                if (!success) {
                    return isOk;
                }
            }
            question.setRightAsk(ask);
            questionDao.saveOrUpdate(question);
            result.put("success", true);
        }
        return result;
    }

    @Override
    public List<List<SysTag>> findQuestionTagsByQuestionList(List<Question> questionList) {
        List<List<SysTag>> lists = new ArrayList<>();
        for (Question question : questionList) {
            List<SysTag> sysTags = findQuestionTagList(question);
            lists.add(sysTags);
        }
        return lists;
    }

    @Override
    public List<Question> findHotQuestion(int max) {
        return questionDao.findAllByStateOrderBy(Article.STATE_PUBLISH, "viewNum", "desc", 0, max);
    }

    @Override
    public List<Question> findOfferQuesiton(int max) {
        return questionDao.findAllByRightAskIsNullAndMoneyGreaterThan(0, "createDate", "desc", 5);
    }

    @Override
    public List<Question> newestQuestion(int max) {
        return questionDao.findAllByStateOrderBy(Question.STATE_PUBLISH, "createDate", "desc", 0, max);
    }

    @Override
    public List<Question> findAllQuestionByCategory(Category category, int offset, int max) {
        return questionDao.findAllByStateAndCategoryOrderBy(Question.STATE_PUBLISH, category, "createDate", "desc", offset, max);
    }

    @Override
    public List<Question> findAllQuestionByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max) {
        return questionDao.findAllQuestionByCreatorAndState(user, statePublish, sort, order, offset, max);
    }

    @Override
    public int countByCreatorAndState(User user, int statePublish) {
        return questionDao.countByUserAndState(user, statePublish);
    }

    @Override
    public Map<String, Object> modifyQuestionState(long[] ids, int state) {
        for (int i = 0; i < ids.length; i++) {
            Question question = questionDao.getById(ids[i]);
            question.setState(state);
            questionDao.saveOrUpdate(question);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public Map<String, Object> syncQuestionIndex() {
        List<Question> questionList = questionDao.list(0, Integer.MAX_VALUE);
        for (Question question : questionList) {
            //searchService.deleteQuestionById(question.getId());
        }
        //searchService.addQuestionToIndex(questionList);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("msg", "同步成功!");
        return result;
    }

    @Override
    public List<Question> findAllAttentionQuestion(User user, int offset, int max) {
        return questionDao.findAllByAttentionQuestion(user, offset, max);
    }

    @Override
    public Map<String, Object> removeAttention(User user, Question question) {
        Map<String, Object> result = new HashMap<>();
        AttentionQuestion attentionQuestion = attentionQuestionDao.findByUserAndQuestion(user, question);
        if (attentionQuestion != null) {
            attentionQuestionDao.delete(attentionQuestion);

            question.setAttentionNum(question.getAttentionNum() - 1);
            questionDao.saveOrUpdate(question);
            result.put("success", true);
            result.put("msg", "取消关注成功");
        } else {
            result.put("success", false);
            result.put("msg", "用户未关注问题" + question.getTitle());
        }
        return result;
    }

    @Override
    public void syncIndex(long[] questionIds) {
        List<Question> questionList = new ArrayList<>();
        for (long questionId : questionIds) {
            Question question = findQuestionById(questionId);
            if (question != null) {
                questionList.add(question);
            }
        }
        for (Question question : questionList) {
            fullTextSearchService.indexQuestion(question);
        }
    }

    @Override
    public List<Question> findAllQuestionByState(int statePublish) {
        return questionDao.findAllBy("state", statePublish);
    }
}
