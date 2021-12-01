package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nombre, apellido, cedula, direccion,imagen, usuario, contrasena;
	ArrayList<Vendedor> listaSolicitudesEnviadas = new ArrayList<>();
	ArrayList<Producto> listaProductos = new ArrayList<>();
	ArrayList<Vendedor> listaVendedoresSolicitudes = new ArrayList<>();
	ArrayList<Vendedor> listaVendedoresAsociados = new ArrayList<>();
	
	public Vendedor() {
		
	}
	
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ArrayList<Vendedor> getListaVendedoresSolicitudes() {
		return listaVendedoresSolicitudes;
	}

	public void setListaVendedoresSolicitudes(ArrayList<Vendedor> listaVendedoresSolicitudes) {
		this.listaVendedoresSolicitudes = listaVendedoresSolicitudes;
	}

	public ArrayList<Vendedor> getListaVendedoresAsociados() {
		return listaVendedoresAsociados;
	}

	public void setListaVendedoresAsociados(ArrayList<Vendedor> listaVendedoresAsociados) {
		this.listaVendedoresAsociados = listaVendedoresAsociados;
	}
	

	public ArrayList<Vendedor> getListaSolicitudesEnviadas() {
		return listaSolicitudesEnviadas;
	}

	public void setListaSolicitudesEnviadas(ArrayList<Vendedor> listaSolicitudesEnviadas) {
		this.listaSolicitudesEnviadas = listaSolicitudesEnviadas;
	}

	public String getImagen() {
		return imagen;
	}
	

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	
	


	
}
