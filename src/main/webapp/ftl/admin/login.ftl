<!Doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台登陆</title>
    <link rel="stylesheet" type="text/css" href="${basePath}css/admin/admin_login.css">
</head>

<body>
<div class="login_container">
    <form action="/admin/login">
        <table>
            <tr>
                <td colspan="2" style="color:red;font-size:10px;">${msg}</td>
            </tr>
            <tr>
                <td>账号</td>
                <td><input type="text" name="name" value="${name}"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password" value="${password}"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="登陆" class="login_btn"/></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>