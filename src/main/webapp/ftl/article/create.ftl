<@override name="title">发表文章</@override>
<@override name="header">
<link rel="stylesheet" type="text/css" href="${basePath}js/lib/bootstrap-validator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${basePath}js/lib/bootstrap-validator/js/language/zh_CN.js"></script>
<link rel="stylesheet" charset="UTF-8" href="/js/lib/bootstrap3-dialog/css/bootstrap-dialog.min.css"/>
<script type="text/javascript" src="/js/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript">
    var CKEDITOR_BASEPATH = "/js/lib/ckeditor/";
    var maxMoney = ${user.balance};
</script>
<script type="text/javascript" src="${basePath}js/lib/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/common/ckconfig.js"></script>
<script type="text/javascript" src="${basePath}js/coderstar/front/createArticle.js"></script>
</@override>
<@override name="body">

<div class="col-sm-12 col-md-9 cs-main-content">

    <form action="/article/save" class="form-horizontal" name="createQuestionForm" id="createQuestionForm" method="post">
        <div class="cs-mod">
            <div class="mod-body">

                <div class="form-group">
                    <p class="col-md-12">
                        <label for="title">文章标题:</label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-8">
                            <!-- 问题标题 -->
                            <input type="text" placeholder="标题" name="title" id="title" value="${title}"
                                   class="form-control">
                            <!-- end 问题标题 -->
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <p class="col-md-12">
                        <label>内容 (选填):</label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-10">
                            <!-- 问题标题 -->
                            <textarea rows="30" cols="50" name="content" id="ckeditor01"
                                      class="form-control">${content}</textarea>
                            <!-- end 问题标题 -->
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="hidden" class="form-control" name="tags" value="${tags}"/>

                    <p class="col-md-12">
                        <label>标签 (选填):</label>
                    </p>

                    <div class="col-md-12 tag-bar" id="tagItems">
                        <#list tags as tag>
                            <span title='双击我删除' ondblclick='$(this).remove()' class="cs-question-tags"
                                  style="display:inline-block;"><i class="icon icon-edit"></i> + ${tag} + </span>
                        </#list>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-8"><input type="text" class="form-control" id="tagTxt"
                                                     placeholder="请输入标签(24个字以内，添加后双击可以移除，提交后不能修改！)"/></div>

                        <div class="col-md-2 text-left">
                            <button type="button"
                                    class="pull-right btn btn-large btn-default"
                                    id="addTagBtn">添加
                            </button>
                        </div>
                    </div>
                </div>

                <div class="form-group">

                    <p class="col-md-12">
                        <label>分类 (必选):</label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-10">
                            <#list categoryList as category>
                                <label>
                                    <input type="checkbox" name="categoryIds" value="${category.id}"/> ${category.name}
                                </label>
                            </#list>
                        </div>
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
<!-- 侧边栏 -->
<div class="col-sm-12 col-md-3 cs-side-bar hidden-xs">
    <!-- 问题发起指南 -->
    <div class="cs-mod publish-help">
        <div class="mod-head">
            <h3>文章发起指南</h3>
        </div>

        <div class="mod-body">
            <p><b>• 文章标题:</b> 请用准确的语言描述您发布的问题思想，发布后不能修改</p>

            <p><b>• 文章内容:</b> 详细补充您的问题内容, 并提供一些相关的素材以供参与者更多的了解您所要问题的主题思想</p>

            <p><b>• 标签:</b> 可以写多个标签，每个标签24个字以内，好的标签可以让别人更快的搜到此问题，发布后不能修改</p>
        </div>
    </div>
    <!-- end 问题发起指南 -->
</div>
<!-- end 侧边栏 -->
</@override>
<@extends name="layout/index.ftl"/>