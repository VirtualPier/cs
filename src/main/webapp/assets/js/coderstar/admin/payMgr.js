/**
 * Created by ligson on 2015/7/10.
 */
$(function () {

    var userGrid = $("#tt");
    userGrid.datagrid({
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {field: 'userId', title: '用户ID', width: 40},
            {
                field: 'user', title: '用户名', width: 50, formatter: function (value) {
                return value.nickName;
            }
            },
            {
                field: 'userCellphone', title: '手机号', width: 50, formatter: function (value, rowData, rowIndex) {
                return rowData.user.cellphone;
            }
            }, {
                field: 'userEmail', title: '邮箱', width: 100, formatter: function (value, rowData, rowIndex) {
                    return rowData.user.email;
                }
            },
            {field: 'createDate', title: '提现日期', width: 100},
            {
                field: 'state', title: '状态', width: 50, formatter: function (value, rowData, rowIndex) {
                if (value == 1) {
                    return "申请";
                } else if (value == 2) {
                    return "批准";
                } else if (value == 3) {
                    return "拒绝";
                } else {
                    return "";
                }
            }
            },
            {field: 'money', title: '提现金额', width: 100},
            {field: 'comments', title: '注释', width: 100},
            {field: 'payAccount', title: '支付账号', width: 100}

        ]],
        toolbar: [{
            id: 'btnadd',
            text: '通过',
            iconCls: 'icon-add',
            handler: function () {
                allowWithdraw();
            }
        }, {
            id: 'btnupdate',
            text: '拒绝',
            iconCls: 'icon-save',
            handler: function () {
                editeLanguage();
            }

        }]
    });
});

function allowWithdraw() {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid('getSelections');
    if (rows) {
        var ids = "";
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            ids += row.id + ",";
        }
        $.post("/payMgr/withdraw", {ids: ids}, function (data) {
            var msg = "";
            for (var i = 0; i < data.length; i++) {
                if (data[i].success) {
                    msg += "记录ID:" + data[i].id + "成功!";
                } else {
                    msg += "记录ID:" + data[i].id + "失败!" + data[i].msg;
                }
            }
            alert(msg);
        }, "json");
    } else {
        alert("请至少选择一条记录!");
    }
}


