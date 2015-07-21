package org.ligson.coderstar2.question.service;

import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionService {

    /***
     * 创建问题
     *
     * @param title
     * @param categoryIds
     * @return
     */
    public Map<String, Object> createQuestion(long id,User user, String title, String description, String[] tags, long[] categoryIds, double money);


    /***
     * 创建分类
     *
     * @param params
     * @return
     */
    public Map<String, Object> createLanguage(Map params);

    /***
     * 修改分类
     *
     * @param params
     * @return
     */
    public Map<String, Object> modifyLanguage(Map params);

    /***
     * 删除分类
     *
     * @param params
     * @return
     */
    public Map<String, Object> deleteLanguage(Map params);

    /***
     * 搜索问题
     *
     * @param offset
     * @param sort
     * @param order
     * @return
     */
    public Map<String, Object> searchQuestion(String title, long tagId, long categoryId, long max, long offset, String sort, String order);

    /***
     * 保存问题回复
     *
     * @param questionId
     * @param content
     * @return
     */
    public Map saveAsk(User user, long questionId, String content);

    /***
     * 评价
     *
     * @param askId
     * @param upOrDown
     * @return
     */
    public Map<String, Object> rateAsk(User user, long askId, String upOrDown);

    /***
     * 删除问题
     *
     * @param ids
     * @return
     */
    public Map<String, Object> deleteQuestion(long[] ids);

    /***
     * 删除问题标签
     *
     * @param question
     * @return
     */
    public boolean deleteTagByQuestion(Question question);

    /***
     * 删除问题类型相关
     *
     * @param question
     * @return
     */
    public boolean deleteCategoryByQuestion(Question question);

    /***
     * 删除问题回复相关
     *
     * @param question
     * @return
     */
    public boolean deleteAskByQuestion(Question question);

    /***
     * 删除回复相关
     *
     * @param ask
     * @return
     */
    public boolean deleteRateByAsk(Ask ask);

    /***
     * 修改问题内容
     *
     * @param questionId
     * @param description
     * @return
     */
    public Map<String, Object> modifyDescription(long questionId, String description);

    /***
     * 关注问题
     *
     * @param user       当前用户
     * @param questionId 问题id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> attentionQuestion(User user, long questionId);

    /***
     * @param max
     * @param offset
     * @param questionId
     * @return
     */
    public Map<String, Object> searchRelatedQuestion(int max, int offset, long questionId);

    public Map<String, Object> questionList(int offset, int max);

    public Map<String, Object> searchQuestion(long categoryId,boolean hasDeal, String sort, int max, int offset);

    public Question findQuestionById(long id);

    public void viewQuestion(Question question);

    public boolean isAttentionQuestion(User user, Question question);

    public List<SysTag> findQuestionTagList(Question question);

    public List<Ask> findQuestionAskList(Question question, String askSort);

    public Map<String, Object> selectRightAsk(long askId);

    public List<List<SysTag>> findQuestionTagsByQuestionList(List<Question> questionList);

    public List<Question> findHotQuestion(int max);

    public List<Question> findOfferQuesiton(int max);

    public List<Question> newestQuestion(int max);

    public List<Question> findAllQuestionByCategory(Category category, int offset, int max);

    public List<Question> findAllQuestionByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max);

    public int countByCreatorAndState(User user, int statePublish);

    public Map<String, Object> modifyQuestionState(long[] ids, int state);

    public Map<String, Object> syncQuestionIndex();

    public List<Question> findAllAttentionQuestion(User user, int offset, int max);

    public Map<String, Object> removeAttention(User user, Question question);
}
