webix.ready(function () {
    webix.ui({
        id: "mainlayot",
        autowidth: true,
        autoheight: true,
        container: "mainlayot",
        margin: 5,
        rows: [
            {
                cols: [
                    {
                        view: 'label',
                        label: '<h4>Админка e-shop</h4>',
                        css: "text_success"
                    },
                    {
                        view: "button",
                        type: "iconButtonTop",
                        icon: "home",
                        label: '<span style="color: white">Дукенге кiру</span>',
                        css: "button_primary button_raised",
                        height: 50,
                        width: 200,
                        click: function () {
                            window.location.href = "shop.jsp"
                        }
                    },
                    {}
                ]
            },
            {
                view: 'button',
                css: "button_warning button_raised",
                label: 'Тауар қосу',
                click: function () {
                    addTWin();
                }
            },
            {
                view: 'button',
                css: "button_info button_raised",
                label: 'Категория қосу',
                click: function () {
                    addTWinCategory();
                }
            }
        ]
    });

});

function addTWin() {
    webix.ui({
        view: "window",
        id: "addTWin",
        modal: true,
        position: "center",
        height: 800,
        width: 1000,
        head: {
            cols: [
                {view: "label", label: "Тауар қосу терезесi"}
            ]
        },
        body: {
            view: "form",
            id: "zFormSave",
            scroll: false,
            css: "bg_panel",
            elements: [{
                rows: [
                    {
                        view: 'text',
                        name: 'nameT',
                        required: true,
                        label: 'Тауар аты'
                    },
                    {
                        name: "category",
                        label: 'Тауар категориясы',
                        view: "richselect",
                        suggest: {
                            css: "auto_width_popup",
                            body: {
                                template: "#name#",
                                url: "/app/getDcategory"
                            }
                        }
                    },
                    {
                        view: 'text',
                        required: true,
                        name: 'costT',
                        label: 'Тауар бағасы'
                    },
                    {
                        view: 'text',
                        required: true,
                        name: 'descT',
                        label: 'Тауар сипаты'
                    },
                    {
                        view: 'text',
                        name: 'countT',
                        required: true,
                        label: 'Тауар cаны'
                    },
                    {
                        cols: [
                            {
                                view: "uploader",
                                icon: "upload",
                                type: "iconButtonTop",
                                width: 30,
                                css: 'button_warning',
                                align: 'center',
                                upload: "/app/upload",
                                multiple: false,
                                formData: {},
                                datatype: "json",
                                counter: 0,
                                on: {
                                    onUploadComplete: function (response) {
                                        console.log(response)
                                    }
                                }
                            },
                            {}
                        ]
                    },
                    {height: 10},
                    {
                        cols: [
                            {
                                view: "button",
                                value: "Қосу",
                                type: "form",
                                css: "button_success button_raised",
                                width: 130,
                                height: 40,
                                click: function () {
                                    createTF();
                                }
                            },
                            {width: 50},
                            {
                                view: "button",
                                value: "Жабу",
                                type: "standart",
                                css: "button_danger button_raised",
                                width: 130,
                                height: 40,
                                click: function () {
                                    this.getTopParentView().hide();
                                }
                            },
                            {}
                        ]
                    }
                ]
            }],
            elementsConfig: {labelPosition: "top", labelAlign: "left"}
        }
    }).hide();

    $$('addTWin').show(false, false);
}

function createTF() {
    let form = $$('zFormSave');
    if (form.validate()) {
        $.ajax({
            url: '/app/createT',
            type: 'GET',
            data: {
                nameT: form.getValues().nameT,
                costT: form.getValues().costT,
                count: form.getValues().countT,
                descT: form.getValues().descT,
                category: form.getValues().category
            },
            success: function (gson) {
                if (gson.result) {
                    notifyMessage('ақпарат', 'Сакталды', notifyType.success);
                } else {
                    notifyMessage('ақпарат', gson.message, notifyType.danger);
                }
            }
        });
    }
}

function addTWinCategory() {
    webix.ui({
        view: "window",
        id: "addTWinCategory",
        modal: true,
        position: "center",
        height: 800,
        width: 1000,
        head: {
            cols: [
                {view: "label", label: "Категория косу терезесi"}
            ]
        },
        body: {
            view: "form",
            id: "zFormSave",
            scroll: false,
            css: "bg_panel",
            elements: [{
                rows: [
                    {
                        view: 'text',
                        name: 'nameT',
                        required: true,
                        label: 'Категория аты'
                    },
                    {height: 10},
                    {
                        cols: [
                            {
                                view: "button",
                                value: "Қосу",
                                type: "form",
                                css: "button_success button_raised",
                                width: 130,
                                height: 40,
                                click: function () {
                                    addwinCtegory();
                                }
                            },
                            {width: 50},
                            {
                                view: "button",
                                value: "Жабу",
                                type: "standart",
                                css: "button_danger button_raised",
                                width: 130,
                                height: 40,
                                click: function () {
                                    this.getTopParentView().hide();
                                }
                            },
                            {}
                        ]
                    }
                ]
            }],
            elementsConfig: {labelPosition: "top", labelAlign: "left"}
        }
    }).hide();

    $$('addTWinCategory').show(false, false);
}


function addwinCtegory() {
    let form = $$('zFormSave');
    if (form.validate()) {
        $.ajax({
            url: '/app/createCat',
            type: 'GET',
            data: {nameT: form.getValues().nameT},
            success: function (gson) {
                console.log(gson)
                if (gson.result) {
                    notifyMessage('ақпарат', 'Сакталды', notifyType.success);
                } else {
                    notifyMessage('ақпарат', gson.message, notifyType.danger);
                }
            }
        });
    }
}