package com.recru1_t.ui.menus;


import com.recru1_t.logik.DataSaver;
import com.recru1_t.ui.model.Menu;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Settings implements Menu {

    private Stage stage;

    // private BorderPane pane = new BorderPane();
    private Region setup() {
        // TableColumn<WorkDay, LocalDate> dateColumn = new TableColumn<>("Date");
        // dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // TableColumn<WorkDay, LocalTime> timeColumn = new TableColumn<>("Time");
        // timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // tableView.getColumns().addAll(dateColumn, timeColumn);
        // pane.setCenter(tableView);

        return null;
    }

    public void setDataSaver(DataSaver dataSaver) {
        // TODO Auto-generated method stub
        
    }


    public Region build(Stage stage) {
        this.stage = stage;
        return setup();
    }



}
