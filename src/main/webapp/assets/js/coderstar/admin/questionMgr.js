$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {
                field: 'title', title: '问题标题', width: 200, formatter: function (value, row, index) {
                return "<a href='/question/view?id=" + row.id + "' target='_blank'>" + row.title + "</a>";
            }
            },
            {
                field: 'state', title: '状态', width: 20, formatter: function (value, row, index) {
                if (row.state == 1) {
                    return "<font color='red'>审核</font>";
                } else if (row.state == 0) {
                    return "<font color='blue'>发布</font>";
                }
            }
            },
            /*{field: 'description', title: '问题描述',width:200},*/
            {field: 'attentionNum', title: '关注数', width: 25},
            {field: 'replyNum', title: '回复数', width: 25},
            {field: 'viewNum', title: '浏览数', width: 25},
            {field: 'createDate', title: '创建时间', width: 50}
        ]],
        nowrap: false,
        toolbar: [{
            id: 'btnadd',
            text: '批量审核',
            iconCls: 'icon-ok',
            handler: function () {
                update();
            }
        }, {
            id: 'btndelete',
            text: '批量删除',
            iconCls: 'icon-cut',
            handler: function () {
                deleteQuestion();
            }
        }, {
            id: 'syncIndexBtn',
            text: '同步所有索引',
            iconCls: 'icon-save',
            handler: function () {
                syncQuestionIndex();
            }
        }]
    });
});
function syncQuestionIndex() {
    $.post("/questionMgr/syncIndex", {}, function (data) {
        if (data.success) {
            $.messager.alert("同步完成");
        }
    });
}
function update() {
    var selected = $('#tt').datagrid("getSelections");
    if (selected.length == 0) {
        $.messager.alert('提示', '请选择要批量审核的数据!', 'info');
        return;
    }
    var idString = "";
    $.each(selected, function (index, item) {
        if (index < selected.length - 1) {
            idString += item.id + ",";
        } else {
            idString += item.id;
        }
    });
    if (!confirm("确认要批量审核选中的问题信息？"))
        return;

    var url = 'editeQuestion';
    $.ajax({
        //设定地址与传递参数到后台
        url: url + "?id=" + idString,
        type: 'post',
        dateType: 'json',
        //判断结果是否正确
        success: function (data) {
            if (data.success) {
                $.messager.alert('提示', '批量审核成功!', 'success');
                $('#dlg').dialog('close');
                $('#tt').datagrid('reload');
            }
        }, error: function () {

        }
    })
}
function deleteQuestion() {
    var url = 'deleteQuestion';
    var selected = $('#tt').datagrid("getSelections");
    if (selected.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据!', 'info');
        return;
    }
    var idString = "";
    $.each(selected, function (index, item) {
        if (index < selected.length - 1) {
            idString += item.id + ",";
        } else {
            idString += item.id;
        }
    });
    if (!confirm("确认要删除选中语言信息？"))
        return;

    $.ajax({
        //设定地址与传递参数到后台
        url: url + "?ids=" + idString,
        type: 'post',
        dateType: 'json',
        //判断结果是否正确
        success: function (data) {
            var msg = "";
            for (var i = 0; i < data.length; i++) {
                var d = data[i];
                if (d.success) {
                    msg += "问题id:" + d.id + "删除成功";
                } else {
                    msg += "问题id:" + d.id + "删除失败," + d.msg;
                }
            }
            $.messager.alert('提示', msg, 'success');
            $("#tt").datagrid("clearSelections");//解决方法：在删除数据成功后清空所有的已选择项
            $('#tt').datagrid('reload');
        }, error: function () {
            $.messager.alert('提示', '删除失败!', 'error');
        }
    })
}
