<@override name="title">订单</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/coderstar/admin/payOrder.js"></script>
</@override>
<@override name="body">
<table id="tt" title="订单" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/payMgr/payOrderList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
</@override>
<@extends name="layout/payMgr.ftl"/>