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
            <g:if test="${question.money > 0}">
                <p><label>悬赏码币:</label>${question.money}</p>
            </g:if>
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

            <g:include view="layouts/share.gsp"/>

        </div>

        <div class="mod-body">
            <div class="content markitup-box">
                <hc:cleanHtml unsafe='${question.description}' whitelist="sample-with-anchor"/>
            </div>
        </div>

        <div class="mod-footer">
            <div class="meta">
                <span class="text-color-999">${org.ligson.coderstar.utils.CTools.humanDateDiff(question.createDate, 'yyyyMMddHHmmss')}
                    发布</span>

                <g:if test="${session.user && session.user.id == question.creator.id}">
                    <div class="pull-right">
                        <input type="button" value="完善问题描述" class="btn btn-default" id="editDescBtn"
                               style="padding:2px;">
                    </div>
                </g:if>
            </div>
        </div>
    </div>
    <g:if test="${session.user && session.user.id == question.creator.id}">
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
    </g:if>
    <div class="cs-mod cs-question-comment">
        <div class="mod-head">
            <ul class="nav nav-tabs cs-nav-tabs active">
                <li ${(!params.sort || (params.sort == 'createDate')) ? "class=active" : ""}>
                    <g:link controller="question" action="view" params="${params + ['sort': 'createDate']}">时间</g:link>
                </li>
                <li ${(!params.sort || (params.sort == 'user')) ? "class=active" : ""}>
                    <g:link controller="question" action="view" params="${params + ['sort': 'user']}">关注的人</g:link>
                </li>

                <li ${(!params.sort || (params.sort == 'supportNum')) ? "class=active" : ""}>
                    <g:link controller="question" action="view" params="${params + ['sort': 'supportNum']}">票数</g:link>
                </li>

                <h2 class="hidden-xs">${question.replyNum} 个回复</h2>
            </ul>
        </div>

        <div class="mod-body cs-feed-list">
            <g:each in="${question.asks}" var="ask">
                <div class="cs-item" uninterested_count="0" force_fold="0" id="answer_list_11">
                    <div class="mod-head">
                        <!-- 用户头像 -->
                        <a class="cs-user-img cs-border-radius-5 pull-right"
                           href="${createLink(controller: 'user', action: 'view', params: [id: ask.user.id])}"
                           data-id="2"><img
                                src="${ask.user.photo}" alt=""
                                onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                        </a> <!-- end 用户头像 -->
                        <div class="title">
                            <p>
                                <a class="cs-user-name"
                                   href="${createLink(controller: 'user', action: 'view', params: [id: ask.user.id])}"
                                   data-id="2">${ask.user.nickName}</a>
                                <g:if test="${(session.user && session.user.id == question.creator.id) || (session.user && session.user.id == ask.user.id)}">
                                    <g:link controller="question" action="deleteAsk" params="[id: ask.id]"
                                            class="btn btn-success pull-right" style="margin-left:10px;">删除
                                    </g:link>
                                </g:if>
                                <g:if test="${session.user && question.creator.id == session.user.id && question.rightAsk == null}">
                                    <button class="btn btn-success pull-right" name="selectRightAskBtn"
                                            onclick="selectRightAsk(${ask.id})">设为满意答案
                                    </button>
                                </g:if>
                                <g:if test="${question.rightAsk && question.rightAsk.id == ask.id}">
                                    <button class="btn btn-success pull-right">已选为最佳答案</button>
                                </g:if>
                            </p>

                        </div>
                    </div>

                    <div class="mod-body clearfix">
                        <!-- 评论内容 -->
                        <div class="markitup-box">
                            <hc:cleanHtml unsafe='${ask.content}' whitelist="sample-with-anchor"/>
                        </div>
                        <!-- end 评论内容 -->
                    </div>

                    <div class="mod-footer">
                        <!-- 社交操作 -->
                        <div class="meta clearfix">
                            <span class="text-color-999 pull-right">${CTools.humanDateDiff(ask.createDate, "yyyyMMddHHmmss")}</span>
                            <!-- 投票栏 -->
                            <span class="operate">
                                <a class="agree" onclick="javascript:support('${ask.id}', 'up')">
                                    <i class="glyphicon glyphicon-thumbs-up"></i> <b class="count"
                                                                                     id="${ask.id}up">${ask.supportNum}</b>
                                </a>
                            </span>
                            <!-- end 投票栏 -->
                            <span class="operate" id="${ask.id}" count="0">
                                <a class="agree" onclick="javascript:support('${ask.id}', 'down')"><i
                                        class="glyphicon glyphicon-thumbs-down"></i> <b
                                        class="count" id="${ask.id}down">${ask.opposeNum}</b>
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
            </g:each>
        </div>

    </div>
    <!-- end 问题详细模块 -->

    <!-- 回复编辑器 -->
    <div class="cs-mod cs-replay-box question">
        <a name="answer_form"></a>

        <g:form controller="question" action="saveAsk" name="answer_form" class="question_answer_form">
            <div class="mod-head">
                <g:if test="${session.user}">
                    <g:link controller="user" action="home" class="cs-user-name">
                        <img alt="${session.user.nickName}"
                             src="${session.user.photo}"
                             onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                    </g:link>
                    <p>${session.user.nickName}
                    </p>
                </g:if>

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
        </g:form>
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
                    <g:link controller="user" action="view" params="[id: question.creator.id]"><img alt="软爷"
                                                                                                    src="${question.creator.photo}"
                                                                                                    onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                    </g:link>
                </dt>
                <dd class="pull-left">
                    <g:link class="cs-user-name" controller="user" action="view"
                            params="[id: question.creator.id]">${question.creator.nickName}</g:link>
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
                <g:each in="${relatedQuestionList}" var="q">
                    <li>
                        <g:link controller="question" action="view" params="[id: q.id]">${q.title}</g:link>
                    </li>
                </g:each>

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