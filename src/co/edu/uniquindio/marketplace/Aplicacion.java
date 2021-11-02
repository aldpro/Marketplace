package co.edu.uniquindio.marketplace;
	
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.marketplace.controllers.ConfiViewController;
import co.edu.uniquindio.marketplace.controllers.VendedorViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Aplicacion extends Application {
	private Stage primaryStage;

	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Marketplace");
		mostrarVentanaPrincipal();
	}

	/*
	@Override
	public void start(Stage stage) {
		TabPane tabPane = new TabPane();
		ArrayList<VendedorController> controllerArrayList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			//Don't just load it into the new node save a reference
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/vendedor.fxml"));
			try {
				//Load it into the new parent node
				Tab tab = new Tab("Tab:"+i, loader.load());
				//Save contoller to arraylist of controllers
				controllerArrayList.add(loader.getController());
				//Add to tabPane
				tabPane.getTabs().add(tab);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//Do some stuff with your contollers
		int index = 0;


		Scene scene = new Scene(tabPane);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}*/

	public static void main(String[] args) {
		launch(args);
	}
	
	
	/** * Initializes the root layout. */
	public void mostrarVentanaPrincipal() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/ConfiView.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			ConfiViewController marketplaceViewController = loader.getController();

			marketplaceViewController.setAplicacion(this);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			//scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	
}
