package pizzeria_v00;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class IncluirDatos {

    public static void incluyendoPizza(Connection conexion, Scanner tec) {
        System.out.println("id: ");
        int id = tec.nextInt();
        tec.nextLine();
        System.out.print("Nombre: ");
        String nombre = tec.nextLine();
        System.out.print("Ingredientes: ");
        String ingredientes = tec.nextLine();
        System.out.print("Precio: ");
        double precio = tec.nextDouble();
        tec.nextLine();

        try {
            String consultaSQL = "INSERT INTO pizza (id, nombre, ingredientes, precio) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            	statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setString(3, ingredientes);
                statement.setDouble(4, precio);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("pizza agregada con éxito.");
                } else {
                    System.out.println("no se pudo agregar la pizza");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al incluir la pizza en la base de datos.");
        } finally {
        }
    }
    
    public static void incluyendoUsuario(Connection conexion, Scanner tec) {
        System.out.println("id: ");
        int id = tec.nextInt();
        tec.nextLine();        
        System.out.print("nombre: ");
        String nombre = tec.nextLine();
        System.out.println("apellido: ");
        String apellido = tec.nextLine();

        try {
            String consultaSQL = "INSERT INTO usuario (id, nombre, apellido) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            	statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setString(3, apellido);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("usuario agregado con éxito");
                } else {
                    System.out.println("no se pudo agregar el usuario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al incluir el usuario en la base de datos.");
        } finally {
        }
    }
    
    public static void incluyendoPedido(Connection conexion, Scanner tec) {
        System.out.println("idpizza: ");
        int idpizza = tec.nextInt();
        tec.nextLine();
        System.out.println("idusuario: ");
        int idusuario = tec.nextInt();
        tec.nextLine();
        System.out.println("idpedido: ");
        int idpedido = tec.nextInt();
        tec.nextLine();
        System.out.println("cantidad: ");
        int cantidad = tec.nextInt();
        tec.nextLine();

        try {
            String consultaSQL = "INSERT INTO pedido (idpizza, idusuario, idpedido, cantidad) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(consultaSQL)) {
            	statement.setInt(1, idpizza);
            	statement.setInt(2, idusuario);
            	statement.setInt(3, idpedido);
            	statement.setInt(4, cantidad);

                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("pedido agregado con éxito.");
                } else {
                    System.out.println("no se pudo agregar el pedido");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al incluir el pedido en la base de datos.");
        } finally {
        }
    }
    
}