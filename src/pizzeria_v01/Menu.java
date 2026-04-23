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
							listandoPizza(conx);
						}
					} while (nl != 0);
					break;
				case 2:
					do {
						addMenu();
						na = tec.nextInt();
						
						switch (na) {
						case 1:
							agregandoPizza(conx, tec);
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
	
	// -- MENU OPCIONES --
	
	public static void homeMenu() {
		System.out.println("- - PIZZERIA - -");
		System.out.println("1 : Listar");
		System.out.println("2 : Agregar");
		System.out.println("0 : Salir...");
	}
	
	public static void listMenu() {
		System.out.println("- - LISTAR - -");
		System.out.println("1 : Listar Pizzas");
		System.out.println("2 : Listar Usuarios");
		System.out.println("0 : Regresar...");
	}
	public static void addMenu() {
		System.out.println("- - AGREGAR - -");
		System.out.println("1 : Agregar Pizza");
		System.out.println("2 : Agregar Usuario");
		System.out.println("0 : Regresar...");
	}

	// -- PROCEDIMIENTOS -- 
	
	public static void listandoPizza(Connection c) {
		List<Pizza> lista = Pizza.listar(c);
		
		for (Pizza i : lista) {
			System.out.println(i.getId() + " > " + i.getNombre() + " : " + i.getIngredientes() + " >> $" + i.getPrecio());
		}
	}
	
	public static void agregandoPizza(Connection c, Scanner t) {
		Pizza p = new Pizza();
		
		t.nextLine();
		System.out.println("Nombre: ");
		p.setNombre(t.nextLine());
		System.out.println("Ingredientes: ");
		p.setIngredientes(t.nextLine());
		System.out.println("Precio: ");
		p.setPrecio(t.nextDouble());
		
		Pizza.agregar(c, p);
	}
	
}
