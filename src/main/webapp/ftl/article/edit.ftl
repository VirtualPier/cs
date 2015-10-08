<@override name="title">编辑文章</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>

<link rel="stylesheet" type="text/css" charset="UTF-8"
      href="${basePath}js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="${basePath}js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/language/zh_CN.js"></script>

<script type="text/javascript">
    var CKEDITOR_BASEPATH = "/js/lib/ckeditor/";
    var maxMoney = ${user.balance};
</script>
<script type="text/javascript" src="${basePath}js/lib/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/common/ckconfig.js"></script>
<link rel="stylesheet" type="text/css" href="/assets/js/lib/bootstrap-tagsinput/bootstrap-tagsinput.css"/>
<script type="text/javascript" src="/assets/js/lib/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/createArticle.js"></script>
</@override>
<@override name="body">
<div class="col-sm-12 col-md-12">

    <form method="post" action="/article/saveArticle" class="form-horizontal" name="createArticleForm"
          id="createArticleForm">
        <input type="hidden" name="id" value="${article.id}"/>

        <div class="cs-mod">
            <div class="mod-body">

                <div class="form-group">
                    <label for="title" class="col-md-2 control-label text-center">标题:</label>

                    <div class="col-md-10">
                        <!-- 问题标题 -->
                        <input type="text" placeholder="标题" name="title" id="title" value="${article.title}"
                               class="form-control">
                        <!-- end 问题标题 -->
                    </div>
                </div>

                <div class="form-group">

                    <label for="ckeditor01" class="col-md-2 control-label text-center">内容:</label>

                    <div class="col-md-10">
                        <!-- 问题标题 -->
                            <textarea rows="30" cols="50" name="description" id="ckeditor01"
                                      class="form-control">${article.description}</textarea>
                        <!-- end 问题标题 -->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label text-center">标签:</label>

                    <div class="col-md-10">
                        <#assign tags=""/>
                        <#list sysTags as tag>
                            <#assign tags="${tag.name},"/>
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
                                <label>
                                    <#if articleCategoryList?seq_contains(category)>
                                        <input type="checkbox" name="categoryIds"
                                               value="${category.id}" checked/> ${category.name}
                                    <#else>
                                        <input type="checkbox" name="categoryIds"
                                               value="${category.id}"/> ${category.name}
                                    </#if>
                                </label>
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