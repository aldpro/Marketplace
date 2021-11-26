package co.edu.uniquindio.marketplace.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import co.edu.uniquindio.marketplace.Aplicacion;
import co.edu.uniquindio.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.model.EstadoProducto;
import co.edu.uniquindio.marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class VendedorViewController {

        Aplicacion aplicacion;
        ModelFactoryController modelFactoryController;
        CrudProductoViewController crudProductoViewController;
        ConfiViewController confiViewController;

        ObservableList <Producto> listaProductosData = FXCollections.observableArrayList();
        Producto productoSeleccionado;
        FilteredList<Producto> filtrarDatosProducto;
        
        String pathImagen;

        private Vendedor vendedor;

        @FXML
        private TabPane tabPane;

        @FXML
        private Label lblNombreVendedor;

        @FXML
        private ImageView ivImagenProducto;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextArea txtAreaComentarios;

        @FXML
        private Circle circleImagenPerfil;

        @FXML
        private TextField txtBuscarVendedores;

        @FXML
        private TextField txtNombreProducto;

        @FXML
        private TextField txtBuscarProducto;

        @FXML
        private TextField txtCategoriaProducto;

        @FXML
        private TextField txtPrecioProducto;

        @FXML
        private ComboBox<EstadoProducto> cbEstadoProducto;

        @FXML
        private Button btnRechazarSolicitud;

        @FXML
        private Button btnFotoPerfil;

        @FXML
        private Button btnAddProducto;

        @FXML
        private Button btnActualizarProducto;

        @FXML
        private Button btnEliminarProducto;

        @FXML
        private Button btnSeleccionarImagen;

        @FXML
        private Button btnNuevoProducto;

        @FXML
        private Button btnAceptarSolicitud;

//    @FXML
//    private Button btnMeGusta;

        @FXML
        private TableView<?> tableNoAsociados;

        @FXML
        private TableView<?> tableSolicitudes;

        @FXML
        private TableView<?> tableAsociados;

        @FXML
        private TableView<Producto> tableProductos;

        @FXML
        private TableColumn<Producto, String> clNombreProducto;

        @FXML
        private TableColumn<Producto, String> columnUsuarioProducto;

        @FXML
        private TableColumn<Producto, String> clCategoriaProducto;

        @FXML
        private TableColumn<Producto, String> clPrecioProducto;

        @FXML
        private TableColumn<Producto, String> clEstadoProducto;


        //CREAR- A�ADIR PRODUCTO-----------------------------------------------------------------------------------------------------------

        @FXML
        void addProductoAction(ActionEvent event) {
                crearProducto();
        }

        //ELIMINAR PRODUCTO ----------------------------------------------------------------------------------------------------------------

        @FXML
        void eliminarProductoAction(ActionEvent event) {

                eliminarProducto();

        }

        //ACTUALIZAR PRODUCTO --------------------------------------------------------------------------------------------------------------

        @FXML
        void actualizarProductoAction(ActionEvent event) {
                actualizarProducto();
        }

        @FXML
        void nuevoProductoAction(ActionEvent event) {
                nuevoProducto();
        }

        @FXML
        void aceptarSolicitudAction(ActionEvent event) {

        }

        @FXML
        void rechazarSolicitudAction(ActionEvent event) {

        }

        @FXML
        void cambiarImagenPerfilAction(ActionEvent event) {

        }
        
        @FXML
        void seleccionarImagenAction(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Buscar Imagen");

                // Agregar filtros para facilitar la busqueda
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                // Obtener la imagen seleccionada
                File imgFile = fileChooser.showOpenDialog(aplicacion.getPrimaryStage());

                // Mostar la imagen
                if (imgFile != null) {
                        Image image = new Image("file:" + imgFile.getAbsolutePath());
                        ivImagenProducto.setImage(image);
                        pathImagen = imgFile.getAbsolutePath();
                }
        }

        @FXML
        void initialize() {
                modelFactoryController = ModelFactoryController.getInstance();
                crudProductoViewController = new CrudProductoViewController(modelFactoryController);
                inicializarProductoView();
//    	colocarImagenBoton();
        }



        private void inicializarProductoView() {
                this.clCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
                this.clEstadoProducto.setCellValueFactory(new PropertyValueFactory<>("estadoProducto"));
                this.clNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                this.clPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
                cbEstadoProducto.getItems().addAll(EstadoProducto.CANCELADO, EstadoProducto.PUBLICADO, EstadoProducto.VENDIDO);

                tableProductos.getItems().clear();
                tableProductos.setItems(getListaProductosData());

                tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
                        productoSeleccionado = newSelection;
                        FileInputStream inputStream;
                        try {
                                inputStream = new FileInputStream(productoSeleccionado.getImagen());
                                Image image =new Image(inputStream);
                                pathImagen = productoSeleccionado.getImagen();
                                mostrarInformacionProducto(productoSeleccionado, image);
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        }
                });
                filtrarDatosProducto = new FilteredList<>(listaProductosData, b-> true);
                txtBuscarProducto.textProperty().addListener((observable, oldValue, newValue)  -> {
                        filtrarDatosProducto.setPredicate(producto  -> {
                                if (newValue==null||newValue.isEmpty()){
                                        return true;
                                }
                                String lowerCaseFilter = newValue.toLowerCase();
                                if (producto.getNombre().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                                        return true;
                                }else if (producto.getCategoria().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                                        return true;
                                }else return false;
                        });
                });

        }

