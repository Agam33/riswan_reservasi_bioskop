package com.ra.bioskop.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {

    public static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-+]" +
            "(.[_A-Za-z0-9-]+)@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)" +
            "(.[A-Za-z]{2,})$";

    /*
       @Param email
       @return true
    */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*
        Random Identifier result :
        [0] -> time_low
        [1] -> time_mid
        [2] -> time_hi_and_version
        [3] -> clock_seq_hi_and_res
        [4] -> node
     */
    public static String[] randomIdentifier(String s) {
        byte[] b = s.getBytes();
        UUID uuid = UUID.nameUUIDFromBytes(b);
        return uuid.toString().split("-");
    }
}
