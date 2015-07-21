<@override name="title">基本资料</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" charset="UTF-8" href="/css/cs/user-setting.css"/>
<link rel="stylesheet" type="text/css" charset="UTF-8" charset="UTF-8"
      href="/js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="${basePath}js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/user/profile.js"></script>
</@override>
<@override name="body">
<div class="row">
    <form id="setting_form" method="post" action="/user/updateProfile" class="form-horizontal">
        <input type="hidden" name="id" value="${user.id}">

        <div class="cs-content-wrap clearfix">
            <div class="cs-user-setting">
                <#include "user/settingBar.ftl">
                <div class="tab-content clearfix">
                    <!-- 基本信息 -->
                    <div class="cs-mod">
                        <div class="mod-body">
                            <div class="cs-mod mod-base">
                                <div class="mod-head">
                                    <h3>基本信息</h3>
                                </div>

                                <div class="mod-body">
                                    <dl class="form-group">
                                        <dt>
                                            <label>用户昵称:</label>
                                        </dt>
                                        <dd class="introduce">
                                            <input type="text" name="nickName" value="${user.nickName}"
                                                   class="form-control" maxlength="128"/>
                                        </dd>
                                    </dl>
                                    <dl class="form-group">
                                        <dt>
                                            <label>
                                                性别:
                                            </label>
                                        </dt>
                                        <dd>

                                            <label class="radio-inline">
                                                <input name="sex" value="1"
                                                       type="radio" ${(user.sex==1)?string("checked","")}> 男
                                            </label>
                                            <label class="radio-inline">
                                                <input name="sex" value="2"
                                                       type="radio"  ${(user.sex==2)?string("checked","")}> 女
                                            </label>
                                        </dd>
                                    </dl>

                                    <dl class="form-group">
                                        <dt><label>介绍:</label></dt>
                                        <dd class="introduce">
                                            <input class="form-control" id="introduce" name="introduce" maxlength="128"
                                                   type="text" value="${user.introduce}">
                                        </dd>
                                    </dl>


                                    <!-- 上传头像 -->
                                    <div class="side-bar">
                                        <dl>
                                            <dt class="pull-left">
                                                <img class="cs-border-radius-5"
                                                     onerror="javascript:this.src='/images/pic_user.gif'"
                                                     src="${user.photo}" alt="" id="avatar_src" width="64" height="64">
                                            </dt>
                                            <dd class="pull-left">
                                                <h5>头像设置</h5>

                                                <p>支持 jpg、gif、png 等格式的图片</p>
                                                <input accept="image/gif,image/png,image/jpeg,image/jpg" single
                                                       class="btn" id="avatar_uploader" type="file" name="photo"/>

                                                <span id="avatar_uploading_status" class="hide"><i
                                                        class="cs-loading"></i> 文件上传中...</span>
                                            </dd>
                                        </dl>
                                    </div>
                                    <!-- end 上传头像 -->
                                </div>
                            </div>
                            <!-- end 基本信息 -->

                            <!-- 联系方式 -->
                            <div class="cs-mod mod-contact">
                                <div class="mod-head">
                                    <h3>联系方式</h3>
                                </div>

                                <div class="mod-body clearfix">
                                    <ul>
                                        <li class="form-group">
                                            <label for="qq">QQ :</label>
                                            <input class="form-control" type="text" id="qq" name="qq"
                                                   value="${user.qq}">
                                        </li>
                                        <li class="form-group">
                                            <label for="cellphone">手机号码 :</label>
                                            <input class="form-control" type="text" id="cellphone" name="cellphone"
                                                   value="${user.cellphone}">
                                        </li>
                                        <li class="form-group">
                                            <label for="email">常用邮箱 :</label>
                                            <input class="form-control" type="text" id="email" name="email"
                                                   value="${user.email}">
                                        </li>
                                        <li class="form-group">
                                            <label for="web">网站 :</label>
                                            <input class="form-control" type="text" id="web" name="web"
                                                   value="${user.web}">
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- end 联系方式 -->

                        </div>

                        <div class="mod-footer clearfix">
                            <input type="submit" class="btn btn-large btn-success pull-right" id="saveBtn" value="保存"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

</@override>
<@extends name="layout/index.ftl"/>