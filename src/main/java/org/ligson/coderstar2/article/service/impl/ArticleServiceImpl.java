package org.ligson.coderstar2.article.service.impl;

import com.boful.common.date.utils.DateUtils;
import com.boful.common.file.utils.FileType;
import com.boful.common.file.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ligson.coderstar2.article.arrentionarticle.dao.AttentionArticleDao;
import org.ligson.coderstar2.article.article.dao.ArticleDao;
import org.ligson.coderstar2.article.articlecategory.dao.ArticleCategoryDao;
import org.ligson.coderstar2.article.articlerate.dao.ArticleRateDao;
import org.ligson.coderstar2.article.articletag.dao.ArticleTagDao;
import org.ligson.coderstar2.article.domains.*;
import org.ligson.coderstar2.article.remark.dao.RemarkDao;
import org.ligson.coderstar2.article.remarkrate.dao.RemarkRateDao;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.system.category.dao.CategoryDao;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.conf.utils.Bootstrap;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.service.FullTextSearchService;
import org.ligson.coderstar2.system.systag.dao.SysTagDao;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by ligson on 2015/7/16.
 * 文章服务
 */
public class ArticleServiceImpl implements ArticleService {
    private static Logger logger = Logger.getLogger(ArticleServiceImpl.class);
    private ArticleDao articleDao;
    private ArticleCategoryDao articleCategoryDao;
    private ArticleTagDao articleTagDao;
    private CategoryDao categoryDao;
    private SysTagService sysTagService;
    private CategoryService categoryService;
    private RemarkDao remarkDao;
    private RemarkRateDao remarkRateDao;
    private RemarkRate remarkRate;
    private ArticleRateDao articleRateDao;
    private AttentionArticleDao attentionArticleDao;
    private PayService payService;
    private SysTagDao sysTagDao;
    private FullTextSearchService fullTextSearchService;

