<#import "includes/pager.ftl" as pager/>
<#import "includes/date.ftl" as df/>
<@override name="title">文章首页</@override>
<@override name="header">
<style type="text/css">
    .paddingLFNone {
        padding-left: 0;
        padding-right: 0;
    }
</style>
</@override>
<@override name="body">
<div class="rows">
    <div class="col-md-2 paddingLFNone">
        <div class="list-group">
            <a href="/article/index?categoryId=-1" class="list-group-item disabled">全部分类</a>
            <#list categoryList as category>
                <a class="list-group-item ${(categoryId==category.id)?string('active','')}"
                   href="/article/index?categoryId=${category.id}">${category.name}</a>
            </#list>
        </div>
    </div>

    <div class="col-sm-12  col-md-7  cs-main-content">
        <ul class="nav nav-tabs cs-nav-tabs  hidden-xs">
            <li ${(order=='createDate')?string("class='active'","")}>
                <a href="/article/index?order=createDate">最新</a>
            </li>
            <li ${(order=='replyNum')?string("class='active'","")}>
                <a href="/article/index?order=replyNum">评论量</a>
            </li>
            <li ${(order=='viewNum')?string("class='active'","")}>
                <a href="/article/index?order=viewNum">热门</a>
            </li>
        </ul>

        <div class="cs-mod cs-explore-list">
            <div class="mod-body">
                <div class="cs-common-list">
                    <#list articleList as article>
                        <div class="cs-item " data-topic-id="">

                            <div class="cs-question-content">
                                <h4>
                                    <a href="/article/view?id=${article.id}" target="_blank">${article.title}</a>
                                </h4>

                                <p>
                                    <label>作者:</label>
                                    <a href="/user/view?id=${article.creator.id}">${article.creator.nickName}</a>
                                    <label>分类:</label>
                                    <#list articleCategoryList[article_index] as category>
                                        <a href="/article/index?categoryId=${category.id}">${category.name}</a>
                                    </#list>

                                </p>

                                <p>
                                    <#list articleTagList[article_index] as tag>
                                        <a class="cs-question-tags"
                                           href="/article/index?tagId=${tag.id}">${tag.name}</a>
                                    </#list>
                                </p>

                                <p>
                                    <span class="text-color-999">${article.attentionNum} 人关注 • ${article.replyNum}
                                        个回复 • ${article.viewNum}
                                        次浏览 • <@df.dateFormat article.createDate/></span>
                                    <span class="text-color-999 related-topic hide">•  来自相关话题</span>
                                </p>

                            </div>
                        </div>
                    </#list>

                </div>
            </div>

            <div class="mod-footer">
                <div class="page-control pagination pull-right">
                    <@pager.pagination total=total offset=offset max=max url="/article/index?categoryId=${categoryId}&order=${order}&tagId=${tagId}"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="panel panel-default">
        <div class="panel-heading">热门标签</div>

        <div class="panel-body">
            <#list sysTags as tag>
                <a href="/article/index?tagId=${tag.id}" class="cs-question-tags">${tag.name}</a>
            </#list>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">作者排行榜</h3>
        </div>

        <div class="panel-body">
            <ul class="nav nav-tabs cs-nav-tabs  hidden-xs">
                <li class="active"><a href="#zhoubang" aria-controls="zhoubang" role="tab" data-toggle="tab">周榜</a></li>
                <li><a href="#yuebang" aria-controls="yuebang" role="tab" data-toggle="tab">月榜</a></li>
                <li><a href="#zongbang" aria-controls="zongbang" role="tab" data-toggle="tab">总榜</a></li>
            </ul>

            <div class="tab-content" style="padding:5px;">
                <div role="tabpanel" class="tab-pane active" id="zhoubang">

                    <ul class="media-list">
                        <#list weekCount as count>
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="/user/view?id=${count[0].id}">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='/images/pic_user.gif'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>${count[1]}篇文章
                                </div>
                            </li>
                        </#list>

                    </ul>
                </div>

                <div role="tabpanel" class="tab-pane" id="yuebang">

                    <ul class="media-list">
                        <#list monthCount as count>
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="/user/view?id=${count[0].id}">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='/images/pic_user.gif'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>
                                ${count[1]}篇文章
                                </div>
                            </li>
                        </#list>

                    </ul>
                </div>

                <div role="tabpanel" class="tab-pane" id="zongbang">

                    <ul class="media-list">
                        <#list allCount as count>
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="/user/view?id=${count[0].id}">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='/images/pic_user.gif'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>
                                ${count[1]}篇文章
                                </div>
                            </li>
                        </#list>

                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>