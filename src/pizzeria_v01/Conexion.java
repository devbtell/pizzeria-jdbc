package pizzeria_v01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection obtenerConexion() throws SQLException {
		
		String url = "jdbc:mysql://localhost/pizzeria";
		String user = "root";
		String pass = "";
		
		return DriverManager.getConnection(url, user, pass);
	}
	
}