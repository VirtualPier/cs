<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title><@block name="title">标题</@block>${appconfig.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Keywords" content="程序员，码农,编程,C语言,程序员,源代码,源码,源码下载,创业，合作，交流">
    <META name="Description" content="关注程序员的职业生涯，即使的解决各种问题，纯干货！">
    <script type="text/javascript">
        var pageConfig = {};
        pageConfig.isLogin = ${user???string("true","false")};
    </script>
    <script type="text/javascript" src="${basePath}js/lib/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${basePath}js/lib/jquery.cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-select/css/bootstrap-select.min.css">
    <script type="text/javascript" src="${basePath}js/lib/bootstrap-select/js/bootstrap-select.min.js"></script>
    <!----百度统计---->
    <script type="text/javascript" src="${basePath}js/coderstar/front/baidustatistics.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}css/cs/base.css"/>
    <script type="text/javascript" src="${basePath}js/coderstar/front/common.js"></script>
<@block name="header"></@block>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <image src="${basePath}images/logo.png"
                   style="width:40px;height:40px;float: left;margin-top:5px;border:0;"/>
            <a href="/index/index" class="navbar-brand">${appconfig.name}</a>

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
            <form action="/index/search" class="navbar-form form-inline navbar-left" role="search">
                <div class="form-group">
                    <select class="form-control selectpicker" name="searchType" data-style="btn-inverse">
                        <option value="1" ${(searchType=="1")?string("selected","")}>问答</option>
                        <option value="2" ${(searchType=="2")?string("selected","")}>文章</option>
                    </select>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" placeholder="输入搜索内容" name="title" value="${title}"/>
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
                        <span class="glyphicon  glyphicon-user"></span>我的 <span class="caret"></span></a>
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
                            <a href="/index/logout">注销</a>
                        </li>
                    </ul>
                </li>
            <#else >
                <li>
                    <a href="/index/login">登录</a>
                </li>
                <li>
                    <a href="/index/register">注册</a>
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
        <a href="/index/ad">广告服务</a>
        <a href="/index/contact">联系我们</a>
        <a href="/index/friendLinks">友情链接</a>
    </p>
</div>
</body>
</html>
