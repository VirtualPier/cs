package org.ligson.coderstar2.article.controllers;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.article.domains.Article;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
        List<List<Category>> articleCategoryList = articleService.findCategoryByArticleList(articleList);
        List<List<SysTag>> articleTagList = articleService.findAllArticleTagList(articleList);

        int total = (int) result.get("total");
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("tagId", tagId);
        request.setAttribute("max", max);
        request.setAttribute("offset", offset);
        request.setAttribute("order", order);
        request.setAttribute("articleTagList", articleTagList);
        request.setAttribute("articleCategoryList", articleCategoryList);
        request.setAttribute("articleList", articleList);
        request.setAttribute("total", total);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("sysTags", sysTags);
        request.setAttribute("weekCount", weekCount);
        request.setAttribute("monthCount", monthCount);
        request.setAttribute("allCount", allCount);
        return "article/index";
    }

    @RequestMapping("/article/save")
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
        Map result = articleService.createArticle(title, content, user, tagArr, categoryIdArray);
        boolean success = (boolean) result.get("success");
        if (success) {
            return "redirect:/article/index";
        } else {
            return "redirect:/article/create";
        }
    }
}
