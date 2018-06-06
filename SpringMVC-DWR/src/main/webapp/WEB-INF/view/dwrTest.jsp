<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>DWR Test</title>
    <script type="application/javascript" src="<%=basePath%>/js/dwr/util.js" ></script>
    <script type="application/javascript" src="<%=basePath%>/js/dwr/engine.js" ></script>
    <script src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
    <%-- 以下是dwr自动生成的js --%>
    <script type="application/javascript" src="<%=basePath%>/js/dwr/interface/DwrPushUtils.js" ></script>
</head>

    <script type="application/javascript">

        $(document).ready(
            function () {
                /* 激活反转 */
                dwr.engine.setActiveReverseAjax(true);
                $("#sign").click(function () {
                    DwrPushUtils.send($("#msg").val());
                });
            }
        );

        /* 回调方法 */
        function callback(msg) {
            $("ul").html($("#ul").html() + "</br>" + msg);
        }
    </script>

<body>

    <ul id="ul" style="color: red; font-size: 60px;"></ul>

    <input type="text" name="msg" id="msg" size="30" style="height: 60px; font-size: 35px;"/>
    <input type="button" id="sign" value="发布信息">

</body>
</html>