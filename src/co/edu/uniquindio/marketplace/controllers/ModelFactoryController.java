package co.edu.uniquindio.marketplace.controllers;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.exceptions.VendedorException;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.model.services.IModelFactoryService;
import co.edu.uniquindio.marketplace.persistencia.Persistencia;
import co.edu.uniquindio.marketplace.exceptions.LoginException;
import co.edu.uniquindio.marketplace.exceptions.ProductoException;
import co.edu.uniquindio.marketplace.model.EstadoProducto;



public class ModelFactoryController implements IModelFactoryService{
	
	Marketplace marketplace;
	
	//------------------------------  Singleton ------------------------------------------------
		// Clase estatica oculta. Tan solo se instanciara el singleton una vez
		private static class SingletonHolder { 
			// El constructor de Singleton puede ser llamado desde aquï¿½ al ser protected
			private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
		}

		// Mï¿½todo para obtener la instancia de nuestra clase
		public static ModelFactoryController getInstance() {
			return SingletonHolder.eINSTANCE;
		}
		
		public ModelFactoryController() {
			
			
//			//1.Inicializar datos y luego guardarlo en el archivo
//			iniciarSalvarDatosPrueba();
//			
			//2. cargar los datos de los archivos
//			cargarDatosDesdeArchivos();
			
			//3. Guardar y cargar el recurso serializable Binario
//			guardarResourceBinario();
//			cargarResourceBinario();
			
			
			//4. Guardar y cargar el recurso serializable XML
			
			cargarResourceXML();
			
			if (marketplace == null){
				System.out.println("es null");
				inicializarDatosProductos();
				guardarResourceXML();
			}
			//Registar la accion de inicio de sesion
			registrarAccionesSistema("Inicio de sesion del usuario: Alejandro", 1, "Inicio de sesion");
			
		}

		
		public void registrarAccionesSistema(String mensaje, int nivel, String accion){
			Persistencia.guardaRegistroLog(mensaje, nivel, accion);
		}
		
	private void inicializarDatosProductos() {

		marketplace = new Marketplace();

        Producto producto = new Producto();
        producto.setCategoria("Hogar");
        producto.setNombre("Alfombra");
        producto.setImagen("C:\\td\\Imagenes\\alfombra.jpg");
        producto.setPrecio(20000);
        producto.setEstadoProducto(EstadoProducto.PUBLICADO);
        marketplace.getListaProductos().add(producto);

        producto = new Producto();
        producto.setCategoria("Hogar");
        producto.setNombre("Aspiradora");
        producto.setImagen("C:\\td\\Imagenes\\aspiradora.jpg");
        producto.setPrecio(30000);
        producto.setEstadoProducto(EstadoProducto.VENDIDO);
        marketplace.getListaProductos().add(producto);

        producto = new Producto();
        producto.setCategoria("Herramientas");
        producto.setNombre("Martillo");
        producto.setImagen("C:\\td\\Imagenes\\martillo.jpg");
        producto.setPrecio(40000);
        producto.setEstadoProducto(EstadoProducto.CANCELADO);
        marketplace.getListaProductos().add(producto);

        System.out.println("Marketplace inicializado "+ marketplace);
	}
	
	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	@Override
	public Producto crearProducto(String nombre, String categoria, double precio,
			EstadoProducto estadoProducto, String pathImagen) {
		
		Producto producto = null;
		
		try {
			producto = getMarketplace().crearProducto(nombre, categoria, precio, estadoProducto, pathImagen);
		} catch (ProductoException e) {
			e.getMessage();
		}
		return producto;
	}

	@Override
	public boolean actualizarProducto(String nombreActual, String nombre, String categoria, double precio,
			EstadoProducto estadoProducto) {
		
		return getMarketplace().actualizarProducto(nombreActual, nombre, categoria, precio, estadoProducto);
		
	}

	@Override
	public Boolean eliminarProducto(String nombre) {
		
		boolean flagProductoExiste = false;
		try {
			flagProductoExiste = getMarketplace().eliminarProducto(nombre);
		} catch (ProductoException e) {
			e.getMessage();
		}
		return flagProductoExiste;
	}
	

	@Override
	public Producto obtenerProducto(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Producto> obtenerProductos() {
		return marketplace.getListaProductos();
	}
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String pathImage, String usuario, String contrasena) {

		Vendedor vendedor = null;

		try {
			vendedor = getMarketplace().crearVendedor(nombre, apellido, cedula, direccion,pathImage,usuario,contrasena);
		} catch (VendedorException e) {
			e.getMessage();
		}
		return vendedor;
	}
	public ArrayList<Vendedor> obtenerVendedores() {
		// TODO Auto-generated method stub
		return marketplace.getListaVendedores();
	}

	public Usuario autenticarUsuario(String usuario, String contrasena) throws LoginException {
		ArrayList<Vendedor>listaVendedores = getMarketplace().getListaVendedores();
		if (usuario.equals("admin")&&contrasena.equals("admin")){
			return new Usuario(null,"../view/ConfiView.fxml","admin");
		}
    	for (Vendedor vendedor : listaVendedores) {
			if (usuario.equals(vendedor.getUsuario())&&contrasena.equals(vendedor.getContrasena())){
				return new Usuario(vendedor,"../view/ConfiView.fxml", "vendedor");
			}
		}
		throw new LoginException("El usuario o contraseña es inválido");
	}
	
	public void iniciarSalvarDatosPrueba() {	
		try{
			Persistencia.guardarProductos(getMarketplace().getListaProductos());	
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void cargarDatosDesdeArchivos() {

		marketplace = new Marketplace();
		try{
			Persistencia.cargarDatosArchivos(getMarketplace());
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void cargarResourceBinario() {
		
		marketplace = Persistencia.cargarRecursoMarketplaceBinario();
	}
	
	public void guardarResourceBinario() {
		
		Persistencia.guardarRecursoBancoBinario(marketplace);
	}
	
	public void guardarResourceXML() {

		Persistencia.guardarRecursoMarketplaceXML(marketplace);
	}
	
	public void cargarResourceXML() {

		marketplace = Persistencia.cargarRecursoMarketplaceXML();
	}

}
