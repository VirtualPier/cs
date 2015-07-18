<#import "../includes/pager.ftl" as pager/>
<@override name="title">问题首页</@override>
<@override name="header">
</@override>
<@override name="body">
<!-- Stack the columns on mobile by making one full-width and the other half-width -->
<div class="row">
    <div class="col-sm-12 col-md-9 cs-main-content">
        <ul class="nav nav-tabs cs-nav-tabs  hidden-xs">
            <li>
                <a href="/question/index?hasDeal=false">待解决</a>
            </li>
            <li>
                <a href="/question/index?hasDeal=true">已解决</a>
            </li>
            <li>
                <a href="/question/index?hasDeal=true&sort=replyNum">热门</a>
            </li>
            <li>
                <a href="/question/index?hasDeal=false&sort=money">悬赏中</a>
            </li>
            <li>
                <a href="/question/index?hasDeal=false&sort=createDate">最新提问</a>
            </li>
        </ul>

        <div class="cs-mod cs-explore-list">
            <div class="mod-body">
                <div class="cs-common-list">
                    <#list questionList as question>
                        <div class="cs-item " data-topic-id="">

                            <div class="cs-question-content">
                                <h4>
                                    <a href="/question/view?${question.id}" target="_blank">${question.title}</a>
                                </h4>

                                <p>
                                    <label>提问人:</label>
                                    <a href="/user/view?id=${question.creator.id}">${question.creator.nickName}</a>
                                    <label>分类:</label>
                                    <a>22</a><a>22</a><a>22</a>
                                </p>

                                <p>
                                    <a href="/question/index" class="cs-question-tags">111111</a>
                                </p>

                                <p>
                                    <span class="text-color-999">
                                        <#if question.money gt 0>
                                            悬赏:${question.money}MB •
                                        </#if>
                                    ${question.attentionNum}人关注 • ${question.replyNum} 个回复 • ${question.viewNum}
                                        次浏览 • ${question.createDate}</span>
                                </p>

                            </div>
                        </div>
                    </#list>
                </div>
            </div>

            <div class="mod-footer">
                <div class="page-control pagination pull-right">
                    <@pager.pagination total=total offset=offset max=10  url="${basePath}question/index?hasDeal=${hasDeal}&sort=${sort}"/>
                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-12 col-md-3 cs-side-bar hidden-xs hidden-sm">
        <div class="cs-mod cs-text-align-justify">
            <div class="mod-head">
                <h3>热门分类</h3>
            </div>

            <div class="mod-body">
                <ul>
                    <#list categoryList as category>
                        <li>
                            <a href="/question/search">${category.name}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>

        <div class="cs-mod cs-text-align-justify">
            <div class="mod-head">
                <h3>热门标签</h3>
            </div>

            <div class="mod-body">
                <a class="cs-question-tags">11111</a>
            </div>
        </div>

        <div class="cs-mod cs-text-align-justify">
            <div class="mod-head">
                <h3>热门用户</h3>
            </div>

            <div class="mod-body">
            </div>
        </div>

    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>