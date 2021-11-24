package co.edu.uniquindio.marketplace.model;

public class Usuario {

	private String usuario, contrasena;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	public Usuario(String usuario, String contrasena) {
		
		this.usuario = usuario;
		this.contrasena = contrasena;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}
	
	
	
}
