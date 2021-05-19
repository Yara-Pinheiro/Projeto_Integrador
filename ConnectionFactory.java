package Banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "system";
	private static String password = "aula";
	private static Connection c = null;

	public static Connection getConnection() {
		if (c == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				c = DriverManager.getConnection(url, user, password);
				System.out.println("Conectou com sucesso");
			} catch (SQLException e) {
				System.out.println("Erro ao conectar: " + e);
			} catch (ClassNotFoundException e) {
				System.out.println("Erro ao registrar: " + e);
			}
		}
		return c;
	}

	public static void closeConnection() {
		if (c != null) {
			try {
				c.close();
				System.out.println("Fechou a conexao com sucesso");
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexao: " + e);
			}
		}
	}
}
