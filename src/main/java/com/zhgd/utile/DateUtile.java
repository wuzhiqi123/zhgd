package com.zhgd.utile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtile {
    //获取（yyyy-MM-dd HH:mm:ss）时间格式
    public static String getDateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        return str ;
    }

    //获取（yyyy-MM-dd HH:mm:ss）时间格式
    public static String getDateString(Date date){
        /*Date date = new Date();*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        return str ;
    }

    //获取（yyyy-MM-dd）时间格式
    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = simpleDateFormat.format(date);
        return str ;
    }

    //获取（yyyy/MM/dd）时间格式
    public static String getDatexiegang(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String str = simpleDateFormat.format(date);
        return str ;
    }
}
