$.ajaxSetup({
    dataFilter: function (data, type) {
        var match = data.match('<div id="loginMark">');
        if (match != null) {
            window.location.href = "/login.jsp";
        } else {
            return data;
        }
    }
});

webix.Date.startOnMonday = true;// что бы первый день недели был понедельник

function inflationWinTableResize() {
    if ($$("inflationWinTable")) {
        var cont_h = $(window).height();
        if (cont_h < 500) {
            cont_h = 500;
        }
        $$("inflationWinTable").define("height", cont_h - 190);
        $$("inflationWinTable").adjust();
    }
}

var notifyType = {
    "info": "alert-info",
    "success": "alert-success",
    "warning": "alert-warning",
    "danger": "alert-danger"
};


function getChild(list, id, field_id, field_parent) {
    var tree = [];

    for (var i in list) {
        if (list[i][field_parent] == id) {
            var obj = {};
            for (var key in list[i]) {
                obj[key] = list[i][key];
            }

            var childs = getChild(list, list[i][field_id], field_id, field_parent);

            if (childs.length > 0)
                obj["data"] = childs;

            tree.push(obj);
        }
    }

    return tree;
}

function loadTreeData(viewId, data) {
    $$(viewId).clearAll();
    $$(viewId).parse(data);
}

function getObjectById(list, id) {
    for (var i in list) {
        if (list[i].id == id) {
            return list[i]
        }
    }
    return null;
}

function setValueBlockEvent(object, value) {
    object.blockEvent();
    object.setValue(value);
    object.unblockEvent();
}

function messageBox(title, text) {
    if (title && text) {
        var len = text.length;
        var width = len * 12;

        if (width > 500)
            width = 500;

        webix.modalbox({
            title: title,
            buttons: ["Ok"],
            text: text,
            width: width
        });
    }
}

function isEmpty(e) {
    if (e != null && e != "")
        return false;
    else
        return true;
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
                messageBox(getResourceName('message.label.danger'), getResourceName('service.has.error') + ' ' + url);
            }
        }
    });
}

function hideTextContainer(view) {
    document.getElementById('loader-wrapper').style.display = 'none';
    document.getElementById(view).style.opacity = '1';
}

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



webix.protoUI({
    name: "combo",
    $cssName: "combo",
    $renderIcon: function () {
        var config = this.config;
        var icons = ["angle-down"];
        if (config.icons && config.icons.length) {
            for (var i = 0; i < config.icons.length; i++) {
                icons.push(config.icons[i]);
            }
        }
        if (icons && icons.length) {
            var height = config.aheight - 2 * config.inputPadding,
                padding = (height - 18) / 2 - 1,
                html = "", pos = 2;
            for (var i = 0; i < icons.length; i++) {
                html += "<span style='right:" + pos + "px;cursor:pointer;height:"
                    + (height - padding) + "px;padding-top:" + padding
                    + "px;' class='webix_input_icon fa-" + icons[i] + "'></span>";

                pos += 24;
            }
            return html;
        }
        return "";
    },
    on_click: {
        "webix_input_icon": function (e, id, node) {
            var name = node.className.substr(node.className.indexOf("fa-") + 3);
            return this.callEvent("on" + name + "IconClick", [e]);
        }
    }
}, webix.ui.combo);


function getMaxDateByAge(strDate, age, type) {
    var date;
    var pattern = /(\d{2})\.(\d{2})\.(\d{4})/;
    if (!isNullOrEmpty(strDate)) {
        if (getZOsnId() != null && getZOsnId() == 91) {
            date = new Date(strDate.replace(pattern, '$3-$2-$1'));
        } else {
            date = new Date(strDate);
        }

        if (date) {
            if (age)
                date.setYear(date.getFullYear() + age);
            if (type && type == 1) {
                date.setDate(date.getDate());
            } else {
                date.setDate(date.getDate() - 1);
            }
        }
        return date;
    }
    return null;
}

function getResourceName(prop) {
    return prop;
}

