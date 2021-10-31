package co.edu.uniquindio.marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.exceptions.VendedorException;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;

public interface IMarketplaceService {

	
	public Producto crearProducto(String nombre, String categoria, double precio, EstadoProducto estadoProducto, String pathImagen) throws ProductoException;
	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio, EstadoProducto estadoProducto);
	public Boolean eliminarProducto(String nombre) throws ProductoException;
	public boolean verificarProductoExistente(String nombre);
	public Producto obtenerProducto (String nombre);
	public ArrayList<Producto> obtenerProductos();
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion)throws VendedorException;
	
}
