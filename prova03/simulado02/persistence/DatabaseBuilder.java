package prova03.simulado02.persistence;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseBuilder {

    private static final String DATABASE_NAME = "employee.db";

    public static void main(String[] args) {
        build();
    }

    public static void build()  {
        try {
            DatabaseBuilder databaseBuilder = new DatabaseBuilder();
            databaseBuilder.dropDatabaseIfExists();
            databaseBuilder.createTable();
            System.out.println("❤ All set ❤");
        } catch (IOException | SQLException e) {
            System.err.println("Ops! Something went wrong.");
        }
    }

    private void dropDatabaseIfExists() throws IOException {
        final Path path = Paths.get(DATABASE_NAME);
        if(Files.exists(path)) {
            Files.delete(path);
            System.out.println("Removing existing database ...");
        }
    }

    public void createTable() throws SQLException {
        final String sql = """
            CREATE TABLE IF NOT EXISTS Employee (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                birth_date TEXT,
                sold_value REAL,
                consultant_in_charge TEXT,
                FOREIGN KEY (consultant_in_charge) REFERENCES Employee(id)
            );""";


        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
}
