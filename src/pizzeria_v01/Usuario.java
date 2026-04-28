package pizzeria_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	
	// -- CONSTRUCTORES --
	
	public Usuario() {
		this.id = 0;
		this.nombre = "";
		this.apellido = "";
	}
	
	public Usuario(int id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	// -- GETTERS & SETTERS --
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	// -- METODOS (DAO) --
	
	// :: listar (read) ::
	public static List<Usuario> listar(Connection conx) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		List<Usuario> lista = new ArrayList<>();
		String consultaSQL = "SELECT * FROM usuario";
		
		try {
			sentencia = conx.createStatement();
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				Usuario usuario = new Usuario(
							resultado.getInt("id"),
							resultado.getString("nombre"),
							resultado.getString("apellido")
						);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error al listar los usuarios -");
			
			return null;
		}
		
		return lista;
	}
	
	// :: agregar (create) ::
	public static void agregar(Connection conx, Usuario usuario) {
		String consultaSQL = "INSERT INTO usuario (nombre, apellido) VALUES (?, ?)";
		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
			
			sentencia.setString(1, usuario.getNombre());
			sentencia.setString(2, usuario.getApellido());
			
			int filasAfectadas = sentencia.executeUpdate();
			
			if (filasAfectadas > 0) {
				System.out.println("- usuario agregado con exito -");
			} else {
				System.out.println("- no se pudo agregar el usuario -");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error al agregar el usuario a la base de datos -");
		}
	}
	
	// :: remover (delete) ::
	public void remover(Connection conx, int id) {
		String consultaSQL = "DELETE FROM usuario WHERE id = ?";
		
		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
			
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error al remover el usuario -");
		}
	}
	
}
