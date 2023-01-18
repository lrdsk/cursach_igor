package ru.yura;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

@Singleton
public class MyDateBase implements Closeable {
    public MyDateBase () {
        this.connect();
    }
    private Connection connection = null; //все переменные для подключения

    public Connection getConnection() {
        return this.connection;
    }


    protected String url = "jdbc:postgresql://localhost:5432/dbtest";

    protected String username = "postgres";

    protected String password = "postgres";

    private void connect() {
        try {
            System.out.println("Подключение к базе данных");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Что-то не так");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
