package org.ligson.coderstar2.system.systag.dao;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.base.dao.BaseDao;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;

/**
 * Created by ligson on 2015/7/16.
 */
public interface SysTagDao extends BaseDao<SysTag> {
    public List<SysTag> findAllByQuestion(Question question);

    public List<List> findHotTag(int max);

    public List<SysTag> findAllByArticle(Article article);

    public List<SysTag> findAllByCreator(User user, int max);

    public List<SysTag> listOrderArticle(int limit);

    public List<SysTag> findQuestionHotTags(int max);
}
