package kr.imcf.ott.timeTest;

import kr.imcf.ott.common.util.TimeUtils;
import org.junit.Assert;
import org.junit.Test;

public class JodaTimeTest {

    @Test
    public void now(){
        String joda1 = TimeUtils.now("yyyy.MM.dd HH:mm:ss");
        String joda2 = TimeUtils.now();

        System.out.println(joda1);
        System.out.println(joda2);

        Assert.assertEquals(joda1, joda2);
    }

}
