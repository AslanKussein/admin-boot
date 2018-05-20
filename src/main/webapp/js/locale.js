function locale() {
    webix.i18n.locales["ru-RU"] = {
        groupDelimiter: " ",
        groupSize: 3,
        decimalDelimiter: ",",
        decimalSize: 2,
        dateFormat: "%d.%m.%Y",
        timeFormat: "%H:%i",
        longDateFormat: "%d %F %Y",
        fullDateFormat: "%d.%m.%Y %H:%i",
        price: "{obj} руб.",
        priceSettings: null, //use number defaults

        calendar: {
            monthFull: ["Январь", "Февраль", "Март", "Апрель", "Maй", "Июнь", "Июль", "Август", "Сентябрь", "Oктябрь", "Ноябрь", "Декабрь"],
            monthShort: ["Янв", "Фев", "Maр", "Aпр", "Maй", "Июн", "Июл", "Aвг", "Сен", "Окт", "Ноя", "Дек"],
            dayFull: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
            dayShort: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
            hours: "Часы",
            minutes: "Минуты",
            done: "Гoтовo",
            clear: "Очистить",
            today: "Сегодня"
        },
        controls: {
            select: "Выбрать"
        }
    };

    webix.i18n.setLocale('ru-RU');
    webix.i18n.setLocale();
}

function locale_kz() {
    webix.i18n.locales["kz-KZ"] = {
        groupDelimiter: " ",
        groupSize: 3,
        decimalDelimiter: ",",
        decimalSize: 2,
        dateFormat: "%d.%m.%Y",
        timeFormat: "%H:%i",
        longDateFormat: "%d %F %Y",
        fullDateFormat: "%d.%m.%Y %H:%i",
        price: "{obj} тг.",
        priceSettings: null,

        monthFull: ["Қаңтар", "Ақпан", "Наурыз", "Сәуір", "Мамыр", "Маусым", "Шілде", "Тамыз", "Қыркүйек", "Қазан", "Қараша", "Желтоқсан"],
        calendar: {
            monthShort: ["Қаң", "Ақп", "Нау", "Сәу", "Мам", "Мау", "Шіл", "Там", "Қыр", "Қаз", "Қар", "Жел"],
            dayFull: ["Жексенбі", "Дүйсенбі", "Сейсенбі", "Сәрсенбі", "Бейсенбі", "Жұма", "Сенбі"],
            dayShort: ["Жб", "Дб", "Сб", "Ср", "Бб", "Жм", "Сб"],
            hours: "Сағат",
            minutes: "Минут",
            done: "Дайын",
            clear: "Тазарту",
            today: "Бүгін"
        },
        controls: {
            select: "Таңдау"
        }
    };

    webix.i18n.setLocale('kz-KZ');
}