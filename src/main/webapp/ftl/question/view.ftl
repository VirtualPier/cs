<#import "includes/date.ftl" as df/>
<@override name="title">${question.title}</@override>
<@override name="header">
<link rel="stylesheet" charset="UTF-8" href="${assetsPath}js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<link rel="stylesheet" type="text/css" href="${assetsPath}js/lib/ckeditor/plugins/codesnippet/lib/highlight/styles/default.css"/>
<script type="text/javascript" src="${assetsPath}js/lib/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
<script type="text/javascript">
    var CKEDITOR_BASEPATH = "${assetsPath}js/lib/ckeditor/";
    var rightAskId = ${question.rightAsk???string(question.rightAsk.id,"-1")};
</script>
<script src="${assetsPath}js/lib/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${assetsPath}js/coderstar/common/ckconfig.js" type="text/javascript"></script>
<script src="${assetsPath}js/coderstar/front/viewQuestion.js" type="text/javascript"></script>
</@override>
<@override name="body">

<div class="col-sm-12 col-md-9 cs-main-content">
    <!-- 话题推荐bar -->
    <!-- 话题推荐bar -->

    <div class="cs-mod cs-question-detail cs-item">
        <div class="mod-head">
            <h1>${question.title}</h1>
            <#if question.money gt 0>
                <p><label>悬赏码币:</label>${question.money}</p>
            </#if>

            <p>
                <label>分类:</label>
                <#list question.questionCategories as category>
                    <a href="/question/index?categoryId=${category.category.id}">${category.category.name}</a>
                </#list>

                <#if (user??)&&(user.id==question.creator.id)>
                    <a class="pull-right" style="margin-left:10px;">
                        <span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
                    <a class="pull-right" style="margin-left:10px;">
                        <span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑</a>
                </#if>
                <a id="attentionQuestionBtn" data-flag="${isAttention?string("0","1")}" class="pull-right"
                   href="javascript:void(0);"
                   onclick="attentionQuestion(${question.id},this)"><span
                        class="glyphicon glyphicon-heart-empty"></span>&nbsp;${isAttention?string("取消关注","关注本文")}</a>

            </p>

            <p><label>标签:</label>
                <#list question.tags as tag>
                    <a href="/question/index?tagId=${tag.tag.id}" class="cs-question-tags">${tag.tag.name}</a>
                </#list>
            </p>

        </div>

        <div class="mod-body">
            <div class="content markitup-box">
            ${question.description}
            </div>
        </div>

        <div class="mod-footer">
            <div class="meta">
                <span class="text-color-999"><@df.dateFormat question.createDate/>发布</span>
                <#include "includes/share.ftl">
            </div>
        </div>
    </div>
    <div class="cs-mod cs-question-comment">
        <div class="mod-head">
            <ul class="nav nav-tabs cs-nav-tabs active">
                <li ${(askSort=='createDate')?string("class='active'","")}>
                    <a href="/question/view?id=${question.id}&askSort=createDate">最新</a>
                </li>
                <li ${(askSort=='supportNum')?string("class='active'","")}>
                    <a href="/question/view?id=${question.id}&askSort=supportNum">热赞</a>
                </li>
                <h2 class="hidden-xs">${question.replyNum} 个回复</h2>
            </ul>
        </div>

        <div class="mod-body cs-feed-list">

            <#list asks as ask>
                <div class="cs-item" uninterested_count="0" force_fold="0" id="answer_list_${ask.id}">
                    <div class="mod-head">
                        <!-- 用户头像 -->
                        <a class="cs-user-img cs-border-radius-5 pull-right"
                           href=""
                           data-id="2"><img
                                src="${ask.user.photo}" alt=""
                                onerror="javascript:this.src='/images/pic_user.gif'">
                        </a> <!-- end 用户头像 -->
                        <div class="title">
                            <p>
                                <a class="cs-user-name"
                                   href="/user/view?id=${ask.user.id}"
                                   data-id="2">${ask.user.nickName}</a>
                                <#if user??>
                                    <#if (user.id==question.creator.id)||(user.id==ask.user.id)>
                                        <a href="/question/deleteAsk?id=${ask.id}"
                                           class="btn btn-success pull-right" style="margin-left:10px;">删除
                                        </a>
                                    </#if>
                                </#if>

                                <#if question.rightAsk??>
                                    <#if question.rightAsk.id==ask.id>
                                        <button class="btn btn-success pull-right">最佳答案</button>
                                    </#if>
                                <#else>
                                    <#if user??>
                                        <#if (user.id==question.creator.id)&&(question.creator.id!=ask.user.id)>
                                            <button class="btn btn-success pull-right" name="selectRightAskBtn"
                                                    onclick="selectRightAsk(${ask.id})">设为最佳答案
                                            </button>
                                        </#if>
                                    </#if>
                                </#if>


                            </p>

                        </div>
                    </div>

                    <div class="mod-body clearfix">
                        <!-- 评论内容 -->
                        <div class="markitup-box">
                        ${ask.content}
                        </div>
                        <!-- end 评论内容 -->
                    </div>

                    <div class="mod-footer">
                        <!-- 社交操作 -->
                        <div class="meta clearfix">
                            <span class="text-color-999 pull-right"><@df.dateFormat ask.createDate/></span>
                            <!-- 投票栏 -->
                            <span class="operate">
                                <a class="agree" onclick="javascript:support('${ask.id}', 'up')">
                                    <i class="glyphicon glyphicon-thumbs-up"></i> <b class="count"
                                                                                     id="${ask.id}up">${ask.supportNum}</b>
                                </a>
                            </span>
                            <!-- end 投票栏 -->
                            <span class="operate" id="1" count="0">
                                <a class="agree" onclick="javascript:support('${ask.id}', 'down')"><i
                                        class="glyphicon glyphicon-thumbs-down"></i> <b
                                        class="count" id="${ask.id}down">${ask.opposeNum}</b>
                                </a>
                            </span>
                        </div>
                        <!-- end 社交操作 -->
                    </div>
                </div>
            </#list>
        </div>

    </div>
    <!-- end 问题详细模块 -->

    <!-- 回复编辑器 -->
    <div class="cs-mod cs-replay-box question">
        <a name="answer_form"></a>

        <form action="/question/saveAsk" name="answer_form" class="question_answer_form" id="answer_form">
            <div class="mod-head">
            </div>
            <div class="mod-body">
                <input type="hidden" name="questionId" value="${question.id}"/>

                <div class="cs-mod cs-editor-box">
                    <textarea rows="5" name="content" id="ckeditor01"></textarea>
                </div>
                <div class="text-right">
                    <br/>
                    <input type="submit" value="回复" class="btn btn-default"/>
                </div>

            </div>
        </form>
    </div>
    <!-- end 回复编辑器 -->