String.prototype.replaceAll = function (token, newToken, ignoreCase) {
    var _token;
    var str = this + "";
    var i = -1;

    if (typeof token === "string") {

        if (ignoreCase) {

            _token = token.toLowerCase();

            while ((
                    i = str.toLowerCase().indexOf(
                        _token, i >= 0 ? i + newToken.length : 0
                    )) !== -1
                ) {
                str = str.substring(0, i) +
                    newToken +
                    str.substring(i + token.length);
            }

        } else {
            return this.split(token).join(newToken);
        }

    }
    return str;
};

function printDataTable(tableName) {
    webix.toExcel($$(tableName), {
        filterHTML: true,
        spans: true
    });
}

function closeWindowModal(windowId) {
    if ($$(windowId)) {
        $$(windowId).close();
    }
}

/*Проверка на номер*/
function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

/*Редактирования таблицы колонки который являются datepicker*/
function textDateToTable(newV, tblName, oldV, btnName, maxDate) {
    var dTable = $$(tblName);
    var item = dTable.getItem(newV.row);

    var currentDate = setDateFormatPointOnTheValue(oldV, 3);

    if (currentDate) {
        if (isNullOrEmpty(maxDate) || new Date(currentDate) <= maxDate) {

            item[newV.column] = setDateFormatPointOnTheValue(oldV, 1);

            dTable.updateItem(newV.row, item);
            if ($$(btnName))
                $$(btnName).enable();
        } else {

            if ($$(btnName))
                $$(btnName).disable();

            item[newV.column] = "";

            dTable.updateItem(newV.row, item);

            dTable.validate();

            notifyMessage(getResourceName('message.label.danger'), getResourceName('max.period.broken'), notifyType.danger);
            $$(btnName).enable();
        }
    } else {
        if ($$(btnName))
            $$(btnName).disable();
    }
}

function setDateFormatPointOnTheValue(value, param) {
    if (!isNullOrEmpty(value)) {
        var value_ = value.replaceAll('.', '');
        value_ = value_.replaceAll(',', '');
        value_ = value_.replaceAll('/', '');
        value_ = value_.replaceAll('-', '');
        if (value_.length == 8) {
            if (isNumeric(value_)) {
                var day_ = value_.substring(0, 2);
                var month_ = value_.substring(2, 4);
                var year_ = value_.substring(4, 8);
                if (month_ > 12) month_ = 12;
                if (year_ > 2999) year_ = 2999;
                switch (param) {
                    case 1:
                        return day_ + "." + month_ + "." + year_;
                    case 2:
                        return year_ + "." + month_ + "." + day_;
                    case 3:
                        return month_ + "." + day_ + "." + year_;
                }
            }
        }
    }
    return null;
}

/*превращает данные в mm.yyyy*/
function getDateValidateToExperinceOrSMZ(value, param) {
    var value_ = value.replaceAll('.', '');
    value_ = value_.replaceAll(',', '');
    value_ = value_.replaceAll('/', '');
    value_ = value_.replaceAll('-', '');
    if (isNumeric(value_) && value_.length == 6) {
        var month_ = value_.substring(0, 2);
        var year_ = value_.substring(2, 6);
        if (month_ > 12) month_ = 12;
        if (year_ > 2999) year_ = 2999;

        switch (param) {
            case 1:
                return year_ + month_;
            case 2:
                return month_ + '.' + year_;
        }
    }

    return null;
}

function nvl(val1, val2) {
    if (isNullOrEmpty(val1))
        return val2;
    else
        return val1;
}

function isValidDateColumn(value) {
    if (isNullOrEmpty(value))
        return true;

    var regEx = /^\d{2}.\d{2}.\d{4}$/;
    return value.match(regEx) != null;
}

function isUserLvl(lvl) {
    for (var i in eappRoleList) {
        if (eappRoleList[i].eappRoleId.lvl == lvl) {
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

function getLocalLanguage() {
    if (getLocalStorage("lang") == "Ru") {
        return locale();
    } else {
        return locale_kz();
    }
}

function getBranchId() {
    if (branch)
        return branch.id;
}

function getBranch() {
    return branch;
}

/**
 * @param array
 * @param value
 * @returns {boolean} ищет внутри массива
 */
function findIntoArray(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i] == value) return true;
    }

    return false;
}

