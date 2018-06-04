package rh.study.jdk8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * java.time.* 新日历类
 */
public class NewDateClassTest {

    public static void main(String[] args) {
        /**
         * LocalDate 只提供年月日信息
         */
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.isLeapYear());

        /**
         * LocalTime 提供时分秒
         */
        System.out.println("========= localTime ============");
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.getMinute());

        /**
         * LocalDateTime 提供年月日时分秒
         */
        LocalDateTime localDateTime = LocalDateTime.now();
    }
}
