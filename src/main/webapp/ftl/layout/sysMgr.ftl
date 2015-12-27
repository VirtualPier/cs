<html>
<head>
    <title><@block name="title"></@block>-系统管理</title>
<#include "layout/adminCommonHead.ftl">
<@block name="header"></@block>
</head>

<body class="easyui-layout">
<#include "layout/adminCommonBody.ftl">
<div id="westPanel" data-options="region:'west',split:false" title="导航菜单" style="width:150px;" about="systemMgr">
    <div class="easyui-accordion" style="width:148px;height:100%;">
        <div title="缓存设置" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
            <ul class="easyui-tree">
                <li><a href="/systemMgr/index">加载缓存</a></li>
                <li><a href="/userMgr/index">缓存设置</a></li>
            </ul>
        </div>
    </div>

</div>
<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'" id="mainDiv">
<@block name="body"></@block>
</div>
</body>
</html>