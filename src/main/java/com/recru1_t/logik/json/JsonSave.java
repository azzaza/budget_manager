
package com.recru1_t.logik.json;

import java.io.File;
import java.text.DateFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.recru1_t.logik.WorkMonth;

public class JsonSave {
    ObjectMapper mapper = new ObjectMapper();
    String filePath = "files/%s/%s.json";
    DateFormat df = DateFormat.getDateInstance();

    public JsonSave() {
        SimpleModule  monthModule = new SimpleModule();
        monthModule.addDeserializer(WorkMonth.class, new MonthDeserializer());
        mapper.registerModule(new JavaTimeModule());
        SimpleModule  dayModule = new SimpleModule();
        dayModule.addDeserializer(com.recru1_t.logik.WorkDay.class, new DayDeserializer());
        mapper.registerModule(dayModule);
        mapper.registerModule(monthModule);
    }


    public void saveWorkMonth(WorkMonth workMonth) {
        try {
            String dirPath = String.format("files/%s", workMonth.getYear());
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fullPath = filePath.formatted(workMonth.getYear(), workMonth.getMonth());
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fullPath), workMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public WorkMonth loadWorkMonth(int month, int year) {
        try {
            String fullPath = filePath.formatted(year, month);
            File file = new File(fullPath);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<WorkMonth>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
