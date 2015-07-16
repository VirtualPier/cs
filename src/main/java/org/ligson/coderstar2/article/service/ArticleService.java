package org.ligson.coderstar2.article.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 文章服务
 */
public interface ArticleService {
    /***
     * 关注文章
     *
     * @param user    关注用户
     * @param article 文章
     * @return
     */
    public Map attentionArticle(User user, Article article);

    /***
     * 审批文章
     *
     * @param article
     * @return
     */
    public Map auditArticle(User user, Article article);

    /***
     * 创建文章
     *
     * @param title
     * @param content
     * @param creator
     */
    public Map createArticle(String title, String content, User creator, String[] tags, Long[] categroyIds);

    /****
     * 删除文章
     *
     * @param user
     * @param articleId
     * @return
     */
    public Map deleteArticle(User user, long articleId);

    public List<Map<String, Object>> deleteArticles(User user, long[] articleIds);

    /***
     * 修改文章
     *
     * @param article
     * @param title
     * @param content
     * @param tags
     * @param categoryIds
     * @return
     */
    public Map modifyArticle(Article article, String title, String content, String[] tags, List<Long> categoryIds);

    public Map removeAttention(User user, Article article);

    /***
     * 打赏文章
     *
     * @param article
     * @param money
     * @param currentUser
     * @return
     */
    public Map rewardArticle(Article article, double money, User currentUser);

    /***
     * 评论保存
     *
     * @param article
     * @param creator
     * @param comment
     * @return
     */
    public Map saveRemark(Article article, User creator, String comment);

    /****
     * 搜索文章
     *
     * @param title       标题
     * @param description 描述
     * @param tagName     标签名
     * @param category    语言
     * @param max
     * @param offset
     * @return
     */
    public Map searchArticle(String title, String description, String tagName, Category category, int max, int offset, String sort, String orderr);

    /***
     * 搜索我的文章
     *
     * @param currentUser
     * @param offset
     * @param max
     * @param sort
     * @param orderr
     * @return
     */
    public Map searchMyArticle(User currentUser, int offset, int max, String sort, String orderr);

    /***
     * 文章投票
     *
     * @param article
     * @param isSupport
     * @param currentUser
     * @return
     */
    public Map supportArticle(Article article, boolean isSupport, User currentUser);

    /***
     * 评论投票
     *
     * @param remark
     * @param isSupport
     * @param currentUser
     * @return
     */
    public Map supportRemark(Remark remark, boolean isSupport, User currentUser);


}