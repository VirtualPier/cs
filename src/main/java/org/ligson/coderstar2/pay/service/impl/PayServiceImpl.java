package org.ligson.coderstar2.pay.service.impl;

import org.ligson.coderstar2.pay.domains.PayOrder;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.payorder.dao.PayOrderDao;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.pay.withdraw.dao.WithdrawDao;
import org.ligson.coderstar2.user.domains.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public class PayServiceImpl implements PayService {
    private PayOrderDao payOrderDao;
    private WithdrawDao withdrawDao;

    public WithdrawDao getWithdrawDao() {
        return withdrawDao;
    }

    public void setWithdrawDao(WithdrawDao withdrawDao) {
        this.withdrawDao = withdrawDao;
    }

    public PayOrderDao getPayOrderDao() {
        return payOrderDao;
    }

    public void setPayOrderDao(PayOrderDao payOrderDao) {
        this.payOrderDao = payOrderDao;
    }

    @Override
    public Map recharge(User currentUser, double money) {
        return null;
    }

    @Override
    public Map trade(User currentUser, int tradeType, long tradeObjId, double money) {
        return null;
    }

    @Override
    public Map<String, Object> payOrderList(int offset, int max) {
        Map<String, Object> result = new HashMap<>();
        List<PayOrder> payOrders = payOrderDao.list(offset, max);
        int total = payOrderDao.countAll();
        result.put("total", total);
        result.put("rows", payOrders);
        return result;
    }

    @Override
    public Map<String, Object> withdrawList(int offset, int max) {
        Map<String, Object> result = new HashMap<>();
        List<Withdraw> withdrawDaos = withdrawDao.list(offset, max);
        int total = withdrawDao.countAll();
        result.put("total", total);
        result.put("rows", withdrawDaos);
        return result;
    }
}
