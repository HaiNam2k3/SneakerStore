package utilities;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;

public class JdbcHelper {
    
        @SuppressWarnings("empty-statement")
    public static Connection getConnection() throws SQLServerException {

//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=Sneacker_Store";
//            String username = "sa";
//            String pass = "123456";
//            return DriverManager.getConnection(url, username, pass);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }

        SQLServerDataSource dataSource= new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("123456");
        dataSource.setPortNumber(1433);
        dataSource.setEncrypt(Boolean.FALSE);
        dataSource.setDatabaseName("SneakerStore");
        return dataSource.getConnection();
    }
     public static void main(String[] args) throws SQLServerException {
        System.out.println(getConnection());

    }
     
}
