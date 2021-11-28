package co.edu.uniquindio.marketplace.model;

public class Usuario {

	private Vendedor vendedor;
	private String view;
	private String rol;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	


	public Usuario(Vendedor vendedor, String view, String rol) {
		super();
		this.vendedor = vendedor;
		this.view = view;
		this.rol = rol;
	}



	public String getView() {
		return view;
	}


	public void setView(String view) {
		this.view = view;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	
}
