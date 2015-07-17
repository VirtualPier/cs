package org.ligson.coderstar2.pay.service;

import org.ligson.coderstar2.user.domains.User;

import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public interface PayService {

    public Map recharge(User currentUser, double money);

    public Map trade(User currentUser, int tradeType, long tradeObjId, double money);
}