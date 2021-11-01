package co.edu.uniquindio.marketplace.persistencia;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.marketplace.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.model.Vendedor;



public class Persistencia {

	public static final String RUTA_ARCHIVO_PRODUCTOS = "C://td//persistencia//archivos//archivoProductos.txt";
	public static final String RUTA_ARCHIVO_VENDEDORES = "C://td//persistencia//archivos//archivoVendedores.txt";
//	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "C://td//persistencia//log//MarketplaceLog.txt";
//	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "C://td//persistencia//model.dat";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_XML = "C://td//persistencia//model.xml";




	public static void cargarDatosArchivos(Marketplace marketplace) throws FileNotFoundException, IOException {


		//cargar archivo de productos
		ArrayList<Producto> productosCargados = cargarProductos();

		if(productosCargados.size() > 0)
			marketplace.getListaProductos().addAll(productosCargados);


		//cargar archivos vendedores
		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();

		if(vendedoresCargados.size() > 0)
			marketplace.getListaVendedores().addAll(vendedoresCargados);


		//cargar archivo objetos

		//cargar archivo empleados

		//cargar archivo prestamo

	}


	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";

		for(Producto producto:listaProductos)
		{
			contenido+= producto.getNombre()+"@@"+producto.getImagen()+"@@"+producto.getCategoria()+"@@"+
		   producto.getPrecio()+"@@"+producto.getEstadoProducto()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);

	}


	public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {

		// TODO Auto-generated method stub
		String contenido = "";

		for(Vendedor vendedor:listaVendedores)
		{
			contenido+= vendedor.getNombre()+"@@"+vendedor.getApellido()+"@@"+vendedor.getCedula()+"@@"+vendedor.getDireccion()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
	}



//	----------------------LOADS------------------------

	/**
	 *
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Producto> cargarProductos() throws FileNotFoundException, IOException
	{
		ArrayList<Producto> listaproductos =new ArrayList<Producto>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";
		String enun="";

		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			String[]campos = linea.split("@@");
			
			Producto producto = new Producto();
			producto.setNombre(campos[0]);
			producto.setImagen(campos[1]);
			producto.setCategoria(campos[2]);
			producto.setPrecio(Double.parseDouble(campos[3]));
			enun = campos[4];
			producto.setEstadoProducto(verificarEnumeracion(enun));
			listaproductos.add(producto);
		}
		return listaproductos;
	}

	private static EstadoProducto verificarEnumeracion(String enun) {

		if(enun.equals(EstadoProducto.CANCELADO)){
			return EstadoProducto.CANCELADO;
		}
		if (enun.equals(EstadoProducto.PUBLICADO)){
			return EstadoProducto.PUBLICADO;
		}
		if (enun.equals(EstadoProducto.VENDIDO)){
			return EstadoProducto.VENDIDO;
		}
		return null;
}


	private static ArrayList<Vendedor> cargarVendedores() throws FileNotFoundException, IOException {

		ArrayList<Vendedor> listaVendedores =new ArrayList<Vendedor>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea="";

		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@@")[0]);
			vendedor.setApellido(linea.split("@@")[1]);
			vendedor.setCedula(linea.split("@@")[2]);
			vendedor.setDireccion(linea.split("@@")[3]);
			listaVendedores.add(vendedor);
		}
		return listaVendedores;
	}


	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion){

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


//	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
//
//		if(validarUsuario(usuario,contrasenia)) {
//			return true;
//		}else {
//			throw new UsuarioExcepcion("Usuario no existe");
//		}
//
//	}
//
//	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
//	{
//		ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
//
//		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
//		{
//			Usuario usuarioAux = usuarios.get(indiceUsuario);
//			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
//		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
//
//		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
//		String linea="";
//
//		for (int i = 0; i < contenido.size(); i++) {
//			linea = contenido.get(i);
//
//			Usuario usuario = new Usuario();
//			usuario.setUsuario(linea.split(",")[0]);
//			usuario.setContrasenia(linea.split(",")[1]);
//
//			usuarios.add(usuario);
//		}
//		return usuarios;
//	}


//	----------------------SAVES------------------------

	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */

//	public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException  {
//		String contenido = "";
//
//		for(Cliente clienteAux:listaClientes) {
//			contenido+= clienteAux.getNombre()+","+clienteAux.getApellido()+","+clienteAux.getCedula()+clienteAux.getDireccion()
//					     +","+clienteAux.getCorreo()+","+clienteAux.getFechaNacimiento()+","+clienteAux.getTelefono()+"\n";
//		}
//		ArchivoUtil.guardarArchivo(ruta, contenido, true);
//	}





	//------------------------------------SERIALIZACIÓN  y XML


	public static Marketplace cargarRecursoMarketplaceBinario() {

		Marketplace marketplace = null;

		try {
			marketplace = (Marketplace)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketplace;
	}

	public static void guardarRecursoBancoBinario(Marketplace marketplace) {

		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, marketplace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static Marketplace cargarRecursoMarketplaceXML() {

		Marketplace marketplace = null;

		try {
			marketplace = (Marketplace)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return marketplace;

	}


	public static void guardarRecursoMarketplaceXML(Marketplace marketplace) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, marketplace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}










}
