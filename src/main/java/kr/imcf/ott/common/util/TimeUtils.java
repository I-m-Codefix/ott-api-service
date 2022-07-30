package kr.imcf.ott.common.util;

import org.joda.time.DateTime;

public class TimeUtils {

    public static String now(String pattern){
        return DateTime.now().toString(pattern);
    }

    public static String now(){
        return DateTime.now().toString("yyyy.MM.dd HH:mm:ss");
    }


}
