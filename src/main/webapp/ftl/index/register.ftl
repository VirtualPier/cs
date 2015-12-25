<@override name="title">用户注册</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${assetsPath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${assetsPath}js/coderstar/front/register.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="col-md-3"></div>

    <div class="cs-register-box col-md-6">

        <div class="mod-head text-center">
            <h1>注册新用户</h1>
        </div>

        <div class="mod-body">
            <form action="/index/saveUser" id="register_form" name="register_form" class="form-horizontal"
                  method="post">


                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label" for="nickName">用户昵称</label>

                    <div class="col-md-8">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="text" class="form-control" id="nickName" name="nickName" placeholder="用户昵称"
                               autocomplete="off" value="${nickName}">
                    </div>
                </div>


                <div class="form-group has-feedback">
                    <label class="col-md-4 control-label" for="cellphone">手机号码</label>

                    <div class="col-md-8">
                        <i></i>
                        <span class="glyphicon glyphicon-phone-alt form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="text" class="form-control" id="cellphone" name="cellphone" placeholder="手机号码"
                               autocomplete="off" value="${cellphone}"/>
                    </div>
                </div>

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
                           for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>

                    <div class="col-md-8">
                        <i></i>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        <input style="display:none;"/>
                        <input type="password" class="form-control" id="password" name="password" placeholder="密码"
                               autocomplete="off"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="pull-right btn btn-large btn-primary">注册</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="mod-footer">

        </div>
    </div>

    <div class="col-md-3"></div>
</div>
</@override>
<@extends name="layout/index.ftl"/>