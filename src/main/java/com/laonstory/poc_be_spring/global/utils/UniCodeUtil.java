package com.laonstory.poc_be_spring.global.utils;

import com.laonstory.poc_be_spring.global.dto.DateDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UniCodeUtil {


    public static String UnicodeToString(String unicode){
        StringBuffer str = new StringBuffer();

        char ch = 0;
        for( int i= unicode.indexOf("\\u"); i > -1; i = unicode.indexOf("\\u") ){
            ch = (char)Integer.parseInt( unicode.substring( i + 2, i + 6 ) ,16);
            str.append( unicode.substring(0, i) );
            str.append( String.valueOf(ch) );
            unicode = unicode.substring(i + 6);
        }
        str.append( unicode );

        return str.toString();





    }
    public static String StringToUniCode(String unicode){
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < unicode.length(); i++) {
            if(((int) unicode.charAt(i) == 32)) {
                str.append(" ");
                continue;
            }
            str.append("\\u");
            str.append(Integer.toHexString((int) unicode.charAt(i)));

        }

        return str.toString();





    }

}
