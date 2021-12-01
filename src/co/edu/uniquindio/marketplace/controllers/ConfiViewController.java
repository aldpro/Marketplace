package co.edu.uniquindio.marketplace.controllers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class ConfiViewController {
	
	Aplicacion aplicacion;
	ModelFactoryController modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	ArrayList<VendedorViewController> vendedorControllers = new ArrayList<>();
	
	String pathImage;
	
	Usuario usuario;

	@FXML
	private TabPane tabPane;

	@FXML
	private AnchorPane main;

	@FXML
	private TextField txtNombreVendedor;
	
	@FXML
	private TextField txtUsuarioVendedor;
	
	@FXML
	private TextField txtContrasenaVendedor;
  
	@FXML
	private TextField txtApellidoVendedor;
  
	@FXML
	private TextField txtCedulaVendedor;
  
	@FXML
	private TextField txtDireccionVendedor;
  
	@FXML
	private Button btnCrearVendedor;
	
	@FXML
	private Button btnInsertarFotoPerfil;
  
	@FXML
	private Circle circleImagenPerfilInicio;

	public ConfiViewController() {

	}

	public Aplicacion getAplicacion() {
			return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
			this.aplicacion = aplicacion;
			for (VendedorViewController controller: vendedorControllers) {
				controller.setAplicacion(aplicacion);
			}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@FXML
	void crearVendedorAction(ActionEvent event) {
  
		crearVendedor();
	}
  
	@FXML
	void insertarFotoPerfilAction(ActionEvent event) {
  
		FileChooser fileChooser = new FileChooser();
		
		
		
		// Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(aplicacion.getPrimaryStage());
		if (file != null){
			Image image = new Image(file.toURI().toString(), 300, 300, false, true);
			circleImagenPerfilInicio.setFill(new ImagePattern(image));
			pathImage = file.getAbsolutePath();
		}
	}

	private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}

	private boolean validarDatosVendedor(String nombre, String apellido, String cedula, String direccion, String pathImage2, String usuario, String contrasena) {

		String mensaje = "";


		if(nombre == null || nombre.equals(""))
			mensaje += "El nombre es invalido \n" ;

		if(apellido == null || apellido.equals(""))
			mensaje += "La categoria es invalida \n" ;

		if(cedula == null || cedula.equals(""))
			mensaje += "La categoria es invalida \n" ;

		if(direccion == null || direccion.equals(""))
			mensaje += "La categoria es invalida \n" ;
		
		if(usuario == null || usuario.equals(""))
			mensaje += "El usuario o contraseña es invalido \n" ;
		
		if(contrasena == null || contrasena.equals(""))
			mensaje += "El usuario o contraseña es invalido \n" ;

		if(mensaje.equals("")){
			return true;
		}else{
			mostrarMensaje("Notificación Vendedor","Datos invalidos",mensaje, Alert.AlertType.WARNING);
			return false;
		}
	}

	private void crearVendedor() {
		  
	  //1. Capturar los datos
	  String nombre = txtNombreVendedor.getText();
	  String apellido = txtApellidoVendedor.getText();
	  String cedula  = txtCedulaVendedor.getText();
	  String direccion = txtDireccionVendedor.getText();
	  String usuario = txtUsuarioVendedor.getText();
	  String contrasena = txtContrasenaVendedor.getText();
	  
	  //2. Validar la informacion
	  if (validarDatosVendedor(nombre, apellido, cedula, direccion, pathImage,usuario,contrasena)) {
		  
		  Vendedor vendedor = null;
		  vendedor = crudVendedorViewController.crearVendedor(nombre, apellido, cedula, direccion, pathImage, usuario, contrasena);
		  
		  if (vendedor != null) {
			  listaVendedoresData.add(vendedor);
			  limpiarCamposVendedores();
			  crudVendedorViewController.guardarDatos();
			  crudVendedorViewController.registrarAccion("El vendedor se ha creado con exito",1,"Crear vendedor");

			  FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VendedorView.fxml"));
			  try {
				  // crear tab
				  Tab tab = new Tab("Vendedor "+ vendedor.getNombre(), loader.load());
				  // Guardar controlador
				  VendedorViewController controller = loader.getController();
				  // Pasar el vendedor al nuevo controlador para cargar datos
				  controller.setVendedor(vendedor);
				  controller.setAplicacion(aplicacion);
				  refrescarVendedoresNoAsociados();
				  vendedorControllers.add(controller);
				  //Add to tabPane
				  tabPane.getTabs().add(tab);
			
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
			  mostrarMensaje("Notificacion de vendedor", "Vendedor creado", "El Vendedor se ha creado con exito", Alert.AlertType.INFORMATION);

		  }else {
			  mostrarMensaje("Notificacion de vendedor", "Vendedor no creado", "El vendedor no se ha creado con exito", Alert.AlertType.INFORMATION);
		  }
	  }else {
			  mostrarMensaje("Notificacion de vendedor", "vendedor no creado", "Los datos ingresados no son invalidos", Alert.AlertType.ERROR);
  
	  }
	  
  }

	private void refrescarVendedoresNoAsociados(){
		for (VendedorViewController vendedorViewController : vendedorControllers) {
			vendedorViewController.refrescarVendedoresNoAsociados();
		}
	}
	private void limpiarCamposVendedores() {
		txtNombreVendedor.setText("");
        txtApellidoVendedor.setText("");
        txtCedulaVendedor.setText("");
        txtDireccionVendedor.setText("");
        circleImagenPerfilInicio.setFill(null);
        txtUsuarioVendedor.setText("");
        txtContrasenaVendedor.setText("");
		
	}

	@FXML
    void initialize() {
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
    	inicializarVendedor();
    	
//    	colocarImagenBoton();
    }
	
	public void refrescarTab(){
		if (usuario.getRol().equals("vendedor")){
			tabPane.getTabs().remove(0);
		}
		
	}
	
	public ObservableList<Vendedor> getListaVendedoresData() {
        listaVendedoresData.addAll(crudVendedorViewController.obtenerVendedores());
        return listaVendedoresData;
	}
	
	void inicializarVendedor(){
		listaVendedoresData.addAll(crudVendedorViewController.obtenerVendedores());
		
		for (int i = 0; i< listaVendedoresData.size(); i++){
			
			Vendedor vendedor = listaVendedoresData.get(i);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VendedorView.fxml"));
			try {
				// crear tab
				Tab tab = new Tab("Vendedor "+ vendedor.getNombre(), loader.load());
				// Guardar controlador
				VendedorViewController vendedorViewController = loader.getController();
				// Pasar el vendedor al nuevo controlador para cargar datos
				vendedorViewController.setVendedor(vendedor);
				vendedorControllers.add(vendedorViewController);
				//add to tapPane
				tabPane.getTabs().add(tab);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
}
