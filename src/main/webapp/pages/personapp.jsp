<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Админка</title>
    <link rel="icon" type="image/ico" sizes="16x16" href="${contextPath}/images/favicon.ico">
    <script src="${contextPath}/plugin/jquery/jquery.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/webix/v5/codebase/webix.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugin/webix/v5/codebase/skins/material.css">
    <script src="${contextPath}/js/locale_kz.js" type="text/javascript"></script>
    <script src="${contextPath}/js/newutils.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/style.css?version=${initParam.buildTimeStamp}" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/declaration.css?version=${initParam.buildTimeStamp}" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/personapp.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${contextPath}/css/loader.css"/>
    <link href="${contextPath}/css/compacter.css?version=${initParam.buildTimeStamp}" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="middlecontent">
    <div id="mainlayot">
        <div id="appsTablePaging"></div>
    </div>
</div>
<div style="position: fixed;bottom: 0; width: 100%;z-index: 9999999">
    <div id="mainprogress"></div>
</div>
<div id="loader-wrapper" style="display:none">
    <div id="loader"></div>
    <div id="textLoader"></div>
</div>

<script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

</body>
</html>