    @Resource
    private UserService userService;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ArticleServiceImpl.logger = logger;
    }

    public FullTextSearchService getFullTextSearchService() {
        return fullTextSearchService;
    }

    public void setFullTextSearchService(FullTextSearchService fullTextSearchService) {
        this.fullTextSearchService = fullTextSearchService;
    }

    public SysTagDao getSysTagDao() {
        return sysTagDao;
    }

    public void setSysTagDao(SysTagDao sysTagDao) {
        this.sysTagDao = sysTagDao;
    }

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public AttentionArticleDao getAttentionArticleDao() {
        return attentionArticleDao;
    }

    public void setAttentionArticleDao(AttentionArticleDao attentionArticleDao) {
        this.attentionArticleDao = attentionArticleDao;
    }

    public ArticleRateDao getArticleRateDao() {
        return articleRateDao;
    }

    public void setArticleRateDao(ArticleRateDao articleRateDao) {
        this.articleRateDao = articleRateDao;
    }

    public RemarkRateDao getRemarkRateDao() {
        return remarkRateDao;
    }

    public void setRemarkRateDao(RemarkRateDao remarkRateDao) {
        this.remarkRateDao = remarkRateDao;
    }

    public RemarkRate getRemarkRate() {
        return remarkRate;
    }

    public void setRemarkRate(RemarkRate remarkRate) {
        this.remarkRate = remarkRate;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public RemarkDao getRemarkDao() {
        return remarkDao;
    }

    public void setRemarkDao(RemarkDao remarkDao) {
        this.remarkDao = remarkDao;
    }

    public SysTagService getSysTagService() {
        return sysTagService;
    }

    public void setSysTagService(SysTagService sysTagService) {
        this.sysTagService = sysTagService;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public ArticleTagDao getArticleTagDao() {
        return articleTagDao;
    }

    public void setArticleTagDao(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Map<String, Object> attentionArticle(User user, Article article) {
        Map<String, Object> result = new HashMap<>();
        AttentionArticle attentionArticle = attentionArticleDao.findByUserAndArticle(user, article);
        if (attentionArticle != null) {
            result.put("success", false);
            result.put("msg", "已经关注!");
        } else {
            attentionArticle = new AttentionArticle();
            attentionArticle.setArticle(article);
            attentionArticle.setUser(user);
            attentionArticleDao.saveOrUpdate(attentionArticle);
            result.put("success", true);
            result.put("msg", "关注成功!");
        }
        return result;

    }

    @Override
    public Map<String, Object> auditArticle(User user, long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Article article = articleDao.getById(ids[i]);
            article.setState(Article.STATE_PUBLISH);
            articleDao.saveOrUpdate(article);
            fullTextSearchService.indexArticle(article);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public Map<String, Object> createArticle(long id, String title, String content, User creator, String[] tags, long[] categroyIds) {
        //保存文章
        Article article = findArticleById(id);
        if (null == article) {
            article = new Article();
        }

        article.setTitle(title);
        article.setDescription(content);
        article.setCreatorId(creator.getId());
        article.setState(Article.STATE_APPLY);
        articleDao.saveOrUpdate(article);
        if (id >= 0) {
            sysTagService.deleteTagByArticle(article);
        }
        for (String tag : tags) {
            if (StringUtils.isNotEmpty(tag)) {
                sysTagService.addArticleTag(creator, article, tag);
            }
        }

        for (long categoryId : categroyIds) {
            ArticleCategory articleCategory = categoryService.addArticleToCategory(article, categoryId);
        }

        Map result = new HashMap();
        result.put("success", true);
        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteArticle(User user, long articleId) {
        //需要删除article
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String msg = "";
        try {
            Article article = findArticleById(articleId);
            if (article != null) {
                if (article.getPoster() != null) {
                    File poster = new File(Bootstrap.webRoot, article.getPoster());
                    if (poster.exists()) {
                        poster.delete();
                    }
                }
            }
            articleDao.execuRemoveSql(articleId);

            flag = true;
            msg = "文章删除成功";
        } catch (Exception e) {
            logger.error("用户ID：" + user.getId() + "，删除articleID：" + articleId + ",时出错了，异常为：" + e.getMessage());
            msg = "删除文章失败，请再次尝试。";
        }
        /*List<String> pros = new ArrayList<>();
        pros.add("id");
        pros.add("creator.id");
        List<Object> vals = new ArrayList<>();
        vals.add(articleId);
        vals.add(user.getId());
        Article article = articleDao.findByAnd(pros,vals);
        boolean flag = false;
        String msg = "";
        if(article == null){
            msg = "根据用户："+user.getCreateName()+",没有找到文章，主键为："+articleId;
        }else{

            flag = true;
            msg = "文章删除成功";
        }*/
        map.put("success", flag);
        map.put("msg", msg);
        return map;
    }

    @Override
    public Map<String, Object> deleteArticles(User user, long[] articleIds) {
        return null;
    }

    @Override
    public Map<String, Object> modifyArticle(User user, Article article, String title, String content, String[] tags, List<Long> categoryIds) {
        return null;
    }

    @Override
    public Map<String, Object> removeAttention(User user, Article article) {
        Map<String, Object> result = new HashMap<>();
        AttentionArticle attentionArticle = attentionArticleDao.findByUserAndArticle(user, article);
        if (attentionArticle != null) {
            attentionArticleDao.delete(attentionArticle);
            article.setAttentionNum(article.getAttentionNum() - 1);
            articleDao.saveOrUpdate(article);
            result.put("success", true);
            result.put("msg", "取消关注成功");
        } else {
            result.put("success", false);
            result.put("msg", "用户未关注!");
        }
        return result;
    }

    @Override
    public Map<String, Object> rewardArticle(User currentUser, Article article, double money) {
        User user = userService.findUserById(article.getCreatorId());
        return payService.transfer(currentUser, user, money, 2, article.getId());
    }

    @Override
    public Map<String, Object> saveRemark(User creator, Article article, String comment) {
        Remark remark = new Remark();
        remark.setArticle(article);
        remark.setUser(creator);
        remark.setContent(comment);
        remarkDao.saveOrUpdate(remark);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @Override
    public Map<String, Object> searchArticle(String title, long tagId, long categoryId, int max, int offset, String sort, String orderr) {
        return articleDao.searhArticle(title, tagId, categoryId, max, offset, sort, orderr);
    }

    @Override
    public Map<String, Object> searchMyArticle(User currentUser, int offset, int max, String sort, String orderr) {
        return null;
    }

    @Override
    public Map<String, Object> supportArticle(User currentUser, Article article, boolean isSupport) {
        Map<String, Object> result = new HashMap<>();
        ArticleRate articleRate = articleRateDao.findByUserAndArticle(currentUser, article);
        if (articleRate == null) {
            articleRate = new ArticleRate();
            articleRate.setArticle(article);
            articleRate.setUser(currentUser);
            articleRate.setSupport(isSupport);
            articleRateDao.saveOrUpdate(articleRate);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "你已经投过票了!");
        }
        return result;
    }

    @Override
    public Map<String, Object> supportRemark(User currentUser, Remark remark, boolean isSupport) {
        Map<String, Object> result = new HashMap<>();
        RemarkRate remarkRate = remarkDao.findByUserAndRemark(currentUser, remark);
        if (remarkRate == null) {
            remarkRate = new RemarkRate();
            remarkRate.setRemark(remark);
            remarkRate.setUser(currentUser);
            remarkRate.setSupport(isSupport);
            remarkRateDao.saveOrUpdate(remarkRate);
            if (isSupport) {
                remark.setSupportNum(remark.getSupportNum() + 1);
            } else {
                remark.setOpposeNum(remark.getOpposeNum() + 1);
            }
            remarkDao.saveOrUpdate(remark);
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "你已经投过票了!");
        }
        return result;
    }

    @Override
    public Map<String, Object> list(int offset, int max) {
        Map<String, Object> result = new HashMap<>();
        int total = articleDao.countAll();
        List<Article> articles = articleDao.list(offset, max);
        result.put("total", total);
        result.put("rows", articles);
        return result;
    }

    @Override
    public List<SysTag> hotsTag(int limit) {
        return sysTagDao.listOrderArticle(limit);
    }

    @Override
    public List<List> hotAuthors(int day) {
        String preWeek = null;
        if (day > 0) {
            Date preWeekDate = DateUtils.subtractDay(new Date(), day);
            preWeek = DateUtils.format(preWeekDate, DateUtils.FORMAT_1);
        } else {
            preWeek = null;
        }
        return articleDao.countByArticleGroupByUser(preWeek);
    }

    @Override
    public Map<String, Object> findAllByCategoryIdAndTagIdOrderBy(long categoryId, long tagId, String order, int max, int offset) {
        List<Article> articles = articleDao.findAllByCategoryIdAndTagIdOrderBy(categoryId, tagId, order, max, offset);
        int total = articleDao.countByCategoryIdAndTagIdOrderBy(categoryId, tagId, order, max, offset);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("articleList", articles);
        return result;
    }

    @Override
    public List<List<SysTag>> findAllSysTagLists(List<Article> articleList) {
        List<List<SysTag>> lists = new ArrayList<>();
        for (Article article : articleList) {
            List<SysTag> articleTags = sysTagDao.findAllByArticle(article);
            lists.add(articleTags);
        }
        return lists;
    }

    @Override
    public List<List<Category>> findCategoryByArticleList(List<Article> articleList) {
        List<List<Category>> categoryList = new ArrayList<>();
        for (Article article : articleList) {
            List<Category> articles = categoryDao.findAllByArticle(article);
            categoryList.add(articles);
        }
        return categoryList;
    }

    @Override
    public Article findArticleById(long articleId) {
        return articleDao.getById(articleId);
    }

    @Override
    public Remark findRemarkById(long remarkId) {
        return remarkDao.getById(remarkId);
    }

    @Override
    public List<Article> findHotArticle(int max) {
        return articleDao.findAllByStateOrderBy(Question.STATE_PUBLISH, "viewNum", "desc", 0, max);
    }

    @Override
    public List<Article> newestArticle(int max) {
        return articleDao.findAllByStateOrderBy(Article.STATE_PUBLISH, "createDate", "desc", 0, max);
    }

    @Override
    public List<Article> findAllArticleByCategory(Category category, int offset, int max) {
        return articleDao.findAllByStateAndCategoryOrderBy(Article.STATE_PUBLISH, category, "createDate", "desc", offset, max);
    }

    @Override
    public List<Remark> findAllRemarkByArticle(Article article, String remarkSort) {
        return remarkDao.findAllByArticleOrderBy(article, remarkSort, "desc");
    }

    @Override
    public List<SysTag> findArticleTagList(Article article) {
        return sysTagService.findByArticle(article);
    }

    @Override
    public void viewArticle(Article article) {
        article.setViewNum(article.getViewNum() + 1);
        articleDao.saveOrUpdate(article);
    }

    @Override
    public boolean isAttentionArticle(User user, Article article) {
        AttentionArticle attentionArticle = attentionArticleDao.findByUserAndArticle(user, article);
        return attentionArticle != null;
    }

    @Override
    public int countByArticleAndUser(Article article, User user) {
        ArticleRate articleRate = articleRateDao.findByUserAndArticle(user, article);
        if (articleRate == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int countByArticleIsSupport(Article article, boolean isSupport) {
        return articleRateDao.countByArticleAndIsSupport(article, isSupport);
    }

    @Override
    public List<Article> findAllArticleByCreatorAndState(User user, int statePublish, String sort, String order, int offset, int max) {
        return articleDao.findAllArticleByCreatorAndState(user, statePublish, sort, order, offset, max);
    }

    @Override
    public int countByCreatorAndState(User user, int statePublish) {
        return articleDao.countByCreatorAndState(user, statePublish);
    }

    @Override
    public boolean deleteTagByArticle(Article article) {
        boolean isFlag = true;
        if (article != null) {
            List<ArticleTag> tagList = articleTagDao.findAllByArticle(article);
            if (tagList != null && tagList.size() > 0) {
                for (int i = 0; i < tagList.size(); i++) {
                    articleTagDao.delete(tagList.get(i));
                }
            }
        }
        return isFlag;
    }

    @Override
    public boolean deleteArticleRateByArticle(Article article) {
        return false;
    }

    @Override
    public boolean deleteArticleCategoryByArticle(Article article) {
        return false;
    }

    @Override
    public boolean deleteRemarkRateAndReplyByArticle(Article article) {
        return false;
    }

    @Override
    public List<Article> findAllAttentionArticle(User user, int offset, int max) {
        return articleDao.findAllAttentionArticle(user, offset, max);
    }

    @Override
    public List<Article> findAllArticleByUserOrder(User user, int offset, int max, String sort, String order) {
        return articleDao.findAllArticleByCreatorAndState(user, -1, sort, order, offset, max);
    }

    @Override
    public void syncIndex(long[] articleIds) {
        List<Article> articleList = new ArrayList<>();
        for (long articleId : articleIds) {
            Article article = findArticleById(articleId);
            if (article != null) {
                articleList.add(article);
            }
        }
        for (Article article : articleList) {
            fullTextSearchService.indexArticle(article);
        }
    }

    @Override
    public List<String> hotKey(String key, int max) {
        return fullTextSearchService.hotArticleKey(key, max);
    }

    @Override
    public List<Article> findAllArticleByState(int statePublish) {
        return articleDao.findAllBy("state", statePublish);
    }

    @Override
    public int countByCreatorAndStateAndTitleLike(User user, int state, String title) {
        return articleDao.countByCreatorAndStateAndTitleLike(user, state, title);
    }

    @Override
    public List<Article> findAllArticleByUserAndTitleLikeOrder(User user, String title, int offset, int max, String sort, String order) {
        return articleDao.findAllArticleByUserAndTitleLikeOrder(user, title, offset, max, sort, order);
    }

    @Override
    public Map<String, Object> recommendArticle(Article article, int recommendNum, CommonsMultipartFile poster) {
        Map<String, Object> result = new HashMap<>();
        if (FileType.isImage(poster.getOriginalFilename())) {
            String fileType = FileUtils.getFileSufix(poster.getOriginalFilename());
            String url = "/upload/article/" + article.getId() + "/" + UUID.randomUUID().toString() + "." + fileType;
            File destFile = new File(Bootstrap.webRoot, url);
            if (!destFile.getParentFile().exists()) {
                destFile.mkdirs();
            }
            try {
                poster.transferTo(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String oldUrl = article.getPoster();
            if (oldUrl != null) {
                File oldFile = new File(Bootstrap.webRoot, oldUrl);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }
            article.setPoster(url);
            article.setRecommendNum(recommendNum);
            articleDao.saveOrUpdate(article);
            result.put("success", true);
            result.put("url", url);
        } else {
            result.put("success", false);
            result.put("msg", "不是图片类型");
        }
        return result;
    }

    @Override
    public List<Article> articleListOrderBy(int offset, int max, String sort, String order) {
        return articleDao.findAllByStateOrderBy(Article.STATE_PUBLISH, sort, order, offset, max);
    }

    @Override
    public int countByCategory(Category category) {
        return articleDao.countByCategoryAndState(category, Article.STATE_PUBLISH);
    }

    @Override
    public Map<String, Object> listArticleRemark(long articleId, int offset, int max) {
        Article article = findArticleById(articleId);
        List<Remark> remarks = remarkDao.findAllByArticleOrderBy(article, offset, max, "createDate", "desc");
        long total = article.getReplyNum();
        Map<String, Object> result = new HashMap<>();
        result.put("remarks", remarks);
        result.put("total", total);
        return result;
    }

    public ArticleCategoryDao getArticleCategoryDao() {
        return articleCategoryDao;
    }

    public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
        this.articleCategoryDao = articleCategoryDao;
    }
}
