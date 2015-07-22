<@override name="title">密码修改</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" charset="UTF-8" href="/css/cs/user-setting.css"/>
<link rel="stylesheet" type="text/css" charset="UTF-8" charset="UTF-8"
      href="/js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="${basePath}js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/user/security.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="cs-user-setting">
            <#include "user/settingBar.ftl">
            <div class="tab-content clearfix">
                <div class="cs-mod">
                    <div class="mod-body">
                        <div class="cs-mod cs-user-setting-bind">
                            <div class="mod-head">
                                <h3>修改密码</h3>
                            </div>
                            <form class="form-horizontal"
                                  action="/user/updateSecurity" method="post"
                                  id="setting_form">
                                <div class="mod-body">
                                    <div class="form-group">
                                        <label class="control-label" for="old_password">当前密码</label>

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <input style="display:none;"/>
                                                <input type="password" class="form-control" id="old_password"
                                                       name="oldPassword" autocomplete="off">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label" for="new_password">新的密码</label>

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <input type="password" class="form-control" id="new_password"
                                                       required="true"
                                                       name="newPassword">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label" for="re_password">确认密码</label>

                                        <div class="row">
                                            <div class="col-lg-4">
                                                <input type="password" class="form-control" id="re_password"
                                                       required="true"
                                                       name="re_password">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="mod-footer clearfix">
                        <a href="javascript:void(0);" onclick="saveClick();" id="saveBtn"
                           class="btn btn-large btn-success pull-right">保存</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>