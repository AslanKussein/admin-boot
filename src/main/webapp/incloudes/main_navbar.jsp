<%@ page import="kz.crtr.emaket.entity.gson.GsonUsers" %>
<%@ page import="kz.crtr.emaket.utils.Constant" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${contextPath}/js/navbar.js" type="text/javascript"></script>

    <style>
        .webix_menu-x .webix_list_item {
            border-right: none;
        }

        .webix_list_item {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div id="mainmenu"></div>
<script>
    var fullname;
    <%

GsonUsers user = (GsonUsers) request.getSession().getAttribute(Constant.USER);
if(user != null) {
%>
    fullname = "<%=user.getUserDetail().getFullName() %>";
    <%
   }

   %>

    webix.ui({
        container: "mainmenu",
        view: "toolbar",
        elements: [
            {},
            {
                view: "menu",
                width: 250,
                borderless: true,
                openAction: "click",
                subMenuPos: "left",
                data: [
                    {value: fullname, icon: "user", $css: "rightSide", submenu: [
                            {id: "logout", value: "Шығу"}
                        ]}
                ],
                type: {
                    subsign: true
                }
            }
        ],
        elementsConfig: {
            labelAlign: "right"
        }
    });

</script>
</body>
</html>