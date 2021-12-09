package co.edu.uniquindio.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.exceptions.VendedorException;
import co.edu.uniquindio.marketplace.model.services.IMarketplaceService;

public class Marketplace implements IMarketplaceService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static String fechaSistema;
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
			EstadoProducto estadoProducto, String pathImagen, Vendedor vendedor) throws ProductoException {

		Producto nuevoProducto = null;
			
		nuevoProducto = new Producto();
		nuevoProducto.setNombre(nombre);
		nuevoProducto.setCategoria(categoria);
		nuevoProducto.setPrecio(precio);
		nuevoProducto.setEstadoProducto(estadoProducto);
		nuevoProducto.setImagen(pathImagen);
		vendedor.getListaProductos().add(nuevoProducto);
		
		return nuevoProducto;
	}

	@Override
	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio,
			EstadoProducto estadoProducto,Vendedor vendedor) {

		Producto producto = null;
		boolean productoActualizado = false;
		
		
		producto=obtenerProducto(nombreActual,vendedor);
		
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
	public Boolean eliminarProducto(String nombre, Vendedor vendedor) throws ProductoException {

		boolean flagEliminado = false;
		Producto producto = null;
		
		producto=obtenerProducto(nombre, vendedor);
		
		if(producto != null) {
			
			vendedor.getListaProductos().remove(producto);
			flagEliminado = true;
		}else {
			throw new ProductoException("El producto con nombre "+nombre+" no se puede eliminar. No existe");
		}
		
		
		return flagEliminado;
		
	}


	@Override
	public ArrayList<Producto> obtenerProductos() {
		return null;
	}

	@Override
	public Producto obtenerProducto(String nombre, Vendedor vendedor) {
		
		Producto productoEncontrado = null;
		
		for (Vendedor vendedor2 : listaVendedores){
			if(vendedor.getCedula().equals(vendedor2.getCedula())) {
				for (Producto producto : vendedor.getListaProductos()) {
					if(nombre.equals(producto.getNombre())){
						productoEncontrado = producto;
					}
				}
			}
		}
		
		
		return productoEncontrado;
	}

	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String pathImage, String usuario, String contrasena) throws VendedorException {

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
			nuevoVendedor.setImagen(pathImage);
			nuevoVendedor.setUsuario(usuario);
			nuevoVendedor.setContrasena(contrasena);
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

	public Producto crearPublicacion(String nombre, String categoria, Double precio, EstadoProducto estadoProducto,
			String pathImagen, Vendedor vendedorPrincipal, Producto productoSeleccionado) {
		// TODO Auto-generated method stub
		return null;
	}

	public Producto crearPublicacion(String nombre, String categoria, Double precio, EstadoProducto estadoProducto,
			String pathImagen, Vendedor vendedor) throws ProductoException {
		Producto nuevoProducto = null;
			
		cargarFechaSistema();
		nuevoProducto = new Producto();
		nuevoProducto.setNombre(nombre);
		nuevoProducto.setCategoria(categoria);
		nuevoProducto.setPrecio(precio);
		nuevoProducto.setEstadoProducto(estadoProducto);
		nuevoProducto.setImagen(pathImagen);
		nuevoProducto.setFecha(fechaSistema);
		vendedor.getListaPublicaciones().add(nuevoProducto);
		
		return nuevoProducto;
	}
	
	private static void cargarFechaSistema() {

		String diaN = "";
		String mesN = "";
		

		Calendar cal1 = Calendar.getInstance();


		int  dia = cal1.get(Calendar.DATE);
		int mes = cal1.get(Calendar.MONTH)+1;
		int year = cal1.get(Calendar.YEAR);
		int hora = cal1.get(Calendar.HOUR);
		int minuto = cal1.get(Calendar.MINUTE);


		if(dia < 10){
			diaN+="0"+dia;
		}
		else{
			diaN+=""+dia;
		}
		if(mes < 10){
			mesN+="0"+mes;
		}
		else{
			mesN+=""+mes;
		}
		

		//		fecha_Actual+= a�o+"-"+mesN+"-"+ diaN;
		fechaSistema = year+"-"+mesN+"-"+diaN+"-"+hora+":"+minuto;
//		fechaSistema = year+"-"+mesN+"-"+diaN;
		//		horaFechaSistema = hora+"-"+minuto;
	}
	
}
