package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL
            = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QLGiaoVu;"
            + "encrypt=false;"
            + "trustServerCertificate=true";

    private static final String USER = "sa";

    private static final String PASSWORD = "sa";

    public static Connection getConnection()
            throws SQLException {
        try {
            Class.forName(
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver"
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Không tìm thấy SQL Server JDBC Driver", e
            );
        }
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
