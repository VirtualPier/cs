<@override name="title">提问问题</@override>
<@override name="header">
<title>${question.title}</title>
<link rel="stylesheet" charset="UTF-8" href="/js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="/js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<link rel="stylesheet" type="text/css" href="/js/lib/ckeditor/plugins/codesnippet/lib/highlight/styles/default.css"/>
<script type="text/javascript" src="/js/lib/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
<script type="text/javascript">
    var CKEDITOR_BASEPATH = "/js/lib/ckeditor/";
</script>
<script src="/js/lib/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="/js/coderstar/common/ckconfig.js" type="text/javascript"></script>
<script src="/js/coderstar/front/viewQuestion.js" type="text/javascript"></script>
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
                <#list categoryList as category>
                    <a href="/index/index?categoryId=${category.id}">${category.name}</a>
                </#list>
                <button id="attentionQuestionBtn" type="button" class="btn btn-info"
                        onclick="attentionQuestion(${question.id})" ${isAttention ?string("disabled","")}><span
                        class="glyphicon glyphicon-heart-empty"></span>&nbsp;${isAttention ?string("已关注","关注")}
                </button>
            </p>
            <p><label>标签:</label>
                <#list question.tags as tag>
                    <a href="/question/index?tagName=${tag.name}" class="cs-question-tags">${tag.name}</a>
                </#list>
            </p>

            <#include "includes/share.ftl">

        </div>

        <div class="mod-body">
            <div class="content markitup-box">
            ${question.description}
            </div>
        </div>

        <div class="mod-footer">
            <div class="meta">
                <span class="text-color-999">${question.createDate}
                    发布</span>


                <div class="pull-right">
                    <input type="button" value="完善问题描述" class="btn btn-default" id="editDescBtn"
                           style="padding:2px;">
                </div>

            </div>
        </div>
    </div>

    <div class="cs-mod cs-question-description-editor hide">
        <div class="mod-body">
            <div class="content markitup-box">
                <textarea rows="5" name="content" id="ckeditor02">${question.description}</textarea>
            </div>
        </div>

        <div class="mod-footer">
            <div class="meta pull-right">
                <div class="pull-right">
                    <input type="button" value="确认修改" class="btn btn-default" id="modifySbtBtn">
                </div>
            </div>
        </div>
    </div>

    <div class="cs-mod cs-question-comment">
        <div class="mod-head">
            <ul class="nav nav-tabs cs-nav-tabs active">
                <li>
                    <a href="/question/view">时间</a>
                </li>
                <li>
                    <a href="/question/view">关注的人</a>
                </li>

                <li>
                    <a href="/question/view">票数</a>
                </li>

                <h2 class="hidden-xs">${question.replyNum} 个回复</h2>
            </ul>
        </div>

        <div class="mod-body cs-feed-list">

            <div class="cs-item" uninterested_count="0" force_fold="0" id="answer_list_11">
                <div class="mod-head">
                    <!-- 用户头像 -->
                    <a class="cs-user-img cs-border-radius-5 pull-right"
                       href=""
                       data-id="2"><img
                            src="" alt=""
                            onerror="javascript:this.src='/images/pic_user.gif'">
                    </a> <!-- end 用户头像 -->
                    <div class="title">
                        <p>
                            <a class="cs-user-name"
                               href=""
                               data-id="2">user name</a>
                            <a controller="question" action="deleteAsk" params="[id: ask.id]"
                               class="btn btn-success pull-right" style="margin-left:10px;">删除
                            </a>


                            <button class="btn btn-success pull-right" name="selectRightAskBtn"
                                    onclick="selectRightAsk(1)">设为满意答案
                            </button>
                            <button class="btn btn-success pull-right">已选为最佳答案</button>
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
                        <span class="text-color-999 pull-right">yearym</span>
                        <!-- 投票栏 -->
                            <span class="operate">
                                <a class="agree" onclick="javascript:support('1', 'up')">
                                    <i class="glyphicon glyphicon-thumbs-up"></i> <b class="count"
                                                                                     id="1up">1</b>
                                </a>
                            </span>
                        <!-- end 投票栏 -->
                            <span class="operate" id="1" count="0">
                                <a class="agree" onclick="javascript:support('1', 'down')"><i
                                        class="glyphicon glyphicon-thumbs-down"></i> <b
                                        class="count" id="2down">1</b>
                                </a>
                            </span>
                        <!-- 可显示/隐藏的操作box -->
                        <div class="more-operate">

                        </div>
                        <!-- end 可显示/隐藏的操作box -->

                    </div>
                    <!-- end 社交操作 -->
                </div>
            </div>
        </div>

    </div>
    <!-- end 问题详细模块 -->

    <!-- 回复编辑器 -->
    <div class="cs-mod cs-replay-box question">
        <a name="answer_form"></a>

        <form controller="question" action="saveAsk" name="answer_form" class="question_answer_form">
            <div class="mod-head">

                <a controller="user" action="home" class="cs-user-name">
                    <img alt="${user.nickName}"
                         src="${user.photo}"
                         onerror="javascript:this.src='/images/pic_user.gif'">
                </a>

                <p>${user.nickName}
                </p>


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
                    <a controller="user" action="view" params="[id: question.creator.id]"><img alt="软爷"
                                                                                               src="${question.creator.photo}"
                                                                                               onerror="javascript:this.src='/images/pic_user.gif'">
                    </a>
                </dt>
                <dd class="pull-left">
                    <a class="cs-user-name" controller="user" action="view"
                       params="[id: question.creator.id]">${question.creator.nickName}</a>

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
                <li>
                    <a>XXXXXXXXXXXX</a>
                    <a>XXXXXXXXXXXX</a>
                </li>
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