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
import co.edu.uniquindio.marketplace.exceptions.SolicitudVendedorException;
import co.edu.uniquindio.marketplace.model.EstadoProducto;



public class ModelFactoryController implements IModelFactoryService, Runnable{
	
	Marketplace marketplace;
	Thread hiloGuardarResourceXML;
	BoundedSemaphore semaforo;
	
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
			
			semaforo = new BoundedSemaphore(1);
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
		return marketplace.getListaVendedores();
	}
	
	public ArrayList<Vendedor> obtenerVendedoresNoAsociados(Vendedor vendedorPrincipal) {
		ArrayList<Vendedor>listaVendedores = getMarketplace().getListaVendedores();
		ArrayList<Vendedor>listaVendedoresNoAsociados = new ArrayList<>();
		
		for (Vendedor vendedor : listaVendedores) {
			
			if( ! vendedorPrincipal.getListaVendedoresAsociados().contains(vendedor) 
					&& ! vendedorPrincipal.getListaVendedoresSolicitudes().contains(vendedor)
					&& ! vendedor.getCedula().equals(vendedorPrincipal.getCedula())){
				listaVendedoresNoAsociados.add(vendedor);
			}
		}
		return listaVendedoresNoAsociados;
	}
	
	public ArrayList<Vendedor> obtenerSolicitudesVendedores(Vendedor vendedorPrincipal) {
		return vendedorPrincipal.getListaVendedoresSolicitudes();
	}

	public void enviarSolicitud(Vendedor vendedorPrincipal, Vendedor vendedor) throws SolicitudVendedorException{
		
		if (vendedor.getListaVendedoresSolicitudes().contains(vendedorPrincipal)){
    		throw new SolicitudVendedorException("Ya tiene una solicitud");
    	}
    	
		ArrayList<Vendedor>listaVendedores = getMarketplace().getListaVendedores();

		for (Vendedor vendedor2 : listaVendedores) {
			
			if (vendedorPrincipal.getCedula().equals(vendedor2.getCedula())){
				vendedor2.getListaSolicitudesEnviadas().add(buscarVendedor(vendedor.getCedula()));
				
			}
			if (vendedor.getCedula().equals(vendedor2.getCedula())){
				vendedor2.getListaVendedoresSolicitudes().add(buscarVendedor(vendedorPrincipal.getCedula()));
			}
		}	
	}
	
	public boolean aceptarSolicitud(Vendedor vendedorPrincipal, Vendedor solicitudSeleccionado) {
		boolean flagAceptado=false;
		
		if (solicitudSeleccionado.getListaSolicitudesEnviadas().contains(vendedorPrincipal)){
    		vendedorPrincipal.getListaVendedoresSolicitudes().remove(solicitudSeleccionado);
    	}
		
		ArrayList<Vendedor>listaVendedores = getMarketplace().getListaVendedores();

		for (Vendedor vendedor2 : listaVendedores) {
			
			if (vendedorPrincipal.getCedula().equals(vendedor2.getCedula())){
				vendedor2.getListaVendedoresAsociados().add(buscarVendedor(solicitudSeleccionado.getCedula()));
				obtenerVendedoresNoAsociados(vendedorPrincipal).remove(solicitudSeleccionado);
				flagAceptado=true;
			}
			if (solicitudSeleccionado.getCedula().equals(vendedor2.getCedula())){
				vendedor2.getListaVendedoresAsociados().add(buscarVendedor(vendedorPrincipal.getCedula()));
				obtenerVendedoresNoAsociados(solicitudSeleccionado).remove(vendedorPrincipal);
				flagAceptado = true;
			}
		}
		return flagAceptado;
	}
	
	public boolean rechazarSolicitud(Vendedor vendedorPrincipal, Vendedor solicitudSeleccionado) {
		boolean flagRechazar = false;
		if (solicitudSeleccionado.getListaSolicitudesEnviadas().contains(vendedorPrincipal)){
			vendedorPrincipal.getListaVendedoresSolicitudes().remove(solicitudSeleccionado);
			flagRechazar = true;
		}
		return flagRechazar;
	}
	
	public ArrayList<Vendedor> obtenerVendedoresAsociados(Vendedor vendedorPrincipal) {
		return vendedorPrincipal.getListaVendedoresAsociados();
	}
	
	public Vendedor buscarVendedor(String cedula){
		ArrayList<Vendedor>listaVendedores = getMarketplace().getListaVendedores();

		for (Vendedor vendedor : listaVendedores) {
			if (cedula.equals(vendedor.getCedula())){
				return vendedor;
			}
			
		}
		return null;
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

		hiloGuardarResourceXML = new Thread(this);
		hiloGuardarResourceXML.start();
		
	}
	
	public void cargarResourceXML() {

		marketplace = Persistencia.cargarRecursoMarketplaceXML();
	}

	@Override
	public void run() {

		Thread hiloActual = Thread.currentThread();
		
		try {
			semaforo.ocupar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hiloActual==hiloGuardarResourceXML){
			Persistencia.guardarRecursoMarketplaceXML(marketplace);
		}
	}

	

	

	

	

}
