package org.ligson.coderstar2.question.admin.controllers;

import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.user.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/questionMgr")
public class QuestionMgrController {
    private static final String prefix = "admin/questionMgr/";
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;

    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String index() {
        return prefix + "index";
    }

    @RequestMapping("/questionList")
    @ResponseBody
    public Map<String, Object> questionList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        return questionService.questionList(offset, max);
    }

    @RequestMapping("deleteQuestion")
    @ResponseBody
    public Map<String, Object> deleteQuestion(@RequestParam("ids") String ids) {
        String[] sIds = ids.split(",");
        long[] lIds = new long[sIds.length];
        for (int i = 0; i < sIds.length; i++) {
            lIds[i] = Long.parseLong(sIds[i]);
        }
        return questionService.deleteQuestion(lIds);
    }

    @RequestMapping("/editeQuestion")
    @ResponseBody
    public Map<String, Object> editeQuestion(@RequestParam("id") String id) {
        String[] sIds = id.split(",");
        long[] lIds = new long[sIds.length];
        for (int i = 0; i < sIds.length; i++) {
            lIds[i] = Long.parseLong(sIds[i]);
        }
        Map<String, Object> result = questionService.modifyQuestionState(lIds, Question.STATE_PUBLISH);
        return result;
    }

    @RequestMapping("/syncQuestionIndex")
    @ResponseBody
    public Map<String, Object> syncQuestionIndex() {
        Map<String, Object> result = questionService.syncQuestionIndex();
        return result;
    }

    @RequestMapping("/categoryMgr")
    public String categoryMgr() {
        return prefix + "categoryMgr";
    }

    @RequestMapping("/syncIndex")
    @ResponseBody
    public Map<String, Object> syncIndex(@RequestParam("questionIds") String questionIds) {
        String[] idArray = questionIds.split(",");
        long[] ids = new long[idArray.length];
        int i = 0;
        for (String qId : idArray) {
            ids[i++] = Long.parseLong(qId);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        questionService.syncIndex(ids);
        return result;
    }

    @RequestMapping("/categoryList")
    @ResponseBody
    public Map<String, Object> categoryList(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        int total = categoryService.list().size();
        List<Category> categoryList = categoryService.list(max, offset);
        result.put("total", total);
        result.put("rows", categoryList);
        return result;
    }

    @RequestMapping("/addCategory")
    @ResponseBody
    public Map<String, Object> addCategory(String name, String description, int sortIndex) {
        Map<String, Object> result = categoryService.addCategory(name, description, sortIndex);
        return result;
    }

    @RequestMapping("/editCategory")
    @ResponseBody
    public Map<String, Object> editCategory(long id, String name, String description, int sortIndex) {
        return categoryService.modifyCategory(id, name, description, sortIndex);
    }

    @RequestMapping("/deleteCategory")
    @ResponseBody
    public List<Map<String, Object>> deleteCategory(@RequestParam("ids") String ids, HttpServletRequest request) {
        User user = (User) request.getAttribute("adminUser");
        Map<String, Object> result = new HashMap<>();
        String[] idString = ids.split(",");
        long[] idArray = new long[idString.length];
        int i = 0;
        for (String idStr : idString) {
            idArray[i++] = Long.parseLong(idStr);
        }
        return categoryService.deleteCategoryList(user, idArray);
    }

    public static String getPrefix() {
        return prefix;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
