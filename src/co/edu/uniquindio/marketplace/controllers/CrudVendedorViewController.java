package co.edu.uniquindio.marketplace.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.exceptions.SolicitudVendedorException;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Vendedor;

public class CrudVendedorViewController {
	
	Aplicacion aplicacion;
	
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	public CrudVendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	public void setMarketplace (Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	
	public ArrayList<Vendedor> obtenerVendedores() {
		
		return modelFactoryController.obtenerVendedores();
	}
	
	public ArrayList<Vendedor> obtenerVendedoresNoAsociados(Vendedor vendedor) {
		
		return modelFactoryController.obtenerVendedoresNoAsociados(vendedor);
	}
	
	public ArrayList<Vendedor> obtenerSolicitudesVendedores(Vendedor vendedorPrincipal) {
		return modelFactoryController.obtenerSolicitudesVendedores(vendedorPrincipal);
	}
	
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	public void enviarSolicitud(Vendedor vendedorPrincipal, Vendedor vendedor) throws SolicitudVendedorException{
		modelFactoryController.enviarSolicitud(vendedorPrincipal, vendedor);
	}
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String pathImage, String usuario, String contrasena) {

		return modelFactoryController.crearVendedor(nombre,apellido,cedula,direccion,pathImage,usuario,contrasena);
	}
	public void guardarDatos() {
		modelFactoryController.guardarResourceXML();
	}
	
	public void registrarAccion(String mensaje, int nivel, String accion) {
		modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
	}

	
	
}
