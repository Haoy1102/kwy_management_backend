package com.kwy.management.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author haoy
 * @description
 * @date 2023/7/8 15:01
 */
public class OrderNumberGenerator {
    private static final String DATE_FORMAT = "yyyyMMddHH";
    private static final int RANDOM_CODE_LENGTH_2 = 2;
    private static final int RANDOM_CODE_LENGTH_3 = 3;

    public static String generateOrderNumber(String number) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateTime = dateFormat.format(new Date());

        String randomCode2 = generateRandomCode(RANDOM_CODE_LENGTH_2);
        String randomCode3 = generateRandomCode(RANDOM_CODE_LENGTH_3);

        // Generate a unique serial number or use your own logic
        String serialNumber = generateSerialNumber(number);

        return dateTime + randomCode2 + serialNumber + randomCode3;
    }

    private static String generateRandomCode(int length) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    private static String generateSerialNumber(String number) {
        // Implement your own logic to generate a unique serial number
        // For example, you can use a database or a counter to generate a unique number
        // This is just a placeholder
        return number;
    }
}
