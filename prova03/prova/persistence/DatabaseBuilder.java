package prova03.prova.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class DatabaseBuilder {

    private final Connection connection;

    public DatabaseBuilder() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    public void createTables() throws SQLException {
        try(final var stmt = connection.createStatement()){
            System.out.println("Creating tables...\n");
            String customerSql = """
                    DROP TABLE IF EXISTS customer;
                    CREATE TABLE customer(
                        plate TEXT PRIMARY KEY,
                        phone TEXT NOT NULL,
                        type TEXT NOT NULL
                    );
                    """;
            stmt.executeUpdate(customerSql);
            System.out.println(customerSql);

            String ticketSql = """
                    DROP TABLE IF EXISTS ticket;
                    CREATE TABLE ticket(
                        ID TEXT PRIMARY KEY,
                        plate TEXT NOT NULL,
                        entry TEXT NOT NULL,
                        exit TEXT,
                        fee REAL,
                        FOREIGN KEY (plate) REFERENCES customer(plate)
                    )
                    """;
            stmt.executeUpdate(ticketSql);
            System.out.println(ticketSql);

            String periodCostSql = """
                    DROP TABLE IF EXISTS period_cost;
                    CREATE TABLE period_cost(
                        hours INTEGER PRIMARY KEY,
                        cost REAL NOT NULL
                    );
                    """;
            stmt.executeUpdate(periodCostSql);
            System.out.println(periodCostSql);
        }
    }

    public void populateDatabase() throws SQLException {
        String periodCostSql = "INSERT INTO period_cost (hours, cost) VALUES (?, ?)";
        String customerSql = "INSERT INTO customer (plate, phone, type) VALUES (?, ?, ?)";
        String ticketSql = "INSERT INTO ticket (id, plate, entry) VALUES (?, ?, ?)";

        try(
                var costStmt = connection.prepareStatement(periodCostSql);
                var customerStmt = connection.prepareStatement(customerSql);
                var ticketStmt = connection.prepareStatement(ticketSql);
        ){
            connection.setAutoCommit(false);

            costStmt.setInt(1, 1);
            costStmt.setDouble(2, 7);
            costStmt.addBatch();

            costStmt.setInt(1, 12);
            costStmt.setDouble(2, 30);
            costStmt.addBatch();

            costStmt.setInt(1, 2);
            costStmt.setDouble(2, 10);
            costStmt.addBatch();

            customerStmt.setString(1, "POO0007");
            customerStmt.setString(2, "(16) 95555-0007");
            customerStmt.setString(3, "MOTORCYCLE");
            customerStmt.addBatch();

            customerStmt.setString(1, "POO0015");
            customerStmt.setString(2, "(16) 95555-0015");
            customerStmt.setString(3, "CAR");
            customerStmt.addBatch();

            final LocalDateTime now = LocalDateTime.now();

            ticketStmt.setString(1, UUID.randomUUID().toString());
            ticketStmt.setString(2, "POO0007");
            ticketStmt.setString(3, now.minusHours(6).toString());
            ticketStmt.addBatch();

            ticketStmt.setString(1, UUID.randomUUID().toString());
            ticketStmt.setString(2, "POO0015");
            ticketStmt.setString(3, now.minusHours(14).toString());
            ticketStmt.addBatch();

            costStmt.executeBatch();
            customerStmt.executeBatch();
            ticketStmt.executeBatch();

            connection.commit();
            System.out.println("The database has been populated.");
        }
        connection.close();
    }
}
