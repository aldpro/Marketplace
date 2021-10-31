package co.edu.uniquindio.marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Producto;

public interface IModelFactoryService {

	
	public Producto crearProducto(String nombre, String categoria, double precio, EstadoProducto estadoProducto, String pathImagen);
	public Boolean eliminarProducto(String nombre);
	public Producto obtenerProducto(String nombre);
	public ArrayList<Producto> obtenerProductos();
	boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio,
			EstadoProducto estadoProducto);
	
	
}
