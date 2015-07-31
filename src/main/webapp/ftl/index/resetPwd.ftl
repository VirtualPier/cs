<@override name="title">重置密码</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/emailResetPwd.js"></script>
<style type="text/css">
    .repeatCode {
        cursor: pointer;
        text-decoration: none;
    }

    .repeatCode:hover {
        -webkit-transform: rotate(180deg);
        -webkit-animation-delay: 0.5s;
        color: #ffffff;
    }
</style>
</@override>
<@override name="body">
<div class="row">
    <div class="col-md-3"></div>

    <div class="cs-login-box col-md-6">
        <div class="mod-body">

            <form action="/index/resetUserPwd" id="resetpwd_form" name="register_form" class="form-horizontal"
                  method="post">
                <input type="hidden" name="id" value="${id}"/>
                <input type="hidden" name="key" value="${key}">

                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label"
                           for="email">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>

                    <div class="col-md-8">
                        <i></i>
                        <span class="glyphicon  glyphicon-lock  form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入新密码"
                               autocomplete="off" value=""/>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label"
                           for="email">再次输入</label>

                    <div class="col-md-8">
                        <i></i>
                        <span class="glyphicon glyphicon-lock  form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="password" class="form-control" id="password2" name="password2"
                               placeholder="请确认您的输入"
                               autocomplete="off" value=""/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="pull-right btn btn-large btn-primary">提交</button>
                        <#if msg??>
                            <span class="label label-danger pull-right"
                                  style="padding:5px;margin-right:10px;margin-top:5px;">*${msg}</span>
                        </#if>
                    </div>
                </div>
            </form>
        </div>

    </div>

    <div class="col-md-3"></div>
</div>
</@override>
<@extends name="layout/index.ftl"/>