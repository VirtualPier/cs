/**
 * Created by Flying on 2015/4/19.
 */


/**
 * 我提问的问题
 */
/*var questionNum = $('#main_contents').children('.cs-item').length;*/

function loadContent() {
    var questionNum = $('#main_contents').children('.cs-item').length;
    $.ajax({
        type: 'POST',
        url: baseUrl + "user/loadMyCreateQuestion",
        data: {offset: questionNum},
        dataType: 'json',
        success: function (data) {
            loadAjaxData(data);
        }

    });
}
$(function () {
    loadContent();
    $("#bp_more_publish").click(function () {
        loadContent();
    });
});

/**
 * 加载问题列表
 * @param question
 */
function loadQuestion(question) {
    var htmlString = "<div class=\"cs-item\">";
    htmlString += "<div class=\"mod-head \" >";
    htmlString += "<p class= \"text-color-999 \">";
    htmlString += "<span>";
    htmlString += question.replyNum + "&nbsp个回复";
    htmlString += "</span>";
    htmlString += "•";
    htmlString += "<a href=\"  " + baseUrl + "question/view?id=" + question.id + "\" class=\" cs-topic-name\">";
    htmlString += question.title;
    htmlString += "</a>";
    var stateString = question.state == 1 ? "审核中" : "已发布";
    htmlString += "&nbsp;&nbsp;<span class=\"label label-info\">" + stateString + "</span>";
    htmlString += "<a href='" + baseUrl + "user/deleteQuestion?ids=" + question.id + "' class='pull-right' style='margin-left:10px;'>删除</a>";
    htmlString += "<a href='" + baseUrl + "question/edit?id=" + question.id + "' class='pull-right'>编辑</a>";
    htmlString += "</p>";
    htmlString += "<h4>";
    htmlString += "<span>";
    htmlString += question.description.substring(0, 300);
    htmlString += "</span>";
    htmlString += "</h4>";
    htmlString += "</div>";
    htmlString += "</div>";

    $("#main_contents").append(htmlString);
}

/**
 * 加载Ajax请求资源
 * @param data
 */
function loadAjaxData(data) {
    if (data.success) {
        $.each(data.questionList, function (index, question) {
            loadQuestion(question)
        });
    } else {
        $("#btn_msg").html("没有更多问题了")
    }
}



