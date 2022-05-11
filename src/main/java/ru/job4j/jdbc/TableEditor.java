package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void statement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
           "create table if not exists %s();", tableName
        );
        statement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s;", tableName
        );
        statement(sql);
        System.out.println("Таблица удалена!");
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add column %s %s;", tableName,
                columnName, type
        );
        statement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName
        );
        statement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s TO %s;", tableName,
                columnName, newColumnName
        );
        statement(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            properties.load(in);
            TableEditor tableEditor = new TableEditor(properties);
            var tableName = "my_table";
            tableEditor.createTable(tableName);
            System.out.println(getTableScheme(connection, tableName));
            tableEditor.addColumn(tableName, "name", "text");
            System.out.println(getTableScheme(connection, tableName));
            tableEditor.renameColumn(tableName, "name", "surname");
            System.out.println(getTableScheme(connection, tableName));
            tableEditor.dropColumn(tableName, "surname");
            System.out.println(getTableScheme(connection, tableName));
            tableEditor.dropTable(tableName);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}