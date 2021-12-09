package co.edu.uniquindio.marketplace.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.exceptions.LoginException;
import co.edu.uniquindio.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {

	Aplicacion aplicacion;
	Vendedor vendedor;
	ModelFactoryController modelFactoryController;
	Stage configStage;
	ConfiViewController confiViewController;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUsuarioLogin;

    @FXML
    private Button btnIngresarLogin;

    @FXML
    private Label LblError;

    @FXML
    private PasswordField txtContrasenaLogin;

    @FXML
    void userLoginAction(ActionEvent event) {
    	String usuario = txtUsuarioLogin.getText();
    	String contrasena = txtContrasenaLogin.getText();
    	try {
			Usuario u = modelFactoryController.autenticarUsuario(usuario, contrasena);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(u.getView()));
			
			Scene scene = new Scene(loader.load(), 1366, 700);
	        Stage stage = new Stage();
	        stage.setTitle("Marketplace");
	        stage.setScene(scene);
	        
			
			ConfiViewController confiViewController = (ConfiViewController)loader.getController();
			confiViewController.setAplicacion(aplicacion);
			confiViewController.setUsuario(u);
			confiViewController.refrescarTab();
			stage.setOnCloseRequest(event1 -> {
				  aplicacion.showPrimaryStage();
			});
			
	        stage.show();
			aplicacion.hidePrimaryStage();
		} catch (LoginException e) {
			e.printStackTrace();
			mostrarMensaje("Notificación Usuario","Datos invalidos",e.getMessage(), Alert.AlertType.ERROR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
    	modelFactoryController = ModelFactoryController.getInstance();

    }

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

		Alert aler = new Alert(alertType);
		aler.setTitle(titulo);
		aler.setHeaderText(header);
		aler.setContentText(contenido);
		aler.showAndWait();
	}
}


