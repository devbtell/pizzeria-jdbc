package pizzeria_v00;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BorrarDatos {

//	public static void borrandoPizza (Connection conexion, Scanner tec) {
//		System.out.println("id: ");
//		int idPizza = tec.nextInt();
//		tec.nextLine();	
//		
//		String consultaSQL = "DELETE FROM pizza WHERE id = "+idPizza;
//		// implementar codigo en donde verifique que la pizza no se encuentre en ningun pedido
//		
//		try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
//			
//			int hacerConsulta = statement.executeUpdate();
//			if (hacerConsulta > 0) {
//				System.out.println("pizza borrada!");
//			} else {
//           		System.out.println("no hay pizza con esa id");
//            		}
//		} catch (SQLException | java.util.InputMismatchException e) {
//			e.printStackTrace();
//            System.out.println("error al borrar la pizza de la base de datos");
//        } finally {
//        }
//    }
	
	public static void borrandoPizza(Connection conexion, Scanner tec) {
        try {
            System.out.println("id: ");
            int idPizza = tec.nextInt();
            tec.nextLine();

            // Verificar si la pizza está asociada a algún pedido antes de borrarla
            if (!pizzaPedido(conexion, idPizza)) {
                // Preparar la consulta SQL para borrar una pizza por su ID
                String consultaSQL = "DELETE FROM pizza WHERE id = ?";
                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    // Establecer el parámetro de la consulta
                    statement.setInt(1, idPizza);

                    // Ejecutar la consulta
                    int filasAfectadas = statement.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("pizza borrada!");
                    } else {
                        System.out.println("no hay pizza con esa id");
                    }
                }
            } else {
                System.out.println("la pizza esta asociada a un pedido y no se puede borrar");
            }
        } catch (SQLException | java.util.InputMismatchException e) {
            e.printStackTrace();
            System.out.println("error al borrar la pizza de la base de datos.");
        }
    }
	
//	public static void borrandoUsuario (Connection conexion, Scanner tec) {
//		System.out.println("id: ");
//		int idUsuario = tec.nextInt();
//		tec.nextLine();	
//		
//		String consultaSQL = "DELETE FROM usuario WHERE id = "+idUsuario;
//		// implementar parte de codigo en donde verifique que el usuario no se encuentre en ningun pedido
//		
//		try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
//			
//			int hacerConsulta = statement.executeUpdate();
//			if (hacerConsulta > 0) {
//				System.out.println("usuario borrado!");
//			} else {
//           		System.out.println("no hay usuario con esa id");
//            		}
//		} catch (SQLException | java.util.InputMismatchException e) {
//			e.printStackTrace();
//            System.out.println("error al borrar el usuario de la base de datos");
//        } finally {
//        }
//    }
	
	public static void borrandoUsuario(Connection conexion, Scanner tec) {
        try {
            System.out.println("id: ");
            int idUsuario = tec.nextInt();
            tec.nextLine();

            if (!usuarioPedido(conexion, idUsuario)) {
                String consultaSQL = "DELETE FROM usuario WHERE id = ?";
                try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
                    statement.setInt(1, idUsuario);

                    int filasAfectadas = statement.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("usuario borrado!");
                    } else {
                        System.out.println("no hay usuario con esa id");
                    }
                }
            } else {
                System.out.println("el usuario esta asociado a un pedido y no se puede borrar");
            }
        } catch (SQLException | java.util.InputMismatchException e) {
            e.printStackTrace();
            System.out.println("error al borrar el usuario de la base de datos.");
        }
    }
	
	public static void borrandoPedido (Connection conexion, Scanner tec) {
		System.out.println("id: ");
		int idPedido = tec.nextInt();
		tec.nextLine();
		
		String consultaSQL = "DELETE FROM pedido WHERE idpedido = "+idPedido;
		
		try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
			
			int hacerConsulta = statement.executeUpdate();
			if (hacerConsulta > 0) {
				System.out.println("se pudo borrar el pedido");
			} else {
           		System.out.println("no hay pedido con esa id");
            		}
		} catch (SQLException | java.util.InputMismatchException e) {
			e.printStackTrace();
            System.out.println("error al borrar el pedido de la base de datos");
        } finally {
        }
    }
	
	
	// funciones para verificar si el id (pizza, usuario) introducido no esta en algun pedido
	
	private static boolean pizzaPedido(Connection conexion, int idPizza) throws SQLException {
        // Verificar si la pizza con el ID proporcionado está asociada a algún pedido
        String consultaSQL = "SELECT COUNT(*) FROM pedido WHERE idpizza = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, idPizza);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
	
	private static boolean usuarioPedido(Connection conexion, int idUsuario) throws SQLException {
        // Verificar si la pizza con el ID proporcionado está asociada a algún pedido
        String consultaSQL = "SELECT COUNT(*) FROM pedido WHERE idusuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
	
	
}
