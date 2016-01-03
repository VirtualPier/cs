package org.ligson.coderstar2.question.ask.service.impl;

import org.ligson.coderstar2.question.ask.dao.AskDao;
import org.ligson.coderstar2.question.ask.service.QuestionAskService;
import org.ligson.coderstar2.question.askreply.dao.AskReplyDao;
import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.question.domains.AskReply;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.domains.Rate;
import org.ligson.coderstar2.question.question.dao.QuestionDao;
import org.ligson.coderstar2.question.rate.dao.RateDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
public class QuestionAskServiceImpl implements QuestionAskService {
    private AskDao askDao;
    private RateDao rateDao;
    private AskReplyDao askReplyDao;
    private QuestionDao questionDao;

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public AskReplyDao getAskReplyDao() {
        return askReplyDao;
    }

    public void setAskReplyDao(AskReplyDao askReplyDao) {
        this.askReplyDao = askReplyDao;
    }

    public RateDao getRateDao() {
        return rateDao;
    }

    public void setRateDao(RateDao rateDao) {
        this.rateDao = rateDao;
    }

    public AskDao getAskDao() {
        return askDao;
    }

    public void setAskDao(AskDao askDao) {
        this.askDao = askDao;
    }

    @Override
    public Ask findAskById(long id) {
        return askDao.getById(id);
    }

    @Override
    public Map<String, Object> deleteAsk(User user, Ask ask) {
        Map<String, Object> result = new HashMap<>();
        if (!((user.getId() == ask.getUserId()) || (user.getId() == ask.getQuestionId()))) {
            result.put("success", false);
            result.put("msg", "权限不足！");
            return result;
        }
        List<Rate> rateList = rateDao.findAllByAsk(ask);
        for (Rate rate : rateList) {
            rateDao.delete(rate);
        }

        List<AskReply> askReplyList = askReplyDao.findAllByAsk(ask);
        for (AskReply askReply : askReplyList) {
            askReplyDao.delete(askReply);
        }
        Question question = questionDao.getById(ask.getQuestionId());

        if (question.getRightAskId() == ask.getId()) {
            question.setRightAskId(-1);
        }
        if (question.getReplyNum() > 0) {
            question.setReplyNum(question.getReplyNum() - 1);
        }
        //question.getAsks().remove(ask);
        questionDao.saveOrUpdate(question);
        askDao.delete(ask);
        result.put("success", true);
        return result;
    }
}
