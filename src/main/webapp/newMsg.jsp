<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>海文 在线短信平台</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/sms.css"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.css"/>
    <script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/alertify.js" type="text/javascript"></script>

    <script>
        $(function () {

            $("#btn_send").click(function () {
                $.post("${pageContext.request.contextPath}/send", {
                    "toUser": $("#toUser").val(),
                    "title": $("#title").val(),
                    "content": $("#content").val()
                },function (data) {
                    if(data=="success") {
                        alertify.alert('提示信息', '发送成功!', function () {
                            $("#title").val("");
                            $("#content").val("");
                            alertify.success('Ok');
                        });
                    }else{
                        alertify.alert('提示信息', '发送失败!', function () {
                            alertify.success('failure');
                        });
                    }
                });
            })
        })
    </script>
</head>
<body>
<%--<form action="${pageContext.request.contextPath}/msg.do?param=sendMsg" method="post" id="msgForm">--%>
<div id="main">
    <div class="mainbox">
        <%@include file="menu.jsp" %>
        <div class="content">
            <div class="message">
                <div class="tmenu">
                    <ul class="clearfix">
                        <li>发送给： <select name="toUser" id="toUser">
                            <c:forEach items="${requestScope.uList}" var="user">
                                <c:choose>
                                    <c:when test="${requestScope.sendid == user.id}">
                                        <option selected="selected" value="${user.id}">${user.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${user.id}">${user.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        </li>
                        <li>标题：<input type="text" name="title" id="title"/></li>
                    </ul>
                </div>
                <div class="view">
                    <textarea name="content" id="content"></textarea>
                    <div class="send">
                        <input type="button" name="submit" value=" " id="btn_send"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--</form>--%>
</body>
</html>
