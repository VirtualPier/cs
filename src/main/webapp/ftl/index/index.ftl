<@override name="title">首页</@override>
<@override name="body">
<div class="row" style="margin-bottom:10px;">
    <div class="col-md-9" style="padding-right:0;">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <#list recommendArticleList as a>
                    <li data-target="#carousel-example-generic"
                        data-slide-to="${a_index}"></li>
                </#list>
                <#list recommendQuestionList as q>
                    <li data-target="#carousel-example-generic"
                        data-slide-to="${(q_index+(recommendArticleList?size))}"></li>
                </#list>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <#list recommendArticleList as a>
                    <div class="item">
                        <img src="${a.poster}"
                             alt="..." onerror="javascript:this.src='/images/nopic.gif'"
                             style="width:100%;height:420px;">

                        <div class="carousel-caption">
                            <h3>[文章]<a style="color:#ffffff;" href="/article/view?id=${a.id}">${a.title}</a></h3>

                            <p></p>
                        </div>
                    </div>
                </#list>
                <#list recommendQuestionList as q>
                    <div class="item ${(q_index==0)?string("active","")}">
                        <img src="${q.poster}"
                             alt="..." onerror="javascript:this.src='/images/nopic.gif'"
                             style="width:100%;height:420px;">

                        <div class="carousel-caption">
                            <h3>[问题]<a style="color:#ffffff;" href="/question/view?id=${q.id}">${q.title}</a></h3>

                            <p></p>
                        </div>
                    </div>
                </#list>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="col-md-3">
        <div class="list-group">
            <#list newQuestionList as q>
                <a href="/question/view?id=${q.id}"
                   class="list-group-item text-ellipsis${(q_index==0)?string(' active','')}"
                   title="${q.title}">${(q_index==0)?string('[问题]','')}${q.title}</a>
            </#list>
        </div>
        <div class="list-group">
            <#list newArticleList as a>
                <a href="/article/view?id=${a.id}"
                   class="list-group-item text-ellipsis${(a_index==0)?string(' active','')}"
                   title="${a.title}">${(a_index==0)?string('[文章]','')}${a.title}
                </a>
            </#list>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <#list categoryList as cat>
            <div class="col-sm-6 col-md-3" title="${cat.name}">
                <div class="thumbnail">
                    <img data-src="holder.js/100%x100" alt="100%x100"
                         style="height: 100px; width: 100%; display: block;"
                         src="${cat.poster}"
                         data-holder-rendered="true" onerror="javascript:this.src='/images/nopic.gif'">

                    <div class="caption">
                        <p><a href="/question/index?categoryId=${cat.id}" class="btn btn-primary" role="button">问答</a>
                            <a href="/article/index?categoryId=${cat.id}" class="btn btn-default"
                               role="button">文章</a></p>
                    </div>
                </div>
            </div>
        </#list>

    </div>

</div>

<div class="row">
    <div class="col-md-9">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#hotQuestion" aria-controls="hotQuestion"
                                                      role="tab" data-toggle="tab">热门问题</a>
            </li>
            <li role="presentation"><a href="#waitQuestion" aria-controls="waitQuestion" role="tab" data-toggle="tab">待解决问题</a>
            </li>
            <li role="presentation"><a href="#offerQuestion" aria-controls="offerQuestion" role="tab"
                                       data-toggle="tab">悬赏问题</a></li>
            <li role="presentation"><a href="#hotArticle" aria-controls="hotArticle" role="tab"
                                       data-toggle="tab">热门文章</a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="hotQuestion">
                <ul class="list-group">
                    <#list hotQuestions as q>
                        <li class="list-group-item${(q_index==0)?string(" notopstyle","")}">
                            <a href="/question/view?id=${q.id}">${q.title}</a>
                        </li>
                    </#list>
                </ul>
            </div>
            <div role="tabpanel" class="tab-pane" id="waitQuestion">
                <#list waitQuestionList as q>
                    <li class="list-group-item${(q_index==0)?string(" notopstyle","")}">
                        <a href="/question/view?id=${q.id}">${q.title}</a>
                    </li>
                </#list>
            </div>
            <div role="tabpanel" class="tab-pane" id="offerQuestion">
                <#list offerQuestionList as q>
                    <li class="list-group-item${(q_index==0)?string(" notopstyle","")}">
                        <span class="label label-warning">${q.money}MB</span><a
                            href="/question/view?id=${q.id}">${q.title}</a>
                    </li>
                </#list>
            </div>
            <div role="tabpanel" class="tab-pane" id="hotArticle">
                <#list hotArticles as a>
                    <li class="list-group-item${(a_index==0)?string(" notopstyle","")}">
                        <a href="/article/view?id=${a.id}">${a.title}</a>
                    </li>
                </#list>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="list-group">
            <a href="#" class="list-group-item active">最新提现</a>
            <#list withdrawList as w>
                <a href="/user/view?id=${w.user.id}" class="list-group-item">
                    <img src="${w.user.photo}" class="img-thumbnail" style="width:60px;height:60px;"
                         onerror="javascript:this.src='/images/nopic.gif'"/>&nbsp;&nbsp;${w.user.nickName}&nbsp;提现${w.trueMoney}
                    元</a>
            </#list>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item active">热门作者</a>
            <#list hotAuthors as u>
                <a href="/user/view?id=${u.id}" class="list-group-item">
                    <img src="${u.photo}" class="img-thumbnail" style="width:60px;height:60px;"
                         onerror="javascript:this.src='/images/nopic.gif'"/>&nbsp;&nbsp;${u.nickName}</a>
            </#list>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item active">问题专家</a>
            <#list hotReplyers as u>
                <a href="/user/view?id=${u.id}" class="list-group-item"><img src="${u.photo}" class="img-thumbnail"
                                                                             style="width:60px;height:60px;"
                                                                             onerror="javascript:this.src='/images/nopic.gif'"/>&nbsp;&nbsp;${u.nickName}
                </a>
            </#list>
        </div>
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>
