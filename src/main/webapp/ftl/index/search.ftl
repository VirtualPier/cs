<#import "includes/pager.ftl" as pager/>
<#import "includes/date.ftl" as df/>
<@override name="title">搜索</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="col-sm-12 cs-main-content">
    <div class="rows">
        <div class="row">
            <div class="col-md-1 text-right">
                <label>分类:</label>
            </div>

            <div class="col-md-11">
                <#list categoryList as category>
                    <a href="/index/search?categoryId=${category.id}&searchType=${searchType}&title=${title}">${category.name}</a>&nbsp;&nbsp;
                </#list>
            </div>
        </div>

        <div class="row">
            <div class="col-md-1 text-right">
                <label>热门标签:</label>
            </div>

            <div class="col-md-11">
                <#list tagCount as tag>
                    <a href="/index/search?tagId=${tag[0].id}&searchType?searchType=${searchType}&title=${title}"
                       class="cs-question-tags">${tag[0].name}</a>
                </#list>
            </div>
        </div>

    </div>

    <div class="cs-mod cs-mod-search-result">
        <div class="mod-head">
            <h1 class="pull-left"><strong>${("1"==searchType)?string("问答","文章")}</strong>搜索结果:</h1>

            <div class="tabbable">
                <ul class="nav nav-tabs cs-nav-tabs active" id="list_nav">
                    <li ${(sort=="viewNum")?string("class='active'","")}><a
                            href="/index/search?sort=viewNum&searchType=${searchType}&title=${title}">浏览量</a>
                    </li>
                    <li ${(sort=="createDate")?string("class='active'","")}><a
                            href="/index/search?sort=createDate&searchType=${searchType}&title=${title}">发布日期</a>
                    </li>
                    <li ${(sort=="replyNum")?string("class='active'","")}><a
                            href="/index/search?sort=replyNum&searchType=${searchType}&title=${title}">回复量</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="mod-body">
            <div class="tab-content">
                <div class="tab-pane active">

                    <div id="search_result">
                        <#list modelList as model>
                            <div class="cs-item active">
                                <p class="cs-title">
                                    <#if searchType=="1">
                                        <a href="/question/view?id=${model.id}" target="_blank">${model.title}</a>
                                    <#else>
                                        <a href="/article/view?id=${model.id}" target="_blank">${model.title}</a>
                                    </#if>
                                </p>

                                <p>
                                    <label>分类:</label>
                                    <#if searchType==1>
                                        <#list model.questionCategories as category>
                                            <a href="/index/search?categoryId=${category.id}&searchType=${searchType}">${category.category.name}</a>
                                        </#list>
                                    <#else>
                                        <#list model.articleCategories as category>
                                            <a href="/index/search?categoryId=${category.id}&searchType=${searchType}">${category.category.name}</a>
                                        </#list>
                                    </#if>
                                    <label>作者:</label>
                                    <a href="/user/view?id=${model.creator.id}">${model.creator.nickName}</a>
                                </p>

                                <p>
                                    <#list model.tags as tag>
                                        <a href="/index/search?tagId=${tag.tag.id}&searchType=${searchType}"
                                           class="cs-question-tags">${tag.tag.name}</a>
                                    </#list>
                                </p>

                                <p class="cs-text-color-666">
                                    <i class="icon icon-comment"></i> ${model.replyNum} 个回复 • ${model.viewNum}
                                    次查看• <@df.dateFormat model.createDate/>发布
                                </p>
                            </div>
                        </#list>

                    </div>

                </div>
            </div>
        </div>

        <div class="mod-footer">
            <div class="page-control pagination  pull-right" style="padding-right:10px;">
                <@pager.pagination offset=offset max=max total=total url="/index/search?searchType=${searchType}&title=${title}&tagId=${tagId}&categoryId=${categoryId}&sort=${sort}&order=${order}"/>
            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>