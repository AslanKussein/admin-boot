package kz.crtr.app.utils;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;

public class Constant {

    public static Long USER_ACTION_ADD = (long) 1;
    public static Long USER_ACTION_EDIT = (long) 2;

    public static enum ENTITY_CLASSES {

        UserDetail, Application, UsersHistory, Documentation;
    }

    //main server ip
    public static final String MAIN_SERVER_IP = "172.16.17.240";

    //constanta authorization
    public static final String USER = "user";

    public static final String DEF_PROPERTIES = "properties";
    /**
     * ID СИСТЕМЫ ЕМАКЕТ, GFSS
     */
    public static final String EAPP_GFSS_SYSTEM_ID = "82,120";
    public static final BigDecimal EAPP_SYSTEM_ID = new BigDecimal(82);

    //Стандартные лимит и старты для DataTable
    public static final int DEF_START_ZERO = 0;
    public static final int DEF_COUNT = 30;

    //Стандартные лимит и старты для DAction
    public static final int DEF_COUNT_DACTION = 20;

    /*
     * SMZSESSION constants
     */
    public static final int DEF_NUMBER_1 = 1;
    public static final int DEF_NUMBER_12 = 12;

    /*
     * PersonSession constants
     */
    public static final int DEF_LST_PRSS_CNT = 10;
    public static final int DEF_PAGESIZE_50 = 50;
    public static final int DEF_PERSON_10 = 10;
    public static final int DEF_PERSON_12 = 12;

    /*
     * DictionarySession constants
     */
    public static final int DICT_NUMBER_58 = 58;
    public static final int DICT_NUMBER_63 = 63;

    /*
     * DocumentSession constants
     */
    public static final int DEF_CNT_DECLAR = 12;

    /*
     * AppSession constants
     */
    public static final String APP_SOURCE_ZDOC = "zDoc";
    public static final String APP_SRC_ZDCPRV = "ZDocPrev";
    public static final String APP_SOURCE_MEG = "MEg";
    public static final String APP_LANG_VALUE_RU = "RU";

    public static final String ZTIP_NEW = "NEW";
    public static final String ZTIP_REC = "REC";
    public static final String ZTIP_MOV = "MOV";
    public static final String ZTIP_SUS = "SUS";
    public static final String ZTIP_GVN = "GVN";
    public static final String ZTIP_INC = "INC";
    public static final String ZTIP_IRN = "IRN";
    public static final String ZTIP_MVN = "MVN";
    public static final String ZTIP_RCN = "RCN";
    public static final String ZTIP_IDX = "IDX";
    public static final String ZTIP_RWL = "RWL";

    public static final int CALENDAR_MONTH = 12;
    public static final int CALENDAR_DAY = 30;

    public static final String DEF_MINUS_1 = "-1";
    public static final String DEF_0 = "0";
    public static final String DEF_12 = "12";
    public static final String DEF_24 = "24";
    public static final String DEF_0704 = "0704";
    public static final String DEF_MSE = "MSE";
    public static final String DEF_ELC = "ELC";

    public static final String DEF_ERROR_MSG = "error";
    public static final String DEF_ERROR_PAR = "params: ";

    public static final String DEF_ADMIN = "ADMIN";

    public static final int[] DEF_SS_OSN = {101, 102, 103, 104, 105};

    public static final Object[] DEF_SS_OSN_STR = {"101", "102", "103", "104", "105"};
    public static final String[] DEF_SS_OSN_EMD = {"0701", "0702", "0703", "0704", "0705"};

    public static final int[] DEF_SS_REPORTS = {17503, 17504, 17505};