function getFaIconByExt(fileext) {
    switch (fileext) {
        case "xls" :
        case "xlsx" :
            return 'file-excel-o';
        case "pdf" :
            return 'file-pdf-o';
        case "doc" :
        case "docx" :
            return 'file-word-o';
        case "rar" :
        case "zip" :
        case "jar" :
        case "7z" :
            return 'file-archive-o';
        case "jpg" :
        case "png" :
        case "bmp" :
        case "gif" :
        case "psd" :
            return 'file-image-o';
        case "txt" :
        case "rtf" :
            return 'file-text';
        case "ppt" :
        case "pptx" :
            return 'file-powerpoint-o';
        case "xml" :
            return 'file-code-o';
        default:
            return 'file-o';
    }
}

function getPensionDate(idSex, birthDate) {
    if (idSex == 1) { // М
        return getMaxDateByAge(birthDate, 63, 1);
    }
    else if (idSex == 0) {// Ж
        return getMaxDateByAge(birthDate, 58, 1);
    }
    else {
        return birthDate;
    }
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

function isPensRashet() {
    var specOtd = [38205];
    for (var i in specOtd) {
        if (isUserRoleId(specOtd[i])) {
            return true;
        }
    }
    return false;
}

function isRsp() {
    var specOtd = [17001, 38201];
    for (var i in specOtd) {
        if (isUserRoleId(specOtd[i])) {
            return true;
        }
    }
    return false;
}

function isReg() {
    var registrator = [38202, 17016];
    for (var i in registrator) {
        if (isUserRoleId(registrator[i])) {
            return true;
        }
    }
    return false;
}

function isRgs() {
    var registrator = [38207];
    for (var i in registrator) {
        if (isUserRoleId(registrator[i])) {
            return true;
        }
    }
    return false;
}

//'osp', -- Специалист областного Филиала
//38216,
function isOsp() {
    var osp = [38216];
    for (var i in osp) {
        if (isUserRoleId(osp[i])) {
            return true;
        }
    }
    return false;
}

function isAud() {
    var aud = [17006, 38239];
    for (var i in aud) {
        if (isUserRoleId(aud[i])) {
            return true;
        }
    }
    return false;
}

function isFondGFSS() {
    var fond = [17013, 17014, 17015];
    for (var i in fond) {
        if (isUserRoleId(fond[i])) {
            return true;
        }
    }
    return false;
}

function isFondSt() {
    var st = getMaketStatus();
    var fond = [
        {role: 17013, st: [16, 29, 65, 43, 4, 75]},
        {role: 17014, st: [44, 17, 5, 66, 76, 10]},
        {role: 17015, st: [6, 45, 67, 18, 11, 77]}
    ];

    for (var i in fond) {
        if (isUserRoleId(fond[i].role) && findIntoArray(fond[i].st, st)) {
              return true;
        }
    }
    return false;
}

function isDsp() {
    var dsp = [38221];
    for (var i in dsp) {
        if (isUserRoleId(dsp[i])) {
            return true;
        }
    }
    return false;
}

function isDgs() {
    var dgs = [38224];
    for (var i in dgs) {
        if (isUserRoleId(dgs[i])) {
            return true;
        }
    }
    return false;
}

function isDbs() {
    var dbs = [38227];
    for (var i in dbs) {
        if (isUserRoleId(dbs[i])) {
            return true;
        }
    }
    return false;
}

/*
 38201,
 'rsp', -- Специалист отделения
 38202,
 'reg', -- Регистратор
 38204,
 'rgs', -- Главный специалист отделения
 38207,
 'rbs', -- Начальник отделения
 38213,
 'osp', -- Специалист областного Филиала
 38216,
 'obs', -- Директор областного Филиала
 38221,
 'dsp', -- Специалист департамента
 38224,
 'dgs', -- Начальник отдела департамента
 38227,
 'dbs', -- Директор департамента
 38235,
 'cen',
 38236,
 'cen',
 38237,
 'cen',
 38239,
 'aud',
 38240,
 'clc',
 38212,
 'nao'
 */

function inflationWin() {
    if (!$$("inflationWin")) {
        webix.ui({
            view: "window",
            id: "inflationWin",
            width: 1100,
            position: "center",
            modal: true,
            on: {
                onHide: function () {
                    window.onscroll = null;
                }
            },
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: getResourceName('app.infl.ipc')},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon",
                                icon: " fa fa-times",
                                type: "iconButton",
                                css: "buttonIconRed",
                                width: 35,
                                on: {
                                    onItemClick: function (pid, e) {
                                        window.onscroll = null;
                                        this.getTopParentView().hide();
                                    }
                                }
                            }
                        ]
                    }
                ]
            },
            body: {
                cols: [
                    {},
                    {
                        id: "inflationWinBody",
                        rows: [
                            {height: 10},
                            {
                                view: "datatable",
                                css: "appTable",
                                id: "inflationWinTable",
                                autoheight: false,
                                autowidth: true,
                                scroll: true,
                                columns: [
                                    {
                                        template: "<b>#year#</b>",
                                        header: "<b>" + getResourceName("app.year") + "</b>",
                                        width: 80
                                    },
                                    {id: "jan", header: "<b>" + getResourceName("app.jan") + "</b>", width: 80},
                                    {id: "feb", header: "<b>" + getResourceName("app.feb") + "</b>", width: 80},
                                    {id: "mar", header: "<b>" + getResourceName("app.mar") + "</b>", width: 80},
                                    {id: "apr", header: "<b>" + getResourceName("app.apr") + "</b>", width: 80},
                                    {id: "may", header: "<b>" + getResourceName("app.may") + "</b>", width: 80},
                                    {id: "jun", header: "<b>" + getResourceName("app.jun") + "</b>", width: 80},
                                    {id: "jul", header: "<b>" + getResourceName("app.jul") + "</b>", width: 80},
                                    {id: "aug", header: "<b>" + getResourceName("app.aug") + "</b>", width: 80},
                                    {id: "sep", header: "<b>" + getResourceName("app.sep") + "</b>", width: 80},
                                    {id: "oct", header: "<b>" + getResourceName("app.oct") + "</b>", width: 80},
                                    {id: "nov", header: "<b>" + getResourceName("app.nov") + "</b>", width: 80},
                                    {id: "dec", header: "<b>" + getResourceName("app.dec") + "</b>", width: 80}
                                ],
                                on: {
                                    onAfterLoad: function (v) {
                                        this.hideProgress();
                                        this.hideOverlay();
                                        if (!this.count()) {
                                            this.showOverlay("<span class='no_data_found'>" + getResourceName('no.data.found') + "</span>");
                                            fortestconnect();
                                        }

                                    },
                                    onBeforeLoad: function () {
                                        webix.extend(this, webix.ProgressBar);
                                        this.showProgress();
                                    }
                                }
                            },
                            {height: 10}
                        ]
                    },
                    {}
                ]
            }
        }).hide();
    }
    $$("inflationWin").show();

    $$("inflationWinTable").clearAll();
    $$("inflationWinTable").define('url', "/other/getVInflationList");
    inflationWinTableResize();
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };

    if (isCen()) {
        getInflationNextDate();
    }
}

