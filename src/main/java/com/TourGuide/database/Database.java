package com.TourGuide.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.TourGuide.actor.Admin;
import com.TourGuide.actor.User;
import com.TourGuide.model.Post;
import com.TourGuide.model.Report;

/**
 * Database
 */
public class Database implements Serializable, AutoCloseable {
    private static final Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }

    private List<Table> tables;

    private Database() {
        load();
    }

    @SuppressWarnings("unchecked")
    public void load() {
        try {
            final File file = new File("Database.dat");
            if (!file.exists()) {
                createNewDatabase();
            } else {
                try (FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis)) {
                    tables = (List<Table>) ois.readObject();
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            final File file = new File("Database.dat");
            try (FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(tables);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewTable(final String name) {
        if (tables == null) {
            tables = new ArrayList<>();
        }

        tables.add(new Table(name));
    }

    public Table getTable(final String name) {
        for (final Table table : tables) {
            if (table.getName().equals(name)) {
                return table;
            }
        }

        return null;
    }

    public void add(final String tableName, final Object data) throws Exception {
        final Table table = getTable(tableName);

        if (table == null) {
            throw new Exception("Table does not exist");
        }

        table.addData(data);
    }

    public void remove(final String tableName, final Object data) throws Exception {
        final Table table = getTable(tableName);

        if (table == null) {
            throw new Exception("Table does not exist");
        }

        table.removeData(data);
    }

    public Object login(String username, String password) throws Exception {
        username = (username != null) ? username : "";
        password = (password != null) ? password : "";

        if (username.equals("admin") && password.equals("0")) {
            return Admin.getInstance();
        }

        final Table table = getTable("users");
        if (table == null) {
            throw new Exception("Table does not exist");
        }

        for (final Object user : table.getRows().values()) {
            final User u = (User) user;
            if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.getStatus()) {
                return u;
            }
        }

        return null;
    }

    public int indexOf(final String tableName, final String username) throws Exception {
        final Table table = getTable(tableName);
        if (table == null) {
            throw new Exception("Table does not exist");
        }

        int index = 0;
        for (final Object user : table.getRows().values()) {
            final User u = (User) user;
            if (u.getUsername().equals(username)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    private void createNewDatabase() {
        createNewTable("users");
        createNewTable("reports");
        createNewTable("posts");
        createNewTable("messages");

        final User acc1 = new User("UN1", "11", null, true, "Zaid", "Ahmad", "Irbid", 28);
        final User acc2 = new User("UN2", "22", null, true, "Omar", "Farook", "Irbid", 30);
        final User acc3 = new User("UN3", "33", null, true, "Maha", "Hani", "Amman", 42);
        final User acc4 = new User("UN4", "44", null, true, "Hamzah", "Ali", "Zarqa", 37);
        final User acc5 = new User("UN5", "55", null, true, "Salma", "Waleed", "Jerash", 40);
        final User acc6 = new User("UN6", "66", null, false, "Ali", "Khaled", "Amman", 26);

        acc1.addFriends(Arrays.asList(acc2, acc3));
        acc2.addFriends(Arrays.asList(acc1, acc3, acc5));
        acc3.addFriends(Arrays.asList(acc2, acc4, acc6));
        acc4.addFriends(Arrays.asList(acc5, acc6));
        acc5.addFriends(Arrays.asList(acc1, acc3, acc4));
        acc6.addFriends(Arrays.asList(acc1, acc2));

        try {
            add("users", acc1);
            add("users", acc2);
            add("users", acc3);
            add("users", acc4);
            add("users", acc5);
            add("users", acc6);

            add("posts", new Post(acc1.getUsername(), "Liverpool beats Man. City 2-1", true, "Sport"));
            add("posts", new Post(acc1.getUsername(), "Apple expects to release iPhone 14 in October", true, "News"));
            add("posts", new Post(acc2.getUsername(), "Expect snow next Sunday", true, "Weather"));
            add("posts", new Post(acc3.getUsername(), "Italy fails to qualify for the world cup", true, "Sport"));
            add("posts", new Post(acc3.getUsername(), "The deficit exceeds 2 million dollars", true, "Economy"));
            add("posts",
                    new Post(acc5.getUsername(), "The minimum wage has been raised to 300 dinars", true, "Economy"));

            add("reports",
                    new Report(acc1.getUsername(), acc2.getUsername(), null, "He is gay!?", null, null, null, null));
            add("reports",
                    new Report(acc3.getUsername(), acc2.getUsername(), null, "Hello, World!", null, null, null, null));

            add("reports",
                    new Report(acc2.getUsername(), acc1.getUsername(), null, "I like one piece!", null, null, null,
                            null));

            add("reports",
                    new Report(acc1.getUsername(), acc5.getUsername(), null, "Hey, there!", null, null, null, null));
            add("reports",
                    new Report(acc3.getUsername(), acc5.getUsername(), null, "This is a report.", null, null, null,
                            null));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        save();
    }

    @Override
    public void close() throws Exception {
        save();
    }
}
