package com.recru1_t.ui.model;

import com.recru1_t.logik.DataSaver;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public interface Menu {
    Region build(Stage stage);
    void setDataSaver(DataSaver dataSaver);
}
