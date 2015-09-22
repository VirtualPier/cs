<div class="tabbable">
    <ul class="nav nav-tabs cs-nav-tabs active">

        <li  ${(springMacroRequestContext.requestUri=="/user/security")?string("class=\"active\"","")}>
            <a href="/user/security">安全设置</a>
        </li>
        <li  ${(springMacroRequestContext.requestUri=="/user/profile")?string("class=\"active\"","")}>
            <a href="/user/profile">基本资料</a>
        </li>
        <h2><i class="icon icon-setting"></i> 用户设置</h2>
    </ul>
</div>