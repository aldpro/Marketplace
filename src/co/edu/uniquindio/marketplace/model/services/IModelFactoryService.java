package co.edu.uniquindio.marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;

public interface IModelFactoryService {

	
	public Producto crearProducto(String nombre, String categoria, double precio, EstadoProducto estadoProducto, String pathImagen, Vendedor vendedor);
	public Boolean eliminarProducto(String nombre, Vendedor vendedor);
	public Producto obtenerProducto(String nombre);
	public ArrayList<Producto> obtenerProductos(Vendedor vendedor);
	boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio,
			EstadoProducto estadoProducto, Vendedor vendedor);
	
	
}