function getInflationNextDate() {
    $$("inflationWinBody").removeView("addIpc");
    $$("inflationWinBody").addView({
        id: "addIpc",
        rows: [
            {
                id: "addIpcCols",
                autoheight: true,
                cols: [{view: "label", autowidth: true, label: getResourceName('app.infl.ipc.add')}]
            },
            {height: 10}
        ]
    });
    get_ajax('/app/wr/maket/getInflationNextDate', 'GET', null, function (gson) {
        if (gson && gson.result) {
            $$("addIpcCols").addView({
                view: "label",
                autowidth: true,
                label: "<b>" + gson.message + "</b>"
            });
            $$("addIpcCols").addView({
                id: "newInflationInput",
                view: "text",
                type: "number",
                width: 100
            });
            $$("addIpcCols").addView({
                width: 15
            });
            $$("addIpcCols").addView({
                css: "noBorder",
                id: 'setInflationBtn',
                width: 180,
                template: '<button type="button" onclick="setInflation()" class="btn btn-success">' + getResourceName('app.just.btn.add') + '</button>'
            });
        }

    }, function (url) {
        messageBox(getResourceName('message.label.danger'), getResourceName('service.has.error') + ' ' + url);
    });
}

function setInflation() {
    $$("newInflationInput").disable();
    $$("setInflationBtn").disable();
    get_ajax('/app/wr/maket/setInflation', 'GET', {value: $$("newInflationInput").getValue()}, function (gson) {
        if (gson && gson.result) {
            $$("inflationWinTable").clearAll();
            $$("inflationWinTable").define('url', "/other/getVInflationList");
            getInflationNextDate();
        } else {
            messageBox(getResourceName('message.label.danger'), gson.message);
        }
        $$("newInflationInput").enable();
        $$("setInflationBtn").enable();
    }, function (url) {
        $$("newInflationInput").enable();
        $$("setInflationBtn").enable();
        messageBox(getResourceName('message.label.danger'), getResourceName('service.has.error') + ' ' + url);
    });
}

