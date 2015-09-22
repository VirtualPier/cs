<@override name="title">充值</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-9 cs-main-content">

            <a name="c_contents"></a>

            <div class="cs-mod clearfix">
                <div class="mod-head common-head">
                    <h2 id="main_title">消费记录</h2>
                </div>

                <div class="mod-body cs-feed-list clearfix" id="main_contents">
                    <br/>

                    <p>1.码币为码农之星网的通用虚拟货币，可购买一切增值服务；</p>

                    <p>2.码币可以随时提现。</p>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">选择充值金额:</h3>
                        </div>

                        <div class="panel-body">
                            <form action="/user/alipay" method="post">
                                <ul class="list-group">
                                    <li class="list-group-item"><input type="radio" name="money" value="1"/> 1元 对应1码币
                                    </li>
                                    <li class="list-group-item"><input type="radio" name="money" value="5"/> 5元 对应5码币
                                    </li>
                                    <li class="list-group-item"><input type="radio" name="money" value="10"/> 10元 对应10码币
                                    </li>
                                    <li class="list-group-item"><input type="radio" name="money" value="20"/> 20元 对应20码币
                                    </li>
                                    <li class="list-group-item"><input type="radio" name="money" value="50"/> 50元 对应50码币
                                    </li>
                                    <li class="list-group-item"><input type="radio" name="money" value="100"/> 100元
                                        对应100码币
                                    </li>
                                </ul>
                                <button type="submit" class="btn btn-info">立即充值</button>
                            </form>

                        </div>
                    </div>
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