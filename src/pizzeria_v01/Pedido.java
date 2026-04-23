package pizzeria_v01;

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
	
}
