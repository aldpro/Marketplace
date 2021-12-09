package co.edu.uniquindio.marketplace.controllers;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;

public class CrudProductoViewController {
	
	Aplicacion aplicacion;
	
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	public CrudProductoViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	public void setMarketplace (Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public ArrayList<Producto> obtenerProductos(Vendedor vendedor) {
		
		return modelFactoryController.obtenerProductos(vendedor);
	}
	
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Producto crearProducto(String nombre, String categoria, Double precio,
			EstadoProducto estadoProducto, String pathImagen, Vendedor vendedor) {
		
		return modelFactoryController.crearProducto(nombre, categoria, precio, estadoProducto, pathImagen,vendedor);
	}

	public boolean eliminarProducto(String nombre, Vendedor vendedor) {
		return modelFactoryController.eliminarProducto(nombre, vendedor);
	}

	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, Double precio,
			EstadoProducto estadoProducto, Vendedor vendedor) {

		return modelFactoryController.actualizarProducto(nombreActual, nombre, categoria, precio, estadoProducto, vendedor);
	}

	public void guardarDatos() {
		modelFactoryController.guardarResourceXML();
	}

	public void registrarAccion(String mensaje, int nivel, String accion) {
		// TODO Auto-generated method stub
		modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
	}

	public ArrayList<Producto> obtenerPublicacion(Vendedor vendedor) {
		return modelFactoryController.obtenerPublicacion(vendedor);
	}

	public Producto crearPublicacion(String nombre, String categoria, Double precio, EstadoProducto estadoProducto,
			String pathImagen, Vendedor vendedor) throws ProductoException {
		
		return modelFactoryController.crearPublicacion(nombre,categoria,precio,estadoProducto,pathImagen,vendedor);
	}

	public boolean agregarPublicacion(Vendedor vendedor, Producto productoSeleccionado) {
		return modelFactoryController.agregarPublicacion(vendedor,productoSeleccionado);
	}

	
	
}
