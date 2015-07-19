<@override name="title">首页</@override>
<@override name="body">
<div class="row">
    <div class="col-md-12">
        <div class="jumbotron">
            <h1>知识造就财富!</h1>

            <p>利用您的闲暇时间帮助别人解决问题,改变您的生活和赚钱方式!</p>

            <p><a class="btn btn-primary btn-lg" href="${createLink(controller: 'question', action: 'index')}"
                  role="button">开始回答问题</a>&nbsp;&nbsp;<a class="btn btn-primary btn-lg"
                                                         href="${createLink(controller: 'article', action: 'create')}"
                                                         role="button">开始发表文章</a>
            </p>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">热门问题</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${questionList}" var="q">
                        <li class="list-group-item text-nowrap" style="overflow: hidden">
                            <g:link controller="question" action="view" params="[id: q.id]">${q.title}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-4">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">热门文章</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${articleList}" var="a">
                        <li class="list-group-item text-nowrap" style="overflow: hidden">
                            <g:link controller="article" action="view" params="[id: a.id]">${a.title}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-4">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">热门标签</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${tagList}" var="tag">
                        <li class="list-group-item">${tag[0].name}(共${tag[1]}个, 相关
                            <g:link controller="index"
                                    action="search"
                                    params="[searchType: 1, tagName: tag[0].name]">问题
                            </g:link>
                            、
                            <g:link
                                    controller="index" action="search"
                                    params="[searchType: 1, tagName: tag[0].name]">文章
                            </g:link>
                            )
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">悬赏问题</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${offerQuestionList}" var="q">
                        <li class="list-group-item text-nowrap" style="overflow:hidden;"><span
                                class="badge pull-left">${q.money}&nbsp;MB</span>&nbsp;&nbsp;
                            <g:link controller="question" action="view" params="[id: q.id]">${q.title}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">最新问题</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${newQuestionList}" var="q">
                        <li class="list-group-item text-nowrap" style="overflow:hidden;">
                            <g:link controller="question"
                                    action="view"
                                    params="[id: q.id]">${q.title}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">最新文章</h3>
            </div>

            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${newArticleList}" var="a">
                        <li class="list-group-item text-nowrap" style="overflow:hidden;">
                            <g:link controller="article"
                                    action="view"
                                    params="[id: a.id]">${a.title}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="rows">
    <g:each in="${languageList}" var="lan" status="st">
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading activate">
                    <h3 class="panel-title">${lan.name}</h3>
                </div>

                <div class="panel-body">
                    <div class="col-md-6">
                        <div class="list-group">
                            <a class="list-group-item disabled">
                                问题
                            </a>
                            <g:each in="${questionLanguageList.get(st)}" var="ql">
                                <g:link controller="question" action="view" params="[id: ql.question.id]"
                                        class="list-group-item">${ql.question.title}</g:link>
                            </g:each>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="list-group">
                            <a href="#" class="list-group-item disabled">
                                文章
                            </a>
                            <g:each in="${articleLanguageList.get(st)}" var="al">
                                <g:link controller="article" action="view" params="[id: al.article.id]"
                                        class="list-group-item">${al.article.title}</g:link>
                            </g:each>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>

</div>
</@override>
<@extends name="layout/index.ftl"/>
