/**
 * Created by ligson on 2015/7/14.
 */
var offset = 0;
function loadMyRechangeLog() {
    $.post("/user/loadMyRechangeLog", {offset: offset}, function (data) {
        if (data.success) {
            var html = "";
            for (var i = 0; i < data.payOrders.length; i++) {
                var rechangeItem = data.payOrders[i];
                html += "<tr>";
                html += "   <th scope=\"row\">" + rechangeItem.id + "</th>";
                html += "   <td>" + rechangeItem.money + "</td>";
                var date = Date.convertTxtFormat(rechangeItem.createDate);
                html += "   <td>" + date + "</td>";
                html += "   <td>成功</td>";
                html += "   <td>" + rechangeItem.comments + "</td>";
                html += "</tr>";
            }
            offset += 10;
            $("#rechangeLogList").append(html);
        }
    });
}
$(function () {
    loadMyRechangeLog();
    $("#bp_more").click(function () {
        loadMyRechangeLog();
    });
});
