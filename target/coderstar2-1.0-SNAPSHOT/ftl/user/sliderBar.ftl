<div class="col-sm-12 col-md-3 cs-side-bar hidden-xs hidden-sm">
    <div class="cs-mod">
        <div class="mod-body">
            <img class="cs-border-radius-5" onerror="javascript:this.src='/images/pic_user.gif'" src="${user.photo}"
                 alt="" id="avatar_src" width="64" height="64"/>

            <p>${user.nickName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="/user/profile">修改</a></p>

            <p>码币:${user.balance}MB&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/user/rechange">充值</a>
                &nbsp;&nbsp;
                <a href="/user/withdraw">提现</a>
            </p>
        </div>
    </div>

    <div class="cs-mod side-nav">
        <div class="mod-body">
            <ul>
                <li>
                    <a ${springMacroRequestContext.requestUri?contains("/user/myPublish")?string("class='active'","")}
                            href="/user/myPublish" rel="focus_topic__focus"><i
                            class="glyphicon glyphicon-edit"></i>提问的问题</a>
                </li>
                <li>
                    <a href="/user/myAttention"
                       rel="all__focus" ${(springMacroRequestContext.requestUri==("/user/myAttention"))?string("class='active'","")}><i
                            class="glyphicon glyphicon-star"></i>关注的问题</a>
                </li>
                <li>
                    <a ${springMacroRequestContext.requestUri?contains("/user/myArticle")?string("class='active'","")}
                            href="/user/myArticle" rel="invite_list__invite"><i
                            class="glyphicon glyphicon-list-alt"></i>发布的文章</a>
                </li>
                <li>
                    <a href="/user/myAttentionArticle"
                       rel="invite_list__invite" ${springMacroRequestContext.requestUri?contains("/user/myAttentionArticle")?string("class='active'","")}><i
                            class="glyphicon glyphicon-heart"></i>关注的文章</a>
                </li>
                <li>
                    <a href="/user/myRechangeLog" ${springMacroRequestContext.requestUri?contains("/user/myRechangeLog")?string("class='active'","")}><i
                            class="glyphicon glyphicon-usd"></i>充值记录</a>
                </li>
                <li>
                    <a href="/user/myTradeLog" ${springMacroRequestContext.requestUri?contains("/user/myTradeLog")?string("class='active'","")}><i
                            class="glyphicon glyphicon-link"></i>交易记录</a>
                </li>
                <li>
                    <a href="/user/myWithdrawLog" ${springMacroRequestContext.requestUri?contains("/user/myWithdrawLog")?string("class='active'","")}><i
                            class="glyphicon glyphicon-print"></i>提现记录</a>
                </li>
            </ul>
        </div>
    </div>

</div>