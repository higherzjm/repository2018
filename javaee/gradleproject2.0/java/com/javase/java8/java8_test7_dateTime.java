package com.javase.java8;

import org.junit.Test;

import java.time.*;

/**
 * Created by zjm on 2017/7/23.
 */
public class java8_test7_dateTime {

    //Clock
    @Test
    public  void  test1(){
        final Clock clock = Clock.systemUTC();
        System.out.println( clock.instant() );
        System.out.println( clock.millis() );
    }
    //LocalTime  LocalDate
    @Test
    public  void  test2(){
        final Clock clock = Clock.systemUTC();
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now( clock );

        System.out.println( date );
        System.out.println( dateFromClock );

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now( clock );

        System.out.println( time );
        System.out.println( timeFromClock );
    }

    //LocalDateTime
    @Test
    public  void  test3(){
        final Clock clock = Clock.systemUTC();
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );

        System.out.println( datetime );
        System.out.println( datetimeFromClock );
    }

    //ZonedDateTime
    @Test
    public void  test4(){
        final Clock clock = Clock.systemUTC();
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );

        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );
    }
}
