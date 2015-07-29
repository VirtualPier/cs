<@override name="title">分类列表</@override>
<@override name="header">
<script type="text/javascript" src="${basePath}js/coderstar/admin/categoryMgr.js"></script>
</@override>
<@override name="body">
<table id="tt" title="分类列表" class="easyui-datagrid" style="height:300px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/questionMgr/categoryList" iconCls="icon-save" pagination="true">
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
    <form id="fm" method="post">

        <div class="fitem">
            <label style="width:100px;">
                语言名称</label>
            <input class="easyui-validatebox" id="name" required="true" name="name" style="width:200px;">
        </div>
        <div class="fitem">
            <label style="width:100px;">
                显示顺序
            </label>
            <input name="sortIndex" id="sortIndex" class="easyui-numberbox" required="true" style="width:200px;">
        </div>
        <div class="fitem">
            <label style="width:100px;">
                描述
            </label>
            <textarea id="description" rows=5 style="width:200px;" required="true" name="description"
                      class="textarea easyui-validatebox"></textarea>

        </div>
        <div class="fitem">
            <label style="width:100px;">
                描述
            </label>
            <input id="poster" type="file" class="input" name="poster"/>
        </div>
        <input type="hidden" name="action" id="hidtype"/>
        <input type="hidden" name="id" id="id"/>
    </form>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="btnSave" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
           iconcls="icon-cancel">取消</a>
    </div>
</div>
</@override>
<@extends name="layout/questionMgr.ftl"/>