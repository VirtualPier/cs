<html>
<head>
    <title><@block name="title"></@block>-支付管理</title>
<#include "layout/adminCommonHead.ftl">
<@block name="header"></@block>
</head>

<body class="easyui-layout">
<#include "layout/adminCommonBody.ftl">
<div id="westPanel" data-options="region:'west',split:false" title="导航菜单" style="width:150px;" about="systemMgr">
    <div class="easyui-accordion" style="width:148px;height:100%;">
        <div title="交易管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
            <ul class="easyui-tree">
                <li><a href="/payMgr/index">提现申请</a>
                </li>
                <!--统计接入APP超市的分布与数量以及名称-->
                <#--<li><a href="/payMgr/rechargeLog">充值记录</a></li>-->
                <!--统计商品的销售情况-->
                <li><a href="/payMgr/payOrder">支付订单</a></li>
                <!--统计商品的销售情况-->
                <li><a href="/payMgr/userMoney">用户资金</a></li>
                <!--统计订单的金额数-->
            </ul>
        </div>
    </div>

</div>

<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'" id="mainDiv">
<@block name="body"></@block>
</div>
</body>
</html>