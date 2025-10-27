package com.recru1_t.ui.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class WeekCalc {
    

    public WeekCalc() {
    }


    public  List<Integer> getWeeksInMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate firstDay = ym.atDay(1);
        LocalDate lastDay = ym.atEndOfMonth();


        // Map week number -> start date (Monday)
         List<Integer> weeks = new ArrayList<>();
        LocalDate current = firstDay.with(DayOfWeek.MONDAY);
        if (current.isBefore(firstDay)) {
            current = current.plusWeeks(1);
        }
        while (!current.isAfter(lastDay)) {
            weeks.add(current.get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR));
            current = current.plusWeeks(1);
        }
        return weeks;
    }
}
