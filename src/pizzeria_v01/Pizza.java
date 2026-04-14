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

	// -- TO STRING --
	
	@Override
	public String toString() {
		return "Pizza [id=" + id + ", nombre=" + nombre + ", ingredientes=" + ingredientes + ", precio=" + precio + "]";
	}
	
	// -- METODOS --

	// :: listar ::
	public static /*List<Pizza>*/ void listarPizza(Connection conectar) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		List<Pizza> lista = new ArrayList<>();
		String consultaSQL = "SELECT * FROM pizza";
		
		try {
			sentencia = conectar.createStatement();
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				Pizza pzz = new Pizza(
							resultado.getInt("id"),
							resultado.getString("nombre"),
							resultado.getString("ingredientes"),
							resultado.getDouble("precio")
						);
				lista.add(pzz);
				System.out.println(pzz.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error de consulta -");
		} finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("- error al cerrar los recursos -");
            }
        }
		
		// return lista;
	}
	
	// :: incluir ::
	public static void incluirPizza(Pizza pzz, Connection conectar) {
		String consultaSQL = "INSERT INTO pizza (nombre, ingredientes, precio) VALUES (?, ?, ?)";
		try {
			PreparedStatement sentencia = conectar.prepareStatement(consultaSQL);
			
			sentencia.setString(1, pzz.getNombre());
			sentencia.setString(2, pzz.getIngredientes());
			sentencia.setDouble(3, pzz.getPrecio());
			
			sentencia.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
