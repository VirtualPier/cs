package org.ligson.coderstar2.article.controllers;

import com.boful.common.date.utils.DateUtils;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.domains.SysTag;
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
    public String index(@RequestParam(value = "categoryId",required = false,defaultValue = "-1")long categoryId,@RequestParam(value = "tagId",required = false,defaultValue = "-1")long tagId,@RequestParam(value = "order",required = false,defaultValue = "createDate")String order,@RequestParam(value = "max",required = false,defaultValue = "15")int max,@RequestParam(value = "offset",required = false,defaultValue = "0")int offset,HttpServletRequest request) {
        List<Category> categoryList = categoryService.list();
        request.setAttribute("categoryList", categoryList);
        List<SysTag> sysTags = articleService.hotsTag(10);
        List<List> weekCount = articleService.hotAuthors(7);
        List<List> monthCount = articleService.hotAuthors(30);
        List<List> allCount = articleService.hotAuthors(-1);
        Map<String,Object> result = articleService.findAllByCategoryIdAndTagIdOrderBy(categoryId, tagId, order,offset,max);

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("sysTags", sysTags);
        request.setAttribute("weekCount", weekCount);
        request.setAttribute("monthCount", monthCount);
        request.setAttribute("allCount", allCount);
        return "article/index";
    }
}
