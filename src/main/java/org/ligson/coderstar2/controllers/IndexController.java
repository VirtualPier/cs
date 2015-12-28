package org.ligson.coderstar2.controllers;

import com.boful.common.date.utils.DateUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.log4j.Logger;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.article.service.ArticleService;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.question.domains.Question;
import org.ligson.coderstar2.question.service.QuestionService;
import org.ligson.coderstar2.system.cache.SysCache;
import org.ligson.coderstar2.system.category.service.CategoryService;
import org.ligson.coderstar2.system.domains.Category;
import org.ligson.coderstar2.system.mail.utils.SendCloudConfig;
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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ligson on 2015/7/16.
 * souye
 */
@Controller
public class IndexController extends BaseController {
    private static final Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    @Qualifier("questionService")
    private QuestionService questionService;
    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;
    @Autowired
    @Qualifier("sysTagService")
    private SysTagService sysTagService;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("payService")
    private PayService payService;

    //@Autowired
    @Qualifier("captchaService")
    private ImageCaptchaService captchaService;

    @Autowired
    @Qualifier("sendCloudConfig")
    private SendCloudConfig cloudConfig;

    @Autowired
    @Qualifier("sysCache")
    private SysCache sysCache;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        List<Question> newQuestionList = sysCache.getNewestQuestionList();
        List<Article> newArticleList = sysCache.getNewestArticleList();
        List<Category> categoryList = sysCache.getCategoryList();
        List<Question> recommendQuestionList = sysCache.getRecommendQuestionList();
        List<Article> recommendArticleList = sysCache.getRecommendArticleList();

        List<Question> hotQuestions = sysCache.getHotQuestions();
        List<Question> waitQuestionList = sysCache.getWaitQuestionList();
        List<Question> offerQuestionList = sysCache.getOfferQuestionList();
        List<Article> hotArticles = sysCache.getHotArticles();
        List<User> hotAuthors = sysCache.getHotAuthors();
        List<User> hotReplyers = sysCache.getHotReplyers();
        List<Withdraw> withdrawList = sysCache.getWithdrawList();

