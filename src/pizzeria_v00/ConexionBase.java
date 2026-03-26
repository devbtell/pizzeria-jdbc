package pizzeria_v00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBase {

	public static Connection obtenerConexion() throws SQLException {
		String url = "jdbc:mysql://localhost/pizzeria";
		String usuario = "root";
		String contraseña = "";
		return DriverManager.getConnection(url, usuario, contraseña);
	}
	
}