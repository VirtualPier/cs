<@override name="title">首页</@override>
<@override name="body">
<div class="row">
    <div class="col-md-12">
        <div class="jumbotron">
            <h1>知识造就财富!</h1>

            <p>利用您的闲暇时间帮助别人解决问题,改变您的生活和赚钱方式!</p>

            <p><a class="btn btn-primary btn-lg" href="/question/index"
                  role="button">开始回答问题</a>&nbsp;&nbsp;<a class="btn btn-primary btn-lg"
                                                         href="/article/create"
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
                    <#list questionList as q>
                        <li class="list-group-item text-nowrap" style="overflow: hidden">
                            <a href="/question/view?id=${q.id}">${q.title}</a>
                        </li>
                    </#list>
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
                    <#list articleList as a>
                        <li class="list-group-item text-nowrap" style="overflow: hidden">
                            <a href="/article/view?id=${a.id}">${a.title}</a>
                        </li>
                    </#list>
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
                    <#list tagCount as tag>
                        <li class="list-group-item">${tag[0].name}(共${tag[1]}个, 相关
                            <a href="/question/index?tagId=${tag[0].id}">问题</a>
                            、
                            <a href="/article/index?tagId=${tag[0].id}">文章</a>
                            )
                        </li>
                    </#list>
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
                    <#list offerQuestionList as q>
                        <li class="list-group-item text-nowrap" style="overflow:hidden;"><span
                                class="badge pull-left">${q.money}&nbsp;MB</span>&nbsp;&nbsp;
                            <a href="/question/view?id=${q.id}">${q.title}</a>
                        </li>
                    </#list>
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
                    <#list newQuestionList as q>
                        <li class="list-group-item text-nowrap" style="overflow:hidden;">
                            <a href="/question/view?id=${q.id}">${q.title}</a>
                        </li>
                    </#list>
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
                    <#list newArticleList as a>
                        <li class="list-group-item text-nowrap" style="overflow:hidden;">
                            <a href="/article/view?id=${a.id}">${a.title}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="rows">
    <#list categoryList as category>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading activate">
                    <h3 class="panel-title">${category.name}</h3>
                </div>

                <div class="panel-body">
                    <div class="col-md-6">
                        <div class="list-group">
                            <a class="list-group-item disabled">
                                问题
                            </a>
                            <#list questionCategoryList[category_index] as ql>
                                <a href="/question/view?id=${ql.id}" class="list-group-item">${ql.title}</a>
                            </#list>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="list-group">
                            <a href="#" class="list-group-item disabled">
                                文章
                            </a>
                            <#list articleCategoryList[category_index] as al>
                                <a href="/article/view?id=${al.id}" class="list-group-item">${al.title}</a>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>

</div>
</@override>
<@extends name="layout/index.ftl"/>
