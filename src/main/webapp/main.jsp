<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>海文在线短信平台</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/sms.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/alertify.css"/>

    <script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/alertify.js" type="text/javascript"></script>

    <script>

        $(function () {
            // 显示第一页的5条数据
            showMsgs(1, 5);

        })

        //查询短消息的函数
        function showMsgs(pageNo, pageSize) {
            //发送ajax请求
            $.get("${pageContext.request.contextPath}/queryAllMsgs/"+pageNo+"/"+pageSize, function (data) {
                //此代码要使用jquery进行拼接
                var $ul = $(".messageList > ul");// 找到ul元素
                $ul.empty();// 清空ul元素中的内容
                // 循环创建li元素
                for (var i = 0; i < data.list.length; i++) {
                    var msg = data.list[i];//取出消息
                    console.log(JSON.stringify(msg));
                    var $li = "";
                    if (msg.state == 1) {
                        $li = $("<li class='unReaded'></li>");
                    } else {
                        $li = $("<li></li>");
                    }
                    var $em1 = $("<em>" + msg.msg_create_date + "</em>");
                    var $em2 = $("<em><a href='${pageContext.request.contextPath}/findAllUsers2/"+msg.sendid+"'>回信</a></em>");
                    var $em3 = $("<em><a href='javascript:delMsgById(" + msg.id + ")'>删除</em>");
                    var $p = $("<p></p>");
                    var $strong = $("<strong>" + msg.title + "</strong>");
                    var $a = "";
                    if (msg.msgcontent.length > 8) {
                        $a = $("<a href='javascript:showMsgById(" + msg.id + ")'>" + msg.msgcontent.substring(0, 8) + ".....</a>");
                    } else {
                        $a = $("<a href='javascript:showMsgById(" + msg.id + ")'>" + msg.msgcontent + "</a>");
                    }

                    $p.append($strong);
                    $p.append($a);
                    $li.append($em1).append($em2).append($em3).append($p);
                    $ul.append($li);
                }

                $a = $("#btns > a");
                $a.eq(0).attr("href", "javascript:showMsgs('" + 1 + "','" + pageSize + "')");
                $a.eq(1).attr("href", "javascript:showMsgs('" + data.prePage + "','" + pageSize + "')");
                $a.eq(2).attr("href", "javascript:showMsgs('" + data.nextPage + "','" + pageSize + "')");
                $a.eq(3).attr("href", "javascript:showMsgs('" + data.pages + "','" + pageSize + "')");

                $span = $("#btns > span");
                $span.eq(0).html(data.pageNum);
                $span.eq(1).html(data.pages);
            }, "json");
        }




        /**
         * 查看具体一条短消息
         * @param id
         */
        function showMsgById(id) {
            window.location = "${pageContext.request.contextPath}/queryMsgById/" + id;
        }

        /**
         * 删除短消息
         * @param id
         * @returns {boolean}
         */
        function delMsgById(id) {
            //提示是否确定删除
            alertify.confirm("是否確定刪除?",
                function () {
                    $.post("${pageContext.request.contextPath}/delMsg/"+id, {_method:"delete"}, function (data) {
                       if(data=="success"){
                           window.location="${pageContext.request.contextPath}/main.jsp";
                       }
                    });

                },
                function () {
                    alertify.error('取消');
                }).set('labels', {ok: '确认', cancel: '取消'}).set('reverseButtons', true);
            return false;//阻止超链接的默认行为
        }


    </script>






</head>
<body>
<div id="main">
    <div class="mainbox">
        <div class="title myMessage png"></div>
        <%@include file="menu.jsp" %>
        <!--错误信息  -->
        <div id="error"></div>
        <!--短消息列表  -->
        <div class="content messageList">
            <ul>

            </ul>
        </div>
        <!--分页栏 -->
       <div align="center" style="margin-top:10px" id="btns">
            <a href="#">首页</a>&nbsp;&nbsp;&nbsp;
            <a href="#">上一页</a>&nbsp;&nbsp;
            <a href="#">下一页</a>&nbsp;&nbsp;
            <span></span>&nbsp;/<span></span>&nbsp;&nbsp;
            <a href="#">最后一页</a>
        </div>

    </div>

</div>


</body>
</html>
