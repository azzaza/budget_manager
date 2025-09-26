package com.recru1_t.user;

public class User {
    private int shouldWorkHours;
    private int holidyPerMonth;
    private int availableHoliday;
    private int overWorkHours;



    public User(){


    }


    public void endMonth(){
        availableHoliday += holidyPerMonth;
    }


}
