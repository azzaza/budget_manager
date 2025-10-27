package com.recru1_t.ui.menus;

import com.recru1_t.logik.DataSaver;
import com.recru1_t.logik.WorkDay;
import com.recru1_t.logik.WorkMonth;
import com.recru1_t.ui.helper.WeekTableView;
import com.recru1_t.ui.model.Menu;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Table implements Menu {

    private Stage stage;
    private BorderPane pane = new BorderPane();
    private DataSaver dataSaver;

    private Region setup() {
        
        
        HBox selectBox = new HBox(10);
        Label seletctMonth = new Label("Select Month:");
        ComboBox<String> monthComboBox = new ComboBox<>();



        monthComboBox.getItems().addAll("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
        monthComboBox.getSelectionModel().select(java.time.LocalDate.now().getMonthValue() - 1);

        Label selectYear = new Label("Select Year:");
        ComboBox<String> yearComboBox = new ComboBox<>();
        ObservableList<String> years = yearComboBox.getItems();
        int currentYear = java.time.LocalDate.now().getYear();
        for (int i = currentYear - 1; i <= 2030; i++) {
            years.add(Integer.toString(i));
        }
        yearComboBox.getSelectionModel().select(Integer.toString(currentYear));




        WorkMonth workMonth = dataSaver.getWorkMonth(java.time.LocalDate.now().getMonthValue(), currentYear);  
       
        WeekTableView table = new WeekTableView(java.time.LocalDate.now().getMonthValue(), currentYear, workMonth);



        monthComboBox.setOnAction(e -> {
            int selectedMonth = monthComboBox.getSelectionModel().getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt(yearComboBox.getSelectionModel().getSelectedItem());
            WorkMonth newWorkMonth = dataSaver.getWorkMonth(selectedMonth, selectedYear);  

            WeekTableView newTable = new WeekTableView(selectedMonth, selectedYear,newWorkMonth);

            pane.setCenter(newTable);
        });

        yearComboBox.setOnAction(e -> {
            int selectedMonth = monthComboBox.getSelectionModel().getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt(yearComboBox.getSelectionModel().getSelectedItem());
            WorkMonth newWorkMonth = dataSaver.getWorkMonth(selectedMonth, selectedYear);  

            WeekTableView newTable = new WeekTableView(selectedMonth, selectedYear,newWorkMonth);
            
            pane.setCenter(newTable);
        });

        selectBox.getChildren().addAll(seletctMonth, monthComboBox, selectYear, yearComboBox);
        selectBox.setStyle("-fx-padding: 10px;");
        pane.setTop(selectBox);
        pane.setCenter(table);
        return pane;
    }

    @Override
    public void setDataSaver(DataSaver dataSaver) {
        this.dataSaver = dataSaver;

    }

    @Override
    public Region build(Stage stage) {
        this.stage = stage;
        return setup();
    }

}
