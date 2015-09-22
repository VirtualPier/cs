<#import "includes/date.ftl" as df/>
<@override name="title">${user.nickName}</@override>
<@override name="header">
<script type="text/javascript">
    var userId = ${viewUser.id};
</script>
<script src="${basePath}js/coderstar/common/string.js"></script>
<script src="${basePath}js/coderstar/common/date.js"></script>
<script src="${basePath}js/coderstar/front/user/view.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-12 cs-main-content">
            <!-- 用户数据内容 -->
            <div class="cs-mod cs-user-detail-box">
                <div class="mod-head">
                    <img alt="${viewUser.nickName}" src="${viewUser.photo}"
                         onerror="javascript:this.src='/images/pic_user.gif'" style="width:100px;height:100px;">

                    <h1>${viewUser.nickName}</h1>

                    <p class="text-color-999"></p>

                    <p class="aw-user-flag">
                        <span>${viewUser.introduce}</span>
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
                                </div>

                            </div>

                            <div class="mod-footer">
                                <!-- 加载更多内容 -->
                                <a class="cs-load-more-content" id="loadQuestionBtn" data-page="1">
                                    <span>更多</span>
                                </a>
                                <!-- end 加载更多内容 -->
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

                                </div>
                            </div>

                            <div class="mod-footer">
                                <!-- 加载更多内容 -->
                                <a class="cs-load-more-content" id="loadArticleBtn" data-page="1">
                                    <span>更多</span>
                                </a>
                                <!-- end 加载更多内容 -->
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
                                    <dt><span>性别:</span></dt>
                                    <dd>${(viewUser.sex==1)?string("男","女")}</dd>
                                </dl>

                                <dl>
                                    <dt><span>最后活跃:</span></dt>
                                    <dd><@df.dateFormat viewUser.lastLoginDate/></dd>
                                </dl>
                                <dl>
                                    <dt><span>个人简介:</span></dt>
                                    <dd>${viewUser.introduce}</dd>
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