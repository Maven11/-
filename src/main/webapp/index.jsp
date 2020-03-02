<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="true" %>
<html>
<head>
    <title>短消息平台</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/sms.css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script>
        var refresh, imgCode, xmlhttp;
        window.onload = function (ev) {
            refresh = document.getElementById("refresh");
            imgCode = document.getElementById("imgCode");
            refresh.onclick = function (ev1) {
                //修改对象中的src属性值
                imgCode.src = "${pageContext.request.contextPath}/validateCode?timestamp=" + new Date().getTime();
                return false;//阻止元素的默认行为
            }

        }

        $(function () {
            $(".btn-login").click(function () {

                $.get("${pageContext.request.contextPath}/login", {
                    "name": $("#name").val(),
                    "pwd": $("#pwd").val(),
                    "code": $("#code").val()
                }, function (data) {
                    if (data.info == "success") {
                        window.location = "${pageContext.request.contextPath}/main.jsp";
                        //msg.do?param=queryAllMsgs&pageSize=5&pageNo=1
                    } else {
                        $("#error").html(data.error);
                        imgCode.src = "${pageContext.request.contextPath}/validateCode";
                    }
                },"json")
            })
            
        })


    </script>
</head>
<body>
<div id="loginTitle" class="png"></div>
<div id="loginForm" class="userForm png">
    <%--<form action="${pageContext.request.contextPath}/lr.do?param=login" method="post">--%>
    <dl>
        <div id="error" style="color:red"></div>
        <dt>用户名：</dt>
        <dd>
            <input type="text" name="name" id="name"/>
        </dd>
        <dt>密 码：</dt>
        <dd>
            <input type="password" name="pwd" id="pwd"/>
        </dd>
        <dt>验证码：</dt>
        <dd><input type="text" name="code" id="code"/></dd>
        <dd>
            <img src="${pageContext.request.contextPath}/validateCode" id="imgCode"><a href="#" id="refresh">换一张</a>
        </dd>

    </dl>
    <div class="buttons">
        <input class="btn-login png" type="button" name="submit" value=" "/>
        <a href="register.jsp"><input class="btn-reg png" type="button" name="register" value=" "/></a>
    </div>
    <%--</form>--%>
</div>


</body>
</html>








