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
            <a href="#" class="list-group-item disabled">热门分类</a>
            <#list categoryList as category>
                <a class="list-group-item" href="/index/search" params="[searchType: 2, languageId: lang.id]">${category.name}</a>
            </#list>
        </div>
    </div>

    <div class="col-sm-12  col-md-7  cs-main-content">
        <ul class="nav nav-tabs cs-nav-tabs  hidden-xs">
            <li>
                <a controller="article" action="index" params="">最新</a>
            </li>
            <li>
                <a controller="article" action="index" params="">评论量</a>
            </li>
            <li>
                <a controller="article" action="index" params="">热门</a>
            </li>
        </ul>

        <div class="cs-mod cs-explore-list">
            <div class="mod-body">
                <div class="cs-common-list">
                    <g:each in="${articleList}" var="article">
                        <div class="cs-item " data-topic-id="">

                            <div class="cs-question-content">
                                <h4>
                                    <g:link controller='article'
                                            action='view'
                                            target="_blank" params="[id: article.id]">${article.title}</g:link>
                                </h4>

                                <p>

                                    <label>作者:</label>
                                    <g:link controller="user" action="view"
                                            params="[id: article.creator.id]">${article.creator.nickName}</g:link>
                                    <label>分类:</label>
                                    <g:each in="${article.languages}" var="lan">
                                        <g:link controller="index" action="search"
                                                params="[languageId: lan.id, searchType: 2]">${lan.name}</g:link>
                                    </g:each>

                                </p>

                                <p>
                                    <g:each in="${article.tags}" var="tag">
                                        <g:link class="cs-question-tags" controller="index" action="search"
                                                params="[tagName: tag.name, searchType: 2]">${tag.name}</g:link>
                                    </g:each>

                                </p>

                                <p>
                                    <span class="text-color-999">${article.attentionNum} 人关注 • ${article.replyNum}
                                        个回复 • ${article.viewNum}
                                        次浏览 • ${org.ligson.coderstar.utils.CTools.humanDateDiff(article.createDate, "yyyyMMddHHmmss")}</span>
                                    <span class="text-color-999 related-topic hide">•  来自相关话题</span>
                                </p>

                            </div>
                        </div>
                    </g:each>

                </div>
            </div>

            <div class="mod-footer">
                <div class="page-control pagination pull-right">
                    <g:paginate total="${total}" controller="article" action="index"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="col-md-3">
    <div class="panel panel-default">
        <div class="panel-heading">热门标签</div>

        <div class="panel-body">
            <g:each in="${tagCount}" var="tagC">
                <g:link class="cs-question-tags" controller="index" action="search"
                        params="[tagName: tagC[0].name, searchType: 2]">${tagC[0].name}(${tagC[1]})
                </g:link>
            </g:each>
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
                        <g:each in="${weekCount}" var="count">
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="#">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>
                                ${count[1]}篇文章
                                </div>
                            </li>
                        </g:each>

                    </ul>
                </div>

                <div role="tabpanel" class="tab-pane" id="yuebang">

                    <ul class="media-list">
                        <g:each in="${monthCount}" var="count">
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="#">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>
                                ${count[1]}篇文章
                                </div>
                            </li>
                        </g:each>

                    </ul>
                </div>

                <div role="tabpanel" class="tab-pane" id="zongbang">

                    <ul class="media-list">
                        <g:each in="${allCount}" var="count">
                            <li class="media">
                                <div class="media-left pull-left">
                                    <a href="#">
                                        <img style="width:64px;height:64px;" class="media-object"
                                             src="${count[0].photo}" alt="..."
                                             onerror="javascript:this.src='${assetPath(src: "pic_user.gif")}'">
                                    </a>
                                </div>

                                <div class="media-body">
                                    <h4 class="media-heading">${count[0].nickName}</h4>
                                ${count[1]}篇文章
                                </div>
                            </li>
                        </g:each>

                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>