$.fn.scrollEnd = function (callback, timeout) {
    $(this).scroll(function () {
        var $this = $(this);
        if ($this.data('scrollTimeout')) {
            clearTimeout($this.data('scrollTimeout'));
        }
        $this.data('scrollTimeout', setTimeout(callback, timeout));
    });
};

function getTemplateByMsg() {
    var html = "<div id='iframe' style='height:100%;width:100%;overflow:auto;'>";
    html += winBody;
    html += "</div>";
    return html;
}

function getChekedStringDatatable(datatable) {
    var checked = "";
    datatable.data.each(function (obj) {
        if (obj && obj.ch1) {
            if (checked != "") {
                checked = checked + ",";
            }

            checked = checked + obj.id;
        }
    });
    return checked;
}

function getChekedDatatable(datatable) {
    var checked = [];
    datatable.data.each(function (obj) {
        if (obj && obj.ch1) {
            checked.push(obj.id);
        }
    });
    return checked;
}

/**
 * @desc печать Iframe по ID;
 * @param elementId
 * @returns {boolean}
 */
function printFrameById(elementId) {
    var frm = document.getElementById(elementId).contentWindow;
    frm.focus();
    frm.print();
    return false;
}

function collapsedOrExpand(item) {
    if (item.config.collapsed) {
        item.collapse();
    } else {
        item.expand();
    }
    item.refresh();
}

var fortestconnectFlag = 0;

function fortestconnect() {
    if (fortestconnectFlag == 0) {
        fortestconnectFlag = 1;
        get_ajax('/app/pages/fortestconnect.jsp', 'GET', null,
            function (gson) {
                fortestconnectFlag = 0;
            },
            function (url) {
                fortestconnectFlag = 0;
            }
        );
    }
}

function setBoldText(text) {
    return '<span style="font-weight: bold">' + text + '</span>';
}

function validateIin(iin) {
    if (iin && iin.length != 12) {
        notifyMessage(getResourceName('message.label.danger'), getResourceName('app.size.iin'), notifyType.danger);
        return false;
    }
    return true;
}

/*Просмотрщик доков*/
function setDocToModalWindow(tab) {
    $("#buttonColsExps").html("");
    get_ajax('/app/wr/doc/getMaketDocList', 'GET', {
        id: getPrevId(),
        tab: tab
    }, function (gson) {
        var i = 0;
        if (gson) {
            for (var k in gson) {
                i += 1;
                if (i == 1) {
                    setImageTowindowModal(gson[k].id, 1);
                }
                $("#buttonColsExps").append(
                    '<button type="button" onclick="setImageTowindowModal(' + gson[k].id + ',' + 1 + ')" id="' + (gson[k].id) + '" class="btn btn-warning" style="color: white; width: 36px; height: 36px">' + i + '</button>&nbsp;&nbsp;'
                );
            }
        }
    });
    $('#openDcModal').modal('show');
}

