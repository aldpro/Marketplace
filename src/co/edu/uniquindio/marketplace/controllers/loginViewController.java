package co.edu.uniquindio.marketplace.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginViewController {

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

    }

    @FXML
    void initialize() {
        

    }
}

