package com.recru1_t.ui.helper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Optional;

public class TimePickerDialog {

    private static final String TIME_PATTERN = "^([01]?\\d|2[0-3]):([0-5]?\\d)$";

    private String selectedTime;

    public String showAndWait(String initialTime) {
        Stage timePickerStage = new Stage();
        timePickerStage.initModality(Modality.APPLICATION_MODAL);
        timePickerStage.setTitle("Select Time");
        if(initialTime == null || !initialTime.matches(TIME_PATTERN)) initialTime = "";
        // Sliders
        Slider hourSlider = new Slider(0, 23,
                initialTime.isEmpty() ? LocalTime.now().getHour() : Integer.parseInt(initialTime.split(":")[0]));
        hourSlider.setMajorTickUnit(1);
        hourSlider.setShowTickMarks(true);
        hourSlider.setShowTickLabels(true);
        hourSlider.setSnapToTicks(true);

        Slider minuteSlider = new Slider(0, 59,
                initialTime.isEmpty() ? LocalTime.now().getMinute() : Integer.parseInt(initialTime.split(":")[1]));
        minuteSlider.setMajorTickUnit(5);
        minuteSlider.setMinorTickCount(4);
        minuteSlider.setShowTickMarks(true);
        minuteSlider.setShowTickLabels(true);
        minuteSlider.setSnapToTicks(true);

        // Preview label
        Label preview = new Label();
        preview.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Runnable updatePreview = () -> {
            int hour = (int) hourSlider.getValue();
            int minute = (int) minuteSlider.getValue();
            preview.setText(String.format("Time: %02d:%02d", hour, minute));
        };
        hourSlider.valueProperty().addListener((obs, oldVal, newVal) -> updatePreview.run());
        minuteSlider.valueProperty().addListener((obs, oldVal, newVal) -> updatePreview.run());
        updatePreview.run();

        // OK button
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            int hour = (int) hourSlider.getValue();
            int minute = (int) minuteSlider.getValue();
            selectedTime = String.format("%02d:%02d", hour, minute);
            timePickerStage.close();
        });

        VBox layout = new VBox(15,
                new Label("Select Hour:"), hourSlider,
                new Label("Select Minute:"), minuteSlider,
                preview, okButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        timePickerStage.setScene(new Scene(layout, 400, 300));
        timePickerStage.showAndWait();
        return Optional.ofNullable(selectedTime).orElse("");
    }
}
