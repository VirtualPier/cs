package org.ligson.coderstar2.user.interceptor;

import org.ligson.coderstar2.article.admin.controllers.ArticleMgrController;
import org.ligson.coderstar2.article.controllers.ArticleController;
import org.ligson.coderstar2.article.domains.Article;
import org.ligson.coderstar2.controllers.AdminController;
import org.ligson.coderstar2.question.admin.controllers.QuestionMgrController;
import org.ligson.coderstar2.question.controllers.QuestionController;
import org.ligson.coderstar2.user.admin.controllers.UserMgrController;
import org.ligson.coderstar2.user.controllers.UserController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by ligson on 2015/7/16.
 * 登陆检查
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String methodName = method.getName();
            Object bean = handlerMethod.getBean();
            if (bean instanceof AdminController) {
                String[] uncheckAction = new String[]{"index", "login", "checkLogin"};
                boolean isExsit = false;
                for (String actionName : uncheckAction) {
                    if (actionName.equals(methodName)) {
                        isExsit = true;
                        break;
                    }
                }
                if (!isExsit) {
                    return adminLoginCheck(request, response);
                }
            }

            if (bean instanceof UserController) {
                String[] uncheckAction = new String[]{"view"};
                boolean isExsit = false;
                for (String actionName : uncheckAction) {
                    if (actionName.equals(methodName)) {
                        isExsit = true;
                        break;
                    }
                }
                if (!isExsit) {
                    return userLoginCheck(request, response);
                }
            }


            if (bean instanceof UserMgrController) {
                return adminLoginCheck(request, response);
            }

            if (bean instanceof QuestionController) {
                String[] uncheckAction = new String[]{"index", "view"};
                boolean isExsit = false;
                for (String actionName : uncheckAction) {
                    if (actionName.equals(methodName)) {
                        isExsit = true;
                        break;
                    }
                }
                if (!isExsit) {
                    return userLoginCheck(request, response);
                }
            }
            if (bean instanceof QuestionMgrController) {
                return adminLoginCheck(request, response);
            }
            if (bean instanceof ArticleMgrController) {
                return adminLoginCheck(request, response);
            }

            if (bean instanceof ArticleController) {
                String[] uncheckAction = new String[]{"index", "view"};
                boolean isExsit = false;
                for (String actionName : uncheckAction) {
                    if (actionName.equals(methodName)) {
                        isExsit = true;
                        break;
                    }
                }
                if (!isExsit) {
                    return userLoginCheck(request, response);
                }
            }
        }
        return true;
    }

    private boolean adminLoginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return loginCheck(request, response, "adminUser", "/admin/index");
    }

    private boolean userLoginCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return loginCheck(request, response, "user", "/index/nologin");
    }

    private boolean loginCheck(HttpServletRequest request, HttpServletResponse response, String sessionAttr, String errorUrl) throws Exception {
        Object object = request.getSession().getAttribute(sessionAttr);
        if (object == null) {
            request.setAttribute("success", false);
            request.setAttribute("msg", "请登录!");
            request.getRequestDispatcher(errorUrl).forward(request, response);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
