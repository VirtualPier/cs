package org.ligson.coderstar2.question.ask.service;

import org.ligson.coderstar2.question.domains.Ask;
import org.ligson.coderstar2.user.domains.User;

import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface QuestionAskService {
    public Ask findAskById(long id);

    public Map<String, Object> deleteAsk(User user, Ask ask);
}
