<!-- 定义标签属性 -->
<#macro dateFormat dateText>
${dateText?substring(0,4)}年${dateText?substring(4,6)}月${dateText?substring(6,8)}日&nbsp;&nbsp;${dateText?substring(8,10)}:${dateText?substring(10,12)}:${dateText?substring(12,14)}
</#macro>