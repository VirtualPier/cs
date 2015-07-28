package org.ligson.coderstar2.user.controllers;

import com.alipay.util.AlipaySubmit;
import org.apache.commons.lang.StringUtils;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.domains.SysTag;
import org.ligson.coderstar2.system.systag.service.SysTagService;
import org.ligson.coderstar2.user.domains.User;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by ligson on 2015/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;
    @Autowired
    @Qualifier("payService")
    private PayService payService;
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;

    @Autowired
    @Qualifier("sysTagService")
    private SysTagService sysTagService;

    public SysTagService getSysTagService() {
        return sysTagService;
    }

    public void setSysTagService(SysTagService sysTagService) {
        this.sysTagService = sysTagService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") long id, HttpServletRequest request) {
        User user = userService.findUserById(id);
        if (user != null) {
            int questionTotal = questionService.countByCreatorAndState(user, Question.STATE_PUBLISH);
            int articleTotal = articleService.countByCreatorAndState(user, Question.STATE_PUBLISH);
            List<SysTag> sysTags = sysTagService.findUserGoodTag(user, 10);
            request.setAttribute("viewUser", user);
            request.setAttribute("questionTotal", questionTotal);
            request.setAttribute("articleTotal", articleTotal);
            request.setAttribute("tags", sysTags);
            return "user/view";
        }
        return "404";
    }

    @RequestMapping("/loadUserArticles")
    @ResponseBody
    public Map<String, Object> loadUserArticles(@RequestParam("id") long id, @RequestParam("offset") int offset) {
        User user = userService.findUserById(id);
        List<Article> articleList = articleService.findAllArticleByCreatorAndState(user, Article.STATE_PUBLISH, "createDate", "desc", offset, 10);
        int total = articleService.countByCreatorAndState(user, Article.STATE_PUBLISH);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", total);
        result.put("articleList", articleList);
        return result;
    }

    @RequestMapping("/loadUserQuestions")
    @ResponseBody
    public Map<String, Object> loadUserQuestions(@RequestParam("id") long id, @RequestParam("offset") int offset) {
        User user = userService.findUserById(id);

        List<Question> questionList = questionService.findAllQuestionByCreatorAndState(user, Question.STATE_PUBLISH, "createDate", "desc", offset, 10);
        int total = questionService.countByCreatorAndState(user, Question.STATE_PUBLISH);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", total);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/myPublish")
    public String myPublish() {
        return "user/myPublish";
    }

    @RequestMapping("/loadMyCreateQuestion")
    @ResponseBody
    public Map<String, Object> loadMyCreateQuestion(@RequestParam(value = "current", required = false, defaultValue = "1") int page, @RequestParam(value = "rowCount", required = false, defaultValue = "10") int max, @RequestParam(value = "searchPhrase", required = false) String searchPhrase, HttpServletRequest request) {
        String sort = "createDate";
        String order = "desc";
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            if (name.startsWith("sort")) {
                int startIndex = name.indexOf("[");
                int endIndex = name.indexOf("]");
                sort = name.substring(startIndex + 1, endIndex);
                order = request.getParameter(name);
                break;
            }
        }
        int offset = (page - 1) * max;
        User user = (User) request.getSession().getAttribute("user");
        String title = searchPhrase;
        int total = questionService.countByCreatorAndStateAndTitleLike(user, -1, title);
        if (max == -1) {
            max = total;
        }
        List<Question> articleList = questionService.findAllQuestionByUserAndTitleLikeOrder(user, title, offset, max, sort, order);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("rows", articleList);
        result.put("total", total);
        result.put("current", page);
        result.put("rowCount", max);
        return result;
    }

    @RequestMapping("/myReply")
    public String myReply() {
        return "user/myReply";
    }

    @RequestMapping("/myAttention")
    public String myAttention() {
        return "user/myAttention";
    }

    @RequestMapping("/loadMyAttentionQuestion")
    @ResponseBody
    public Map<String, Object> loadMyAttentionQuestion(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Question> questionList = questionService.findAllAttentionQuestion(user, offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("questionList", questionList);
        return result;
    }

    @RequestMapping("/myArticle")
    public String myArticle() {
        return "user/myArticle";
    }

    @RequestMapping("/loadMyArticle")
    @ResponseBody
    public Map<String, Object> loadMyArticle(@RequestParam(value = "current", required = false, defaultValue = "1") int page, @RequestParam(value = "rowCount", required = false, defaultValue = "10") int max, @RequestParam(value = "searchPhrase", required = false) String searchPhrase, HttpServletRequest request) {
        String sort = "createDate";
        String order = "desc";
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            if (name.startsWith("sort")) {
                int startIndex = name.indexOf("[");
                int endIndex = name.indexOf("]");
                sort = name.substring(startIndex + 1, endIndex);
                order = request.getParameter(name);
                break;
            }
        }
        int offset = (page - 1) * max;
        User user = (User) request.getSession().getAttribute("user");
        String title = searchPhrase;
        int total = articleService.countByCreatorAndStateAndTitleLike(user, -1, title);
        if (max == -1) {
            max = total;
        }
        List<Article> articleList = articleService.findAllArticleByUserAndTitleLikeOrder(user, title, offset, max, sort, order);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("rows", articleList);
        result.put("total", total);
        result.put("current", page);
        result.put("rowCount", max);
        return result;
    }


    @RequestMapping("/myAttentionArticle")
    public String myAttentionArticle() {
        return "user/myAttentionArticle";
    }

    @RequestMapping("/loadMyAttentionArticle")
    @ResponseBody
    public Map<String, Object> loadMyAttentionArticle(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Article> articleList = articleService.findAllAttentionArticle(user, offset, 10);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("articleList", articleList);
        return result;
    }

    @RequestMapping("/deleteArticleAttention")
    public String deleteArticleAttention(@RequestParam("id") long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Article article = articleService.findArticleById(id);
        articleService.removeAttention(user, article);
        return "redirect:/user/myAttentionArticle";
    }


    @RequestMapping("/deleteQuestionAttention")
    public String deleteQuestionAttention(@RequestParam("id") long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Question question = questionService.findQuestionById(id);
        Map<String, Object> map = questionService.removeAttention(user, question);
        return "redirect:/user/myAttention";
    }

    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("ids") String ids, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String[] idString = ids.split(",");
        long[] idArray = new long[idString.length];
        for (int i = 0; i < idArray.length; i++) {
            idArray[i] = Long.parseLong(idString[i]);
        }
        Map<String, Object> result = questionService.deleteQuestion(idArray);
        return "redirect:/user/myPublish";
    }

    @RequestMapping("/deleteArticle")
    public String deleteArticle(@RequestParam("id") long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        articleService.deleteArticle(user, id);
        return "redirect:/user/myArticle";
    }

    @RequestMapping("/myRechangeLog")
    public String myRechangeLog() {
        return "user/myRechangeLog";
    }

    @RequestMapping("/loadMyRechangeLog")
    @ResponseBody
    public Map<String, Object> loadMyRechangeLog(int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return payService.findAllPayOrderByUser(user, offset, 10);
    }

    @RequestMapping("/myWithdrawLog")
    public String myWithdrawLog() {
        return "user/myWithdrawLog";
    }

    @RequestMapping("/loadMyWithdrawLog")
    @ResponseBody
    public Map<String, Object> loadMyWithdrawLog(@RequestParam("offset") int offset, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        List<Withdraw> withdraws = payService.loadMyWithdrawLogList(user, offset, 10, "createDate", "desc");
        result.put("withdrawList", withdraws);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/rechange")
    public String rechange() {
        return "user/rechange";
    }

    @RequestMapping("/alipay")
    public void alipay(@RequestParam("money") int money, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = payService.recharge(user, money);
        Map map1 = (Map) map.get("requestMap");
        String html = AlipaySubmit.buildRequest(map1, "post", "确认");
        try {
            response.setContentType("text/html");
            response.getWriter().print(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/alipay_notify")
    public String alipay_notify(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map result = payService.payResult(request);
        request.setAttribute("msg", result.get("msg"));
        return "user/alipay_notify";
    }

    @RequestMapping("/alipay_return")
    public String alipay_return(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map result = payService.payResult(request);
        request.setAttribute("msg", result.get("msg"));
        return "user/alipay_notify";
    }

    @RequestMapping("/myTradeLog")
    public String myTradeLog() {
        return "user/myTradeLog";
    }

    @RequestMapping("/loadMyTradeLog")
    @ResponseBody
    public Map<String, Object> loadMyTradeLog(@RequestParam("offset") int offset, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<TradeRecord> tradeRecords = payService.loadMyTradeLog(user, offset, 10, "createDate", "desc");
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("tradeRecords", tradeRecords);
        return result;
    }

    @RequestMapping("/withdraw")
    public String withdraw(@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        User user1 = userService.findUserById(user.getId());
        Withdraw withdraw = payService.findWithDrawByStateAndUser(1, user1);
        if (withdraw == null) {
            request.setAttribute("msg", msg);
            request.setAttribute("balance", user1.getBalance());
            return "user/withdraw";
        } else {
            request.setAttribute("msg", "您还有一笔提现申请正在处理中,请稍后在提交!");
            return "user/alipay_notify";
        }
    }

    @RequestMapping("/applyWithDraw")
    public String applyWithDraw(@RequestParam(value = "money") double money, @RequestParam("payAccount") String payAccount, @RequestParam(value = "comments", required = false) String comments, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        User user1 = userService.findUserById(user.getId());
        Map<String, Object> result = payService.withdraw(user1, money, comments, payAccount);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user2 = userService.findUserById(user1.getId());
            request.getSession().setAttribute("user", user2);
            return "redirect:/user/myWithdrawLog";
        } else {
            String msg = (String) result.get("msg");
            model.addAttribute("msg", msg);
            return "redirect:/user/withdraw";
        }
    }

    @RequestMapping("/profile")
    public String profile(HttpServletRequest request) {
        return "user/profile";
    }

    @RequestMapping("/updateProfile")
    @ResponseBody
    public Map<String, Object> updateProfile(String nickName, int sex, String introduce, String qq, String cellphone, String email, String web, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> result = userService.updateUser(user, nickName, sex, introduce, qq, cellphone, email, web);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user2 = userService.findUserById(user.getId());
            request.getSession().setAttribute("user", user2);
        }
        return result;
    }

    @RequestMapping("/security")
    public String security() {
        return "user/security";
    }

    @RequestMapping("/checkUnique")
    @ResponseBody
    public Map<String, Object> checkUnique(@RequestParam(value = "cellphone", required = false) String cellphone, @RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (StringUtils.isNotBlank(cellphone)) {
            boolean isUnique = userService.cellphoneIsUnique(cellphone, user);
            result.put("success", true);
            result.put("valid", isUnique);
            return result;
        }

        if (StringUtils.isNotBlank(email)) {
            boolean isUnique = userService.emailIsUnique(email);
            result.put("success", true);
            result.put("valid", isUnique);
            return result;
        }
        result.put("success", false);
        result.put("valid", false);
        return result;
    }

    @RequestMapping("/modifyPhoto")
    @ResponseBody
    public Map<String, Object> modifyPhoto(@RequestParam("photo") CommonsMultipartFile photo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return userService.modifyPhoto(photo, user);
    }

    @RequestMapping("/updateSecurity")
    @ResponseBody
    public Map<String, Object> updateSecurity(String oldPassword, String newPassword, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return userService.resetPassword(user, user.getId(), oldPassword, newPassword);
    }


    @RequestMapping("/uploadFile")
    public void uploadFile(@RequestParam("CKEditorFuncNum") String CKEditorFuncNum, @RequestParam("upload") CommonsMultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> result = userService.uploadFile(user, upload);
        boolean success = (boolean) result.get("success");
        StringBuffer sb = new StringBuffer();
        if (success) {
            String url = (String) result.get("url");
            String callback = CKEditorFuncNum;
            sb.append("<script type=\"text/javascript\">");
            sb.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + url + "','')");
            sb.append("</script>");
        } else {
            String msg = (String) result.get("msg");
            sb.append("<span style='color:#FF0000;'>" + msg + "</span>");
        }
        try {
            response.getWriter().println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/uploadImage")
    public void uploadImage(@RequestParam("CKEditorFuncNum") String CKEditorFuncNum, @RequestParam("upload") CommonsMultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> result = userService.uploadFile(user, upload);
        boolean success = (boolean) result.get("success");
        StringBuffer sb = new StringBuffer();
        if (success) {
            String url = (String) result.get("url");
            String callback = CKEditorFuncNum;
            sb.append("<script type=\"text/javascript\">");
            sb.append("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + url + "','')");
            sb.append("</script>");
        } else {
            String msg = (String) result.get("msg");
            sb.append("<span style='color:#FF0000;'>" + msg + "</span>");
        }
        try {
            response.getWriter().println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
