package com.recru1_t.ui.menus;



import com.recru1_t.logik.WorkDay;
import com.recru1_t.ui.model.Menu;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Table implements Menu {

    private Stage stage;
    private BorderPane pane = new BorderPane();
    private TableView<WorkDay> tableView = new TableView<>();

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


            
            selectBox.getChildren().addAll(seletctMonth, monthComboBox, selectYear, yearComboBox);
            selectBox.setStyle("-fx-padding: 10px;");
            pane.setTop(selectBox);
            pane.setCenter(tableView);
            return pane;
    }

    @Override
    public Region build(Stage stage) {
        this.stage = stage;
        return setup();
    }

}
