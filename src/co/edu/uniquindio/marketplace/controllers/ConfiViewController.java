package co.edu.uniquindio.marketplace.controllers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.Aplicacion;
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
import javafx.stage.Stage;

public class ConfiViewController {
	
	Aplicacion aplicacion;
	ModelFactoryController modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	ArrayList<VendedorViewController> vendedorControllers = new ArrayList<>();

	@FXML
	private TabPane tabPane;

	@FXML
	private AnchorPane main;

	@FXML
	private TextField txtNombreVendedor;
  
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
	}
  
	@FXML
	void crearVendedorAction(ActionEvent event) {
  
		crearVendedor();
	}
  
	@FXML
	void insertarFotoPerfilAction(ActionEvent event) {
  
		FileChooser open = new FileChooser();
		Stage stage = (Stage)main.getScene().getWindow();
		File file = open.showOpenDialog(stage);
		if (file != null){
			Image image = new Image(file.toURI().toString(), 300, 300, false, true);
			circleImagenPerfilInicio.setFill(new ImagePattern(image));
		}else{
			System.out.println("No existe el archivo");
		}
	}

	private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}

	private boolean validarDatosVendedor(String nombre, String apellido, String cedula, String direccion) {

		String mensaje = "";


		if(nombre == null || nombre.equals(""))
			mensaje += "El nombre es invalido \n" ;

		if(apellido == null || apellido.equals(""))
			mensaje += "La categoria es invalida \n" ;

		if(cedula == null || cedula.equals(""))
			mensaje += "La categoria es invalida \n" ;

		if(direccion == null || direccion.equals(""))
			mensaje += "La categoria es invalida \n" ;

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
	  
	  //2. Validar la informacion
	  if (validarDatosVendedor(nombre, apellido, cedula, direccion) == true) {
		  
		  Vendedor vendedor = null;
		  vendedor = crudVendedorViewController.crearVendedor(nombre, apellido, cedula, direccion);
		  
		  if (vendedor != null) {
			  listaVendedoresData.add(vendedor);
			  limpiarCamposVendedores();

			  FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VendedorView.fxml"));
			  try {
				  // crear tab
				  Tab tab = new Tab("Vendedor "+ vendedor.getNombre(), loader.load());
				  // Guardar controlador
				  VendedorViewController controller = loader.getController();
				  // Pasar el vendedor al nuevo controlador para cargar datos
				  controller.setVendedor(vendedor);
				  vendedorControllers.add(controller);
				  //Add to tabPane
				  tabPane.getTabs().add(tab);
			  } catch (IOException e) {
				  e.printStackTrace();
			  }

  //    			crudProductoViewController.guardarDatos();
  //    			crudProductoViewController.registrarAccion("El producto se ha creado con �xito",1,"crear producto");
			  mostrarMensaje("Notificacion de vendedor", "Vendedor creado", "El Vendedor se ha creado con exito", Alert.AlertType.INFORMATION);

		  }else {
			  mostrarMensaje("Notificacion de vendedor", "Vendedor no creado", "El vendedor no se ha creado con exito", Alert.AlertType.INFORMATION);
		  }
	  }else {
			  mostrarMensaje("Notificacion de vendedor", "vendedor no creado", "Los datos ingresados no son invalidos", Alert.AlertType.ERROR);
  
	  }
	  
  }

	private void limpiarCamposVendedores() {
		txtNombreVendedor.setText("");
        txtApellidoVendedor.setText("");
        txtCedulaVendedor.setText("");
        txtDireccionVendedor.setText("");
		
	}

	@FXML
    void initialize() {
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
    	inicializarVendedor();
//    	colocarImagenBoton();
    }
	
	void inicializarVendedor(){
		
		for (int i = 0; i< listaVendedoresData.size(); i++){
			Vendedor vendedor = listaVendedoresData.get(i);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VendedorView.fxml"));
			try {
				// crear tab
				Tab tab = new Tab("Vendedor "+ vendedor.getNombre(), loader.load());
				// Guardar controlador
				VendedorViewController controller = loader.getController();
				// Pasar el vendedor al nuevo controlador para cargar datos
				controller.setVendedor(vendedor);
				vendedorControllers.add(controller);
				//add to tapPane
				tabPane.getTabs().add(tab);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
}
