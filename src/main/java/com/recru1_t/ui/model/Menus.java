package com.recru1_t.ui.model;

import com.recru1_t.ui.menus.Add;
import com.recru1_t.ui.menus.Settings;
import com.recru1_t.ui.menus.Table;

public enum Menus {
    ADD("Add", "Add Wrok Day", new Add()),
    TABLE("Table", "View Work Days", new Table()),
    SETTINGS("Settings", "Application Settings", new Settings());

    private final String menuName;
    private final String header;
    private final Menu menu;

    Menus(String menuName, String header,Menu menu) {
        this.menuName = menuName;
        this.header = header;
        this.menu = menu;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getHeader() {
        return header;
    }
    public Menu getMenu() {
        return menu;
    }
}
