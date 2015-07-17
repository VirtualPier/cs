$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40, hidden: true},
            {field: 'name', title: '语言名称', width: 100},
            {field: 'sort_index', title: '显示顺序', width: 100},
            {field: 'description', title: '描述', width: 100},
            {field: 'questionNum', title: '问题总数', width: 100}
        ]],
        toolbar: [{
            id: 'btnadd',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                newLanguage();
            }
        }, {
            id: 'btnupdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {
                editeLanguage();
            }

        }, '-', {
            id: 'btncut',
            text: '删除',
            iconCls: 'icon-cut',
            handler: function () {
                deleteLanguage();
            }
        }]
    });
});
function newLanguage() {
    $("#dlg").dialog("open").dialog('setTitle', '新增编程语言');
    $('#fm').form('clear');//清空窗体数据
}
function save() {
    var url = 'addLanguage';
    var data=$('#fm').serialize();
    if(!$('#fm').form('validate'))return;
    $.ajax({
        //设定地址与传递参数到后台
        url: url,
        data:data,
        type:'post',
        dateType:'json',
        //判断结果是否正确
        success: function (result) {
            if(result.success){
                $.messager.alert('提示', '保存成功!', 'success');
                $('#dlg').dialog('close');
                $('#tt').datagrid('reload');
            }
        }, error: function () {
            $.messager.alert('提示', '保存失败!', 'error');
        }
    })
};
function update(){
    var url = 'editeLanguage';
    var data=$('#fm').serialize();
    if(!$('#fm').form('validate'))return;
    $.ajax({
        //设定地址与传递参数到后台
        url: url,
        data:data,
        type:'post',
        dateType:'json',
        //判断结果是否正确
        success: function (data) {
            if(data.success){
                $.messager.alert('提示', '更新成功!', 'success');
                $('#dlg').dialog('close');
                $('#tt').datagrid('reload');
            }
        }, error: function () {

        }
    })
}
function editeLanguage() {
    var rows = $('#tt').datagrid('getSelected');
    if (rows) {
        $("#dlg").dialog("open").dialog('setTitle', '编辑编程语言');
        $('#id').val(rows.id);
        $('#name').val(rows.name);
        $('#sortIndex').val(rows.sort_index);
        $('#description').val(rows.description);
        $('#btnSave').attr('onclick','update()');
    }
    else {
        $.messager.alert('提示', '请选择要修改的数据');
        return;
    }
}

function deleteLanguage() {
    var url = 'deleteLanguage';
    var selected = $('#tt').datagrid("getSelections");
    if (selected.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据!', 'info');
        return;
    }
    var idString = "";
    $.each(selected, function (index, item) {
        if(index<selected.length-1){
            idString += item.id + ",";
        }else{
            idString += item.id;
        }
    });
    if (!confirm("确认要删除选中语言信息？"))
        return;

    $.ajax({
        //设定地址与传递参数到后台
        url: url+"?id="+idString,
        type:'post',
        dateType:'json',
        //判断结果是否正确
        success: function (data) {
            if(data.success){
                $.messager.alert('提示', '删除成功!', 'success');
                $("#tt").datagrid("clearSelections");//解决方法：在删除数据成功后清空所有的已选择项
                $('#tt').datagrid('reload');
            }
        }, error: function () {
            $.messager.alert('提示', '删除失败!', 'error');
        }
    })
}
