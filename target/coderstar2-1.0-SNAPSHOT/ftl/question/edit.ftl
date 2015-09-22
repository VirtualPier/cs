<@override name="title">编辑问题</@override>
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
<script type="text/javascript" src="${basePath}js/coderstar/front/createQuestion.js"></script>
</@override>
<@override name="body">
<div class="col-sm-12 col-md-9 cs-main-content">

    <form method="post" action="/question/saveQuestion" class="form-horizontal" name="createQuestionForm"
          id="createQuestionForm">
        <input type="hidden" name="id" value="${question.id}"/>

        <div class="cs-mod">
            <div class="mod-body">

                <div class="form-group">
                    <p class="col-md-12">
                        <label for="title">问题标题:</label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-8">
                            <!-- 问题标题 -->
                            <input type="text" placeholder="问题标题(提交后不能修改)" name="title" id="title"
                                   value="${question.title}"
                                   class="form-control">
                            <!-- end 问题标题 -->
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <p class="col-md-12">
                        <label for="title">悬赏码币(MB):
                            <small>(悬赏后可以吸引更多的人帮你解决问题)</small>
                        </label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-2">
                            <!-- 悬赏金额 -->
                            <input type="text" name="money" id="money" value="${question.money}" class="form-control"/>
                            <!-- end 悬赏金额 -->
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <p class="col-md-12">
                        <label>问题补充 (选填):</label>
                    </p>

                    <div class="col-md-12">
                        <div class="col-md-10">
                            <!-- 问题标题 -->
                            <textarea rows="30" cols="50" name="description" id="ckeditor01"
                                      class="form-control">${question.description}</textarea>
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
                        <#list sysTags as tag>
                            <span title='双击我删除' ondblclick='$(this).remove()' class="cs-question-tags"
                                  style="display:inline-block;"><i class="icon icon-edit"></i>${tag.name}</span>
                        </#list>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-8"><input type="text" class="form-control" id="tagTxt"
                                                     placeholder="请输入标签,空格也可以快速增加(24个字以内，添加后双击可以移除！)"/></div>

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
                                    <#if questionCategoryList?seq_contains(category)>
                                        <input type="checkbox" name="categoryIds"
                                               value="${category.id}" checked/> ${category.name}
                                    <#else>
                                        <input type="checkbox" name="categoryIds"
                                               value="${category.id}"/> ${category.name}
                                    </#if>
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
            <h3>问题发起指南</h3>
        </div>

        <div class="mod-body">
            <p><b>• 问题标题:</b> 请用准确的语言描述您发布的问题思想，发布后不能修改</p>

            <p><b>• 问题补充:</b> 详细补充您的问题内容, 并提供一些相关的素材以供参与者更多的了解您所要问题的主题思想</p>

            <p><b>• 标签:</b> 可以写多个标签，每个标签24个字以内，好的标签可以让别人更快的搜到此问题，发布后不能修改</p>
        </div>
    </div>
    <!-- end 问题发起指南 -->
</div>
<!-- end 侧边栏 -->
</@override>
<@extends name="layout/index.ftl"/>