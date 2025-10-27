package com.recru1_t.logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.recru1_t.logik.json.JsonSave;

public class DataSaver {

    private final Map<Integer, WorkMonth> workMonths =  new HashMap<>();

    public DataSaver() {
    }   


    public void addWorkDay(WorkDay workDay) {
        int month = workDay.getDate().getMonthValue();
        int year = workDay.getDate().getYear();
        int key = year * 100 + month; // Unique key for each month-year combination
        WorkMonth workMonth = workMonths.get(key);
        if (workMonth == null) {
            workMonth = new WorkMonth(month, year, new ArrayList<>());
            workMonths.put(key, workMonth);
        }
        workMonth.addWorkDay(workDay);
        JsonSave jsonSave = new JsonSave();
        jsonSave.saveWorkMonth(workMonth);
    }

    public WorkMonth getWorkMonth(int month, int year) {
        int key = year * 100 + month;
        if (workMonths.containsKey(key)) {
            return workMonths.get(key);
        } else {
            JsonSave jsonSave = new JsonSave();
            WorkMonth loadedMonth = jsonSave.loadWorkMonth(month, year);
            if (loadedMonth != null) {
                workMonths.put(key, loadedMonth);
            }
            return loadedMonth;
        }
    }
}
