<@override name="title">充值记录</@override>
<@override name="header">
<script type="text/javascript" src="/js/coderstar/common/baseurl.js"></script>
<script type="text/javascript" src="/js/coderstar/common/date.js"></script>
<script type="text/javascript" src="/js/coderstar/front/user/myRechangeLog.js"></script>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-9 cs-main-content">

            <a name="c_contents"></a>

            <div class="cs-mod clearfix">
                <div class="mod-head common-head">
                    <h2 id="main_title">充值记录</h2>
                </div>

                <div class="mod-body cs-feed-list clearfix" id="main_contents">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>充值金额</th>
                            <th>时间</th>
                            <th>状态</th>
                            <th>注释</th>
                        </tr>
                        </thead>
                        <tbody id="rechangeLogList">
                        </tbody>
                    </table>
                </div>

                <div class="mod-footer">
                    <!-- 加载更多内容 -->
                    <a id="bp_more" class="cs-load-more-content" data-page="1">
                        <span>加载更多内容</span>
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