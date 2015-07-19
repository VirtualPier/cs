package org.ligson.coderstar2.pay.service.impl;

import org.ligson.coderstar2.pay.blockedfund.dao.BlockedFundDao;
import org.ligson.coderstar2.pay.domains.BlockedFund;
import org.ligson.coderstar2.pay.domains.PayOrder;
import org.ligson.coderstar2.pay.domains.TradeRecord;
import org.ligson.coderstar2.pay.domains.Withdraw;
import org.ligson.coderstar2.pay.payorder.dao.PayOrderDao;
import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.pay.traderecord.dao.TradeRecordDao;
import org.ligson.coderstar2.pay.withdraw.dao.WithdrawDao;
import org.ligson.coderstar2.user.dao.UserDao;
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
    private TradeRecordDao tradeRecordDao;
    private UserDao userDao;
    private BlockedFundDao blockedFundDao;

    public BlockedFundDao getBlockedFundDao() {
        return blockedFundDao;
    }

    public void setBlockedFundDao(BlockedFundDao blockedFundDao) {
        this.blockedFundDao = blockedFundDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TradeRecordDao getTradeRecordDao() {
        return tradeRecordDao;
    }

    public void setTradeRecordDao(TradeRecordDao tradeRecordDao) {
        this.tradeRecordDao = tradeRecordDao;
    }

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
    public Map<String, Object> trade(User currentUser, int tradeType, long tradeObjId, double money, boolean isBlocked) {
        Map<String, Object> result = new HashMap<>();
        if (money > 0 && money <= currentUser.getBalance()) {
            TradeRecord tradeRecord = new TradeRecord();
            tradeRecord.setMoney(money);
            tradeRecord.setObjId(tradeObjId);
            tradeRecord.setType(TradeRecord.Type_PAY);
            tradeRecord.setUser(currentUser);
            tradeRecordDao.saveOrUpdate(tradeRecord);
            if (isBlocked) {
                Map<String, Object> result2 = blockedMoney(currentUser, money, "悬赏问题", tradeType, tradeObjId);
                boolean success = (boolean) result2.get("success");
                if (!success) {
                    return result2;
                }
            }
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "余额不足!");
        }

        return result;
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

    @Override
    public Map<String, Object> transfer(User fromUser, User toUser, double money, int tradeType, long tradeObjId) {
        Map<String, Object> result = new HashMap<>();
        if (money > fromUser.getBalance()) {
            result.put("success", false);
            result.put("msg", "余额不足!");
            return result;
        }
        //支出
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setMoney(money);
        tradeRecord.setType(TradeRecord.Type_PAY);
        tradeRecord.setUser(fromUser);
        tradeRecord.setObjId(tradeObjId);
        tradeRecord.setType(tradeType);
        tradeRecordDao.saveOrUpdate(tradeRecord);

        //收入
        TradeRecord tradeRecord2 = new TradeRecord();
        tradeRecord2.setMoney(money);
        tradeRecord2.setType(TradeRecord.Type_INCOME);
        tradeRecord2.setUser(toUser);
        tradeRecord2.setObjId(tradeObjId);
        tradeRecord2.setObjType(tradeType);
        //支出账户
        tradeRecordDao.saveOrUpdate(tradeRecord2);
        fromUser.setBalance(fromUser.getBalance() - money);
        userDao.saveOrUpdate(fromUser);
        //收入账户
        toUser.setBalance(toUser.getBalance() + money);
        userDao.saveOrUpdate(toUser);

        result.put("success", true);
        return result;
    }

    @Override
    public Map<String, Object> blockedMoney(User user, double money, String comments, int objType, long objId) {
        Map<String, Object> result = new HashMap<>();
        if (money > 0 && money <= user.getBalance()) {
            BlockedFund blockedFund = new BlockedFund();
            blockedFund.setMoney(money);
            blockedFund.setComments(comments);
            blockedFund.setUser(user);
            blockedFund.setState(BlockedFund.STATE_LOCK);
            blockedFund.setObjType(objType);
            blockedFund.setObjId(objId);
            blockedFundDao.saveOrUpdate(blockedFund);

            user.setBlockedFund(user.getBlockedFund() + money);
            userDao.saveOrUpdate(user);

            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "余额不足！");
        }
        return result;
    }
}
