package org.ligson.coderstar2.article.controllers;

import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.domains.Remark;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false, defaultValue = "-1") long categoryId, @RequestParam(value = "tagId", required = false, defaultValue = "-1") long tagId, @RequestParam(value = "order", required = false, defaultValue = "createDate") String order, @RequestParam(value = "max", required = false, defaultValue = "15") int max, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset, HttpServletRequest request) {
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        List<SysTag> sysTags = articleService.hotsTag(10);
        List<List> weekCount = articleService.hotAuthors(7);
        List<List> monthCount = articleService.hotAuthors(30);
        List<List> allCount = articleService.hotAuthors(-1);
        Map<String, Object> result = articleService.findAllByCategoryIdAndTagIdOrderBy(categoryId, tagId, order, max, offset);
        List<Article> articleList = (List<Article>) result.get("articleList");
        //List<List<Category>> articleCategoryList = articleService.findCategoryByArticleList(articleList);
        //List<List<SysTag>> articleTagList = articleService.findAllSysTagLists(articleList);

        int total = (int) result.get("total");
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("tagId", tagId);
        request.setAttribute("max", max);
        request.setAttribute("offset", offset);
        request.setAttribute("order", order);
        //request.setAttribute("articleTagList", articleTagList);
        //request.setAttribute("articleCategoryList", articleCategoryList);
        request.setAttribute("articleList", articleList);
        request.setAttribute("total", total);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("sysTags", sysTags);
        request.setAttribute("weekCount", weekCount);
        request.setAttribute("monthCount", monthCount);
        request.setAttribute("allCount", allCount);
        return "article/index";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(value = "title", required = true) String title, @RequestParam(value = "content", required = false) String content, @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "categoryIds", required = true) String categoryIds, @RequestParam(value = "money", required = false, defaultValue = "0") int money, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String[] categoryIdStringArray = categoryIds.split(",");
        long[] categoryIdArray = new long[categoryIdStringArray.length];
        for (int i = 0; i < categoryIdArray.length; i++) {
            categoryIdArray[i] = Long.parseLong(categoryIdStringArray[i]);
        }
        String[] tagArr = null;
        if (tags == null) {
            tagArr = new String[0];
        } else {
            tagArr = tags.split(";");
        }
        Map result = articleService.createArticle(-1, title, content, user, tagArr, categoryIdArray);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/user/myArticle";
        } else {
            return "redirect:/article/create";
        }
    }

    @RequestMapping("/saveRemark")
    public String saveRemark(long articleId, String content, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Article article = articleService.findArticleById(articleId);
        articleService.saveRemark(user, article, content);
        return "redirect:/article/view?id=" + articleId;
    }

    @RequestMapping("/supportRemark")
    @ResponseBody
    public Map<String, Object> supportRemark(long remarkId, boolean isSupport, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Remark remark = articleService.findRemarkById(remarkId);
        return articleService.supportRemark(user, remark, isSupport);
    }

    @RequestMapping("/supportArticle")
    @ResponseBody
    public Map<String, Object> supportArticle(long articleId, boolean isSupport, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Article article = articleService.findArticleById(articleId);
        return articleService.supportArticle(user, article, isSupport);
    }

    @RequestMapping("/attentionArticle")
    @ResponseBody
    public Map<String, Object> attentionArticle(long id,int flag, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Article article = articleService.findArticleById(id);
        if(flag == 0){
            //设定取消关注
            return articleService.removeAttention(user, article);
        }
        return articleService.attentionArticle(user, article);
    }

    @RequestMapping("/rewardArticle")
    @ResponseBody
    public Map<String, Object> rewardArticle(@RequestParam("id") long id, @RequestParam("money") int money, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Article article = articleService.findArticleById(id);
        return articleService.rewardArticle(user, article, money);
    }


    @RequestMapping("/view")
    public String view(@RequestParam("id") long id, @RequestParam(value = "remarkSort", defaultValue = "supportNum", required = false) String remarkSort, HttpServletRequest request) {
        Article article = articleService.findArticleById(id);
        List<Remark> asks = articleService.findAllRemarkByArticle(article, remarkSort);
        List<SysTag> tags = articleService.findArticleTagList(article);
        List<Category> categoryList = categoryService.findArticleCategoryList(article);
        articleService.viewArticle(article);
        Object object = request.getSession().getAttribute("user");
        boolean isAttention = false;
        boolean isDisabled = false;
        if (object != null) {
            User user = (User) object;
            isAttention = articleService.isAttentionArticle(user, article);
            int count = articleService.countByArticleAndUser(article, user);
            isDisabled = count > 0;
        }
        int supportNum = articleService.countByArticleIsSupport(article, true);
        int opposeNum = articleService.countByArticleIsSupport(article, false);

        request.setAttribute("isDisabled", isDisabled);
        request.setAttribute("supportNum", supportNum);
        request.setAttribute("opposeNum", opposeNum);
        request.setAttribute("article", article);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("isAttention", isAttention);
        request.setAttribute("tags", tags);
        request.setAttribute("remarkList", asks);
        request.setAttribute("remarkSort", remarkSort);
        return "/article/view";
    }

    @RequestMapping("/create")
    public String create(HttpServletRequest request) {
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        return "/article/create";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") long id, HttpServletRequest request) {
        Article article = articleService.findArticleById(id);
        request.setAttribute("article", article);
        List<SysTag> sysTags = articleService.findArticleTagList(article);

        String tags = "";
        for (SysTag sysTag : sysTags) {
            tags += sysTag.getName() + ";";
        }
        request.setAttribute("sysTags", sysTags);
        request.setAttribute("tags", tags);
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        List<Category> articleCategoryList = categoryService.findArticleCategoryList(article);
        request.setAttribute("articleCategoryList", articleCategoryList);
        return "article/edit";
    }

    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    public String saveArticle(@RequestParam(value = "id", defaultValue = "-1", required = false) long id, @RequestParam(value = "title", required = true) String title, @RequestParam(value = "description", required = false) String description, @RequestParam(value = "tags", required = false) String tags, @RequestParam(value = "categoryIds", required = true) String categoryIds, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String[] categoryIdStringArray = categoryIds.split(",");
        long[] categoryIdArray = new long[categoryIdStringArray.length];
        for (int i = 0; i < categoryIdArray.length; i++) {
            categoryIdArray[i] = Long.parseLong(categoryIdStringArray[i]);
        }


        String[] tagArr = null;
        if (tags == null) {
            tagArr = new String[0];
        } else {
            tagArr = tags.split(";");
        }
        //(long id,String title, String content, User creator, String[] tags, long[] categroyIds)
        Map result = articleService.createArticle(id, title, description, user, tagArr, categoryIdArray);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/article/view?id=" + id;
        } else {
            return "redirect:/article/edit?id=" + id;
        }
    }

}
