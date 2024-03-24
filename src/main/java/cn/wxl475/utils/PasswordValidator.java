package cn.wxl475.utils;

import java.util.regex.Pattern;

/**
 * 密码校验器。
 */
public class PasswordValidator {
    /**
     * 密码由数字和下划线组成，且大于等于8个字符，小于等于16个字符。
     */
    public static boolean isCharacterAndNumber(String password) {
        String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return Pattern.matches(pattern, password);
    }

}