function setImageTowindowModal(id, i) {
    var item;
    var src = "http://" + window.location.host + "/app/wr/doc/getUploadedImageFile?fileid=" + id;
    if (i == 2) {
        item = $('#idContainer2');
    }

    item.empty();

    var options = {};
    var it = item.ImageTrans(options);
    it.load(src);

    $("#idContainer2 img").css("maxWidth", $("#idContainer2").width());
    $(".btnZoomOut").on("click", function () {
        var width = $("#idContainer2 img").width();

        width = width - 50;

        $("#idContainer2 img").width(width);
        $("#idContainer2 img").css("maxWidth", width);
    });
    $(".btnZoomIn").on("click", function () {
        var width = $("#idContainer2 img").width();

        width = width + 50;

        $("#idContainer2 img").width(width);
        $("#idContainer2 img").css("maxWidth", width);
    });

    $(".idLeft").on("click", function () {
        it.left();
    });
    $(".idRight").on("click", function () {
        it.right();
    });

    hideTextContainer("mainlayot");
}

function setPdfExpSmz() {
    $("#documentViewExp").attr("src", "/app/wr/doc/getUploadedPdfFile?fileid=" + $('#selectDoc').val());
}

function setImageToExpDocs(id, i) {
    if ($$('pdfViewerExp') != null && $$('pdfViewerExp').config.hidden) {
        var item = null;
        var src = "http://" + window.location.host + "/app/wr/doc/getUploadedImageFile?fileid=" + id;
        if (i == 1) {
            item = $('.idContainerInner');
        } else if (i == 2) {
            item = $('#idContainerInner2');
        }

        item.empty();

        var options = {};
        var it = item.ImageTrans(options);
        it.load(src);
        $(".expZoom").html(100);
        $(".idContainerInner img").css("maxWidth", $(".idContainerInner").width());
        $(".btnZoomOut").on("click", function () {
            var width = $(".idContainerInner img").width();
            var percentBefore = $(".expZoom").html();
            var percent = Number.parseInt(percentBefore) - 5;
            if (percent > 0) {
                width = width / percentBefore * percent;
                $(".expZoom").html(percent);
                $(".idContainerInner img").width(width);
                $(".idContainerInner img").css("maxWidth", width);
            }
        });
        $(".btnZoomIn").on("click", function () {
            var width = $(".idContainerInner img").width();
            var percentBefore = $(".expZoom").html();
            var percent = Number.parseInt(percentBefore) + 5;
            if (percent < 200) {
                width = width / percentBefore * percent;
                $(".expZoom").html(percent);
                $(".idContainerInner img").width(width);
                $(".idContainerInner img").css("maxWidth", width);
            }
        });
        $(".idLeft").on("click", function () {
            it.left();
        });
        $(".idRight").on("click", function () {
            it.right();
        });

        hideTextContainer("mainlayot");
    } else {
        setPdfExpSmz();
    }
}


function setImageToWinExpDocs(id, i) {
    var item = null;
    var src = "http://" + window.location.host + "/app/wr/doc/getUploadedImageFile?fileid=" + id;
    if (i == 1) {
        item = $('.viewidContainerInner');
    } else if (i == 2) {
        item = $('#viewidContainerInner2');
    }

    item.empty();

    var options = {};
    var it = item.ImageTrans(options);
    it.load(src);
    $(".viewexpZoom").html(100);
    $(".viewidContainerInner img").css("maxWidth", $(".viewidContainerInner").width());
    $(".btnZoomOut").on("click", function () {
        var width = $(".viewidContainerInner img").width();
        var percentBefore = $(".viewexpZoom").html();
        var percent = Number.parseInt(percentBefore) - 5;
        if (percent > 0) {
            width = width / percentBefore * percent;
            $(".viewexpZoom").html(percent);
            $(".viewidContainerInner img").width(width);
            $(".viewidContainerInner img").css("maxWidth", width);
        }
    });
    $(".btnZoomIn").on("click", function () {
        var width = $(".viewidContainerInner img").width();
        var percentBefore = $(".viewexpZoom").html();
        var percent = Number.parseInt(percentBefore) + 5;
        if (percent < 200) {
            width = width / percentBefore * percent;
            $(".viewexpZoom").html(percent);
            $(".viewidContainerInner img").width(width);
            $(".viewidContainerInner img").css("maxWidth", width);
        }
    });
    $(".idLeft").on("click", function () {
        it.left();
    });
    $(".idRight").on("click", function () {
        it.right();
    });

    hideTextContainer("mainlayot");
}

