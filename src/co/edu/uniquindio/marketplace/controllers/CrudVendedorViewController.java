package co.edu.uniquindio.marketplace.controllers;

import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.model.Marketplace;
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
	
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String pathImage) {

		return modelFactoryController.crearVendedor(nombre,apellido,cedula,direccion,pathImage);
	}
	public void guardarDatos() {
		modelFactoryController.guardarResourceXML();
	}
	
	public void registrarAccion(String mensaje, int nivel, String accion) {
		// TODO Auto-generated method stub
		modelFactoryController.registrarAccionesSistema(mensaje, nivel, accion);
	}
	
}
