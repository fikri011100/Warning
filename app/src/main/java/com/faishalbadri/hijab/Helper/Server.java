package com.faishalbadri.hijab.Helper;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by faishal on 9/14/17.
 */

public class Server {

    public static String BASE_URL = "http://api.santriprogrammer.com/hijab/library/";
    public static String BASE_IMG = "http://api.santriprogrammer.com/hijab/img/";
    public static String imgus = "https://i.ytimg.com/vi/";
    public static String VIDEO_YT = "https://www.youtube.com/watch?v=";
    public static String lanjut = "/mqdefault.jpg";
    public static String YT_CODE = "AIzaSyAZtqvIpixBElGAIewxDbfOABF0V35TWTY";

    public static boolean isEmpty(EditText editText){
        /*jika banyak huruf lebih dari 0*/
        if (editText.getText().toString().trim().length() > 0){
            /*tidak dikembalikan*/
            return false;
        }else {
            /*kembalikan*/
            return true;
        }
    }

    /*fungsi untuk menampilkan pesan*/
    public static void pesan (Context c, String msg){
        Toast.makeText(c,msg, Toast.LENGTH_LONG).show();
    }

    //untuk cek validasi email
    public static boolean isEmailValid(EditText email) {
        boolean isValid = false;
        String expression = "\"/^\\\\w+[\\\\+\\\\.\\\\w-]*@([\\\\w-]+\\\\.)*\\\\w+[\\\\w-]*\\\\.([a-z]{2,4}|\\\\d+)$/i\";";
        CharSequence inputStr = email.getText().toString();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}