function exportToExcel(reportName, type) {
    var template;
    var title = reportName + '.xls';
    var htmls = "";
    var uri = 'data:application/vnd.ms-excel;base64,';

    switch (type) {
        case 1:
            template = document.getElementById('reportResultPdfFrame').contentWindow.document.body.getElementsByClassName('Grid')[0].innerHTML;
            break;
        case 2:
            template = document.getElementById('reportWinFrame').contentWindow.document.body.getElementsByClassName('Grid')[0].innerHTML;
            break;
    }

    var base64 = function (s) {
        return window.btoa(unescape(encodeURIComponent(s)))
    };
    var format = function (s, c) {
        return s.replace(/{(\w+)}/g, function (m, p) {
            return c[p];
        })
    };
    var ctx = {worksheet: reportName, table: htmls};
    var link = document.createElement("a");
    link.download = title;
    link.href = uri + base64(format(template, ctx));
    link.click();

}

function exportToWordRep(type, reportName) {
    var template;
    switch (type) {
        case 1:
            template = document.getElementById('reportResultPdfFrame').contentWindow.document.body.innerHTML;
            break;
        case 2:
            template = document.getElementById('reportWinFrame').contentWindow.document.body.innerHTML;
            break;
    }
    var link = document.createElement('a');
    var fName = reportName + '.doc';
    link.setAttribute('download', fName);

    link.setAttribute('href', 'data:' + 'text/doc' + ';charset=utf-8,' + encodeURIComponent(template));
    link.click();
}

function deleteValExpAccomView(tableName, id, btnName) {
    $$(tableName).remove(id);
    notifyMessage(getResourceName('message.label'), getResourceName('app.record.drop'), notifyType.info);
    $$(btnName).enable();
}

/*
 * Для Смз и стажа
 * */
function wxTextViewToDatePickerFirstInput(value, formId, itemName) {
    if (formId == "maketWinForm") {
        var value_ = value.slice(3);
        var date_ = getDateValidateToExperinceOrSMZ(value_, 2);
        setValueBlockEvent($$(formId).elements[itemName], date_);
    } else if (beforeGson && beforeGson.app && beforeGson.app.dateObr != null) {
        var dateObr = beforeGson.app.dateObr;
        dateObr = dateObr.slice(3);
        dateObr = getDateValidateToExperinceOrSMZ(dateObr, 1);
        var date_ = getDateValidateToExperinceOrSMZ(value, 1);
        if (date_ && dateObr) {
            if (date_ <= dateObr) {
                date_ = getDateValidateToExperinceOrSMZ(value, 2);
                setValueBlockEvent($$(formId).elements[itemName], date_);
            } else {
                notifyMessage(getResourceName('message.label.danger'), getResourceName('prtd.zp.dobr'), notifyType.danger);
                setValueBlockEvent($$(formId).elements[itemName], "");
            }
        } else {
            setValueBlockEvent($$(formId).elements[itemName], "");
        }
    }
}

function getSmzPensCalcPrv(newV, formId, elemenName) {
    var form = $$(formId);
    var dateObr = form.getValues().dateObr;
    dateObr = dateObr.slice(3);
    dateObr = getDateValidateToExperinceOrSMZ(dateObr, 1);
    var date_ = getDateValidateToExperinceOrSMZ(newV, 1);
    if (date_ && dateObr) {
        if (date_ <= dateObr) {
            date_ = getDateValidateToExperinceOrSMZ(newV, 2);
            setValueBlockEvent(form.elements[elemenName], date_);
        } else {
            notifyMessage(getResourceName('message.label.danger'), getResourceName('prtd.zp.dobr'), notifyType.danger);
            setValueBlockEvent(form.elements[elemenName], "");
        }
    } else {
        setValueBlockEvent(form.elements[elemenName], "");
    }
}

function scrollToByName(name) {
    if (!isNullOrEmpty(name)) {
        var scrollTo = $('#' + name);
        $('html, body').animate({
            scrollTop: scrollTo.offset().top
        }, 'slow');
    }
}