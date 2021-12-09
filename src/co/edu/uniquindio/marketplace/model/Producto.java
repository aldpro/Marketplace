package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;


public class Producto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nombre, imagen, categoria;
	private double precio;
	private EstadoProducto estadoProducto;
	private Vendedor vendedor;
	private String fecha;
	
	public Producto() {
		
	}


	public Vendedor getVendedor() {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public EstadoProducto getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(EstadoProducto estadoProducto) {
		this.estadoProducto = estadoProducto;
	}
	
	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	

}
