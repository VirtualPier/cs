package org.ligson.coderstar2.pay.service.impl;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import org.dom4j.DocumentException;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public Map<String, Object> recharge(User currentUser, double money) {
        PayOrder payOrder = new PayOrder();
        payOrder.setComments("码农之星在线充值!");
        payOrder.setMoney(money);
        payOrder.setState(PayOrder.STATE_PAYING);
        payOrder.setUser(currentUser);
        payOrder.setType(PayOrder.TYPE_ALIPAY);
        payOrderDao.saveOrUpdate(payOrder);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("payOrder", payOrder);
        result.put("requestMap", alipayParams(payOrder));
        return result;
    }

    private Map alipayParams(PayOrder payOrder) {

        String basePath = "http://127.0.0.1:8080";
        String appName = "码农之星在线充值!";
        //支付类型
        String payment_type = "1";
        //必填，不能修改
        //服务器异步通知页面路径
        String notify_url = basePath + "/user/alipay_notify";
        //需http://格式的完整路径，不能加?id=123这类自定义参数
        //页面跳转同步通知页面路径
        String return_url = basePath + "/user/alipay_return";
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
        //商户订单号
        String out_trade_no = payOrder.getGuid();
        //商户网站订单系统中唯一订单号，必填
        //订单名称
        String subject = appName + "在线充值";
        //必填
        //付款金额
        String total_fee = payOrder.getMoney() + "";
        //必填
        //订单描述
        String body = appName + "充值付款，订单号为：" + payOrder.getGuid() + "，金额为：" + total_fee;
        //商品展示地址
        String show_url = basePath;
        //需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

        //防钓鱼时间戳
        String anti_phishing_key = null;
        try {
            anti_phishing_key = AlipaySubmit.query_timestamp();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //若要使用请调用类文件submit中的query_timestamp函数

        //客户端的IP地址
        //String exter_invoke_ip = "221.238.152.26";
        //非局域网的外网IP地址，如：221.0.0.1

        String sign_type = "MD5";
        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        return sParaTemp;
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

    @Override
    public Map<String, Object> findAllPayOrderByUser(User user, int offset, int max) {
        List<PayOrder> payOrders = payOrderDao.findAllByUser(user, offset, max);
        int total = payOrderDao.countByUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("total", total);
        result.put("payOrders", payOrders);
        return result;
    }

    @Override
    public Map<String, Object> payResult(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        //获取支付宝POST过来反馈信息
        Map<String, String> params1 = new HashMap<String, String>();
        Map<String, Object> requestParams = request.getParameterMap();
        for (String key : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(key);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < values.length; i++) {
                buffer.append(values[i]);
                if (i != values.length - 1) {
                    buffer.append(",");
                }
            }
            params1.put(key, buffer.toString());
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = request.getParameter("out_trade_no");
        //支付宝交易号
        String trade_no = request.getParameter("trade_no");
        //交易状态
        String trade_status = request.getParameter("trade_status");
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if (AlipayNotify.verify(params1)) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            PayOrder payOrder = payOrderDao.findBy("guid", out_trade_no);
            if (payOrder != null) {
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
                    if (payOrder.getState() != PayOrder.STATE_SUCEESS) {
                        User user = payOrder.getUser();
                        payOrder.setState(PayOrder.STATE_SUCEESS);
                        payOrder.setComments("支付宝订单号:" + trade_no);
                        payOrder.setOutOrder(trade_no);
                        user.setBalance(user.getBalance() + payOrder.getMoney());
                        userDao.saveOrUpdate(user);
                    }
                    result.put("success", true);
                    result.put("msg", "交易成功!");
                } else {
                    payOrder.setState(PayOrder.STATE_FAIL);
                    payOrder.setComments("支付宝订单号:" + trade_no);
                    payOrderDao.saveOrUpdate(payOrder);

                    result.put("success", false);
                    result.put("msg", "充值失败！");
                }
            } else {
                result.put("success", false);
                result.put("msg", "订单不存在!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "你可以买彩票去了,这几率你都能遇到!");
        }
        return result;
    }

    @Override
    public List<TradeRecord> loadMyTradeLog(User user, int offset, int max, String sort, String order) {
        return tradeRecordDao.findAllByUser(user, offset, max, sort, order);
    }

    @Override
    public List<Withdraw> loadMyWithdrawLogList(User user, int offset, int max, String sort, String order) {
        List<Withdraw> withdraws = withdrawDao.findAllByUser(user, offset, max, sort, order);
        return withdraws;
    }

    @Override
    public Withdraw findWithDrawByStateAndUser(int state, User user1) {
        return withdrawDao.findAllByUserAndState(user1, state);
    }

    @Override
    public Map<String, Object> withdraw(User currentUser, double money, String comments, String payAccount) {
        Map<String, Object> result = new HashMap<>();
        if (money > currentUser.getBalance()) {
            result.put("success", false);
            result.put("msg", "超出余额了!");
            return result;
        }
        if (payAccount == null) {
            result.put("success", false);
            result.put("msg", "支付账户没有填写");
            return result;
        }
        Withdraw withdraw = new Withdraw();
        withdraw.setUser(currentUser);
        withdraw.setComments(comments);
        withdraw.setMoney(money);
        withdraw.setState(Withdraw.STATE_APPLY);
        withdraw.setPayAccount(payAccount);
        withdrawDao.saveOrUpdate(withdraw);

        Map<String, Object> result2 = blockedMoney(currentUser, money, "用户提现", 3, withdraw.getId());
        boolean success = (boolean) result2.get("succcess");
        if (!success) {
            return result2;
        }
        result.put("success", true);
        return result;
    }
}
