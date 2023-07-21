package com.kwy.management.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author haoy
 * @description
 * @date 2023/7/20 21:22
 */
public class NumberConverterUtil {
    private static final String[] CN_NUMERIC = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] CN_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万"};
    private static final String CN_INTEGER = "元整";
    private static final String CN_TEN_CENTS = "角";
    private static final String CN_CENTS = "分";

    public static String convertToChineseNumber(double number) {
        BigDecimal bd = new BigDecimal(number);
        long integerPart = bd.setScale(0, BigDecimal.ROUND_DOWN).longValue();
        int decimalPart = bd.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue() % 100;

        StringBuilder result = new StringBuilder();
        result.append(convertToChineseNumber(integerPart));

        if (decimalPart > 0) {
            result.append(convertToChineseNumber(decimalPart / 10)).append(CN_TEN_CENTS);
            result.append(convertToChineseNumber(decimalPart % 10)).append(CN_CENTS);
        } else {
            result.append(CN_INTEGER);
        }

        return result.toString();
    }

    private static String convertToChineseNumber(long number) {
        if (number == 0) {
            return CN_NUMERIC[0];
        }

        StringBuilder result = new StringBuilder();
        int unitIndex = 0;

        while (number > 0) {
            int digit = (int) (number % 10);
            if (digit == 0) {
                if (unitIndex == 4 || unitIndex == 8) {
                    result.insert(0, CN_UNIT[unitIndex]);
                }
            } else {
                String digitStr = CN_NUMERIC[digit];
                String unitStr = CN_UNIT[unitIndex];
                result.insert(0, digitStr + unitStr);
            }

            unitIndex++;
            number /= 10;
        }

        return result.toString();
    }

    // 示例用法
    public static void main(String[] args) {
        double number1 = 184400.32;
        double number2 = 184400.00;

        String chineseNumber1 = convertToChineseNumber(number1);
        String chineseNumber2 = convertToChineseNumber(number2);

        System.out.println(chineseNumber1);
        System.out.println(chineseNumber2);
    }
}
