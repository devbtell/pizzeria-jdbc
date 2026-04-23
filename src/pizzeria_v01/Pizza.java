package pizzeria_v01;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
	
	private int id;
	private String nombre;
	private String ingredientes;
	private double precio;
	
	// -- CONSTRUCTORES --
	
	public Pizza() {
		this.id = 0;
		this.nombre = "";
		this.ingredientes = "";
		this.precio = 0.0;
	}
	
	public Pizza(int id, String nombre, String ingredientes, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.precio = precio;
	}

	// -- GETTERS & SETTERS --
	
	public int getId() {
		return id;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	// -- METODOS (DAO) --

	// :: listar (read) ::
	public static List<Pizza> listar(Connection conx) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		List<Pizza> lista = new ArrayList<>();
		String consultaSQL = "SELECT * FROM pizza";
		
		try {
			sentencia = conx.createStatement();
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				Pizza pzz = new Pizza(
							resultado.getInt("id"),
							resultado.getString("nombre"),
							resultado.getString("ingredientes"),
							resultado.getDouble("precio")
						);
				lista.add(pzz);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error de consulta -");
			
			return null;
		}
		
		return lista;
	}
	
	// :: agregar (create) ::
	public static void agregar(Connection conx, Pizza pzz) {
		String consultaSQL = "INSERT INTO pizza (nombre, ingredientes, precio) VALUES (?, ?, ?)";
		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
			
			sentencia.setString(1, pzz.getNombre());
			sentencia.setString(2, pzz.getIngredientes());
			sentencia.setDouble(3, pzz.getPrecio());
			
			int filasAfectadas = sentencia.executeUpdate();
			
			if (filasAfectadas > 0) {
				System.out.println("- pizza agregada con éxito -");
			} else {
				System.out.println("- no se pudo agregar la pizza -");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("- error al agregar la pizza a la base de datos -");
		}
	}
	
	// :: modificar (update) ::
	/* public void actualizar(Connection conx, Pizza pzz) {
		String consultaSQL = ""; 
		
		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
			
			sentencia.setInt(1, pzz.getId());
			sentencia.setString(2, pzz.getNombre());
			sentencia.setString(3, pzz.getIngredientes());
			sentencia.setDouble(4, pzz.getPrecio());
			
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} */
	
	// :: remover (delete) ::
	public void remover(Connection conx, int id) {
		String consultaSQL = "DELETE FROM pizza WHERE id = ?";

		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);

			sentencia.setInt(1, id);
			sentencia.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