    public static final int[] DEF_ARRAY_SGP_20 = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37};

    public static final int[] DEF_ARRAY_OSN_41_42 = {41, 42};
    public static final int[] DEF_ARRAY_OSN_71_74 = {71, 74};

    public static final String[] DEF_STRING_AR_41_42 = {"41", "42"};

    public static final int[] DEF_ARRAY_OSN_61_62_77 = {61, 62, 77};

    public static final String NOTICE_TYPE_XML = "xml";

    public static final Integer DEF_SS_NOTICATE_ID_1 = 1;
    public static final Integer DEF_SS_STATUS_ID_7 = 7;

    public static final String DEF_X_H_P_TEXT = "Размер месячной пенсионной выплаты(с учетом доплаты до размера пособия) ";
    public static final String DEF_X_H_P_TEXT_KAZ = "Айлық зейнетақы төлемінің мөлшерi (жәрдемақы мөлшерiне дейiн жеткiзiлген қосымша ақыны ескере отырып)";

    public static final String DEF_PATT_WITH_M_Y = "MM.yyyy";
    public static final String DEF_PATT_WITH = "dd MMMM";
    public static final String DEF_PATT_WOUT = "MMMM";
    public static final String DEF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DEF_YYYY_MM_DD_DOT = "yyyy.MM.dd";
    public static final String DEF_MM_DD_YYYY_DOT = "MM.dd.yyyy";
    public static final String DEF_DD_MM_YYYY_DOT = "dd.MM.yyyy";
    public static final String DEF_PATT_ALL_VALUE = "dd.MM.yyyy HH.mm.ss";
    public static final String DEF_PATT_VALUE_SIMICOLON = "dd.MM.yyyy HH:mm:ss";

    public static final String DEF_HTML_BR = "<br />";

    public static final DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };

    public static final String[] myMontSymbols = new String[]{"", "а)", "б)", "в)", "г)", "д)", "е)", "ё)", "ж)", "з)", "и)", "й)", "к)", "л)", "м)", "н)", "о)", "п)", "р)",
            "с)", "т)", "у)", "ф)", "х)", "ц)", "ч)", "ш)", "щ)", "ъ)", "ы)", "ь)", "э)", "ю)", "я)"};

    public static final String DEF_USERNAME = "j_username";
    public static final String DEF_PASSWORD = "j_password";

    public static final String DEF_USER_ROLE_BACK = "EMAKET_BACK";
    public static final String DEF_USER_ROLE_DEVELOPER = "EMAKET_DEVELOPER";
    public static final String DEF_USER_ROLE_VIEW = "EMAKET_VIEW";
    public static final String DEF_USER_ROLE_AUDIT = "EMAKET_AUDIT";

    public static final String DEF_JNDINAME_EM5 = "kz.gcvp_app_war_v1PU";
    public static final String DEF_JNDINAME_EMIMG = "kz.gcvp_app_img";
    public static final String DEF_JDBC_JNDI = "jdbc/tbl";

    public static final String DEF_N = "N";
    public static final String DEF_Y = "Y";

    public static final String DEF_ENCODING_UTF8 = "UTF-8";

    public static final String DEF_SLF = "SLF";
    public static final String DEF_TEC = "TEC";

    public static final String DEF_ALL_LIFE = "(бессрочно)";
    public static final String DEF_ALL_LIFE_WOUT = "бессрочно";
    public static final String DEF_ALL_LIFE_WOUT_KZ = "өмірлік";

    public static final String DEF_TENGE = " тенге.";

    public static final String DEF_IS_AN = "Является";
    public static final String DEF_ALL_LIVE = "бессрочно";
    public static final String DEF_ALL_LIVE_12_11 = "пожизненно";
    public static final String DEF_ALL_LIVE_12_11_KAZ = "өмірлік";
    public static final String DEF_NOT_IS_AN = "Не является";
    public static final String DEF_SYMBOL_NUMBER = "№";
    public static final String DEF_PASSWORD_USERS = "$2a$10$7xH86bLDMrDwODPFJdTNseA.3bP4AaJ//El1jMD2sAio0ZLkvy.HG";
    public static final String DEF_SLACE_VALUE = "________________________________________________________________";
    public static final String DEF_SLACE__SHORT_VALUE = "__________";

    public static final int[] DEF_WITHOUT_ECP_STATE = {30, 31, 32, 62, 72, 82, 91};
    public static final int[] DEF_VALIDATE_STATUS = {0, 8, 69, 34, 38, 60, 80, 89};

    public static final int[] DEF_CENTER_ARRAYS = {38235, 38236, 38237};

    public static final int[] DEF_GFSS_ARRAYS = {17013, 17014, 17015};

    public static final int[] DEF_AUDIT_ARRAYS = {17006, 38239};

    public static final int[] DEF_FRONT_ARRAYS = {38202, 17016, 38201, 17001};
    public static final int[] DEF_RESH_PENS = {38205};

    public static final int[] DEF_ADMIN_USER_LIST = {31888, 31890, 31887};
    public static final int[] DEF_IS_PROACTIVE = {61, 62, 77, 105};

    public static final int[] DEF_RECEP_AUTO_SAVE = {14, 30, 71, 105, 102};
}