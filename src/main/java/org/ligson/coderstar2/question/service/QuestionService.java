package org.ligson.coderstar2.question.service;

import org.ligson.coderstar2.user.domains.User;

import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionService {
    /***
     * 关注问题
     *
     * @param user       当前用户
     * @param questionId 问题id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map attentionQuestion(User user, long questionId);
}
