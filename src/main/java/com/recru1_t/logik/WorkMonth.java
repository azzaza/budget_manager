package com.recru1_t.logik;

import java.util.List;

public class WorkMonth {
    private int month;
    private int year;
    private int totalWorkHours;
    private int totalWorkMinutes;
    private List<WorkDay> workDays;
    private int totalWorkHoursBefore;
    private int totalWorkMinutesBefore;


    public WorkMonth(int month, int year, List<WorkDay> workDays) {
        this.month = month;
        this.year = year;
        this.workDays = workDays;
        calculateTotalWorkTime();
    }


    public int getMonth() {
        return month;
    }   
    public int getYear() {
        return year;
    }

    public int getTotalWorkHours() {
        return totalWorkHours;
    }
    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void addWorkDay(WorkDay workDay) {
        workDays.add(workDay);
        calculateTotalWorkTime();
    }

    public void removeWorkDay(WorkDay workDay) {
        workDays.remove(workDay);
        calculateTotalWorkTime();
    }

    private void calculateTotalWorkTime() {
        totalWorkHours = 0;
        totalWorkMinutes = 0;
        for (WorkDay day : workDays) {
            totalWorkHours += day.getWrokHours();
            totalWorkMinutes += day.getWorkMinutes();
        }
        // Convert total minutes to hours and minutes
        totalWorkHours += totalWorkMinutes / 60;
        totalWorkMinutes = totalWorkMinutes % 60;
    }


}
