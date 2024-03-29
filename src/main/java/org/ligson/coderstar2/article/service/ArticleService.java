package org.ligson.coderstar2.article.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 * 文章服务
 */
public interface ArticleService {
    /**
     * 关注文章
     *
     * @param user    关注用户
     * @param article 文章
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> attentionArticle(User user, Article article);

    /**
     * 审批文章
     *
     * @param user 操作用户
     * @param ids  文章Id集合
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> auditArticle(User user, long[] ids);

    /**
     * 创建文章
     *
     * @param title       文章标题
     * @param content     文章内容
     * @param creator     文章创建者
     * @param tags        标签,格式:标签1,标签2...
     * @param categroyIds 分类id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> createArticle(long id, String title, String content, User creator, String[] tags, long[] categroyIds);

    /**
     * *
     * 删除文章
     *
     * @param user      当前用户
     * @param articleId 文章id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> deleteArticle(User user, long articleId);

    /**
     * @param user       当前用户
     * @param articleIds 文章id
     * @return [ [success:true/false,msg:XXXX,...], [success:true/false,msg:XXXX,...],...]
     */
    public Map<String, Object> deleteArticles(User user, long[] articleIds);

    /**
     * 修改文章
     *
     * @param user        当前用户
     * @param article     文章
     * @param title       文章标题
     * @param content     文章内容
     * @param tags        文章标签,格式:标签1,标签2,...
     * @param categoryIds 分类id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> modifyArticle(User user, Article article, String title, String content, String[] tags, List<Long> categoryIds);

    /**
     * 取消文章关注
     *
     * @param user    当前用户
     * @param article 文章
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> removeAttention(User user, Article article);

    /**
     * 打赏文章
     *
     * @param currentUser 当前用户
     * @param article     文章id
     * @param money       钱
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> rewardArticle(User currentUser, Article article, double money);

    /**
     * 评论保存
     *
     * @param creator 当前用户
     * @param article 文章
     * @param comment 评论内容
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> saveRemark(User creator, Article article, String comment);

    /**
     * *
     * 搜索文章
     *
     * @param title  标题
     * @param max    每页记录数
     * @param offset 开始记录
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> searchArticle(String title, long tagId, long categoryId, int max, int offset, String sort, String orderr);

    /**
     * 搜索我的文章
     *
     * @param currentUser 当钱用户
     * @param offset      开始
     * @param max         每页记录
     * @param sort        排序
     * @param orderr      顺序
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> searchMyArticle(User currentUser, int offset, int max, String sort, String orderr);

    /**
     * 文章投票
     *
     * @param currentUser 当前用户
     * @param article     文章
     * @param isSupport   是否支持
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> supportArticle(User currentUser, Article article, boolean isSupport);

    /**
     * 评论投票
     *
     * @param currentUser 当前用户
     * @param remark      评论
     * @param isSupport   是否支持
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map<String, Object> supportRemark(User currentUser, Remark remark, boolean isSupport);


    public Map<String, Object> list(int offset, int max);

    public List<SysTag> hotsTag(int limit);

    public List<List> hotAuthors(int day);

    public Map<String, Object> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public List<List<SysTag>> findAllSysTagLists(List<Article> articleList);

    public List<List<Category>> findCategoryByArticleList(List<Article> articleList);

    public Article findArticleById(long articleId);

    public Remark findRemarkById(long remarkId);

    public List<Article> findHotArticle(int max);

    public List<Article> newestArticle(int max);

    public List<Article> findAllArticleByCategory(Category category, int offset, int max);

    public List<Remark> findAllRemarkByArticle(Article article, String remarkSort);

    public List<SysTag> findArticleTagList(Article article);

    public void viewArticle(Article article);

    public boolean isAttentionArticle(User user, Article article);

    public int countByArticleAndUser(Article article, User user);

    public int countByArticleIsSupport(Article article, boolean isSupport);

    public List<Article> findAllArticleByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max);

    public int countByCreatorAndState(User user, int statePublish);

    public boolean deleteTagByArticle(Article article);

    public boolean deleteArticleRateByArticle(Article article);

    public boolean deleteArticleCategoryByArticle(Article article);

    public boolean deleteRemarkRateAndReplyByArticle(Article article);

    public List<Article> findAllAttentionArticle(User user, int offset, int max);

    public List<Article> findAllArticleByUserOrder(User user, int offset, int max, String sort, String order);

    public void syncIndex(long[] articleIds);

    public List<String> hotKey(String key, int max);

    public List<Article> findAllArticleByState(int statePublish);

    public int countByCreatorAndStateAndTitleLike(User user, int state, String title);

    public List<Article> findAllArticleByUserAndTitleLikeOrder(User user, String title, int offset, int max, String sort, String order);

    public Map<String, Object> recommendArticle(Article article, int recommendNum, CommonsMultipartFile poster);

    public List<Article> articleListOrderBy(int offset, int max, String sort, String order);

    public int countByCategory(Category category);

    public Map<String, Object> listArticleRemark(long articleId, int offset, int max);
}