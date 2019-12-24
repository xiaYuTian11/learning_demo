package com.demo.base;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Date;

/**
 * java8 时间类
 *
 * @author TMW
 * @date 2019/12/24 10:20
 */
public class LocalDateTimeTest {

    @Test
    public void test01() {
        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime now2 = LocalDateTime.now();
        LocalDate of = LocalDate.of(2020, 11, 20);
        System.out.println(of);
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(of.isLeapYear());
        Clock clock = Clock.systemUTC();
        System.out.println(clock.getZone());
        Instant now3 = Instant.now();
        System.out.println(now3);
        System.out.println(Date.from(now3));
        Period between = Period.between(of, now);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
        String format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now2);
        System.out.println(format);
        String format1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").format(now2);
        System.out.println(format1);
        System.out.println("---------long毫秒值转换为日期---------");
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String longToDateTime = df.format(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(System.currentTimeMillis()),ZoneId.of("Asia/Shanghai")));
        System.out.println(longToDateTime);
    }

}
