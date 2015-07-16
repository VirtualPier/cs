/**
 * Created by Administrator on 2015/4/17 0017.
 */
$(function () {
    $("#search_form").submit(function () {
        var ipt = $(this).find("input[name='title']");
        if ((ipt.val() + "").trim().length == 0) {
            return false;
        }
    });
});

function checkLoginUser(){

}