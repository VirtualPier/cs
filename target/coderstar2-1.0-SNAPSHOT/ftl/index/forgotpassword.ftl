<@override name="title">忘记密码?</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/forgotpassword.js"></script>
<style type="text/css">
    .repeatCode {
        cursor: pointer;
        text-decoration:none;
    }

    .repeatCode:hover {
        -webkit-transform: rotate(180deg);
        -webkit-animation-delay: 0.5s;
        color:#ffffff;
    }
</style>
</@override>
<@override name="body">
<div class="row">
    <div class="col-md-3"></div>

    <div class="cs-login-box col-md-6">
        <div class="mod-body">

            <form action="/index/submitMail" id="register_form" name="register_form" class="form-horizontal"
                  method="post">
                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label"
                           for="email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>

                    <div class="col-md-8">
                        <i></i>
                        <span class="glyphicon  glyphicon-envelope  form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="text" class="form-control" id="email" name="email" placeholder="邮箱地址"
                               autocomplete="off" value="${email}"/>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label"
                           for="email">验证码:</label>

                    <div class="col-md-8">
                        <div class="input-group">
                            <input style="display:none;"/>
                            <input type="text" class="form-control" id="code" name="code" placeholder="输入右侧验证码"
                                   autocomplete="off" value=""/>

                            <div class="input-group-addon" style="padding-top:1px;padding-bottom:1px;"><img
                                    src="/index/captcha" id="codeImg" style="width:80px;height:30px;">
                                <span class="glyphicon glyphicon-repeat repeatCode" title="看不清,重新生成!"></span>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="pull-right btn btn-large btn-primary">下一步</button>
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