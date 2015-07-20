<@override name="title">${user.nickName}</@override>
<@override name="header">
<script type="text/javascript">
    var userId = ${user.id};
</script>
<script src="${basePath}js/coderstar/front/user/view.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-12 cs-main-content">
            <!-- 用户数据内容 -->
            <div class="cs-mod cs-user-detail-box">
                <div class="mod-head">
                    <img alt="${user.nickName}" src="${user.photo}"
                         onerror="javascript:this.src='/images/pic_user.gif'">

                    <h1>${user.nickName}</h1>

                    <p class="text-color-999"></p>

                    <p class="aw-user-flag">
                        <span>${user.introduce}</span>
                    </p>
                </div>
                <div class="mod-footer">
                    <ul class="nav nav-tabs cs-nav-tabs">
                        <li><a href="#questions" id="page_questions" data-toggle="tab">发问<span
                                class="badge">${questionTotal}</span>
                        </a></li>
                        <li><a href="#answers" id="page_answers" data-toggle="tab">文章<span
                                class="badge">${articleTotal}</span></a>
                        </li>
                        <li><a href="#detail" id="page_detail" data-toggle="tab">详细资料</a></li>
                    </ul>
                </div>
            </div>
            <!-- end 用户数据内容 -->

            <div class="cs-user-center-tab">
                <div class="tab-content">
                    <div class="tab-pane" id="questions">
                        <div class="cs-mod">
                            <div class="mod-head">
                                <h3>发问</h3>
                            </div>

                            <div class="mod-body">
                                <div class="cs-profile-publish-list" id="contents_user_actions_questions">
                                    <#list questionList as question>
                                        <div class="cs-item">
                                            <div class="cs-mod">
                                                <div class="mod-head">
                                                    <h4 class="cs-hide-txt">
                                                        <a href="/quesiton/view?id=${question.id}">${question.title}</a>
                                                    </h4>
                                                </div>

                                                <div class="mod-body">
                                                    <span class="cs-border-radius-5 count pull-left"><i
                                                            class="glyphicon glyphicon-edit"></i>${question.replyNum}
                                                    </span>

                                                    <p class="text-color-999">${question.viewNum}
                                                        次浏览• ${question.attentionNum} 个关注</p>

                                                    <p class="text-color-999">${question.createDate}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </div>

                            </div>

                            <div class="mod-footer">
                                <#if questionTotal gt 10>
                                    <!-- 加载更多内容 -->
                                    <a class="cs-load-more-content" id="loadQuestionBtn" data-page="1">
                                        <span>更多</span>
                                    </a>
                                    <!-- end 加载更多内容 -->
                                </#if>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane" id="answers">
                        <div class="cs-mod">
                            <div class="mod-head">
                                <h3>文章</h3>
                            </div>

                            <div class="mod-body">
                                <div class="cs-profile-answer-list" id="contents_user_actions_answers">
                                    <#list articleList as article>
                                        <div class="cs-item">
                                            <div class="cs-mod">
                                                <div class="mod-head">
                                                    <h4 class="cs-hide-txt">
                                                        <a href="/article/view?id=${article.id}">${article.title}</a>
                                                    </h4>
                                                </div>

                                                <div class="mod-body">
                                                    <span class="cs-border-radius-5 count pull-left"><i
                                                            class="glyphicon glyphicon-thumbs-up"></i>${article.replyNum}
                                                    </span>

                                                    <p class="text-color-999">
                                                        <#if article.descripton?length gt 300>
                                                        ${article.description?substring(0,300)}
                                                        <#else>
                                                        ${article.description}
                                                        </#if>
                                                    </p>

                                                    <p class="text-color-999">${article.createDate}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>

                            <div class="mod-footer">
                                <#if articleTotal gt 10>
                                    <!-- 加载更多内容 -->
                                    <a class="cs-load-more-content" id="loadArticleBtn" data-page="1">
                                        <span>更多</span>
                                    </a>
                                    <!-- end 加载更多内容 -->
                                </#if>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane active" id="detail">
                        <div class="cs-mod">
                            <div class="mod-head">
                                <h3>详细资料</h3>
                            </div>

                            <div class="mod-body cs-user-center-details">
                                <dl>
                                    <dt><span>最后活跃:</span></dt>
                                    <dd>${user.lastLoginDate}</dd>
                                </dl>

                                <dl>
                                    <dt><span>擅长领域:</span></dt>
                                    <dd class="cs-user-center-details-good-topic">
                                        <div>
                                            <span class="topic-tag">
                                                <#list tags as tag>
                                                    <a class="text" data-id="${tag.id}"
                                                       href="/article/index?tagId=${tag.id}">${tag.name}</a>
                                                </#list>
                                            </span>
                                        </div>
                                    </dd>
                                </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end 侧边栏 -->
</div>
</@override>
<@extends name="layout/index.ftl"/>