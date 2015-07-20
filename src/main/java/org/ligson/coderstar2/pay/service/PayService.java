package org.ligson.coderstar2.pay.service;

import org.ligson.coderstar2.user.domains.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface PayService {

    public Map<String, Object> recharge(User currentUser, double money);

    public Map<String, Object> trade(User currentUser, int tradeType, long tradeObjId, double money, boolean isBlocked);

    public Map<String, Object> payOrderList(int offset, int max);

    public Map<String, Object> withdrawList(int offset, int max);

    /***
     * 转账
     *
     * @param fromUser
     * @param toUser
     * @param money
     * @param tradeType
     * @param tradeObjId
     * @return
     */
    public Map<String, Object> transfer(User fromUser, User toUser, double money, int tradeType, long tradeObjId);

    /***
     * 冻结资金
     *
     * @param user
     * @param money
     * @param comments
     * @param objType
     * @param objId
     * @return
     */
    public Map<String, Object> blockedMoney(User user, double money, String comments, int objType, long objId);

    public Map<String,Object> findAllPayOrderByUser(User user, int offset, int max);

    public Map<String,Object> payResult(HttpServletRequest request);
}
