<div class="admin-con" data-options="region:'north'" style="height:100px;overflow:hidden;border:0">
    <div class="container-fluid">
        <div class="row">
            <div class="ism_logo logo-width col-md-2">
                <h1 class="logo-img">后台管理系统</h1>
            </div>

            <div class="ism_nav col-md-8">
                <div class="ism_nav_item">
                <#if springMacroRequestContext.requestUri?contains("/questionMgr")>
                    <a class="btn btn-default btn-block active" href="/questionMgr/index">问题管理</a>
                <#else >
                    <a class="btn btn-default btn-block " href="/questionMgr/index">问题管理</a>
                </#if>
                </div>

                <div class="ism_nav_item">
                <#if springMacroRequestContext.requestUri?contains("/userMgr")>
                    <a class="btn btn-default btn-block active" href="/userMgr/index">用户管理</a>
                <#else >
                    <a class="btn btn-default btn-block" href="/userMgr/index">用户管理</a>
                </#if>
                </div>


                <div class="ism_nav_item">
                <#if springMacroRequestContext.requestUri?contains("/articleMgr")>
                    <a class="btn btn-default btn-block active" href="/articleMgr/index">文章管理</a>
                <#else >
                    <a class="btn btn-default btn-block" href="/articleMgr/index">文章管理</a>
                </#if>
                </div>

                <div class="ism_nav_item">
                <#if springMacroRequestContext.requestUri?contains("/payMgr")>
                    <a class="btn btn-default btn-block active" href="/payMgr/index">支付管理</a>
                <#else >
                    <a class="btn btn-default btn-block" href="/payMgr/index/">支付管理</a>
                </#if>
                </div>

                <div class="ism_nav_item">
                <#if springMacroRequestContext.requestUri?contains("/systemMgr")>
                    <a class="btn btn-default btn-block active" href="/systemMgr/userList">系统管理</a>
                <#else >
                    <a class="btn btn-default btn-block" href="/systemMgr/userList/">系统管理</a>
                </#if>
                </div>
            </div>


            <div class="ism_user col-md-2">
                <div class="ism_user">
                    <img class="img-circle" src="/images/use-img.jpg"/>

                    <p>
                        <a href="">${session.user.nickName}</a>
                        <span>2014-05-06</span>
                    </p>

                </div>
            </div>
        </div>
    </div>
</div>