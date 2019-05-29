package dao;

import java.sql.Connection;
import java.sql.DriverManager; //setar os tipos de banco de dados
import java.sql.SQLException;

public class ConnectionFactory {

    //método para devolver a conexão
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/db_livraria",
                    "livreiro",
                    "Suporte99");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
