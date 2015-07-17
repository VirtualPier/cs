package org.ligson.coderstar2.question.service;

import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.user.domains.User;

import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionService {

    /***
     * 创建问题
     *
     * @param title
     * @param languageId
     * @return
     */
    public Map createQuestion(User user, String title, String description, String tags, long languageId, double money);


    /***
     * 创建分类
     *
     * @param params
     * @return
     */
    public Map createLanguage(Map params);

    /***
     * 修改分类
     *
     * @param params
     * @return
     */
    public Map modifyLanguage(Map params);

    /***
     * 删除分类
     *
     * @param params
     * @return
     */
    public Map deleteLanguage(Map params);

    /***
     * 搜索问题
     *
     * @param sort
     * @param order
     * @param offset
     * @return
     */
    public Map searchQuestion(String sort, String order, long offset, long max, String tagName, long languageId, String title);

    /***
     * 保存问题回复
     *
     * @param questionId
     * @param content
     * @return
     */
    public Map saveAsk(long questionId, String content);

    /***
     * 评价
     *
     * @param askId
     * @param upOrDown
     * @return
     */
    public Map rateAsk(long askId, String upOrDown);

    /***
     * 删除问题
     *
     * @param id or ids
     * @return
     */
    public Map deleteQuestion(String id);

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
    public boolean deleteLanguageByQuestion(Question question);

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
    public Map modifyDescription(long questionId, String description);

    /***
     * 关注问题
     *
     * @param user       当前用户
     * @param questionId 问题id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map attentionQuestion(User user, long questionId);

    /***
     * @param max
     * @param offset
     * @param questionId
     * @return
     */
    public Map searchRelatedQuestion(int max, int offset, long questionId);

    public Map<String, Object> questionList(int offset, int max);
}
