package co.edu.uniquindio.marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;

public interface IMarketplaceService {

	
	public Producto crearProducto(String nombre, String categoria, double precio, EstadoProducto estadoProducto, String pathImagen, Vendedor vendedor) throws ProductoException;
	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio, EstadoProducto estadoProducto, Vendedor vendedor);
	public Boolean eliminarProducto(String nombre, Vendedor vendedor) throws ProductoException;
	public Producto obtenerProducto (String nombre, Vendedor vendedor);
	public ArrayList<Producto> obtenerProductos();
	
}
