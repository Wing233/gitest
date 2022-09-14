import org.junit.Test;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/7 18:52
 */


public class TestCommonList {

    @Test
    public void testCalendar() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -1);
        int i = instance.get(Calendar.DATE);
        System.out.println(i);
    }

    @Test
    public void testSubstring() {
        System.out.println("2022年09月13日 10:18:32".substring(0,11));
    }

}
