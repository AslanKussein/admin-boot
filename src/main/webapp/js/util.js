function notifyMessage(title, text, cls) {
    if (isNullOrEmpty(cls)) cls = notifyType.info;
    $.notify({
            title: title,
            message: text
        },
        {
            template: '<div class="alert ' + cls + '" role="alert"> ' +
            ' <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
            + '<strong>{1}</strong> {2} &nbsp;&nbsp;</div>'
        });
}

function isNullOrEmpty(e) {
    if (e == null || e == "" || e == undefined)
        return true;
    else
        return false;
}

function get_ajax(url, type, data, callback, errcallback) {
    $.ajax({
        url: url,
        type: type,
        data: data,
        success: function (gson) {
            callback(gson);
        },
        statusCode: {
            401: function () {
                createLoginWindow();
            }
        },
        error: function () {
            if (errcallback) {
                errcallback(url);
            } else {
                notifyMessage(getResourceName('message.label.danger'), getResourceName('service.has.error') + ' ' + url, notifyType.danger);
            }
        }
    });
}

function isCen() {
    var cen = [38235, 38236, 38237];
    for (var i in cen) {
        if (isUserRoleId(cen[i])) {
            return true;
        }
    }
    return false;
}

function isUserRoleId(roleId) {
    for (var i in eappRoleList) {
        if (eappRoleList[i].eappRoleId.id == roleId) {
            return true;
        }
    }
    return false;
}

/*set and get localStorage to page*/
function setLocalStorage(key, value) {
    localStorage.setItem(key, value);
}

function getLocalStorage(key) {
    return localStorage.getItem(key);
}

function getResourceName(prop) {
    return jQuery.i18n.prop(prop);
}

var notifyType = {
    "info": "alert-info",
    "success": "alert-success",
    "warning": "alert-warning",
    "danger": "alert-danger"
};

function nvl(val1, val2) {
    if (isNullOrEmpty(val1))
        return val2;
    else
        return val1;
}