package cn.willow.demo.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * @author willow
 * @date 2024/6/28
 */
public class TimeUtil {
    public static void main(String[] args) {
//        //瞬时
//        {
//            Instant instant = Instant.now();
//            System.out.println(instant);
//        }
//        //时间
//        {
//            LocalTime localTime = LocalTime.now();
//            System.out.println(localTime);
//        }
//        //日期
//        {
//            LocalDate localDate = LocalDate.now();
//            System.out.println(localDate);
//        }
//        //日期+时间
//        {
//            LocalDateTime localDateTime = LocalDateTime.now();
//            System.out.println(localDateTime);
//        }
//        //时区
//        {
//            ZonedDateTime zonedDateTime = ZonedDateTime.now();
//            System.out.println(zonedDateTime);
//        }
//        //周期
//        {
//            Period period = Period.between(LocalDate.of(2020, 1, 1), LocalDate.now());
//            System.out.println(period.getYears());
//        }
//        //时长
//        {
//            Duration duration = Duration.ofDays(1);
//            System.out.println(duration.getSeconds());
//        }
//        //时间间隔
//        {
//            Date time1 = new Date(124, 5, 14, 0, 0, 0);
//            System.out.println(time1);
//            Date now = new Date(124, 5, 15, 0, 0, 0);
//            System.out.println(now);
//            Duration duration = Duration.ofMillis(now.getTime() - time1.getTime());
//            System.out.println(duration.toMinutes());
//        }
//        //时间字符串转对象
//        {
//            String dateString = "2024-06-27 23:00:00";
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
//            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//            System.out.println(date);
//        }
        //时间计算
        {
            String dateString = "2024-06-27 23:00:00";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
            System.out.println(localDateTime.plusMinutes(-10));

            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Date tenMinutesAgo = addMinutes(date, -10);
            System.out.println(tenMinutesAgo);
        }
    }

    /**
     * 按分钟偏移后的时间
     * @param date 时间
     * @param minutesOffset 分钟偏移量
     * @return 按分钟偏移后的时间
     */
    private static Date addMinutes(Date date, int minutesOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutesOffset);
        return calendar.getTime();
    }
}
