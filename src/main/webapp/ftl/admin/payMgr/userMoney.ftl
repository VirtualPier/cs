<@override name="title">用户资金</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/coderstar/admin/userMoney.js"></script>
</@override>
<@override name="body">
<table id="tt" title="用户资金" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/userMgr/userList/" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
</@override>
<@extends name="layout/payMgr.ftl"/>