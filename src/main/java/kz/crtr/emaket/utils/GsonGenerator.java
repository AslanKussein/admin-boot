package kz.crtr.emaket.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bekzhan
 */
public class GsonGenerator {

    public static class FieldGson {

        private String fieldName;
        private String fieldType;
        private Field field;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }
    }

    private static Boolean isNullOrEmpty(String value) {
        return (value == null || value.trim().isEmpty());
    }

    private static String getFirstUpper(String value) {
        if (!isNullOrEmpty(value)) {
            return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
        }
        return null;
    }

    private static String getSpace(int cnt) {
        String space = "";

        for (int i = 0; i < cnt; i++) {
            space = space + " ";
        }

        return space;
    }

    private static String getNewLine(int cnt) {
        String line = "";

        for (int i = 0; i < cnt; i++) {
            line = line + "\n";
        }

        return line;
    }

    private static String getGsonListType(Field field) {
        String listFieldClass = field.getGenericType().toString();
        String fieldType = listFieldClass.substring(listFieldClass.lastIndexOf('.') + 1).replace(">", "");
        return "List<Gson" + fieldType + ">";
    }

    private static String getGsonFieldType(Field field) {

        String fieldType = field.getType().getSimpleName();

        switch (fieldType) {
            case "short":
            case "Short":
                return "Short";
            case "Serializable":
            case "byte[]":
                return "byte[]";
            case "BigDecimal":
                return "BigDecimal";
            case "BigInteger":
                return "BigInteger";
            case "Integer":
                return "Integer";
            case "long":
            case "Long":
                return "Long";
            case "Date":
            case "String":
                return "String";
            case "Character":
                return "Character";
            case "Boolean":
                return "Boolean";
            case "List":
                return getGsonListType(field);
            default:
                return "Gson" + fieldType;
        }
    }

    private static List<FieldGson> getObjectFields(Object obj) {
        List<FieldGson> fields = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            if (fieldName.indexOf("_persistence") != 0 && !fieldName.equals("serialVersionUID")) {
                FieldGson fieldGson = new FieldGson();
                fieldGson.setFieldName(fieldName);
                fieldGson.setFieldType(fieldType);
                fieldGson.setField(field);
                fields.add(fieldGson);
            }
        }
        return fields;
    }

    public static String getGsonWrapper(String className, List<FieldGson> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        StringBuilder sBuffer = new StringBuilder();
        sBuffer.append(String.format("public static Gson%1$2s wrapToGson%1$2s(%1$2s obj) {", className));
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("if (obj != null) {");
        sBuffer.append(getNewLine(1));
        sBuffer.append(String.format(getSpace(8) + "Gson%1$2s gson = new Gson%1$2s();", className));
        sBuffer.append(getNewLine(1));

        for (FieldGson field : list) {
            String wrapSetter;

            if (!field.getFieldType().equals("Date")) {
                if (field.getFieldType().indexOf("Gson") == 0) {
                    wrapSetter = getSpace(8) + "gson.set%1$2s(wrapTo%2$2s(obj.get%1$2s()));";
                    wrapSetter = String.format(wrapSetter, getFirstUpper(field.getFieldName()), field.getFieldType());
                } else {
                    wrapSetter = getSpace(8) + "gson.set%1$2s(obj.get%1$2s());";
                    wrapSetter = String.format(wrapSetter, getFirstUpper(field.getFieldName()));
                }
            } else {
                wrapSetter = getSpace(8) + "gson.set%1$2s(dateToString(obj.get%1$2s()));";
                wrapSetter = String.format(wrapSetter, getFirstUpper(field.getFieldName()));
            }

            sBuffer.append(wrapSetter);
            sBuffer.append(getNewLine(1));
        }

        sBuffer.append(getSpace(8)).append("return gson;");
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("}");
        sBuffer.append(getNewLine(1));
        sBuffer.append(getNewLine(1));
        sBuffer.append(getSpace(4)).append("return null;");
        sBuffer.append(getNewLine(1));
        sBuffer.append("}");

        return sBuffer.toString();
    }

    public static String getGsonWrapperList(String className) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder sBuffer = new StringBuilder();
        String wrapperText
                = "public static List<Gson%1$2s> wrapToGson%1$2sList(List<%1$2s> list) {\n"
                + getSpace(4) + "List<Gson%1$2s> gsonList = new ArrayList<>();        \n"
                + getSpace(4) + "for (%1$2s o : list) {\n"
                + getSpace(8) + "gsonList.add(wrapToGson%1$2s(o));\n"
                + getSpace(4) + "}\n"
                + getSpace(4) + "return gsonList;\n"
                + "}";

        sBuffer.append(String.format(wrapperText, className));
        return sBuffer.toString();
    }

    private static String getGsonClass(String className, List<FieldGson> list) {

        StringBuilder sBuffer = new StringBuilder();
        sBuffer.append(String.format("public class Gson%1$2s {", className));
        sBuffer.append(getNewLine(2));

        for (FieldGson field : list) {
            String propertyLine = getSpace(4) + "private " + getGsonFieldType(field.getField()) + " " + field.getFieldName() + ";";
            sBuffer.append(propertyLine);
            sBuffer.append(getNewLine(1));
        }

        for (FieldGson field : list) {
            String getterSetter
                    = getSpace(4) + "public %1$2s get%2$2s() { \n"
                    + getSpace(8) + "return %3$2s; \n"
                    + getSpace(4) + "} \n"
                    + "\n"
                    + getSpace(4) + "public void set%2$2s(%1$2s %3$2s) {\n"
                    + getSpace(8) + "this.%3$2s = %3$2s;\n"
                    + getSpace(4) + "}";

            getterSetter = String.format(getterSetter, getGsonFieldType(field.getField()), getFirstUpper(field.getFieldName()), field.getFieldName());

            sBuffer.append(getNewLine(1));
            sBuffer.append(getterSetter);
            sBuffer.append(getNewLine(1));
        }

        sBuffer.append(getNewLine(1));
        sBuffer.append("}");
        return sBuffer.toString();
    }

    public static String generateGsonClasses(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String className = obj.getClass().getSimpleName();
        List<FieldGson> list = getObjectFields(obj);

        StringBuilder sBuffer = new StringBuilder();
        String gsonClass = getGsonClass(className, list);
        sBuffer.append(gsonClass);
        sBuffer.append(getNewLine(2));
        sBuffer.append(getGsonWrapper(className, list));
        sBuffer.append(getNewLine(2));
        sBuffer.append(getGsonWrapperList(className));
        return sBuffer.toString();
    }
}
