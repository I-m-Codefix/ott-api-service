package kr.imcf.ott.common.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String now(String pattern){
        return DateTime.now().toString(pattern);
    }

    public static String now(){
        return DateTime.now().toString("yyyy.MM.dd HH:mm:ss");
    }

    public static String dateFomat(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

}
