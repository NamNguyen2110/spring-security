package com.practice.spring.security.common.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.*;

public class CommonUtils {

    public static int count(String str, char c) {
        if (isNullOrEmpty(str)) {
            return 0;
        }

        int dem = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                dem++;
            }
        }

        return dem;
    }

    public static String toLikeString(String s) {
        if (s != null) {
            s = "%" + s.trim() + "%";
            s = s.toLowerCase();
        }
        return s;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(Collection<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check min length of String
     *
     * @param s         : String
     * @param maxLength
     * @return Created by NQManh - 18/6/2019
     */
    public static boolean maxLength(String s, int maxLength) {
        return s.length() <= maxLength ? true : false;
    }

    /**
     * Check min length of String
     *
     */
    public static boolean minLength(String s, int minLength) {
        return s.length() >= minLength ? true : false;
    }

    /**
     * Check String only contain number

     */
    public static boolean isNumber(String s) {
        return s.matches("[0-9]+");
    }

    public static boolean isDigitWithDot(String cell) {

        try {
            Double.parseDouble(cell);
            return true;
        } catch (Exception e) {
            return false;
        }
//        if(cell == null || "".equals(cell.trim()))
//            return false;
//        int count = 0;
//        for (int i = 0; i < cell.length(); i++) {
//            if (cell.charAt(i) == '.') {
//                count++;
//                if (count > 1) {
//                    return false;
//                }
//            }
//        }
//
//        cell = cell.replaceAll("\\.", "0");
//        if(cell.charAt(0) == '-') {
//            cell = cell.replaceAll("-", "0");
//        }
//        return isNumber(cell);
    }

    public static void main(String args[]) {
        System.out.println(isDigitWithDot("anndjanskdja.smjkansdj"));
        System.out.println(isDigitWithDot("21216514.13216546"));
        System.out.println(isDigitWithDot("6.500000000000001E-4"));


        System.out.println(convertCellDigit("21216514.13216546"));
        System.out.println(convertCellDigit("21216514.13216546"));

        System.out.println(convertCellDigit("21216514.13686"));
        System.out.println(convertCellDigit("-2121212312313123123116514"));


        System.out.println(convertCellDigit("21E12"));
        System.out.println(convertCellDigit("1.98173058152535097597990"));


        System.out.println(String.format("%.15f", 1000000000000000.0));
        System.out.println(10000000000000.0);


        List<Integer> ints = new ArrayList<>();
        ints.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        Collections.shuffle(ints);
        System.out.println(ints);


        String str1 = "22.1234567891111111111111111111111";
        String str2 = "22.1234567891111111111111111111111";

        BigDecimal val1 = new BigDecimal(str1);
        BigDecimal val2 = new BigDecimal(str2);
        System.out.println("compare");
        System.out.println(val1.compareTo(val2));

        System.out.println(str1.equals(str2));
        System.out.println(str1.contentEquals(str2));

    }

    public static String toPlainString(Double value) {
        if (value == null)
            return "";
        BigDecimal big = new BigDecimal(value, MathContext.DECIMAL64);

//        return String.format("%.15f", value);
        String value1 = big.toPlainString();
        return value1;
    }

    public static String toPlainString(Long value) {
        if (value == null)
            return "";
        BigDecimal big = new BigDecimal(value);

//        return String.format("%.15f", value);
        return big.toPlainString();

    }

    public static String subLengthWithConfig(String data, Long length) {
        if (data == null) {
            return "";
        }
        if (data.length() < length) {
            return data;
        }

        return data.substring(0, length.intValue()); //cat = do dai database cho phep
    }


    public static String convertCellDigit(String cell) {
        if (cell == null || "".equals(cell)) {
            return "";
        }
        try {
            BigDecimal d = new BigDecimal(cell);
            DecimalFormat f = new DecimalFormat("###,###.##");
            cell = f.format(d);
//            if(cell.indexOf(".") == -1){
//                return cell + ".0";
//            }
        } catch (Exception e) {

        }
        return cell;
    }

    public static Object copy(Object fromBean) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder out = new XMLEncoder(bos);
        out.writeObject(fromBean);
        out.close();
        ByteArrayInputStream bis = new
                ByteArrayInputStream(bos.toByteArray());
        XMLDecoder in = new XMLDecoder(bis);
        Object toBean = in.readObject();
        in.close();
        return toBean;
    }


    /**
     * Check String only contain alphanumeric character
     *
     */
    public static boolean isAlphanumeric(String s) {
        return s.matches("[a-zA-Z]+");
    }

    /**
     * Check String only contain number, alphanumeric, underscore
     *
     */
    public static boolean isNumberOrAlphanumeric(String s) {
        return s.matches("[a-zA-Z0-9_]+");
    }

    public static Long parseLongExcel(String data) {
        if (data == null || "".equals(data)) {
            return 0l;
        }
        try {
            return Long.parseLong(data);
        } catch (Exception e) {
            return 0l;
        }
    }

    //    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
    public static Double parseDoubleExcel(String data) {
        if (data == null || "".equals(data)) {
            return 0d;
        }
        try {
            return Double.parseDouble(data);
        } catch (Exception e) {
            return 0d;
        }
    }

    public static boolean isDouble(String... datas) {
        for (String data : datas) {
            if (data == null || "".equals(data)) {
                return false;
            }
            try {
                Double.parseDouble(data);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOneOfDouble(String... datas) {
        for (String data : datas) {
            try {
                Double.parseDouble(data);
                return true;
            } catch (Exception e) {

            }
        }
        return false;
    }

    public static double parseDoubleExcelDemo(String data) {
        if (data == null || "".equals(data)) {
            return 0d;
        }
        try {
            return Double.parseDouble(data);
        } catch (Exception e) {
            return 0d;
        }
    }

    public static BigDecimal parseBigDecimalExcel(String data) {
        if (data == null || "".equals(data)) {
            return new BigDecimal("0");
        }
        try {
            return new BigDecimal(data, MathContext.DECIMAL64);
        } catch (Exception e) {
            return new BigDecimal("0");
        }
    }

    public static Integer parseIntegerExcel(String data) {
        if (data == null || "".equals(data)) {
            return 0;
        }
        try {
            return Integer.parseInt(data);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean isNullObject(Object obj) {
        return obj == null;
    }

    public static boolean isNullOrEmpty(String name) {
        return name == null || "".equals(name.trim());
    }

    public static boolean isEmptyObject(Object value) {
        if (value == null)
            return true;
        if (value instanceof String)
            return CommonUtils.isNullOrEmpty((String) value);
        return false;
    }

    public static String lowerFirstChar(String simpleName) {
        String first = simpleName.substring(0, 1);
        return first.toLowerCase() + simpleName.substring(1);
    }

    public static String safeToString(Object oldValue) {
        if (oldValue == null) {
            return "";
        }
        if (oldValue instanceof String) {
            String data = (String) oldValue;
            return data.trim();
        }
        return String.valueOf(oldValue);
    }

    public static String getTableName(Class<?> c) {
        String className = c.getSimpleName();
        return toTableName(className);
    }

    public static Long safeToLong(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Long) {
            Long data = (Long) object;
            return data.longValue();
        }
        if (object instanceof BigInteger) {
            return ((BigInteger) object).longValue();
        }

        if (object instanceof Integer) {
            return ((Integer) object).longValue();
        }

        if (object instanceof BigDecimal) {
            return ((BigDecimal) object).longValue();
        }

        if (object instanceof Boolean) {
            return ((Boolean) object) ? 1L : 0L;
        }

        return null;

    }

    private static void convertAndAppendTo(StringBuilder modifiedString, char currentChar,
                                           String separator) {
        if (Character.isUpperCase(currentChar)) {
            modifiedString.append(separator);
        }
        currentChar = Character.toUpperCase(currentChar);
        modifiedString.append(currentChar);
    }

    public static String toTableName(String s) {
        StringBuilder modifiedString = new StringBuilder();
        char[] sToArray = s.toCharArray();
        convertAndAppendTo(modifiedString, sToArray[0], "");

        for (int i = 1; i < sToArray.length; i++) {
            convertAndAppendTo(modifiedString, sToArray[i], "_");
        }
        return modifiedString.toString();
    }

    /**
     * Kiểm tra độ dài String theo byte
     *
     * @param s   : String cần kiểm tra
     * @param min : Độ dài nhỏ nhất
     * @param max : Độ dài lớn nhất
     * @return
     * @see :CreatedBy NQManh - 26/6/2019
     */
    public static boolean checkLengthUTF8(String s, int min, int max) {
        return (s.getBytes().length >= min && s.getBytes().length <= max);
    }
//    public static boolean safeToBoolean(Object object){
//        if (object ==null){
//            return false;
//        }
//        Long result = safeToLong(object);
//        if (result ==1){
//            return  true;
//        }
//        return false;
//    }

    public static boolean compareString(String a, String b) {
        a = a == null ? "" : a.trim();
        b = b == null ? "" : b.trim();
        return a.equals(b);
    }
}
