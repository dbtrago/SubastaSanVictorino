package co.uniquindio.programacion3.subasta.controller;

import java.awt.Label;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.persistence.ArchivoUtil;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import co.uniquindio.programacion3.subasta.server.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//a
public class PrincipalAnuncianteController {

	private Aplicacion aplicacion;
	Persona persona;
	LoginController loginController;

	String documento;

    @FXML
    private ImageView image;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnMisVentas;

	@FXML
	private Button btnSalir;

	@FXML
	private Button btnCrearAnuncio;

	@FXML
	private Button btnExportar;

	@FXML
	private TableView<Anuncio> tablaAnunciante;

	@FXML
	private TableColumn<Anuncio, String> columnaNombre;

	@FXML
	private TableColumn<Anuncio, String> columnaDescripcion;

	@FXML
	private TableColumn<Anuncio, String> columnaPujantes;

	@FXML
	private TableColumn<Anuncio, String> columnaPrecio;

	ObservableList<Anuncio> listaProductos = FXCollections.observableArrayList();

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnProductosPuja;

	@FXML
	private Button btnCrearProducto;

	@FXML
	private Text nombreUser;

	@FXML
	private Label lblnombre;

	@FXML
	private Label lblprecio;

	@FXML
	private Label lblcodigo;

	@FXML
	private Label lblfecha1;

	@FXML
	private Label lblfecha2;

	@FXML
	private Label lbldescripcion;

	@FXML
	private Label lbltipo;

	@FXML
	private Button btnChat;

	private Stage primaryStage;

	@FXML
	void abrirChat(ActionEvent event) {
		aplicacion.ventanaChatComprador();
		aplicacion.ventanaChatVendedor();
		Persistencia.guardaRegistroLog("El anunciante ha abierto el chat", 1, "Acción ver");
	}

	@FXML
	void ventanaInicio(ActionEvent event) {
		aplicacion.ventanaPrincipal();
		Persistencia.guardaRegistroLog("El anunciante ha cerrado sesion", 1, "Acción salir");
	}

	@FXML
	void ventanaCrearAnuncio(ActionEvent event) {

	}

	@FXML
	void ventanaCrearProducto(ActionEvent event) {
		aplicacion.ventanaProducto(nombreUser.getText(), documento);
		Persistencia.guardaRegistroLog("El anunciante ingreso a crear un producto", 1, "Acción crear");
	}

	@FXML
	void ventanaProductosPuja(ActionEvent event) {
		aplicacion.ventanaProductosPuja(nombreUser.getText(), documento);
		Persistencia.guardaRegistroLog("El anunciante ingreso a ver pujas", 1, "Acción ver");
	}

	@FXML
	void ventanaMisVentas(ActionEvent event) {
		aplicacion.ventanaMisVentasAnunciante(nombreUser.getText(), documento);
		Persistencia.guardaRegistroLog("El anunciante ingreso a ver sus ventas", 1, "Acción ver");
	}

	@FXML
	void exportarCSV(ActionEvent event) {

		if (anuncioSeleccion != null) {
			String contenido = String.valueOf(anuncioSeleccion);
			FileChooser seleccionarArchivo = new FileChooser();

			seleccionarArchivo.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv"));

			File file = seleccionarArchivo.showSaveDialog(new Stage());

			try {
				ArchivoUtil.guardarArchivoCSV(file, contenido, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			aplicacion.mostrarMensaje("Genial!", "Archivo guardado correctamente", "", AlertType.CONFIRMATION);
		} else {
			aplicacion.mostrarMensaje("Recuerde que", "Debe seleccionar un producto", "", AlertType.WARNING);
		}
	}

	// ---------------------TABLA-------------------------

	ObservableList<Anuncio> listadoAnuncios = FXCollections.observableArrayList();

	private Anuncio anuncioSeleccion;

	@FXML
	void initialize() {
		this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
		this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.columnaPujantes.setCellValueFactory(new PropertyValueFactory<>("0"));
		this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));

		tablaAnunciante.getSelectionModel().selectedItemProperty().addListener((obs, olSelection, newSelection) -> {

			if (newSelection != null) {
				anuncioSeleccion = newSelection;
				mostrarDatos ();
			}

		});
	}

	public void mostrarDatos (){
		image.setImage(new Image(new ByteArrayInputStream(anuncioSeleccion.getImageByte())));
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Anuncio> getListaProductos() {
		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> listaAnuncios = (ArrayList<Anuncio>) objetoAux;
		listaProductos.addAll(listaAnuncios);
		return listaProductos;
	}

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
		if (nombre != "" && documento != "") {
			this.documento = documento;
			nombreUser.setText(nombre);
		}

		tablaAnunciante.getItems().clear();
		tablaAnunciante.setItems(getListaProductos());
		tablaAnunciante.refresh();
	}
}
