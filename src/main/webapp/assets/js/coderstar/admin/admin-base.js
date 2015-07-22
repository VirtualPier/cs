/**
 * Created by ligson on 2014/12/5 0005.
 */
$(function () {
    var westPanel = $("#westPanel");
    var about = westPanel.attr("about");
    var liMenu = westPanel.find("li");
    liMenu.click(function () {
        var index = liMenu.index($(this));
        $.cookie("admin-menu-index-" + about, index);
    });

    var showIndex = $.cookie("admin-menu-index-" + about);
    var accordion = $(".easyui-accordion");
    if (showIndex) {
        var navTree = $(liMenu[showIndex]).parents(".easyui-tree");
        var panel = $(liMenu[showIndex]).parents(".panel")[0];
        var panelArray = accordion.find(".panel");
        var panelIndex = panelArray.index(panel);
        accordion.accordion("select", panelIndex);
        var navTitle = $(panel).find(".panel-title").html() + "&gt;&gt;" + $(liMenu[showIndex]).find("a").html();
        $("#mainDiv").panel("setTitle", navTitle)
    }

    $(".ism_nav_item").click(function () {
        for (var obj in $.cookie()) {
            if (typeof(obj) == "string") {
                var proName = obj + "";
                if (proName.indexOf("admin-menu-index-") != -1) {
                    $.cookie(proName, null);
                }
            }
        }

    });


    //自定义验证器
    $.extend($.fn.validatebox.defaults.rules, {
        number: {
            validator: function (value, param) {
                return /^[0-9]*$/.test(value);
            },
            message: '请输入数字类型'
        }
    });
});