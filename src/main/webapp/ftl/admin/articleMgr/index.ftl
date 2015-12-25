<@override name="title">文章列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/coderstar/admin/articleMgr.js"></script>
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
<div id="recommendDlg" title="推荐文章" class="easyui-dialog" closed="true" style="width:400px;height:180px;">
    <form id="recommendForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value=""/>
        <table style="margin-top:10px;margin-left:10px;">
            <tr>
                <td><label>推荐度:</label></td>
                <td><input id="recommendNumTbx" class="easyui-textbox" type="text" name="recommendNum"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td><label>海报:</label></td>
                <td><input class="input" name="poster" type="file" id="poster"/></td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRecommendForm()">提交</a>
        </div>
    </form>
</div>
</@override>
<@extends name="layout/articleMgr.ftl"/>