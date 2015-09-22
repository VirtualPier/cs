<@override name="title">用户列表</@override>
<@override name="header">
<script type="text/javascript" src="${basePath}js/coderstar/admin/userList.js"></script>
</@override>
<@override name="body">
<table id="tt" title="用户列表" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/userMgr/userList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>

<div id="toolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="plsh()">批量审核</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon icon-edit" plain="true"
       onclick="editUser()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="destroyUser()">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="destroyUser()">禁用</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
       onclick="destroyUser()">解锁</a>

    <div style="margin: 5px 5px;">
        用户名：<input type="text" class="easyui-textbox" style="height: 18px;" name="name"/>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px;"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id"/>
        <select name="state" id="state" style="width:200px">
            <option value="1">正常</option>
            <option value="0">禁用</option>
            <option value="2">待审核</option>
        </select>
    </form>

    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="btnSave" class="easyui-linkbutton" onclick="update()"
           iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')"
           iconcls="icon-cancel">取消</a>
    </div>
</div>

<div id="resetPwdDlg" class="easyui-dialog" title="Toolbar and Buttons" style="width:350px;height:250px;padding:10px"
     data-options="closed:true">
    <g:form controller="userMgr" action="resetPassword">
        <input type="hidden" name="userId" value=""/>
        <table cellpadding="5">
            <tr>
                <td><label>新密码:</label></td>
                <td><input class="easyui-textbox" type="password" name="password1" data-options="required:true"/></td>
            </tr>
            <tr>
                <td><label>确认输入:</label></td>
                <td><input class="easyui-textbox" type="password" name="password2"
                           data-options="required:true"/></td>
            </tr>
        </table>
    </g:form>
</div>
</@override>
<@extends name="layout/userMgr.ftl"/>