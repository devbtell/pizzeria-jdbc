package pizzeria_v01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private int idpedido;
	private int idpizza;
	private int idusuario;
	private int cantidad;
	
	// -- CONSTRUCTORES --
	
	public Pedido() {
		this.idpedido = 0;
		this.idpizza = 0;
		this.idusuario = 0;
		this.cantidad = 0;
	}
	
	public Pedido(int idpedido, int idpizza, int idusuario, int cantidad) {
		this.idpedido = idpedido;
		this.idpizza = idpizza;
		this.idusuario = idusuario;
		this.cantidad = cantidad;
	}

	// -- GETTERS & SETTERS --
	
	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getIdpizza() {
		return idpizza;
	}

	public void setIdpizza(int idpizza) {
		this.idpizza = idpizza;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	// -- METODOS (DAO)
	
	// :: listar (read) ::
	public static List<Pedido> listar(Connection conx) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		List<Pedido> lista = new ArrayList<Pedido>();
		String consultaSQL = "SELECT * FROM pedido";
		
		try {
			sentencia = conx.createStatement();
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				Pedido pedido = new Pedido(
							resultado.getInt("idpedido"),
							resultado.getInt("idpizza"),
							resultado.getInt("idusuario"),
							resultado.getInt("cantidad")
						);
				lista.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error al listar el pedido -");
			
			return null;
		}
		
		return lista;
	}
	
	// :: agregar (create) ::
	public static void agregar(Connection conx, Pedido pedido) {
		String consultaSQL = "INSERT INTO pedido (idpedido, idpizza, idusuario, cantidad) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
			
			sentencia.setInt(1, pedido.idpizza);
			sentencia.setInt(2, pedido.idusuario);
			sentencia.setInt(3, pedido.cantidad);
			
			int filasAfectadas = sentencia.executeUpdate();
			
			if (filasAfectadas > 0) {
				System.out.println("- pedido agregado con exito -");
			} else {
				System.out.println("- no se pudo agregar el pedido -");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("- error al agregar el pedido a la base de datos -");
		}
	}
	
	// :: remover (delete) ::
		public void remover(Connection conx, int id) {
			String consultaSQL = "DELETE FROM pedido WHERE id = ?";
			
			try {
				PreparedStatement sentencia = conx.prepareStatement(consultaSQL);
				
				sentencia.setInt(1, id);
				sentencia.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("- error al remover el pedido -");
			}
		}
		
}
