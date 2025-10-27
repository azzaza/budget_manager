package com.recru1_t.ui.helper;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

import com.recru1_t.logik.WorkDay;
import com.recru1_t.logik.WorkMonth;

public class WeekTableView extends GridPane {

    private final int month;
    private final int year;
    private WorkMonth workMonth;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    // German weekday labels as in your image
    private static final String[] WEEKDAYS = {"Mo", "Di", "Mi", "Do", "Fr", "Sa"};

    public WeekTableView(int month, int year, WorkMonth workMonth) {
        this.month = month;
        this.year = year;
        this.workMonth = workMonth;

        setGridLinesVisible(true);
        setAlignment(Pos.CENTER);
        getStyleClass().add("week-table");

        buildTable();
    }

    private void buildTable() {
        // === Header Row ===
        WeekCalc weekCalc = new WeekCalc();

        List<Integer> weekNumbers = weekCalc.getWeeksInMonth(year, month);
        addHeaderCell("Woche", 0, 0);

        int col = 1;
        for (int week : weekNumbers) {
            addHeaderCell(String.valueOf(week), col++, 0);
        }

        // === Weekday Labels ===
        for (int row = 0; row < WEEKDAYS.length; row++) {
            addHeaderCell(WEEKDAYS[row], 0, row + 1);
        }

        // === Fill Data ===
        fillDynamicData(weekNumbers);
    }

    private void fillDynamicData(List<Integer> weekNumbers) {
        int col = 1;
        for (int week : weekNumbers) {
            LocalDate monday = getMondayOfISOWeek(year, week);
            for (int i = 0; i < WEEKDAYS.length; i++) {
                LocalDate date = monday.plusDays(i);

                
                

                String dateStr = dateFormatter.format(date);
                String timeStr = "";
                String valueStr = "";


                if (workMonth != null) {
                   WorkDay workDay = workMonth.getWorkDayByDate(date);
                    if (workDay != null) {
                        timeStr = String.format("%02d:%02d - %02d:%02d",
                                workDay.getStartHour(), workDay.getStartMinute(),
                                workDay.getEndHour(), workDay.getEndMinute());
                       
                    } 
                }

                VBox cellBox = new VBox(
                        new Label(dateStr),
                        new Label(timeStr),
                        new Label(valueStr)
                );
                cellBox.setAlignment(Pos.CENTER);
                cellBox.setSpacing(2);

                add(cellBox, col, i + 1);
            }
            col++;
        }
    }

 

    private LocalDate getMondayOfISOWeek(int year, int isoWeek) {
        return LocalDate.ofYearDay(year, 4)
                .with(WeekFields.ISO.weekOfWeekBasedYear(), isoWeek)
                .with(WeekFields.ISO.dayOfWeek(), 1);
    }

    private void addHeaderCell(String text, int col, int row) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setMinSize(100, 40);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: black; -fx-border-width: 0.5;");
        add(label, col, row);
    }


   

    // Optional CSS helper
    public static String defaultCSS() {
        return """
            .week-table {
                -fx-border-color: black;
                -fx-border-width: 1;
            }
            .week-table .label {
                -fx-alignment: center;
                -fx-border-color: black;
                -fx-border-width: 0.5;
                -fx-padding: 5;
            }
            """;
    }
}