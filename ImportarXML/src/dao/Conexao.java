package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	public Connection getConexao() throws Exception {
        Connection conn;
        try {
            String driver = "org.postgresql.Driver";
            String servidor = "localhost:5432";
            String conexao = "jdbc:postgresql";
            String usuario = "postgres";
            String senha = "admin";
            String bdNome = "XML";
            Class.forName(driver);
            conn = DriverManager.getConnection(conexao + "://" + servidor + "/" + bdNome, usuario, senha);

        } catch (Exception ex) {
            throw ex;
        }
        return conn;
    }
}
