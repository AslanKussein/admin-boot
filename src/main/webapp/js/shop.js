function getTovarByDcategory(object) {
    $.ajax({
        url: '/app/getDcategoryById',
        type: 'GET',
        data: {dId: object.id},
        success: function (gson) {
            if(gson.message.length > 3){
                $$('projectList').define("scroll", "true");
                $$('projectList').refresh();
            }
            loadTreeData('projectList',gson.message);
        }
    });
}

function getTovarByDcategoryByName() {
    if (!isNullOrEmpty($('#searchS').val())) {
        $.ajax({
            url: '/app/getTovarByDcategoryByName',
            type: 'GET',
            data: {name: $('#searchS').val()},
            success: function (gson) {
                if(gson.message.length > 3){
                    $$('projectList').define("scroll", "true");
                    $$('projectList').refresh();
                }
                loadTreeData('projectList',gson.message);
            }
        });
    } else {
        notifyMessage('Кате', 'Іздеу параметрлерін енгізіңіз', notifyType.danger);
    }
}

webix.ready(function () {
    webix.ui({
        id: "mainlayot",
        autowidth: true,
        autoheight: true,
        container: "mainlayot",
        margin: 5,
        rows: [
            {
                id: 'mainRows',
                rows: []
            },
            {
                view: "list",
                container: "mainlayout",
                id: 'projectList',
                borderless: true,
                scroll: false,
                minHeight: 1000,
                autowidth: true,
                type: {
                    templateStart: "<div>",
                    template: "html->template_container",
                    templateEnd: "</div>"
                },
                scheme: {
                    $init: function (data) {
                        data["pos"] = this.count();
                        data["id"] = data.id;
                        if(data.name != undefined){
                            data["name"] = "("+data.name+")";
                        }
                        if(data.descr != undefined){
                            data["description"] = data.descr;
                        } else {
                            data["description"] = " ";
                        }

                    }
                }
            }
        ]
    });
});

function addToTrash() {

}