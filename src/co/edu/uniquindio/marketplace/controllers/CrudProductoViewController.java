package co.edu.uniquindio.marketplace.controllers;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;

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

	public ArrayList<Producto> obtenerProductos() {
		
		return modelFactoryController.obtenerProductos();
	}
	
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Producto crearProducto(String nombre, String categoria, Double precio,
			EstadoProducto estadoProducto, String pathImagen) {
		
		return modelFactoryController.crearProducto(nombre, categoria, precio, estadoProducto, pathImagen);
	}

	public boolean eliminarProducto(String nombre) {
		return modelFactoryController.eliminarProducto(nombre);
	}

	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, Double precio,
			EstadoProducto estadoProducto) {

		return modelFactoryController.actualizarProducto(nombreActual, nombre, categoria, precio, estadoProducto);
	}

	public void guardarDatos() {
		modelFactoryController.guardarResourceXML();
	}

	public void registrarAccion(String mensaje, int nivel, String accion) {
		// TODO Auto-generated method stub
		modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
	}

	
	
}
