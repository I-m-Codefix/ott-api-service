package kr.imcf.ott.common.util;

public class AsciiUtils {

    public static boolean isKorean(String str) {
        if (str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
            return true;
        else
            return false;
    }
}
