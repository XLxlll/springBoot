<%--
  Created by IntelliJ IDEA.
  User: XL
  Date: 2016/7/27
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var checkCode = $.trim($("checkCode").val());
                if(checkCode==""){
                    alert("验证码不能为空！");
                    $("#checkCode").focus();
                    return false;
                }else{
                    $(this).submit()}
            });
        });
    </script>
    <script type="text/javascript">
        $("#change").click(function () {
            changeCode();
        });
        function changeCode() {
            var checkImage = $("#checkImage");
//        当前验证码图片隐藏
            checkImage.hide();
//        换一个新的验证码图片！Math.random().fadeIn()保证每次的请求路径不同
            checkImage.prop("src","<%=basePath%>/getKaptchaImage?id="+Math.random().fadeIn());
//        取消事件处理
            event.cancelBubble = true;
        }

   </script>
</head>
<body>
<form action="/check"" method="post">
    验证码：<input style="width: 70px" type="text" name="checkCode"
               id="checkCode" maxlength="4" autocomplete="off"/>
    <img src="<%=basePath%>/getKaptchaImage" id="checkImage" alt="验证码">
    <%--使a标签的类型为button--%>
    <a href="" type="button" class="change" onclick="changeCode()">看不清换一张？</a>
    <input type="submit" value="提交">
</form>
</body>
</html>
