<#-- 自定义的分页指令。
属性说明：
   pageNo      当前页号(int类型)
   pageSize    每页要显示的记录数(int类型)
   toURL       点击分页标签时要跳转到的目标URL(string类型)
   recordCount 总记录数(int类型)
 使用方式：
  <#if recordCount??>
    <#import "../ftl/fts_pagination.ftl" as p>
    <@p.pagination pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="xxxxx"/>
  </#if>
 -->

<!-- 定义标签属性 -->
<#macro pagination offset max total url>

    <#if url?string?index_of("?")?int gt -1 >
        <#assign baseUrl = url+"&">
    <#else>
        <#assign baseUrl = url+"?">
    </#if>

    <#assign page1 = total*1.00/max>
    <#assign page2 = (total/max)?int>
    <#if page1 gt page2>
        <#assign pageCount = page2+1>
    <#else>
        <#assign pageCount = page2>
    </#if>
    <#assign currentPage = (offset/max+1)>

    <#if pageCount gt 0 >
    <!--上一页-->
        <#if currentPage gt 1>
        <a href="${baseUrl}max=${max}&offset=${(currentPage-2)*max}" class="prevLink">上页</a>
        </#if>
    <!--前五个-->
        <#if (currentPage-5) gt 0>
            <#assign startOffset = (currentPage-5)>
        <#else>
            <#assign startOffset = 1 >
        </#if>
        <#list startOffset..currentPage  as pgNo>
            <#if currentPage==pgNo>
            <a href="${baseUrl}max=${max}&offset=${(pgNo-1)*max}" class="currentStep">${pgNo}</a>
            <#else>
            <a href="${baseUrl}max=${max}&offset=${(pgNo-1)*max}" class="step">${pgNo}</a>
            </#if>
        </#list>
    <!--后五个-->
        <#if (currentPage+5) gt pageCount>
            <#assign endOffset = pageCount>
        <#else>
            <#assign endOffset = (currentPage+5) >
        </#if>

        <#if (currentPage+1) lt (pageCount+1)>
            <#list (currentPage+1)..endOffset  as pgNo>
                <#if currentPage==pgNo>
                <a href="${baseUrl}max=${max}&offset=${(pgNo-1)*max}" class="currentStep">${pgNo}</a>
                <#else>
                <a href="${baseUrl}max=${max}&offset=${(pgNo-1)*max}" class="step">${pgNo}</a>
                </#if>
            </#list>
        </#if>


        <#if pageCount gt 1>
        <!--下一页-->
        <a href="${baseUrl}max=${max}&offset=${currentPage*max}" class="nextLink">下页</a>
        </#if>
    </#if>





</#macro>