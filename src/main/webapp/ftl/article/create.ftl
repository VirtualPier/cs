<@override name="title">发表文章</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="/assets/js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="/assets/js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="/assets/js/lib/bootstrap-validator/js/language/zh_CN.js"></script>
<link rel="stylesheet" charset="UTF-8" href="/assets/js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="/assets/js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
    var CKEDITOR_BASEPATH = "/js/lib/ckeditor/";
    var maxMoney = ${user.balance};
</script>
<script type="text/javascript" src="/assets/js/lib/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/assets/js/coderstar/common/ckconfig.js"></script>
<link rel="stylesheet" type="text/css" href="/assets/js/lib/bootstrap-tagsinput/bootstrap-tagsinput.css"/>
<script type="text/javascript" src="/assets/js/lib/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>

<script type="text/javascript" src="/assets/js/coderstar/front/create.js"></script>
</@override>
<@override name="body">

<div class="col-sm-12 col-md-12">
    <!--隐藏表单域，记录当前页面的类型-->
    <input type="hidden" id="type" value="article"/>

    <form action="/article/save" class="form-horizontal" name="createQuestionForm" id="createQuestionForm"
          method="post">
        <div class="cs-mod">
            <div class="mod-body">

                <div class="form-group">
                    <label for="title" class="col-md-2 control-label text-center">标题:</label>

                    <div class="col-md-10">
                        <!-- 问题标题 -->
                        <input type="text" placeholder="标题" name="title" id="title" value="${title}"
                               class="form-control">
                        <!-- end 问题标题 -->
                    </div>
                </div>

                <div class="form-group">

                    <label for="ckeditor01" class="col-md-2 control-label text-center">内容:</label>

                    <div class="col-md-10">
                        <!-- 问题标题 -->
                            <textarea rows="30" cols="50" name="content" id="ckeditor01"
                                      class="form-control">${content}</textarea>
                        <!-- end 问题标题 -->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label text-center">标签:</label>

                    <div class="col-md-10">
                        <#assign tags=""/>
                        <#list tags as tag>
                            <#assign tags="${tag},"/>
                        </#list>
                        <input type="text" class="form-control" id="tagTxt" data-role="tagsinput"
                               placeholder="输入后回车提交" value="${tags}" name="tags"/>
                    </div>
                </div>

                <div class="form-group">

                    <label class="col-md-2 control-label text-center">分类:</label>


                    <div class="col-md-10">
                        <#list categoryList as category>
                            <div class="checkbox pull-left">
                                &nbsp;&nbsp;<label><input type="checkbox" name="categoryIds"
                                                          value="${category.id}"/>${category.name}</label>
                            </div>
                        </#list>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-md-12 text-center">
                        <div class="col-md-10 text-center">
                            <button type="submit" class="pull-right btn btn-large btn-primary">提交</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </form>
</div>
</@override>
<@extends name="layout/index.ftl"/>