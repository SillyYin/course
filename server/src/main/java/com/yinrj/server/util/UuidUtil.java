package com.yinrj.server.util;

import java.util.UUID;

/**
 * @author Yin
 * @date 2020/12/27
 */
public class UuidUtil {
    public static final String[] CHARS = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    /**
     * 获取短UUID
     * 短id是将32位id转为62进制8位id。
     * 原理是将uuid转为10进制，再对62取余
     * @return
     */
    public static String getShortUuid() {
        StringBuilder stringBuffer = new StringBuilder();
        String uuid = UuidUtil.getUuid();
        for (int i = 0; i < 8; ++i) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            // x对62取余
            stringBuffer.append(CHARS[x % 0x3E]);
        }
        return stringBuffer.toString();
    }

    private static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