//	private void colocarImagenBoton(){
//		URL linkMeGusta = getClass().getResource("/Info/like.jpg");
//		Image imagenLike = new Image(linkMeGusta.toString(),24,24,false,true);
//		btnMeGusta.setGraphic(new ImageView(imagenLike));
//	}

        private void mostrarInformacionProducto(Producto productoSeleccionado, Image image) {

                if(productoSeleccionado != null) {
                        txtNombreProducto.setText(productoSeleccionado.getNombre());
                        txtCategoriaProducto.setText(productoSeleccionado.getCategoria());
                        cbEstadoProducto.setValue(productoSeleccionado.getEstadoProducto());
                        txtPrecioProducto.setText(String.valueOf(productoSeleccionado.getPrecio()));
                        ivImagenProducto.setImage(image);
                }
        }

        public VendedorViewController() {

        }

        public Aplicacion getAplicacion() {
                return aplicacion;
        }

        public void setAplicacion(Aplicacion aplicacion) {
                this.aplicacion = aplicacion;

                SortedList<Producto> sortedData = new SortedList<>(filtrarDatosProducto);
                sortedData.comparatorProperty().bind(tableProductos.comparatorProperty());
                tableProductos.setItems(sortedData);
        }

        public CrudProductoViewController getCrudProductoViewController() {
                return crudProductoViewController;
        }

        public void setCrudProductoViewController(CrudProductoViewController crudProductoViewController) {
                this.crudProductoViewController = crudProductoViewController;
        }

        public ObservableList<Producto> getListaProductosData() {
                listaProductosData.addAll(crudProductoViewController.obtenerProductos());
                return listaProductosData;
        }

        public void setListaProductosData(ObservableList<Producto> listaProductosData) {
                this.listaProductosData = listaProductosData;
        }

        public ModelFactoryController getModelFactoryController() {
                return modelFactoryController;
        }

        public void setModelFactoryController(ModelFactoryController modelFactoryController) {
                this.modelFactoryController = modelFactoryController;
        }

        public Producto getProductoSeleccionado() {
                return productoSeleccionado;
        }

        public void setProductoSeleccionado(Producto productoSeleccionado) {
                this.productoSeleccionado = productoSeleccionado;
        }

        //----------------------------------------------------------------------------------------------------------------------------------

        private void limpiarCamposProducto() {
                txtNombreProducto.setText("");
                txtCategoriaProducto.setText("");
                txtPrecioProducto.setText("");
                cbEstadoProducto.setValue(null);
                ivImagenProducto.setImage(null);
        }

        private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

                Alert aler = new Alert(alertType);
                aler.setTitle(titulo);
                aler.setHeaderText(header);
                aler.setContentText(contenido);
                aler.showAndWait();
        }

        private boolean mostrarMensajeConfirmacion(String mensaje) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmaci�n");
                alert.setContentText(mensaje);
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                        return true;
                } else {
                        return false;
                }
        }


        private boolean datosValidos(String nombre, String categoria,  Double precio, EstadoProducto estadoProducto, String pathImagen) {

                String mensaje = "";


                if(nombre == null || nombre.equals(""))
                        mensaje += "El nombre es invalido \n" ;

                if(categoria == null || categoria.equals(""))
                        mensaje += "La categoria es invalida \n" ;

                if(precio == null || String.valueOf(precio).equals(""))
                        mensaje += "El precio es invalido \n" ;

                if(estadoProducto == null)
                        mensaje += "El estado del producto es invalido \n" ;

                if (pathImagen == null){
                        mensaje += "La imagen es invalida \n";
                }

                if(mensaje.equals("")){
                        return true;
                }else{
                        mostrarMensaje("Notificaci�n Producto","Datos invalidos",mensaje, AlertType.WARNING);
                        return false;
                }
        }

        private void crearProducto() {

                //1. Capturar los datos

                String nombre = txtNombreProducto.getText();
                String categoria = txtCategoriaProducto.getText();
                Double precio = Double.parseDouble(txtPrecioProducto.getText());
                EstadoProducto estadoProducto = cbEstadoProducto.getValue();

                //2. Validar la informacion
                if (datosValidos(nombre, categoria, precio, estadoProducto, pathImagen) == true) {

                        Producto producto = null;
                        producto = crudProductoViewController.crearProducto(nombre, categoria, precio, estadoProducto, pathImagen);

                        if (producto != null) {
                                listaProductosData.add(producto);
                                crudProductoViewController.guardarDatos();
                                crudProductoViewController.registrarAccion("El producto se ha creado con exito",1,"crear producto");
                                mostrarMensaje("Notificacion de producto", "Producto creado", "El producto se ha creado con exito", AlertType.INFORMATION);
                                limpiarCamposProducto();
                        }else {
                                mostrarMensaje("Notificacion de producto", "Producto no creado", "El producto no se ha creado con exito", AlertType.INFORMATION);
                        }
                }else {
                        mostrarMensaje("Notificacion de producto", "Producto no creado", "Los datos ingresados son invalidos", AlertType.ERROR);

                }
        }

        private void eliminarProducto() {

                boolean productoEliminado = false;

                if(productoSeleccionado != null) {

                        if(mostrarMensajeConfirmacion("�Est� seguro de eliminar el producto seleccionado?")== true) {

                                productoEliminado = crudProductoViewController.eliminarProducto(productoSeleccionado.getNombre());

                                if(productoEliminado == true ) {
                                        listaProductosData.remove(productoSeleccionado);
                                        productoSeleccionado = null;
                                        crudProductoViewController.guardarDatos();
                                        crudProductoViewController.registrarAccion("El producto se ha eliminado con exito",1, "Eliminar producto");
                                        tableProductos.getSelectionModel().clearSelection();
                                        limpiarCamposProducto();
                                        mostrarMensaje("Notificaci�n de producto", "Producto eliminado", "El producto se ha eliminado con �xito", AlertType.INFORMATION);
                                }else {
                                        mostrarMensaje("Notificaci�n de producto", "Producto no eliminado", "El producto no se puede eliminado con �xito", AlertType.ERROR);
                                }

                        }

                }else {

                        mostrarMensaje("Notificaci�n de producto", "Producto no seleccionado", "Seleccione un producto de la lista", AlertType.WARNING);
                }

        }

        private void actualizarProducto() {

                //1. Capturar los datos
                String nombre = txtNombreProducto.getText();
                String categoria = txtCategoriaProducto.getText();
                Double precio = Double.parseDouble(txtPrecioProducto.getText());
                EstadoProducto estadoProducto = cbEstadoProducto.getValue();
                boolean productoActualizado = false;

                //2. Verificar el producto seleccionado
                if(productoSeleccionado != null) {

                        //3. Validar la informaci�n
                        if (datosValidos(nombre, categoria, precio, estadoProducto, pathImagen) == true) {

                                productoActualizado = crudProductoViewController.actualizarProducto(productoSeleccionado.getNombre(), nombre, categoria, precio, estadoProducto);


                                if (productoActualizado == true) {
                                        tableProductos.refresh();
                                        mostrarMensaje("Notificacion de producto", "Producto actualizado", "El producto se ha actualizado con exito", AlertType.INFORMATION);
                                        crudProductoViewController.guardarDatos();
                                        crudProductoViewController.registrarAccion("El producto no se ha actualizado con exito", 1, "Actualizar Producto");
                                        limpiarCamposProducto();
                                }else {
                                        mostrarMensaje("Notificacion de producto", "Producto no actualizado", "El producto no se ha actualizado con exito", AlertType.INFORMATION);
                                }
                        }else {
                                mostrarMensaje("Notificacion de producto", "Producto no actualizado", "Los datos ingresados son inv�lidos", AlertType.ERROR);

                        }
                }
        }

        private void nuevoProducto() {
                mostrarMensaje("Notificacion Producto", "Nuevo Producto", "Ingrese los datos del nuevo producto en los campos correspondientes, luego oprima <a�adir producto>.", AlertType.INFORMATION);
                limpiarCamposProducto();

        }

        public void setVendedor(Vendedor vendedor){
//        	Image im = new Image(vendedor.getImagen());
                this.vendedor = vendedor;
                this.lblNombreVendedor.setText(vendedor.getNombre() + " " + vendedor.getApellido());
//                this.circleImagenPerfil.setFill(new ImagePattern(im));
                //TODO: Cargara datos del vendedor
        }
}

