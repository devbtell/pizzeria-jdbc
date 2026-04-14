package pizzeria_v01;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		Connection conectar = null;
		
		try {
			conectar = Conexion.obtenerConexion();
			
			Pizza pzz = new Pizza();
			Scanner tec = new Scanner(System.in);
			
			System.out.println("Nombre: ");
			pzz.setNombre(tec.nextLine());
			System.out.println("Ingredientes: ");
			pzz.setIngredientes(tec.nextLine());
			System.out.println("Precio: ");
			pzz.setPrecio(tec.nextDouble());
			
			Pizza.incluirPizza(pzz, conectar);
			
			Pizza.listarPizza(conectar);
			
			tec.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
