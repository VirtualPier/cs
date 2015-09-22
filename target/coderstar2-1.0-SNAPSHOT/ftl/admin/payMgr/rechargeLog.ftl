<@override name="title">用户充值记录</@override>
<@override name="header">
<script type="text/javascript" src="${basePath}js/coderstar/admin/rechargeLog.js"></script>
</@override>
<@override name="body">
<table id="tt" title="用户充值记录" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/payMgr/rechargeList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
</@override>
<@extends name="layout/payMgr.ftl"/>