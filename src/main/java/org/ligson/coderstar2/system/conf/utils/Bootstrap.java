package org.ligson.coderstar2.system.conf.utils;

import org.apache.log4j.Logger;
import org.ligson.coderstar2.user.service.UserService;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;

/**
 * Created by ligson on 2015/7/16.
 * 系统初始化
 */
public class Bootstrap implements InitializingBean {
    private static Logger logger = Logger.getLogger(Bootstrap.class);
    private static File webRoot = null;
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setWebRootPath() {
        String u = this.getClass().getResource("/").getPath();
        File file = new File(u).getParentFile().getParentFile();
        webRoot = file;
    }

    public void initSuper(){
        userService.initSuper();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        setWebRootPath();


        initSuper();
        logger.info("系统启动!");
        logger.info("webRoot" + webRoot.getAbsolutePath());

    }
}
