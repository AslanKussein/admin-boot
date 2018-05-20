<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>e-shop - жүйеге кіру</title>
    <script src="${contextPath}/plugin/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="${contextPath}/plugin/jquery/jquery.i18n.properties.min.js" type="text/javascript"></script>
    <link href="${contextPath}/plugin/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap/v4/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/js/login.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
    <link href="${contextPath}/css/login.css?1" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/css/compacter.css?1" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="${contextPath}/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/util.js?version=${initParam.buildTimeStamp}" type="text/javascript"></script>
</head>
<body id="main">
<div id="loginMark">

    <div class="container">

        <div style="padding-top: 10px">

            <div class="mx-auto center-block">

                <div style="height: 30px"></div>

                <div class="card card-default">
                    <div class="card-header"><i class="fa fa-lock fa-1x"></i>&nbsp;&nbsp; <span
                            class="loginthesystem">e-shop - жүйеге кіру</span>
                    </div>
                    <div class="card-block" id="loginBody">
                        <div class="card card-container">
                            <div style="height: 10px"></div>
                            <form id="loginForm" class="form-signin" action="/login" method="POST">
                                <span id="reauth-email" class="reauth-email"></span>
                                <div class="input-group margin-bottom-sm" style="padding: 0 20px">
                                    <span class="input-group-addon wtShadow"><i
                                            class="fa fa-envelope-o fa-fw"></i></span>
                                    <input class="form-control username wtShadow" id="username" name="username"
                                           maxlength="20"
                                           placeholder="Қолданушының аты" readonly="readonly"
                                           onfocus="this.removeAttribute('readonly')"
                                           onkeypress="submitOnEnter(event);">
                                </div>

                                <div style="height: 10px"></div>

                                <div class="input-group" style="padding: 0 20px">
                                    <span class="input-group-addon wtShadow"><i class="fa fa-key fa-fw"></i></span>
                                    <input class="form-control password wtShadow" id="password" name="password"
                                           type="password"
                                           placeholder="Құпия сөз"
                                           readonly="readonly" onfocus="this.removeAttribute('readonly')"
                                           onkeypress="submitOnEnter(event);">
                                </div>
                                <div id="pnlIndicator" style="color: red; font-size:  16px"></div>
                                <div class="loadingContainer">
                                    <i class="fa fa-refresh fa-spin fa-3x fa-fw"></i>
                                    <span class="sr-only">Loading...</span>
                                </div>

                                <div class="errmsg"></div>

                                <div style="text-align: center">
                                    <a class="btn btn btn-success enter wtShadow" href="javascript:login()"
                                       role="button">Кіру</a>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </div>

                                <div id="browserVersion" style="padding: 10px;">
                                    <a id="browserVersionLink" style="color: navy; font-size: 20px"></a>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">

        function submitOnEnter(event) {
            if (event.keyCode == 13) {
                login()
            }
        }

        $('#username').val(localStorage.getItem("j_username"));

        $(function () {
            $('#password').on('keydown', function (e) {
                $(this).data('_lastKey', e.which);
            });
            $('#password').on('keypress', function (e) {

                var lastKey = +$(this).data('_lastKey');

                if (lastKey < 47 || lastKey > 90)
                    return true;

                var letter = String.fromCharCode(e.which);
                var upper = letter.toUpperCase();
                var lower = letter.toLowerCase();
                var isNumeric = lastKey >= 48 && lastKey <= 57;

                var caps = false;

                if (isNumeric)
                    caps = (lastKey == e.which && e.shiftKey) || (lastKey != e.which && !e.shiftKey);
                else if ((letter === upper && !e.shiftKey) || (letter === lower && e.shiftKey))
                    caps = !isNumeric;

                if (caps) {
                    $('#pnlIndicator').text("Назар аударыңыз: CapsLock басылған!");
                } else {
                    $('#pnlIndicator').text("")
                }
            });
        });

    </script>
    <script src="${contextPath}/plugin/bootstrap/v4/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>