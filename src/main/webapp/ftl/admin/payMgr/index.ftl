<@override name="title">提现申请</@override>
<@override name="header">
<script type="text/javascript" src="${basePath}js/coderstar/admin/payMgr.js"></script>
</@override>
<@override name="body">
<table id="tt" title="申请列表" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/payMgr/withdrawList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>

<div id="toolbar">
    <div style="margin: 5px 5px;">
        语言名称：<input type="text" class="easyui-textbox" style="height: 18px;" name="name"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
</div>

</@override>
<@extends name="layout/questionMgr.ftl"/>