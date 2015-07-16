/**
 * Created by ligson on 2015/7/14.
 */
var offset = 10;
function loadMyRechangeLog() {
    $.post("/user/myTradeLog", {offset: offset, format: "json"}, function (data) {
        if (data.success) {
            var html = "";
            for (var i = 0; i < data.tradeList.length; i++) {
                var trade = data.tradeList[i];
                html += "<th scope=\"row\">${trade.id}</th>";
                html += "   <td>" + trade.createDate + "</td>";
                html += "   <td>" + (trade.type == 1 ? "支出" : "收入") + "</td>";
                html += "   <td>" + (trade.objType == 1 ? "问题" : "文章") + "</td>";
                html += "   <td>" + trade.objId + "</td>";
                html += "   <td>" + trade.money + "</td>";
                html += "</th>";
            }
            offset += 10;
            $("#tradeLogList").append(html);
        }
    });
}
$(function () {
    $("#bp_more").click(function () {
        loadMyRechangeLog();
    });
});
