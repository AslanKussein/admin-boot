$(document).ajaxStart(function () {
    $(".loadingContainer").show();
}).ajaxStop(function () {
    $(".loadingContainer").hide();
});

function logout() {
    $.post("auth", function () {
        window.location.href = "./logout";
    });
}

$(document).ready(function () {
    $("input[name='password']").on('keyup', function (e) {
        if (e.which == 13) {
            login();
        }
    });
    $("input[name='username']").on('keyup', function (e) {
        if (e.which == 13) {
            login();
        }
    });
});

/*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*/
function alertError(msg) {
    $('.errmsg').html(msg);
}

function login() {
    var form = $("#loginForm");
    var data = form.serialize();
    var username = $("[name='username']").val();
    var password = $("[name='password']").val();

    if (!$("[name='username']").val() || !$("[name='password']").val()) {
        alertError("Введите логин и пароль");
        return;
    }
    setLocalStorage("j_username", username);

    correctBrowserVersion(username, password, data, 1);
}

function correctBrowserVersion(username, password, data, type) {
    var brData = getOSVersionAndBrowserVersion();
    if (brData.os == "Windows XP" || brData.os == "Windows Vista") {
        if (brData.browserVersion < 49) {
            checkBrowserVersionError(brData.browser, 49, brData.os);
            return false;
        }
    } else if (brData.browser == "Chrome") {
        if (brData.version < 56) {//56
            checkBrowserVersionError(brData.browser, 56, brData.os);
            return false;
        }
    } else if (brData.browser == "msie") {
        if (brData.version < 11) {
            checkBrowserVersionError(brData.browser, 11, brData.os);
            return false;
        }
    } else if (brData.browser == "Firefox") {
        if (brData.version < 49) {
            checkBrowserVersionError(brData.browser, 49, brData.os);
            return false;
        }
    }

    if (type == 1) {
        loginSubmit(username, password, data);
    }
    else if (type == 2) {
        loginSubmitList(username, password, data)
    }
}

/**
 * @desc проверка версии браузера;
 * @desc beljerin;
 */
function getOSVersionAndBrowserVersion() {
    var jscd = {};
    {
        var unknown = '-';

        var nAgt = navigator.userAgent;
        var browser = navigator.appName;
        var version = '' + parseFloat(navigator.appVersion);

        // Chrome
        if ((verOffset = nAgt.indexOf('Chrome')) != -1) {
            browser = 'Chrome';
            version = nAgt.substring(verOffset + 7);
        }
        // Firefox
        else if ((verOffset = nAgt.indexOf('Firefox')) != -1) {
            browser = 'Firefox';
            version = nAgt.substring(verOffset + 8);
        }
        // MSIE 11+
        else if (nAgt.indexOf('Trident/') != -1) {
            browser = 'msie';
            version = nAgt.substring(nAgt.indexOf('rv:') + 3);
        }

        // trim the version string
        if ((ix = version.indexOf(';')) != -1) version = version.substring(0, ix);
        if ((ix = version.indexOf(' ')) != -1) version = version.substring(0, ix);
        if ((ix = version.indexOf(')')) != -1) version = version.substring(0, ix);

        var os = unknown;
        var clientStrings = [
            {s: 'Windows 10', r: /(Windows 10.0|Windows NT 10.0)/},
            {s: 'Windows 8.1', r: /(Windows 8.1|Windows NT 6.3)/},
            {s: 'Windows 8', r: /(Windows 8|Windows NT 6.2)/},
            {s: 'Windows 7', r: /(Windows 7|Windows NT 6.1)/},
            {s: 'Windows Vista', r: /Windows NT 6.0/},
            {s: 'Windows XP', r: /(Windows NT 5.1|Windows XP)/},
            {s: 'Linux', r: /(Linux|X11)/},
            {s: 'Mac OS X', r: /Mac OS X/},
            {s: 'Mac OS', r: /(MacPPC|MacIntel|Mac_PowerPC|Macintosh)/}
        ];
        for (var id in clientStrings) {
            var cs = clientStrings[id];
            if (cs.r.test(nAgt)) {
                os = cs.s;
                break;
            }
        }
    }

    var versionParts = [];
    var versionPartsMatch = version.match(/(\d+)/g) || [];
    for (var i = 0; i < versionPartsMatch.length; i++) {
        versionParts.push(versionPartsMatch[i]);
    }
    if (versionParts.length > 0) {
        version = versionParts[0];
    }

    jscd['os'] = os;
    jscd['browser'] = browser;
    jscd['version'] = version;

    return jscd;
}

