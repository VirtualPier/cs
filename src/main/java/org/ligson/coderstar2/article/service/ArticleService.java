package org.ligson.coderstar2.article.service;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
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
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map attentionArticle(User user, Article article);

    /***
     * 审批文章
     *
     * @param user    操作用户
     * @param article 文章
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map auditArticle(User user, Article article);

    /***
     * 创建文章
     *
     * @param title       文章标题
     * @param content     文章内容
     * @param creator     文章创建者
     * @param tags        标签,格式:标签1,标签2...
     * @param categroyIds 分类id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map createArticle(String title, String content, User creator, String[] tags, long[] categroyIds);

    /****
     * 删除文章
     *
     * @param user      当前用户
     * @param articleId 文章id
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map deleteArticle(User user, long articleId);

    /***
     * @param user       当前用户
     * @param articleIds 文章id
     * @return [ [success:true/false,msg:XXXX,...], [success:true/false,msg:XXXX,...],...]
     */
    public List<Map<String, Object>> deleteArticles(User user, long[] articleIds);

    /***
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
    public Map modifyArticle(User user, Article article, String title, String content, String[] tags, List<Long> categoryIds);

    /***
     * 取消文章关注
     *
     * @param user    当前用户
     * @param article 文章
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map removeAttention(User user, Article article);

    /***
     * 打赏文章
     *
     * @param currentUser 当前用户
     * @param article     文章id
     * @param money       钱
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map rewardArticle(User currentUser, Article article, double money);

    /***
     * 评论保存
     *
     * @param creator 当前用户
     * @param article 文章
     * @param comment 评论内容
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map saveRemark(User creator, Article article, String comment);

    /****
     * 搜索文章
     *
     * @param title       标题
     * @param description 描述
     * @param tagName     标签名
     * @param category    语言
     * @param max         每页记录数
     * @param offset      开始记录
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map searchArticle(String title, String description, String tagName, Category category, int max, int offset, String sort, String orderr);

    /***
     * 搜索我的文章
     *
     * @param currentUser 当钱用户
     * @param offset      开始
     * @param max         每页记录
     * @param sort        排序
     * @param orderr      顺序
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map searchMyArticle(User currentUser, int offset, int max, String sort, String orderr);

    /***
     * 文章投票
     *
     * @param currentUser 当前用户
     * @param article     文章
     * @param isSupport   是否支持
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map supportArticle(User currentUser, Article article, boolean isSupport);

    /***
     * 评论投票
     *
     * @param currentUser 当前用户
     * @param remark      评论
     * @param isSupport   是否支持
     * @return [success:true/false,msg:XXXX,...]
     */
    public Map supportRemark(User currentUser, Remark remark, boolean isSupport);


    public Map<String, Object> list(int offset, int max);

    public List<SysTag> hotsTag(int limit);

    public List<List> hotAuthors(int day);

    public Map<String, Object> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset);

    public List<List<SysTag>> findAllArticleTagList(List<Article> articleList);

    public List<List<Category>> findCategoryByArticleList(List<Article> articleList);
}