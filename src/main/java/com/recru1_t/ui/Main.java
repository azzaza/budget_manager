package com.recru1_t.ui;


import java.util.HashMap;
import java.util.Map;

import com.recru1_t.ui.model.MenuBuilder;
import com.recru1_t.ui.model.Menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import java.awt.Dimension;
// import java.awt.Toolkit;

public class Main extends Application {
    int count = 0;

    @Override
    public void start(Stage stage) {
        Map<Menus,Scene> scenes = new HashMap<>();
        for (Menus menu: Menus.values()){
            MenuBuilder builder = new MenuBuilder(menu,stage,scenes);
            Scene scene = new Scene(builder.build(),800,600);
            scenes.put(menu, scene);
        }
        
        stage.setScene(scenes.get(Menus.ADD));
        stage.setTitle(Menus.ADD.getMenuName());
        // stage.setScene(mainScene);
        stage.show();   
    }

    public static void main(String[] args) {
        launch();
    }

}