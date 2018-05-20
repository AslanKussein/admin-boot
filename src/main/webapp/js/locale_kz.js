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