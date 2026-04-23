package pizzeria_v01;

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

	/*public void setId(int id) {
		this.id = id;
	}*/

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
	
}
