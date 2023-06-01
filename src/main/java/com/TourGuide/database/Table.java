package com.TourGuide.database;

import java.io.*;
import java.util.*;

/**
 * Table
 */
public class Table implements Serializable {
    private final String name;
    private final Hashtable<Integer, Object> rows;
    private int id;

    public String getName() {
        return name;
    }

    public Hashtable<Integer, Object> getRows() {
        return rows;
    }

    public int getID() {
        return id;
    }

    public Table(String name) {
        this.name = name;
        this.rows = new Hashtable<>();
        this.id = 0;
    }

    public void addData(Object data) {
        id++;
        rows.put(id, data);
    }

    public void removeData(Object data) {
        rows.entrySet().removeIf(entry -> entry.getValue().equals(data));
    }

    public int indexOf(Object data) {
        int index = 0;

        for (Object val : rows.values()) {
            if (val.equals(data)) {
                return index;
            }
            index++;
        }

        return index;
    }
}

// class Table {
// private final String name;
// private final Hashtable rows;
// private int id;

// public Table(String name) {
// this.name = name;
// this.rows = new Hashtable();
// this.id = 0;
// }

// public String getName() {
// return name;
// }

// public Hashtable getRows() {
// return rows;
// }

// public int getId() {
// return id;
// }

// public void addData(Object data) {
// id++;
// rows.put(id, data);
// }

// public void removeData(Object data) {
// rows.remove(data);
// }

// public int indexOf(Object data) {
// int index = 0;
// for (Object val : rows.values()) {
// if (val.equals(data)) {
// return index;
// }
// index++;
// }
// return index;
// }
// }
