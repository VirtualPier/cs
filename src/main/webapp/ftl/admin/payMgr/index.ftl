<@override name="title">提现申请</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/coderstar/admin/payMgr.js"></script>
</@override>
<@override name="body">
<table id="tt" title="申请列表" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:true,fix:true" pagination="true" rownumbers="true"
       fitColumns="true"
       url="/payMgr/withdrawList" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>


<div id="withdrawDlg" class="easyui-dialog" title="提现"
     data-options="iconCls:'icon-save',closed:true,modal:true,toolbar:[{text:'提交',iconClass:'icon-add',handler:function(){$('#ff').form('submit');}},{text:'取消',iconClass:'icon-add',handler:function(){$('#ff').form('clear');$('#withdrawDlg').dialog('close');}}]"
     style="width:400px;height:200px;padding:10px">
    <form method="post" id="ff" action="/payMgr/withdraw">
        <input type="hidden" name="id" value=""/>
        <table>
            <tr>
                <td>批准金额:</td>
                <td><input class="easyui-textbox" type="text" name="money"
                           data-options="required:true,validType:'number'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</@override>
<@extends name="layout/payMgr.ftl"/>