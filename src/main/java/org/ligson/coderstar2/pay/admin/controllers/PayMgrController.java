package org.ligson.coderstar2.pay.admin.controllers;

import org.ligson.coderstar2.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by ligson on 2015/7/17.
 */
@Controller
@RequestMapping("/payMgr")
public class PayMgrController {
    private static final String prefix = "admin/payMgr/";
    @Autowired
    @Qualifier("payService")
    private PayService payService;

    public PayService getPayService() {
        return payService;
    }

    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @RequestMapping("/index")
    public String index() {
        return prefix + "index";
    }

    @RequestMapping("/payOrderList")
    @ResponseBody
    public Map<String, Object> payOrderList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        return payService.payOrderList(offset, max);
    }

    @RequestMapping("/withdrawList")
    @ResponseBody
    public Map<String, Object> withdrawList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows) {
        int max = rows;
        int offset = (page - 1) * max;
        return payService.withdrawList(offset, max);
    }

    @RequestMapping("/withdraw")
    @ResponseBody
    public Map<String, Object> withdraw(String ids) {
        String[] widthDrawIds = ids.split(",");
        return payService.allowWithDraw(null);
    }
}