        request.setAttribute("withdrawList", withdrawList);
        request.setAttribute("hotAuthors", hotAuthors);
        request.setAttribute("hotReplyers", hotReplyers);
        request.setAttribute("hotQuestions", hotQuestions);
        request.setAttribute("hotArticles", hotArticles);
        request.setAttribute("offerQuestionList", offerQuestionList);
        request.setAttribute("waitQuestionList", waitQuestionList);
        request.setAttribute("recommendQuestionList", recommendQuestionList);
        request.setAttribute("recommendArticleList", recommendArticleList);
        request.setAttribute("newQuestionList", newQuestionList);
        request.setAttribute("newArticleList", newArticleList);
        request.setAttribute("categoryList", categoryList);
        return "index/index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        if (name != null) {
            request.setAttribute("name", name);
        }
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "index/login";
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, HttpServletRequest request, Model model) {
        Map<String, Object> result = userService.login(name, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = (User) result.get("user");
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            String msg = (String) result.get("msg");
            model.addAttribute("msg", msg);
            model.addAttribute("name", name);
            return "redirect:/login";
        }

    }

    @RequestMapping("/register")
    public String register(@RequestParam(value = "nickName", required = false) String nickName, @RequestParam(value = "cellphone", required = false) String cellphone, @RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
        if (nickName != null) {
            request.setAttribute("nickName", nickName);
        }
        if (cellphone != null) {
            request.setAttribute("cellphone", cellphone);
        }
        if (email != null) {
            request.setAttribute("email", email);
        }
        return "index/register";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@RequestParam(value = "nickName") String nickName, @RequestParam(value = "cellphone") String cellphone, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email, HttpServletRequest request, Model model) {
        Map<String, Object> result = userService.register(email, nickName, cellphone, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = (User) result.get("user");
            model.addAttribute("name", user.getEmail());
            model.addAttribute("msg", "请您登陆！");
            return "redirect:/login";
        } else {
            request.setAttribute("nickName", nickName);
            request.setAttribute("cellphone", cellphone);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            return "redirect:/register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        Map<String, Object> result = userService.logout(request);
        boolean success = (boolean) result.get("success");
        return "redirect:/index";
    }

    @RequestMapping("/nologin")
    public String nologin(@RequestParam(value = "format", required = false) String format) {
        if ("html".equals(format) || format == null) {
            return "redirect:/login";
        } else {
            return "redirect:/responseJson";
        }
    }

    @RequestMapping("/checkEmailExist")
    @ResponseBody
    public Map<String, Object> checkEmailExist(String email) {
        Map<String, Object> result = new HashMap<>();
        boolean isUnique = userService.emailIsUnique(email);
        result.put("success", isUnique);
        result.put("valid", isUnique);
        return result;
    }

    @RequestMapping("/checkCellphoneExist")
    @ResponseBody
    public Map<String, Object> checkCellphoneExist(String cellphone) {
        Map<String, Object> result = new HashMap<>();
        boolean isUnique = userService.cellphoneIsUnique(cellphone);
        result.put("success", isUnique);
        result.put("valid", isUnique);
        return result;
    }

    @RequestMapping("/responseJson")
    @ResponseBody
    public Map<String, Object> responseJson(@RequestParam(value = "success") boolean success, @RequestParam(value = "msg") String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/ad")
    public String ad() {
        return "index/ad";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "index/contact";
    }

    @RequestMapping("/friendLinks")
    public String friendLinks() {
        return "index/friendLinks";
    }


    @RequestMapping("/searchKey")
    @ResponseBody
    public Map<String, Object> searchKey(String key, Integer type) {
        //返回的格式为[{value:}}
        Map<String, Object> map = new HashMap<>();
        int maxWorld = 10;
        //1、根据key进行在document中查找对应匹配关键字,根据类型获取对应关键字
        List<String> words = null;
        if (1 == type) {
            //question
            words = questionService.hotKey(key, maxWorld);
        } else {
            //article
            words = articleService.hotKey(key, maxWorld);
        }
        List<Map<String, String>> ll = new ArrayList<>();
        for (String word : words) {
            Map<String, String> hotKeyMap = new HashMap<>();
            hotKeyMap.put("value", word);
            if (!hotKeyMap.isEmpty()) {
                ll.add(hotKeyMap);
            }
        }
        map.put("hotKeys", ll);
        return map;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchType", defaultValue = "1") int searchType, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "categoryId", defaultValue = "-1", required = false) long categoryId, @RequestParam(value = "tagId", defaultValue = "-1", required = false) long tagId, @RequestParam(value = "sort", defaultValue = "createDate", required = false) String sort, @RequestParam(value = "order", defaultValue = "desc", required = false) String order, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset, @RequestParam(value = "max", defaultValue = "15", required = false) int max, HttpServletRequest request) {
        List modelList = null;
        int total = 0;
        if (searchType == 1) {
            Map<String, Object> result = questionService.searchQuestion(title, tagId, categoryId, max, offset, sort, order);
            modelList = (List) result.get("questionList");
            total = (int) result.get("total");
        } else {
            Map<String, Object> result = articleService.searchArticle(title, tagId, categoryId, max, offset, sort, order);
            modelList = (List) result.get("articleList");
            total = (int) result.get("total");
        }
        List<Category> categoryList = categoryService.list();
        List<List> tagCount = sysTagService.hotsTag(10);

        request.setAttribute("tagCount", tagCount);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("modelList", modelList);
        request.setAttribute("sort", sort);
        request.setAttribute("total", total);
        request.setAttribute("searchType", searchType);
        request.setAttribute("order", order);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("tagId", tagId);
        request.setAttribute("title", title);
        request.setAttribute("max", max);
        request.setAttribute("offset", offset);
        return "index/search";

    }

    @RequestMapping("/uploadFile")
    public void uploadFile(@RequestParam("upload") CommonsMultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> result = userService.uploadFile(user, upload);
        return;
    }

    @RequestMapping("/forgotpassword")
    public String forgotpassword(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        if (email != null) {
            request.setAttribute("email", email);
        }
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "index/forgotpassword";
    }

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            String captchaId = request.getSession().getId();
            BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());

            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.setContentType("image/jpeg");

            ImageIO.write(challenge, "jpeg", jpegOutputStream);
            byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

            ServletOutputStream respOs = response.getOutputStream();
            respOs.write(captchaChallengeAsJpeg);
            respOs.flush();
            respOs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/submitMail")
    public String submitMail(String email, String code, HttpServletRequest request, Model model) {
        boolean isUnique = userService.emailIsUnique(email);
        boolean isValid = captchaService.validateResponseForID(request.getSession().getId(), code);

        model.addAttribute("email", email);
        if (isUnique) {
            model.addAttribute("msg", "邮箱不存在");
            return "redirect:/forgotpassword";
        }
        if (!isValid) {
            model.addAttribute("msg", "验证码过期");
            return "redirect:/forgotpassword";
        }
        return "redirect:/waitCheckEmail";
    }

    @RequestMapping("/waitCheckEmail")
    public String waitCheckEmail(String email, HttpServletRequest request) {
        User user = userService.findUserByEmail(email);
        if (user != null) {
            Map<String, String> varMap = new HashMap<>();
            varMap.put("appName", cloudConfig.getAppName());
            varMap.put("appDomain", cloudConfig.getAppDomain());
            varMap.put("email", email);
            varMap.put("nickName", user.getNickName());
            String key = userService.emailResetPasswordKey(user);
            String url = cloudConfig.getAppDomain() + "/index/resetPwd?id=" + user.getId() + "&key=" + key;
            varMap.put("url", url);
            varMap.put("date", DateUtils.format(new Date(), DateUtils.FORMAT_2));
            boolean isSuccess = cloudConfig.sendMail(email, "resetpwd", varMap);
            logger.debug("send mail to " + email + " is success:" + isSuccess);
            request.setAttribute("success", isSuccess);
        } else {
            request.setAttribute("success", false);
        }
        request.setAttribute("email", email);
        return "index/waitResult";
    }

    @RequestMapping("/resetPwd")
    public String resetPwd(@RequestParam(value = "id", required = true) long id, @RequestParam(value = "key", required = true) String key, @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
        request.setAttribute("id", id);
        request.setAttribute("key", key);
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "index/resetPwd";
    }

    @RequestMapping("/resetUserPwd")
    public String resetUserPwd(long id, String key, String password, Model model) {
        Map<String, Object> result = userService.resetPwdByIdAndKey(id, key, password);
        boolean success = (boolean) result.get("success");
        if (success) {
            User user = userService.findUserById(id);
            model.addAttribute("name", user.getEmail());
            model.addAttribute("msg", "请您登陆！");
            return "redirect:/index/login";
        } else {
            String msg = result.get("msg").toString();
            model.addAttribute("id", id);
            model.addAttribute("key", key);
            model.addAttribute("msg", msg);
            return "redirect:/resetPwd";
        }
    }
}
