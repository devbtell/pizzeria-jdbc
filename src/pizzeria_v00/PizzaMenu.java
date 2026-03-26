package pizzeria_v00;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PizzaMenu {

	public static void main(String[] args) throws SQLException {
		Connection conexion = null;
		Scanner tec = new Scanner(System.in);
		int menuNum;
		
		try {
			conexion = ConexionBase.obtenerConexion();
			
			do {
				
				System.out.println("¿que quieres hacer?");
				System.out.println("1 listar las pizzas");
				System.out.println("2 listar los usuarios");
				System.out.println("3 listar los pedidos");
				System.out.println("4 incluir una nueva pizza");
				System.out.println("5 incluir un nuevo usuario");
				System.out.println("6 incluir un nuevo pedido");
				System.out.println("7 borrar una pizza");
				System.out.println("8 borrar un usuario");
				System.out.println("9 borrar un pedido");
				System.out.println("0 salir");
				menuNum = tec.nextInt();
				
				switch (menuNum) {
				case 1:
					System.out.println("listando las pizzas..");
					ListarDatos.listandoPizzas(conexion);
					System.out.println();
					break;
				case 2:
					System.out.println("listando los usuarios..");
					ListarDatos.listandoUsuarios(conexion);
					System.out.println();
					break;
				case 3:
					System.out.println("listando los pedidos..");
					ListarDatos.listandoPedidos(conexion);
					System.out.println();
					break;
				case 4:
					System.out.println("¿que pizza quieres añadir?");
					IncluirDatos.incluyendoPizza(conexion, tec);
					System.out.println();
					break;
				case 5:
					System.out.println("¿que usuario quieres añadir?");
					IncluirDatos.incluyendoUsuario(conexion, tec);
					System.out.println();
					break;
				case 6:
					System.out.println("¿que pedido quieres añadir?");
					IncluirDatos.incluyendoPedido(conexion, tec);
					System.out.println();
					break;
				case 7:
					System.out.println("¿que pizza quieres borrar?");
					BorrarDatos.borrandoPizza(conexion, tec);
					System.out.println();
					break;
				case 8:
					System.out.println("¿que usuario quieres borrar?");
					BorrarDatos.borrandoUsuario(conexion, tec);
					System.out.println();
					break;
				case 9:
					System.out.println("¿que pedido quieres borrar?");
					BorrarDatos.borrandoPedido(conexion, tec);
					System.out.println();
					break;
				case 0:
					System.out.println("hasta luego!");
					System.exit(0);
				}
				
			} while(menuNum != 0);
			
		} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error al interactuar con la base de datos.");
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión.");
            }

            tec.close();
        }
    }
}