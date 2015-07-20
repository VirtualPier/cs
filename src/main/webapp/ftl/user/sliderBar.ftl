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
                    <a href="/user/myAttention" rel="all__focus" class="active"><i class="glyphicon glyphicon-star"></i>我关注的问题</a>
                </li>
                <li>
                    <a href="/user/myPublish" rel="focus_topic__focus" class="active"><i
                            class="glyphicon glyphicon-check"></i>我提问的问题</a>
                </li>
                <li>
                    <a href="/user/myArticle" rel="invite_list__invite" class="active"><i
                            class="glyphicon glyphicon-edit"></i>我发布的文章</a>
                </li>
                <li>
                    <a href="/user/myAttentionArticle" rel="invite_list__invite" class="active"><i
                            class="glyphicon glyphicon-edit"></i>我关注的文章</a>
                </li>
                <li>
                    <a href="/user/myRechangeLog"><i class="glyphicon glyphicon-edit"></i>充值记录</a>
                </li>
                <li>
                    <a href="/user/myTradeLog"><i class="glyphicon glyphicon-edit"></i>交易记录</a>
                </li>
                <li>
                    <a href="/user/myWithdrawLog"><i class="glyphicon glyphicon-edit"></i>提现记录</a>
                </li>
            </ul>
        </div>
    </div>

</div>