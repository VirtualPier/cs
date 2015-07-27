<@override name="title">我发布的文章</@override>
<@override name="header">
<script type="text/javascript" src="/js/coderstar/common/baseurl.js"></script>
<script type="text/javascript" src="/js/coderstar/front/user/myArticle.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-9 cs-main-content">

            <a name="c_contents"></a>

            <div class="cs-mod clearfix">
                <div class="mod-head common-head">
                    <h2 id="main_title">我发表的文章</h2>
                </div>

                <div class="mod-body cs-feed-list clearfix" id="main_contents">

                </div>

                <div class="mod-footer">
                    <!-- 加载更多内容 -->
                    <a id="bp_more_reply" class="cs-load-more-content disabled" data-page="1">
                        <span id="btn_msg">查看更多</span>
                    </a>
                    <!-- end 加载更多内容 -->
                </div>
            </div>
        </div>
        <!-- 侧边栏 -->
        <#include "user/sliderBar.ftl">
        <!-- end 侧边栏 -->
    </div>
</div>
</@override>
<@extends name="layout/index.ftl"/>