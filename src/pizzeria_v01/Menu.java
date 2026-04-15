package pizzeria_v01;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		Scanner tec = new Scanner(System.in);
		Connection conx = null;
		int nm, nl, na;
		
		try {
			conx = Conexion.conectar();
			
			do {
				homeMenu();
				nm = tec.nextInt();
				
				switch (nm) {
				case 1:
					do {
						listMenu();
						nl = tec.nextInt();
						
						switch (nl) {
						case 1:
							List<Pizza> lista = Pizza.listar(conx);
							for (Pizza i : lista) {
								System.out.println(i.getId() + " --> " + i.getNombre() + " : " + i.getIngredientes() + " >> $" + i.getPrecio());
							}
						}
					} while (nl != 0);
					break;
				case 2:
					do {
						addMenu();
						na = tec.nextInt();
						
						switch (na) {
						case 1:
							Pizza pzz = new Pizza();
							
							System.out.println("Nombre: ");
							pzz.setNombre(tec.nextLine());
							System.out.println("Ingredientes: ");
							pzz.setIngredientes(tec.nextLine());
							System.out.println("Precio: ");
							pzz.setPrecio(tec.nextDouble());
							
							Pizza.agregar(pzz, conx);
						}
					} while (na != 0);
					break;
				case 3:
					
					break;
				}
				
			} while (nm != 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            try {
                if (conx != null) {
                    conx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión.");
            }

            tec.close();
        }
		
	}
	
	public static void homeMenu() {
		System.out.println("- - PIZZERIA - -");
		System.out.println(":: [1] listar  ::");
		System.out.println(":: [2] agregar ::");
		System.out.println(":: [0] salir   ::  ");
	}
	
	public static void listMenu() {
		System.out.println("- - LISTAR - -");
		System.out.println("1 -> listar pizzas");
		System.out.println("2 -> listar usuarios");
		System.out.println("0 -> salir...");
	}
	public static void addMenu() {
		System.out.println("- - AGREGAR - -");
		System.out.println("1 -> agregar pizza");
		System.out.println("2 -> agregar usuarios");
		System.out.println("0 -> salir...");
	}

}
