package com.recru1_t.logik;

import java.time.LocalDate;

public class WorkDay {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int wrokHours;
    private int workMinutes;

    private int overTimeHours;
    private int ovetTimeMinutes;

    private Absence absence = Absence.NONE;


    private LocalDate date;


    public WorkDay(Absence absence, LocalDate date) {
        this.absence = absence;
        this.date = date;
        this.startHour = 0;
        this.startMinute = 0;
        this.endHour = 0;
        this.endMinute = 0;
        this.wrokHours = 0;
        this.workMinutes = 0;
        this.overTimeHours = 0;
        this.ovetTimeMinutes = 0;
        
    }

    public WorkDay(int startHour, int startMinute, int endHour, int endMinute,LocalDate date) {
      
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.date = date;
        calculateWorkTime();
    }


    private void calculateWorkTime() {
        int totalStartMinutes = startHour * 60 + startMinute;
        int totalEndMinutes = endHour * 60 + endMinute;
        int totalWorkMinutes = totalEndMinutes - totalStartMinutes;

        wrokHours = totalWorkMinutes / 60;
        workMinutes = totalWorkMinutes % 60;
    }
    

    public int getEndHour() {
        return endHour;
    }
    public int getEndMinute() {
        return endMinute;
    }
    public int getStartHour() {
        return startHour;
    }
    public int getStartMinute() {
        return startMinute;
    }
    public int getWrokHours() {
        return wrokHours;
    }
    public int getWorkMinutes() {
        return workMinutes;
    }
    public LocalDate getDate() {
        return date;
    }

    public Absence getAbsence() {
        return absence;
    }
    

    public int getOverTimeHours() {
        return overTimeHours;
    }
    public int getOvetTimeMinutes() {
        return ovetTimeMinutes;
    }
    

    
}
