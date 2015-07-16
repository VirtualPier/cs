/**
 * Created by ligson on 2015/7/14.
 */
var offset = 10;
function loadMyRechangeLog() {
    $.post("/user/myRechangeLog", {offset: offset, format: "json"}, function (data) {
        if (data.success) {
            var html = "";
            for (var i = 0; i < data.rechangeList.length; i++) {
                var rechangeItem = data.rechangeList[i];
                html += "<tr>";
                html += "   <th scope=\"row\">" + rechangeItem.id + "</th>";
                html += "   <td>" + rechangeItem.money + "</td>";
                var date = Date.convertTxtFormat(rechangeItem.createDate);
                html += "   <td>" + date + "</td>";
                html += "   <td>成功</td>";
                html += "</tr>";
            }
            offset += 10;
            $("#rechangeLogList").append(html);
        }
    });
}
$(function () {
    $("#bp_more").click(function () {
        loadMyRechangeLog();
    });
});
