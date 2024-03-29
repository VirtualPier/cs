<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title><@block name="title">标题</@block>${appconfig.name}</title>
    <meta name="baidu-site-verification" content="vDC6CHwEsN"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta property="qc:admins" content="55716664776374524734126375"/>
    <meta name="Keywords" content="程序员,码农,编程,C语言,程序员,源代码,源码,源码交易,创业，合作，交流">
    <META name="Description" content="关注程序员的职业生涯，即时的解决工作中的各种问题，纯干货！">
    <link rel="shortcut icon" href="${assetsPath}images/logo.ico" type="image/x-icon">
    <script type="text/javascript">
        var pageConfig = {};
        pageConfig.isLogin = ${user???string("true","false")};
        pageConfig.basePath = "${assetsPath}";
    </script>

    <script type="text/javascript" src="${assetsPath}js/lib/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${assetsPath}js/lib/jquery.cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${assetsPath}js/lib/bootstrap-typeahead/example.css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${assetsPath}js/lib/bootstrap-select/css/bootstrap-select.min.css"/>
    <script type="text/javascript" src="${assetsPath}js/lib/bootstrap-select/js/bootstrap-select.min.js"></script>
    <!----百度统计---->
    <script type="text/javascript" src="${assetsPath}js/coderstar/front/baidustatistics.js"></script>
    <link rel="stylesheet" type="text/css" href="${assetsPath}css/cs/base.css"/>
    <script type="text/javascript" src="${assetsPath}js/coderstar/front/common.js"></script>
<@block name="header"></@block>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">切换</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <image src="${assetsPath}images/logo.png"
                   style="width:40px;height:40px;float: left;margin-top:5px;border:0;"/>
            <a href="${basePath}" class="navbar-brand" style="margin-left:5px;">${appName}</a>

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
            <#if springMacroRequestContext.requestUri?contains("/question")>
                <li class="active">
                    <a href="/question/index">问答</a>
                </li>
            <#else >
                <li>
                    <a href="/question/index">问答</a>
                </li>
            </#if>
            <#if springMacroRequestContext.requestUri?contains("/article")>
                <li class="active">
                    <a href="/article/index">文章</a>
                </li>
            <#else >
                <li>
                    <a href="/article/index">文章</a>
                </li>
            </#if>

            </ul>
            <form action="/search" class="navbar-form form-inline navbar-left" role="search">
                <div class="form-group">
                    <select class="form-control selectpicker" name="searchType" data-style="btn-inverse">
                        <option value="1" ${(searchType=="1")?string("selected","")}>问答</option>
                        <option value="2" ${(searchType=="2")?string("selected","")}>文章</option>
                    </select>
                </div>

                <div class="form-group">

                    <input type="text" class="form-control typeahead" data-provide="typeahead" placeholder="输入搜索内容"
                           id="titleSearch"
                           name="title" value="${title}"/>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-default"><span class="glyphicon  glyphicon-search"></span>搜索
                    </button>
                </div>
            </form>

            <ul class="nav navbar-nav navbar-right">
            <#if user??>
                <li>
                    <a href="/question/create"><span
                            class="glyphicon  glyphicon-plus"></span>我要提问</a>
                </li>
                <li>
                    <a href="/article/create"><span
                            class="glyphicon  glyphicon-plus"></span>发表文章</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false">
                        <img alt="${user.photo}" style="width:20px;height:20px;float:left;" class="media-object"
                             src="${user.photo}"
                             onerror="javascript:this.src='/images/pic_user.gif'" title="${user.nickName}">
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li>
                            <a href="/user/myPublish">提问的问题</a>
                        </li>
                        <li>
                            <a href="/user/myAttention">关注的问题</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/user/myArticle">发布的文章</a>
                        </li>
                        <li>
                            <a href="/user/myAttentionArticle">关注的文章</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/user/rechange">充值</a>
                        </li>
                        <li>
                            <a href="/user/withdraw">提现</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/user/profile">修改信息</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/logout">注销</a>
                        </li>
                    </ul>
                </li>
            <#else >
                <li>
                    <a href="/login">登录</a>
                </li>
                <li>
                    <a href="/register">注册</a>
                </li>
            </#if>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div class="container bg-white ligson-content">
<@block name="body">body内容</@block>
</div>

<div class="container ligson-footer">
    <p class="text-center">
        <a href="/ad">广告服务</a>
        <a href="/contact">联系我们</a>
        <a href="/friendLinks">友情链接</a>
    </p>
</div>
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-typeahead/typeahead.bundle.js"></script>
</body>
</html>
