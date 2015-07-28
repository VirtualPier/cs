<#import "includes/date.ftl" as df/>
<@override name="title">${question.title}</@override>
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
<script src="/js/coderstar/front/viewArticle.js" type="text/javascript"></script>
</@override>
<@override name="body">
<div class="rows">
    <div class="col-md-10 col-sm-12">
        <div class="cs-mod cs-question-detail cs-item">
            <div class="mod-head">
                <h1>${article.title}</h1>

                <p><label>分类:</label>
                    <#list article.articleCategories as articleCategory>
                        <a href="/article/index?categoryId=${articleCategory.category.id}">${articleCategory.category.name}</a>
                    </#list>

                    <#if (user??)&&(user.id==article.creator.id)>
                        <a class="pull-right" style="margin-left:10px;" href="/user/deleteArticle?id=${article.id}">
                            <span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
                        <a class="pull-right" style="margin-left:10px;" href="/article/edit?id=${article.id}">
                            <span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑</a>
                    </#if>
                    <a class="pull-right" data-flag="${isAttention?string("0","1")}" href="javascript:void(0);"
                       onclick="attentionArticle(${article.id},this)" id="attentionArticleBtn"><span
                            class="glyphicon glyphicon-heart-empty"></span>&nbsp;${isAttention?string("取消关注","关注本文")}
                    </a>
                </p>

                <p><label>标签:</label>
                    <#list article.tags as ta>
                        <a href="/article/index?tagId=${ta.tag.id}" class="cs-question-tags">${ta.tag.name}</a>
                    </#list>
                </p>
                <#include "includes/share.ftl">
            </div>

            <div class="mod-body">
                <div class="content markitup-box" style="overflow:hidden;">
                ${article.description}
                </div>
            </div>

            <div class="mod-footer">
                <div class="meta">
                    <span class="text-color-999"><@df.dateFormat article.createDate/>发布</span>
                </div>
            </div>
        </div>

        <div class="cs-mod">
            <div class="mod-body">
                <div style="text-align: center;padding:10px;">
                    <button type="button" class="btn btn-success"
                            onclick="supportArticle(${article.id}, true, this);" ${isDisabled?string("disabled" , "")}><span
                            class="glyphicon glyphicon-thumbs-up"></span>顶一下(${supportNum})
                    </button>
                    <button type="button" class="btn btn-danger"
                            onclick="supportArticle(${article.id}, false, this);" ${isDisabled?string("disabled","")}><span
                            class="glyphicon glyphicon-thumbs-down"></span>太水了(${opposeNum})
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="rewardArticle(${article.id}, this);"><span
                            class="glyphicon glyphicon-usd" aria-hidden="true"></span>打赏一下
                    </button>
                </div>
            </div>
        </div>

        <div class="cs-mod">
            <div class="mod-body">
                <div style="text-align: center;padding:10px;">
                </div>
            </div>
        </div>

        <div class="cs-mod cs-question-comment">
            <div class="mod-head">
                <ul class="nav nav-tabs cs-nav-tabs active">
                    <li ${(remarkSort=='createDate')?string("class='active'","")}>
                        <a href="/article/view?id=${article.id}&remarkSort=createDate">最新</a>
                    </li>


                    <li ${(remarkSort=='supportNum')?string("class='active'","")}>
                        <a href="/article/view?id=${article.id}&remarkSort=supportNum">热赞</a>
                    </li>

                    <h2 class="hidden-xs">${article.remarks?size} 个回复</h2>
                </ul>
            </div>

            <div class="mod-body cs-feed-list">
                <#list remarkList as remark>
                    <div class="cs-item" uninterested_count="0" force_fold="0" id="answer_list_${remark.id}">
                        <div class="mod-head">
                            <!-- 用户头像 -->
                            <a href="/user/view?id=${remark.user.id}" class="cs-user-img cs-border-radius-5 pull-right"><img
                                    src="${remark.user.photo}" alt=""
                                    onerror="javascript:this.src='/images/pic_user.gif'"></a>
                            <!-- end 用户头像 -->
                            <div class="title">
                                <p>
                                    <a class="cs-user-name" href="/user/view?id=${remark.user.id}"
                                       data-id="2">${remark.user.nickName}</a>
                                </p>

                            </div>
                        </div>

                        <div class="mod-body clearfix">
                            <!-- 评论内容 -->
                            <div class="markitup-box">
                            ${remark.content}
                            </div>
                            <!-- end 评论内容 -->
                        </div>

                        <div class="mod-footer">
                            <!-- 社交操作 -->
                            <div class="meta clearfix">
                                <span class="text-color-999 pull-right"><@df.dateFormat remark.createDate/>之前</span>
                                <!-- 投票栏 -->
                                <span class="operate">
                                    <a class="agree" onclick="javascript:supportRemark(${remark.id}, 'up')">
                                        <i class="glyphicon glyphicon-thumbs-up"></i> <b class="count"
                                                                                         id="remark_${remark.id}_up_count">${remark.supportNum}</b>
                                    </a>
                                </span>
                                <!-- end 投票栏 -->
                                <span class="operate">
                                    <a class="agree" onclick="javascript:supportRemark(${remark.id}, 'down')"><i
                                            class="glyphicon glyphicon-thumbs-down"></i> <b class="count"
                                                                                            id="remark_${remark.id}_down_count">${remark.opposeNum}</b>
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
                </#list>

            </div>

        </div>
        <!-- 回复编辑器 -->
        <div class="cs-mod cs-replay-box question">
            <a name="answer_form"></a>

            <form method="post" action="/article/saveRemark" name="answer_form" class="question_answer_form"
                  id="answer_form">
                <input type="hidden" name="articleId" value="${article.id}">

                <div class="mod-head">
                </div>

                <div class="mod-body">

                    <div class="cs-mod cs-editor-box">
                        <textarea rows="5" name="content" id="ckeditor01"
                                  style="visibility: hidden; display: none;"></textarea>
                    </div>

                    <div class="text-right">
                        <br>
                        <input type="submit" value="回复" class="btn btn-default">
                    </div>
                </div>
            </form>
        </div>
        <!-- end 回复编辑器 -->
    </div>

    <div class="col-md-2  col-sm-12 cs-side-bar hidden-xs hidden-sm">
        <div class="cs-mod">
            <div class="mod-head">
                <h3></h3>
            </div>

            <div class="mod-body">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail" style="border:0;">
                            <img alt="软爷" style="width:64px;height:64px;" class="media-object"
                                 src="${article.creator.photo}"
                                 onerror="javascript:this.src='/images/pic_user.gif'">

                            <div class="caption">
                                <h3 class="text-center">
                                    <a href="/user/view?id=${article.creator.id}">${article.creator.nickName}</a>
                                </h3>

                                <p>${article.creator.introduce}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="cs-mod">
            <div class="mod-head">
                <h3>相关文章</h3>
            </div>

            <div class="mod-body font-size-12">
                <ul>
                    <#list relatedArticleList as at>
                        <li>
                            <a href="/article/view?id=${at.id}">${at.title}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>