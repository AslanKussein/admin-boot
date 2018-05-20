<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>e-shop</title>
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
    <link href="${contextPath}/css/projectlist.css?version=${initParam.buildTimeStamp}" rel="stylesheet"
          type="text/css"/>
    <script src="${contextPath}/js/shop.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${contextPath}/css/loader.css"/>
    <link href="${contextPath}/css/compacter.css?version=${initParam.buildTimeStamp}" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="mywrapper">
    <div class="header">
        <%@include file='/incloudes/main_navbar.jsp'%>
    </div>
    <div class="pagetitle" id="breadcrumbs">
        <ul class="breadcrumbs">
        </ul>
    </div>

    <script>
        $.ajax({
            url: '/app/getDcategory',
            type: 'GET',
            data: {},
            success: function (gson) {
                for (let data of gson) {
                    $("#breadcrumbs ul").append('<li><a class="current" id="' + data.id + '" onclick="getTovarByDcategory(this)" >' + data.name + '</a></li>');
                }
            }
        });
    </script>
    <div class="container">
        <div class="row">
            <div id="custom-search-input">
                <div class="input-group col-md-12">
                    <input type="text" id="searchS" class=" search-query form-control" placeholder="Іздеу"/>
                    <span class="input-group-btn">
                                    <button class="btn btn-danger" type="button" onchange="getTovarByDcategoryByName()" onclick="getTovarByDcategoryByName()">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                </div>
            </div>

        </div>
    </div>

    <div class="middle">
        <div class="mycontainer">
            <div class="mycontent">
                <div class="mainlayout">
                    <div id="mainlayot">
                        <div id="template_container" style="display: none; width: 100%; height: 100%">
                            <div class="mainDiv" style="position: relative;">
                                <div style="float: left;height: 230px;margin: 10px; border: 1px #c4c4c4 solid;padding: 55px">
                                    <img width="400" height="400" src="#imgBdy#" alt="#imageName#">
                                </div>
                                <div class="mainIterator">#pos#.</div>

                                <div>
                                    <div style="margin-top: 8px">
                                        <a style="cursor: pointer;" class='delete_row'>
                                            <span style="display: none">#id#</span>
                                            <b class="project-name">#name#</b>
                                            <span style="float: right;color: navy;font-weight: bold;font-size: 16px">#cost# тенге</span>
                                        </a>
                                    </div>
                                    <div class="desc">
                                        <span>#description#</span>
                                    </div>
                                    <div class="b-post__only" style="margin: 10px">
                                        <span>қоймада <b>#count#</b> қалды</span>
                                        <%--<input type="submit" class="btn btn-info" value="Корзенкеге">--%>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/plugin/bootstrap-3.3.4-dist/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${contextPath}/plugin/bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

</body>
</html>