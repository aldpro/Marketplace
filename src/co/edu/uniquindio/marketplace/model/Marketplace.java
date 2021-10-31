package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.exceptions.VendedorException;
import co.edu.uniquindio.marketplace.model.services.IMarketplaceService;

public class Marketplace implements IMarketplaceService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	ArrayList<Vendedor> listaVendedores = new ArrayList<>(); //Limitada a una red de m�ximo 10 vendedores
	ArrayList<Producto> listaProductos = new ArrayList<>();

	public Marketplace() {
		
	}

	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@Override
	public Producto crearProducto(String nombre, String categoria, double precio,
			EstadoProducto estadoProducto, String pathImagen) throws ProductoException {

		Producto nuevoProducto = null;
		boolean flagProductoExiste = false;
		
		flagProductoExiste = verificarProductoExistente(nombre);
		
		if (flagProductoExiste == true) {
			throw new ProductoException("El producto con nombre "+nombre+" no se puede crear. Ya existe");
			
		}else {
			
			nuevoProducto = new Producto();
			nuevoProducto.setNombre(nombre);
			nuevoProducto.setCategoria(categoria);
			nuevoProducto.setPrecio(precio);
			nuevoProducto.setEstadoProducto(estadoProducto);
			nuevoProducto.setImagen(pathImagen);
			getListaProductos().add(nuevoProducto);
			
		}
		
		
		return nuevoProducto;
	}

	@Override
	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio,
			EstadoProducto estadoProducto) {

		Producto producto = null;
		boolean productoActualizado = false;
		
		
		producto=obtenerProducto(nombreActual);
		
		if(producto != null) {
			
			producto.setNombre(nombre);
			producto.setCategoria(categoria);
			producto.setPrecio(precio);
			producto.setEstadoProducto(estadoProducto);
			productoActualizado = true;
			
		}
		
		return productoActualizado;
		
	}

	@Override
	public Boolean eliminarProducto(String nombre) throws ProductoException {

		boolean flagEliminado = false;
		Producto producto = null;
		
		producto=obtenerProducto(nombre);
		
		if(producto != null) {
			
			getListaProductos().remove(producto);
			flagEliminado = true;
		}else {
			throw new ProductoException("El producto con nombre "+nombre+" no se puede eliminar. No existe");
		}
		
		
		return flagEliminado;
		
	}

	@Override
	public boolean verificarProductoExistente(String nombre) {

		boolean flagProductoExiste = false;
		
		for (Producto producto : listaProductos) {
			
			if(producto.getNombre().equalsIgnoreCase(nombre)) {
				flagProductoExiste = true;
				break;
			}
		}
		
		return flagProductoExiste;
	}

	@Override
	public ArrayList<Producto> obtenerProductos() {
		return null;
	}

	@Override
	public Producto obtenerProducto(String nombre) {
		
		Producto productoEncontrado = null;
		
		for (Producto producto : listaProductos) {
			
			if(producto.getNombre().equalsIgnoreCase(nombre)) {
				productoEncontrado = producto;
				break;
			}
		}
		
		
		return productoEncontrado;
	}

	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) throws VendedorException {

		Vendedor nuevoVendedor = null;
		boolean flagVendedorExiste = false;

		flagVendedorExiste = verificarVendedorExistente(cedula);

		if (flagVendedorExiste == true) {
			throw new VendedorException("El vendedor con cedula "+cedula+" no se puede crear. Ya existe");

		}else {

			nuevoVendedor = new Vendedor();
			nuevoVendedor.setNombre(nombre);
			nuevoVendedor.setApellido(apellido);
			nuevoVendedor.setCedula(cedula);
			nuevoVendedor.setDireccion(direccion);
			getListaVendedores().add(nuevoVendedor);

		}


		return nuevoVendedor;
	}

	private boolean verificarVendedorExistente(String cedula) {
		boolean flagVendedorExistente = false;

		for (Vendedor vendedor : listaVendedores) {

			if(vendedor.getCedula().equalsIgnoreCase(cedula)) {
				flagVendedorExistente = true;
				break;
			}
		}

		return flagVendedorExistente;
	}
	
	
}
