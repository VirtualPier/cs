<@override name="title">提现申请</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" charset="UTF-8"
      href="/js/lib/bootstrap-validator/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="/js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
    var maxMoney = ${balance};
</script>
<script type="text/javascript" src="/js/coderstar/front/user/withdraw.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-9 cs-main-content">

            <a name="c_contents"></a>

            <div class="cs-mod clearfix">
                <div class="mod-head common-head">
                    <h2 id="main_title">提现申请</h2>
                </div>

                <div class="mod-body cs-feed-list clearfix" id="main_contents">
                    <p style="padding-top:10px;padding-bottom:10px;padding-left:100px;padding-right:100px;">
                        提交申请后,系统正常情况下会在24小时之内给你响应,如果提现人数过多最迟不会超过三个工作日,本平台提现手续费用为提现金额的15%.</p>
                    <br/>

                    <p class="warning">${msg}</p>

                    <form class="form-horizontal" id="applyWithDrawForm" action="/user/applyWithDraw"
                          name="applyWithDrawForm" method="post">
                        <div class="form-group">
                            <label for="moneyIpt" class="col-sm-2 control-label">提现金额</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="moneyIpt" placeholder="提现金额" name="money"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="payAccount" class="col-sm-2 control-label">支付宝账号</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="payAccount" placeholder="支付账号"
                                       name="payAccount"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="commentsIpt" class="col-sm-2 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>

                            <div class="col-sm-10">
                                <textarea class="form-control" id="commentsIpt" placeholder="备注"
                                          name="comments"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">提交申请</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <!-- 侧边栏 -->
        <#include "user/sliderBar.ftl">
        <!-- end 侧边栏 -->
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>