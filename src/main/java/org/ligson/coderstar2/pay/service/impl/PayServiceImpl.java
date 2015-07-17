package org.ligson.coderstar2.pay.service.impl;

import org.ligson.coderstar2.pay.service.PayService;
import org.ligson.coderstar2.user.domains.User;

import java.util.Map;

/**
 * Created by ligson on 2015/7/16.
 */
public class PayServiceImpl implements PayService {
    @Override
    public Map recharge(User currentUser, double money) {
        return null;
    }

    @Override
    public Map trade(User currentUser, int tradeType, long tradeObjId, double money) {
        return null;
    }
}
