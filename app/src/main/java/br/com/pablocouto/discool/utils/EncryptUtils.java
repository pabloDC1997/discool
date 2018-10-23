package br.com.pablocouto.discool.utils;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Pablo on 08/07/2017.
 */


public class EncryptUtils {
    public static final String DEBUG_TAG = EncryptUtils.class.getName();

    public static String encrypt(String param) {
        try {

            final String salt = "d9f19bfc32c85d3116dd1925732bb9c3";
            final String s = salt + param;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            return new BigInteger(1,m.digest()).toString(16);

        } catch(Exception e ) {
            Log.e(DEBUG_TAG, e.getMessage());
            return null;
        }
    }
}
