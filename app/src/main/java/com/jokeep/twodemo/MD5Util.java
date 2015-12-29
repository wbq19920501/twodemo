package com.jokeep.twodemo;

import java.security.MessageDigest;

public class MD5Util {

    private static final String MIXER = "55C2780050E9820BE7FCEFB7F59E1E811EF342F71240D9EFAF5423343ED9A560F7A8C746C08706C6027C7DCE33CEBF2C2A256AC90CE71AE76A851E50CB7CEEBD";
    private static final String SALT = "35ECCA9337CB4A6A354148113BED1932";

    public static String calcMD5(String md5Str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(md5Str.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            String temp = "";
            for (byte b : bytes) {
                temp = Integer.toHexString(b & 0XFF);
                sb.append(temp.length() == 1 ? "0" + temp : temp);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPasswd(String rawPasswd) {
        StringBuffer sb = new StringBuffer();
        sb.append(MIXER.subSequence(0, 16));
        sb.append(rawPasswd);
        sb.append(MIXER.substring(16, 32));
        sb.append(rawPasswd);
        sb.append(MIXER.substring(32, 64));
        sb.append(rawPasswd);
        sb.append(MIXER.substring(64, 128));
        sb.append(calcMD5(sb.toString())).append(SALT);
        return calcMD5(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(getPasswd("11111111"));
    }
}
