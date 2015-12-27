package org.ligson.coderstar2.system.controllers;

import org.ligson.coderstar2.controllers.BaseController;
import org.ligson.coderstar2.system.cache.SysCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligson on 2015/7/24.
 */
@Controller
@RequestMapping("/systemMgr")
public class SystemMgr extends BaseController {
    @Resource
    private SysCache sysCache;

    @RequestMapping("/index")
    public String index() {
        return "admin/sysMgr/index";
    }

    @ResponseBody
    @RequestMapping("/loadIndexCache")
    public Map<String, Object> loadIndexCache() {
        Map<String, Object> result = new HashMap<>();
        sysCache.init();
        result.put("success", true);
        return result;
    }
}