function checkBrowserVersionError(browserName, browserVersion, os) {
    downloadCorrectBrowserVersion(browserName, os, browserVersion);
    alertError(getResourceName('app.broser.err.txt.ref') + ' ' + browserName + " до версии " + browserVersion + " или выше!");
}

function downloadCorrectBrowserVersion(browserName, os) {
    if (os == "Windows XP") {
        downloadBrowserOld();
    } else {
        downloadBrowserNew(browserName);
    }
}

function downloadBrowserOld() {
    var icon = '<i style="font-size: 19px;" class="fa fa-chrome"></i>';
    var url = "/app/softs/49.exe";

    $('#browserVersionLink').attr("href", url);
    $('#browserVersionLink').html(icon + ' Скачать ' + browserName)
}

function downloadBrowserNew(browserName) {
    var host = localStorage.getItem("host");
    var url = "";
    var icon = "";
    switch (browserName) {
        case "Chrome":
            icon = '<i style="font-size: 19px;" class="fa fa-chrome"></i>';
            url = host + "google.chrome.x86.exe";
            break;
        case "msie":
            icon = '<i style="font-size: 19px;" class="fa fa-internet-explorer"></i>';
            url = host + "IE11.exe";
            break;
        case "Firefox":
            icon = '<i style="font-size: 19px;" class="fa fa-firefox"></i>';
            url = host + "FirefoxSetup.exe";
            break;
    }
    $('#browserVersionLink').attr({href: url});
    $('#browserVersionLink').html(icon + ' Скачать ' + browserName)
}

function loginSubmit(username, password, data) {
    if (username.trim().length == 0 || password.trim().length == 0) {
        alertError("Введите логин и пароль");
        return;
    }

    correctBrowserVersion(username, password, data, 2);
}

function loginSubmitList(username, password, data) {
    alertError('');
    $(".loadingContainer").show();
    $("#enter").prop("disabled", true);
    $.ajax({
        url: $("#loginForm").action,
        type: 'POST',
        data: data,
        statusCode: {
            401: function (message) {
                alertError(message.responseText);
            },
            200: function () {
                window.location.href = "/pages/personapp.jsp";
            }
        }
    });
}

function changeLangSubmit(e) {
    var language = e;
    if (e == undefined)
        language = 'Ru';
    $('#langBlock').html(' ' + language + ' ');
    localStorage.setItem("lang", language);

    $.ajax({
        url: "/dop/changeLang",
        type: 'POST',
        data: {lang: language},
        success: function (gson) {
            if (gson) {
                window.location.href = "/pages/personapp.jsp";
            }
        }
    });
}

function changeLang(value) {
    localStorage.setItem('lang', value);
    changeLangSubmit(value);
    window.location.href = "";
}

setTimeout(function () {
    $("#password").focus();
    $("#username").focus();
}, 500);


function setIsTestDb() {
    $.ajax({
        url: "/dop/isTestDb",
        type: 'POST',
        success: function (gson) {
            if (gson) {
                if (gson.result) {
                    $("#main").removeClass("body_main_db");
                    $("#main").addClass("body_test_db");
                } else {
                    $("#main").addClass("body_main_db");
                }
            } else {
                alertError('Ошибка', nvl(gson, 'Ошибка сервера'));
            }
        }
    });
}