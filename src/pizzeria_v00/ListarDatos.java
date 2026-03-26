package pizzeria_v00;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarDatos {
	
	public static void listandoPizzas(Connection conexion) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.createStatement();
			String consultaSQL = "SELECT * FROM pizza";
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				String col00 = resultado.getString("id");
				String col01 = resultado.getString("nombre");
				String col02 = resultado.getString("ingredientes");
				String col03 = resultado.getString("precio");
				System.out.println("id: " + col00 + "| " + col01 + ": " + col02 + " $" + col03);
			}
		} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error de consulta");
        } 
		finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error al cerrar los recursos");
            }
        }
    }
	
	public static void listandoUsuarios(Connection conexion) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.createStatement();
			String consultaSQL = "SELECT * FROM usuario";
			resultado = sentencia.executeQuery(consultaSQL);
			
			while (resultado.next()) {
				String col00 = resultado.getString("id");
				String col01 = resultado.getString("nombre");
				String col02 = resultado.getString("apellido");
				System.out.println("id: " + col00 + "| " + col01 + " " + col02);
			}
		} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error de consulta");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error al cerrar los recursos");
            }
        }
    }
	
	public static void listandoPedidos(Connection conexion) {
		Statement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conexion.createStatement();
			String consultaSQL = "SELECT pe.idpedido, us.nombre, us.apellido, pi.nombre, pi.precio, pe.cantidad FROM pedido pe JOIN pizza pi ON pe.idpizza = pi.id JOIN usuario us ON pe.idusuario = us.id";
			resultado = sentencia.executeQuery(consultaSQL);
			while (resultado.next()) {
				System.out.println();
				String col00 = resultado.getString("pe.idpedido");
				String col01 = resultado.getString("us.nombre");
				String col02 = resultado.getString("us.apellido");
				String col03 = resultado.getString("pi.nombre");
				String col04 = resultado.getString("pi.precio");
				String col05 = resultado.getString("pe.cantidad");
				System.out.println("numero id del pedido: " + col00);
				System.out.println("usuario: " + col01 + " " + col02);
				System.out.println("pizza: " + col03 + " $" + col04);
				double numPrecio = Double.parseDouble(col04);
				double numCantidad = Double.parseDouble(col05);
				double totalPrecio = numPrecio * numCantidad;
				System.out.println("cantidad: " + col05 + "| total: $" + totalPrecio);
			}
		} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error de consulta");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error al cerrar los recursos");
            }
        }
    }
	
}