</div>
<!-- 侧边栏 -->
<div class="col-md-3 cs-side-bar hidden-xs hidden-sm">
    <!-- 发起人 -->
    <div class="cs-mod">
        <div class="mod-head">
            <h3>提问人</h3>
        </div>

        <div class="mod-body">
            <dl>
                <dt class="pull-left cs-border-radius-5">
                    <a href="/user/view?id=${question.creator.id}"><img title="${question.creator.nickName}"
                                                                        alt="${question.creator.nickName}"
                                                                        src="${question.creator.photo}"
                                                                        onerror="javascript:this.src='/images/pic_user.gif'"/></a>
                </dt>
                <dd class="pull-left">
                    <a class="cs-user-name"
                       href="/user/view?id=${question.creator.id}">
                        <#if user??>
                            <#if user.id==question.creator.id>
                                我
                            <#else>
                            ${question.creator.nickName}
                            </#if>
                        <#else>
                        ${question.creator.nickName}
                        </#if>
                    </a>

                    <p></p>
                </dd>
            </dl>
        </div>
    </div>
    <!-- end 发起人 -->

    <!-- 相关问题 -->
    <div class="cs-mod">
        <div class="mod-head">
            <h3>相关问题</h3>
        </div>

        <div class="mod-body font-size-12">
            <ul>
                <#list relatedQuestionList as qt>
                    <li>
                        <a href="/question/view?id=${qt.id}">${qt.title}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <!-- end 相关问题 -->

    <!-- 问题状态 -->

    <!-- end 问题状态 -->
</div>
<!-- end 侧边栏 -->
</@override>
<@extends name="layout/index.ftl"/>