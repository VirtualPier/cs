<@override name="title">提问的问题</@override>
<@override name="header">
<script type="text/javascript" src="/js/coderstar/common/baseurl.js"></script>
<link href="/js/lib/jquery-bootgrid/jquery.bootgrid.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/lib/jquery-bootgrid/jquery.bootgrid.min.js"></script>
<script type="text/javascript" src="/js/coderstar/common/date.js"></script>
<script type="text/javascript" src="/js/coderstar/front/user/myQuestion.js"></script>
<style type="text/css">
    .column-header-anchor {
        text-align: center;
    }
</style>
</@override>
<@override name="body">
<div class="row">
    <div class="cs-content-wrap clearfix">
        <div class="col-sm-12 col-md-9 cs-main-content">

            <a name="c_contents"></a>

            <div class="cs-mod clearfix">
                <div class="mod-head common-head">
                    <h2 id="main_title">我提问的问题</h2>
                </div>

                <div class="mod-body cs-feed-list clearfix" id="main_contents">
                    <table id="grid-basic" class="table table-condensed table-hover table-striped">
                        <thead>
                        <tr>
                            <th data-column-id="id" data-type="numeric" data-visible="false" data-searchable="false">
                                ID
                            </th>
                            <th data-column-id="title" data-formatter="title" data-cssClass="table-title">问题</th>
                            <th data-column-id="createDate" data-align="center" data-formatter="createDate"
                                data-searchable="false">创建时间
                            </th>
                            <th data-column-id="state" data-align="center" data-formatter="state">状态</th>
                            <th data-column-id="viewNum" data-align="center" data-searchable="false">浏览量</th>
                            <th data-column-id="replyNum" data-align="center" data-searchable="false">回答量</th>
                            <th data-formatter="delOper" data-align="center" data-headerAlign="center"
                                data-searchable="false" data-sortable="false">删除
                            </th>
                            <th data-formatter="editOper" data-align="center" data-headerAlign="center"
                                data-searchable="false" data-sortable="false">编辑
                            </th>
                        </tr>
                        </thead>
                    </table>
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