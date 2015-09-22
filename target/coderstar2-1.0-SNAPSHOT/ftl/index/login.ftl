<@override name="title">登陆</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/login.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="col-md-3"></div>

    <div class="cs-login-box col-md-6">
        <div class="mod-head text-center">
            <h1>登录</h1>
        </div>

        <div class="mod-body">
            <form action="/index/checkLogin" name="login_form" class="form-horizontal">
                <div class="form-group has-feedback">
                    <div class="col-md-12">
                        <i></i>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="text" class="form-control" id="name" name="name" placeholder="请输入邮箱或者手机号"
                               autocomplete="off" value="${name}"/>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <div class="col-md-12">
                        <i></i>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="password" class="form-control" id="password" name="password" placeholder="密码"
                               autocomplete="off"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-8">
                        <#if msg??>
                            <div class="alert alert-danger">${msg}</div>
                        </#if>
                    </div>

                    <div class="col-md-4 text-center">
                        <a href="/index/forgotpassword" class="pull-right" style="margin-top:10px;margin-left:10px;">忘记密码?</a>
                        <input type="submit" class="pull-right btn btn-large btn-primary" value="登录"/>
                    </div>
                </div>
            </form>
        </div>

        <div class="mod-footer">
            <span>还没有账号?</span>&nbsp;&nbsp;
            <a href="/index/register">立即注册</a>&nbsp;&nbsp;
            <#--<img src="/images/qq_login_logo.png" onclick="qqLogin()">-->
        </div>
    </div>

    <div class="col-md-3"></div>
</div>
</@override>
<@extends name="layout/index.ftl"/>