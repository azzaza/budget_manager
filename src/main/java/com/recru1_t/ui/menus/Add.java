package com.recru1_t.ui.menus;


import com.recru1_t.logik.Absence;
import com.recru1_t.logik.WorkDay;
import com.recru1_t.ui.helper.TimePickerDialog;
import com.recru1_t.ui.model.Menu;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Add implements Menu {

    private Stage stage;
    private BorderPane pane = new BorderPane();

    private Region setup() {

        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Select Date:");
        VBox dateBox = new VBox(10);
        dateBox.getChildren().addAll(dateLabel, datePicker);
        dateBox.setAlignment(Pos.CENTER);
        datePicker.setValue(java.time.LocalDate.now());

        Button startTimeButton = new Button("Set Start Time");
        TextField startTimeTextField = new TextField();
        HBox startBox = new HBox(10);
        Button endTimeButton = new Button("Set End Time");
        TextField endTimeTextField = new TextField();
        HBox endBox = new HBox(10);
        VBox timeBox = new VBox(20);
        Label timeLabel = new Label("Select start and end Time:");
        timeBox.setAlignment(Pos.CENTER);
        timeBox.getChildren().addAll(timeLabel, startBox, endBox);

        startBox.setAlignment(Pos.CENTER_LEFT);
        startBox.getChildren().addAll(startTimeTextField, startTimeButton);
        startTimeButton.setOnAction(e -> {
            TimePickerDialog timePicker = new TimePickerDialog();
            String time = timePicker.showAndWait(startTimeTextField.getText());
            startTimeTextField.setText(time);
        });
        endBox.setAlignment(Pos.CENTER_LEFT);
        endBox.getChildren().addAll(endTimeTextField, endTimeButton);
        endTimeButton.setOnAction(e -> {
            TimePickerDialog timePicker = new TimePickerDialog();
            String time = timePicker.showAndWait(endTimeTextField.getText());
            endTimeTextField.setText(time);
        });

        VBox absenceBox = new VBox(10);
        absenceBox.setAlignment(Pos.CENTER);
        Label absenceLabel = new Label("Select Absence:");
        absenceBox.getChildren().addAll(absenceLabel);


        ObservableList<String> absenceOptions = javafx.collections.FXCollections.observableArrayList();
        for (Absence abs : Absence.values()) {
            absenceOptions.add(abs.getDescription());
        }

       ComboBox<String> absenceComboBox = new ComboBox<String>(absenceOptions);
        absenceComboBox.getSelectionModel().select(Absence.NONE.getDescription());
        absenceBox.getChildren().add(absenceComboBox);


        Label confirmLabel = new Label(datePicker.getValue() + " " + startTimeTextField.getText() + " - "
                + endTimeTextField.getText());
        Button confirmButton = new Button("Confirm");
        if (startTimeTextField.getText().isEmpty() || endTimeTextField.getText().isEmpty()) {
            confirmLabel.setText("Please select date, start time and end time.");
            confirmButton.setDisable(true);
        }

        startTimeTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !endTimeTextField.getText().isEmpty() && absenceComboBox.getValue().equals(Absence.NONE.getDescription())) {
                confirmLabel.setText(datePicker.getValue() + " " + startTimeTextField.getText() + " - "
                        + endTimeTextField.getText());
                confirmButton.setDisable(false);
            } else {
                confirmLabel.setText("Please select date, start time and end time.");
                confirmButton.setDisable(true);
            }
        });
        endTimeTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty() && !startTimeTextField.getText().isEmpty() && absenceComboBox.getValue().equals(Absence.NONE.getDescription())) {
                confirmLabel.setText(datePicker.getValue() + " " + startTimeTextField.getText() + " - "
                        + endTimeTextField.getText());
                confirmButton.setDisable(false);
            } else {
                confirmLabel.setText("Please select date, start time and end time.");
                confirmButton.setDisable(true);
            }
        });
        absenceComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.equals(Absence.NONE.getDescription())) {
                if (!startTimeTextField.getText().isEmpty() && !endTimeTextField.getText().isEmpty()) {
                    confirmLabel.setText(datePicker.getValue() + " " + startTimeTextField.getText() + " - "
                            + endTimeTextField.getText());
                    confirmButton.setDisable(false);
                } else {
                    confirmLabel.setText("Please select date, start time and end time.");
                    confirmButton.setDisable(true);
                }
            } else {
                confirmLabel.setText(datePicker.getValue() + " " + newVal);
                confirmButton.setDisable(false);
            }
        });



        confirmButton.setOnAction(e -> {
            if (!absenceComboBox.getValue().equals(Absence.NONE.getDescription())) {
                // Handle absence case
                WorkDay workDay = new WorkDay(Absence.valueOf(absenceComboBox.getValue().toUpperCase()), datePicker.getValue());
                // For now, just clear the fields and disable the button
                absenceComboBox.getSelectionModel().select(Absence.NONE.getDescription());
                startTimeTextField.clear();
                endTimeTextField.clear();
                confirmButton.setDisable(true);
                return;
            }
            // datePicker.setValue(null);
            WorkDay workDay = new WorkDay(Integer.parseInt(startTimeTextField.getText(0, 2)),
                    Integer.parseInt(startTimeTextField.getText(3, 5)),
                    Integer.parseInt(endTimeTextField.getText(0, 2)),
                    Integer.parseInt(endTimeTextField.getText(3, 5)), datePicker.getValue());
            startTimeTextField.clear();
            endTimeTextField.clear();
            confirmButton.setDisable(true);
        });

        HBox confirmBox = new HBox(10);
        confirmBox.setAlignment(Pos.CENTER);
        confirmBox.getChildren().addAll(confirmLabel, confirmButton);

        pane.setLeft(absenceBox);
        pane.setCenter(dateBox);
        pane.setRight(timeBox);
        pane.setBottom(confirmBox);
        pane.setStyle("-fx-padding: 20px;");

        return pane;
    }

    @Override
    public Region build(Stage stage) {
        this.stage = stage;
        return setup();
    }
}
