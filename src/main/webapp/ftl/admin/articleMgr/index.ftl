<@override name="title">文章列表</@override>
<@override name="header">
<script type="text/javascript" src="${basePath}js/coderstar/admin/articleMgr.js"></script>
</@override>
<@override name="body">
<table id="tt" title="文章列表" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/articleMgr/articleList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
<div id="toolbar">
    <div style="margin: 5px 5px;">
        语言名称：<input type="text" class="easyui-textbox" style="height: 18px;" name="name"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
</div>
<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px;"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">

    </div>
</div>
</@override>
<@extends name="layout/articleMgr.ftl"/>