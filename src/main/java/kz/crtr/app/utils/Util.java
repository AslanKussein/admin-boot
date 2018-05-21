package kz.crtr.app.utils;

import com.google.gson.Gson;
import kz.crtr.app.entity.gson.GsonEappUserRole;
import kz.crtr.app.entity.gson.GsonUsers;
import kz.crtr.app.gson.GsonResult;
import kz.crtr.app.lang.Lang;
import kz.crtr.app.lang.UTF8Control;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static kz.crtr.app.utils.Constant.*;
import static kz.crtr.app.utils.DateUtils.dateToString;

/**
 * @author a.kussein
 */
public class Util {


    public static String createGuid() {
        return java.util.UUID.randomUUID().toString();
    }

    public static Object getSingleResultOrNull(Query query) {
        List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        }
        throw new NonUniqueResultException();
    }

    public static GsonResult getGsonResult(Boolean result, Object message) {
        GsonResult gson = new GsonResult();
        gson.setResult(result);
        gson.setMessage(message);
        return gson;
    }

    public static String getResultGson(Boolean result, Object message) {
        return objectToJson(getGsonResult(result, message));
    }

//    public static String getUploadGson(Boolean result, String sname, Integer fileSize, Long id) {
//        JSONObject tempObject = new JSONObject();
//        tempObject.put("sname", sname);
//        tempObject.put("result", result);
//        tempObject.put("fileId", id);
//        tempObject.put("sizetext", getFileSizeString(fileSize));
//        return tempObject.toJSONString();
//    }

    public static Integer int_nvl(Integer val1, Integer val2) {
        if (val1 == null) {
            return val2;
        }

        return val1;
    }

    public static Boolean isNullOrEmptyCollection(List list) {
        return (list == null || list.size() == 0);
    }

    public static Integer getZeroOrIntegerValueByString(String value) {
        try {
            if (isNullOrEmpty(value))
                return 0;
            else
                return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String getFirstUpperString(String value) {
        if (!isNullOrEmpty(value)) {
            return value.substring(0, 1).toUpperCase() + value.substring(1, value.length()).toLowerCase();
        }
        return null;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    private static String getFileSizeString(Integer fileSize) {
        if (fileSize <= 0) {
            return "0";
        }
        final String[] units = new String[]{"байт", "килобайт", "мегабайт", "гигабайт", "терабайт"};
        int digitGroups = (int) (Math.log10(fileSize) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(fileSize / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static Long integerToLong(Integer val) {
        if (val == null) {
            return null;
        }

        return val.longValue();
    }

    public static String integerToString(Integer val) {
        if (val == null) {
            return null;
        }

        return val.toString();
    }

    public static Long bigIntegerToLong(BigInteger val) {
        if (val == null)
            return null;

        return val.longValue();
    }

    public static Long bigDecimalToLong(BigDecimal val) {
        if (val == null)
            return null;

        return val.longValue();
    }

    public static Integer bigDecimalToInteger(BigDecimal val) {
        if (val == null)
            return null;

        return val.intValue();
    }

    public static String bigDecimalToString(BigDecimal val) {
        if (val == null)
            return null;

        return val.toString();
    }

    public static String bigIntegerToString(BigInteger val) {
        if (val == null)
            return null;

        return val.toString();
    }

    public static BigDecimal bigIntegerToBigDecimal(BigInteger val) {
        if (val == null)
            return null;

        return stringToBigdecimal(bigIntegerToString(val));
    }

    public static BigDecimal stringToBigdecimal(String val) {
        if (isNullOrEmpty(val))
            return null;

        try {
            return newBigDecimal(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Float stringToFloat(String val) {
        if (isNullOrEmpty(val))
            return null;

        try {
            return Float.parseFloat(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String objectToSQLBlob(Object object) {
        ObjectOutput out;
        byte[] bytes = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            out = new ObjectOutputStream(bos);
            try {
                out.writeObject(object);
            } finally {
                out.flush();
            }
            bytes = bos.toByteArray();

            Blob blob = new SerialBlob(bytes);

            bytes = null;

//            return blobToString(blob);
            return null;
        } catch (IOException | SQLException e) {
            return null;
        }
    }

    public static Short integerToShort(Integer val) {
        return val.shortValue();
    }

    public static Integer stringToInteger(String val) {
        try {
            if (isNullOrEmpty(val))
                return null;
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static BigDecimal stringToBigDecimal(String val) {
        if (isNullOrEmpty(val)) {
            return null;
        }

        return newBigDecimal(val.replaceAll(",", "."));
    }

    public static Long stringToLong(String val) {
        if (val == null || val.isEmpty()) {
            return null;
        }

        return Long.parseLong(val);
    }

    public static String longToString(Long val) {
        if (val == null) {
            return null;
        }

        return val.toString();
    }

    public static String doubleToString(Double val) {
        if (val == null) {
            return null;
        }

        return val.toString();
    }

    public static BigInteger stringToBigInteger(String val) {
        if (isNullOrEmpty(val)) {
            return null;
        }

        return new BigInteger(val);
    }

    public static BigInteger longToBigInteger(Long val) {
        return BigInteger.valueOf(val);
    }

    public static String objectToString(Object obj) {
        if (obj != null && !obj.toString().trim().isEmpty()) {
            return obj.toString();
        }

        return null;
    }

    public static String dateObjectToString(Object obj) {
        if (obj != null && !obj.toString().trim().isEmpty()) {
            return dateToString((Date) obj, DEF_DD_MM_YYYY_DOT);
        }

        return null;
    }

    public static String characterToString(Character val) {
        if (val != null) {
            return val.toString();
        }
        return null;
    }

    public static String getUserFullName(String lastName, String firstName, String patronName) {
        StringBuilder fullName = new StringBuilder();

        if (!isNullOrEmpty(lastName)) {
            fullName.append(getFirstUpperString(lastName));
        }

        if (!isNullOrEmpty(firstName)) {
            fullName.append(" ").append(getFirstUpperString(firstName));
        }

        if (!isNullOrEmpty(patronName)) {
            fullName.append(" ").append(getFirstUpperString(patronName));
        }

        return fullName.toString();
    }

    public static String getUserShortFullName(String lastName, String firstName, String patronName) {
        StringBuilder fullName = new StringBuilder();

        if (!isNullOrEmpty(lastName)) {
            fullName.append(getFirstUpperString(lastName));
        }

        if (!isNullOrEmpty(firstName)) {
            fullName.append(" ").append(firstName.substring(0, 1).toUpperCase()).append(".");
        }

        if (!isNullOrEmpty(patronName)) {
            fullName.append(patronName.substring(0, 1).toUpperCase()).append(".");
        }

        return fullName.toString();
    }

    public static String textConcat(String delimeter, String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String anA : a) {
            if (!isNullOrEmpty(anA)) {
                if (!anA.equals("newline")) {
                    sb.append(anA);
                    sb.append(delimeter);
                } else {
                    sb.append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }

    public static String objectToJson(Object obj) {
        return (new Gson().toJson(obj));
    }

//    public static String getUserUnitialNameVDeadPerson(UBwTable obj) throws Exception {
//        if (obj != null) {
//            String fullName = obj.getLastName() + " " + obj.getFirstName().substring(0, 1) + ".";
//            if (!isNullOrEmpty(obj.getMiddleName())) {
//                return fullName + " " + obj.getMiddleName().substring(0, 1) + ".";
//            }
//            return fullName;
//        }
//        throw new Exception();
//    }

    /**
     * @return ServerName
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Double sum(List<? extends Number> numList) {
        Double result = 0.0;
        for (Number num : numList) {
            result += num.doubleValue();
        }
        return result;
    }

//    /**
//     * @param message;
//     * @return gsonResult;
//     * @author beljerin;
//     * @desc возвращет текст ошибки
//     */
//    public static GsonDecision getErrorMessage(String message) {
//        GsonDecision gsonResult = new GsonDecision();
//        gsonResult.setResult(Boolean.FALSE);
//        gsonResult.setMessage(message);
//        return gsonResult;
//    }
//
//    /**
//     * @param maket;
//     * @param exp;
//     * @param list;
//     * @return gsonResult;
//     * @author beljerin;
//     * @desc возвращет Макет и решения по добавленному стажу
//     */
//    public static GsonDecision gsonExpResult(GsonMaket maket, GsonExperienceConsider exp, List<GsonVEappMaketState> list) {
//        GsonDecision gsonResult = new GsonDecision();
//        gsonResult.setResult(true);
//        gsonResult.setMaket(maket);
//        gsonResult.setMessage(exp);
//        gsonResult.setActionList(list);
//        return gsonResult;
//    }

    /**
     * @param lang;
     * @return ResourceBundle;
     * @author beljerin
     * @desc Метод определяет язык интерфейса
     */
    private static ResourceBundle getLanguage(Lang lang) {
        switch (lang) {
            case Kz:
                return ResourceBundle.getBundle("strings_kz", new UTF8Control());
            case Ln:
                return ResourceBundle.getBundle("strings_ln", new UTF8Control());
            default:
                return ResourceBundle.getBundle("strings_ru", new UTF8Control());
        }
    }

    /**
     * @author beljerin
     * @desc Из даты в календарь
     */
    public static Calendar getCalendar(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(date);
            return cal;
        }
        return null;
    }

    public static String nvl(String val1, String val2) {
        if (isNullOrEmpty(val1))
            return val2;
        return val1;
    }

    public static <E> E nvl(E val1, E val2) {
        if (val1 == null)
            return val2;
        return val1;
    }

    public static String getValueOrEmpty(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str;
    }

    public static <X> X getValueOrEmptyGnric(X x) {
        if (x == null) {
            return (X) "";
        }
        return x;
    }

    // Webix не принимает ID = 0
    public static BigDecimal changeSFamRodId(BigDecimal id) {
        if (Objects.equals(id, BigDecimal.ZERO)) {
            return newBigDecimal(-1);
        }

        return id;
    }

    /**
     * @param osnId;
     * @param array;
     * @param eClass;
     * @return {boolean} ищет внутри массива
     * @author beljerin
     */
    @SafeVarargs
    public static <X> Boolean findIntoArrays(Object eClass, int osnId, X... array) {
        if (eClass.equals(int.class)) {
            int[][] castedArray = (int[][]) array;
            for (int[] anArray : castedArray) {
                for (int seArray : anArray) {
                    if (seArray == osnId) return true;
                }
            }
        } else if (eClass.equals(String.class)) {
            String[] anArray = (String[]) array;
            for (String seArray : anArray) {
                if (seArray.equals(String.valueOf(osnId))) return true;
            }
        }

        return false;
    }

    /**
     * @param osnId;
     * @param array;
     * @param eClass;
     * @return {boolean} ищет внутри массива
     * @author beljerin
     */
    @SafeVarargs
    public static <X> Boolean findIntoArrays(Object eClass, String osnId, X... array) {
        if (eClass.equals(String.class)) {
            String[] anArray = (String[]) array;
            for (String seArray : anArray) {
                if (seArray.equals(osnId)) return true;
            }
        }

        return false;
    }

//    /**
//     * @param summ;
//     * @return summ;
//     * @desc сумма с прописью
//     * @author beljerin;
//     */
//    public static String getStringSummByNumber(BigDecimal summ) {
//        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
//                RuleBasedNumberFormat.SPELLOUT);
//        return nf.format(summ);
//    }
//
//    /**
//     * @param date;
//     * @return dd.MMMM.yyyy or MMMM.yyyy;
//     * @author beljerin;
//     * @desc Метод возвращает имя месяца по дате
//     * @author beljerin;
//     */
//    public static String getCorrectFullDate(Date date, final String pattern) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, myDateFormatSymbols);
//        Calendar cal = getCalendar(date);
//        if (cal != null) {
//            String month = dateFormat.format(date);
//            int year = cal.get(Calendar.YEAR);
//            return month + " " + year + " г. ";
//        }
//        return null;
//    }

    /**
     * @param date;
     * @return dd.MMMM.yyyy or MMMM.yyyy;
     * @desc Метод возвращает имя месяца по дате
     * @author a.amanzhol;
     */
    public static String getCorrectFullDate(Date date, final String pattern, DateFormatSymbols myDateFormatSymbols) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, myDateFormatSymbols);
        Calendar cal = getCalendar(date);
        if (cal != null) {
            String month = dateFormat.format(date);
            int year = cal.get(Calendar.YEAR);
            return month + " " + year;
        }
        return null;
    }

    public static String[] substringByLengthArray(String value, int len) {
        if (!isNullOrEmpty(value)) {
            value = value.replaceAll("\n", "");// если не убрать переносы он не делит по len
            value = value.replaceAll("(.{" + len + "})", "$1@@");
            return value.split("\\@@");
        }
        return null;
    }

    /**
     * @param uPassword;
     * @return String;
     * @desc Хэширования по MD5 код;
     */
    public static String getMd5Apache(String uPassword) {
        return DigestUtils.md5Hex(uPassword);
    }

    /**
     * @param xmlCalendar ;
     * @desc метод возвращает дату из XML день в день
     * @author beljerin
     */
    public static String gTeXMLGregorianCalendar(XMLGregorianCalendar xmlCalendar) {
        if (xmlCalendar != null) {
            return dateToString(xmlCalendar.toGregorianCalendar().getTime());
        }
        return null;
    }

    /**
     * Генрит  HTML
     *
     * @param body
     * @return HTML String
     */
    public static String getHtmlSetBody(String body) {
        StringBuilder s = new StringBuilder("<!DOCTYPE html>\n")
                .append("<html Test=\"en\"> ")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title></title> ")
                .append("</head>")
                .append("<body>")
                .append(body)
                .append("</body>")
                .append("</html>");
        return s.toString();
    }

    public static List<String> getExtensions() {
        List<String> list = newArrayList();
        list.add("JPG");
        list.add("JPEG");
        list.add("GIF");
        list.add("PNG");
        return list;
    }

    public static String getExtensionsString(List<String> list) {
        String str = null;
        for (String ext : list) {
            str = getDelimetrForDic(3, ext);
        }

        return str;
    }

    /**
     * @desc Метод для делиметра справочник
     * @autor beljerin
     */
    public static String getDelimetrForDic(int type, String... strings) {
        String delimetr;
        switch (type) {
            case 1:
                delimetr = " - ";
                break;
            case 2:
                delimetr = " ";
                break;
            case 3:
                delimetr = ", ";
                break;
            default:
                delimetr = " ";
                break;
        }
        StringJoiner stringJoiner = new StringJoiner(delimetr);
        for (String string : strings) {
            stringJoiner.add(string);
        }
        return stringJoiner.toString();
    }

    public static <E> BigDecimal newBigDecimal(E e) {
        try {
            return new BigDecimal(String.valueOf(e));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static List<BigDecimal> removeItemFromListByList(List<BigDecimal> list, List<BigDecimal> subList) {
        List<BigDecimal> result = newArrayList();
        for (BigDecimal item : list) {
            if (!subList.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }


    public static boolean isLevOtd(GsonUsers user) {
        List<Long> cenIdList = newArrayList();
        cenIdList.add(38201L);
        cenIdList.add(38204L);
        cenIdList.add(38207L);
        if (user != null && user.getEappUserRoleList() != null) {
            for (GsonEappUserRole eappUserRole : user.getEappUserRoleList()) {
                if (cenIdList.contains(eappUserRole.getEappRoleId().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLevObl(GsonUsers user) {
        List<Long> cenIdList = newArrayList();
        cenIdList.add(38213L);
        cenIdList.add(38216L);
        if (user != null && user.getEappUserRoleList() != null) {
            for (GsonEappUserRole eappUserRole : user.getEappUserRoleList()) {
                if (cenIdList.contains(eappUserRole.getEappRoleId().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLevCen(GsonUsers user) {
        List<Long> cenIdList = newArrayList();
        cenIdList.add(38235L);
        cenIdList.add(38236L);
        cenIdList.add(38237L);
        if (user != null && user.getEappUserRoleList() != null) {
            for (GsonEappUserRole eappUserRole : user.getEappUserRoleList()) {
                if (cenIdList.contains(eappUserRole.getEappRoleId().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLevDep(GsonUsers user) {
        List<Long> cenIdList = newArrayList();
        cenIdList.add(38221L);
        cenIdList.add(38224L);
        cenIdList.add(38227L);
        if (user != null && user.getEappUserRoleList() != null) {
            for (GsonEappUserRole eappUserRole : user.getEappUserRoleList()) {
                if (cenIdList.contains(eappUserRole.getEappRoleId().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLevRsp(GsonUsers user) {
        List<Long> cenIdList = newArrayList();
        cenIdList.add(17001L);
        cenIdList.add(38201L);
        if (user != null && user.getEappUserRoleList() != null) {
            for (GsonEappUserRole eappUserRole : user.getEappUserRoleList()) {
                if (cenIdList.contains(eappUserRole.getEappRoleId().getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param users;
     * @desc Проверва фрот оффис ли юзер;
     */
    public static Boolean isFrontOfficeUser(GsonUsers users) {
        for (GsonEappUserRole s : users.getEappUserRoleList()) {
            if (findIntoArrays(int.class, s.getEappRoleId().getId().intValue(), DEF_FRONT_ARRAYS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param users;
     * @desc Проверка центр ли юзер;
     */
    public static Boolean isCenterUser(GsonUsers users) {
        for (GsonEappUserRole s : users.getEappUserRoleList()) {
            if (findIntoArrays(int.class, s.getEappRoleId().getId().intValue(), DEF_CENTER_ARRAYS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param users;
     * @desc Проверка центр ли юзер;
     */
    public static Boolean isFondUser(GsonUsers users) {
        for (GsonEappUserRole s : users.getEappUserRoleList()) {
            if (findIntoArrays(int.class, s.getEappRoleId().getId().intValue(), DEF_GFSS_ARRAYS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param users;
     * @desc Проверка центр ли юзер;
     */
    public static Boolean isPensRash(GsonUsers users) {
        for (GsonEappUserRole s : users.getEappUserRoleList()) {
            if (findIntoArrays(int.class, s.getEappRoleId().getId().intValue(), DEF_RESH_PENS)) {
                return true;
            }
        }
        return false;
    }

//    /**
//     * @param workbook;
//     * @param spreadsheet;
//     * @param empinfo;
//     * @desc выгрузка в excel;
//     */
//    public static File getApacheReport(XSSFWorkbook workbook, XSSFSheet spreadsheet, Map<String, Object[]> empinfo) throws IOException {
//        XSSFRow row;
//        XSSFCellStyle style = workbook.createCellStyle();
//        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//
//        //Iterate over data and write to sheet
//        Set<String> keyid = empinfo.keySet();
//        int rowid = 0;
//
//        for (String key : keyid) {
//            row = spreadsheet.createRow(rowid++);
//            Object[] objectArr = empinfo.get(key);
//            int cellid = 0;
//
//            for (Object obj : objectArr) {
//                Cell cell = row.createCell(cellid++);
//                cell.setCellValue((String) obj);
//                cell.setCellStyle(style);
//            }
//        }
//
//        File file = new File("NewExcel.xlsx");
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            workbook.write(fos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            keyid.clear();
//            workbook.close();
//            empinfo.clear();
//        }
//
//        return file;
//    }

    /**
     * @param nSum;
     * @desc Для гос гарантии;
     */
    public static String getDelimetredSummForGgos(String nSum, String pattern) {
        if (!isNullOrEmpty(nSum)) {
            Float k = Float.parseFloat(nSum);
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            return decimalFormat.format(k);
        }
        return null;
    }

//    /**
//     * @param personList;
//     * @param status;
//     * @throws WriterException ;
//     * @desc возвращает ЭЦП в баркоде
//     * @author Bekzhan
//     */
//    public static void getBarCodeByStatus(List<GsonSignPerson> personList, int status, HashMap<String, Object> parametersMap) throws Exception {
//        int signCnt = 1;
//        BufferedImage bf = null;
//        try {
//            for (GsonSignPerson personObj : personList) {
//                if (personObj.getPost() == status) {
//                    String[] signList = substringByLengthArray(personObj.getSign(), 1000);
//                    if (signList != null) {
//                        for (String sign : signList) {
//                            bf = getMatrixByString(sign);
//                            parametersMap.put("EAPP_1_QRCODE" + signCnt, bf);
//                            signCnt++;
//                        }
//                    }
//                }
//            }
//        } finally {
//            if (bf != null) {
//                bf.flush();
//            }
//        }
//    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    /**/
//    public static String getFullName(UBwTable deadPerson) {
//        return nvl(deadPerson.getSicId().getRn(), "") + " " + getUserFullName(deadPerson.getLastName(), deadPerson.getFirstName(), deadPerson.getMiddleName());
//    }
}