package co.edu.uniquindio.marketplace;
	
import java.io.IOException;
import co.edu.uniquindio.marketplace.controllers.ConfiViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
			ConfiViewController confiViewController = loader.getController();

			confiViewController.setAplicacion(this);
			

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
