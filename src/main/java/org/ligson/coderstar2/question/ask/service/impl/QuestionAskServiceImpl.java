package org.ligson.coderstar2.question.ask.service.impl;

import org.ligson.coderstar2.question.ask.dao.AskDao;
import org.ligson.coderstar2.question.ask.service.QuestionAskService;

/**
 * Created by ligson on 2015/7/17.
 */
public class QuestionAskServiceImpl implements QuestionAskService {
    private AskDao askDao;

    public AskDao getAskDao() {
        return askDao;
    }

    public void setAskDao(AskDao askDao) {
        this.askDao = askDao;
    }
}
