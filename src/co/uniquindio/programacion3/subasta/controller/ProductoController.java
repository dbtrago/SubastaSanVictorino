package co.uniquindio.programacion3.subasta.controller;

import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.modell.TipoProducto;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import co.uniquindio.programacion3.subasta.server.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductoController {

	private Aplicacion aplicacion;

	Subasta subasta;
	String nombre;
	String documento;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<String, Anuncio> columCodigo;

	@FXML
	private DatePicker fechaInicial;

	@FXML
	private Button btnSubirImagen;

	@FXML
	private DatePicker fechaFinal;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TableColumn<String, Anuncio> columNombre;

	@FXML
	private TableColumn<String, Anuncio> columPrecio;

	@FXML
	private TextField txtNombreProducto;

	@FXML
	private TextField txtPrecio;

	@FXML
	private ComboBox<String> cmbTipoProducto;

	@FXML
	private Button btnAtras;

	@FXML
	private ImageView image;

	@FXML
	private Button btnGuardar;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnLimpiarAnuncio;

	@FXML
	private TableView<Anuncio> tablaAnuncios;

	@FXML
	private Label txtNombreAnunciante;

	@FXML
	private TextArea txtDescripcion;

	@FXML
	private Button btnModificar;

	private String[] tiposProductos = { "TECNOLOGIA", "HOGAR", "DEPORTES", "VEHICULOS", "BIEN_RAIZ" };

	private byte[] arrayImage;

	/*
	 * Metodo que permite limpiar los campos de texto en caso de tener texto
	 */
	@FXML
	void btnlimpiarAnuncio(ActionEvent event) {

		txtNombreProducto.clear();
		txtDescripcion.clear();
		txtCodigo.clear();
		txtPrecio.clear();
		image.setImage(null);
		fechaInicial.setValue(null);
		fechaFinal.setValue(null);
		cmbTipoProducto.setValue("Tipos de productos");

		txtCodigo.setDisable(false);

		Persistencia.guardaRegistroLog("Anunciante limpio los campos de texto", 1, "Acción limpiar");
	}

	/*
	 * Metodo que permite actualizar un anuncio
	 */
	@FXML
	void btnModificarAnuncio(ActionEvent event) {

		String nombreProducto = txtNombreProducto.getText();
		String descripcion = txtDescripcion.getText();
		String codigo = txtCodigo.getText();
		String valorInicial = this.txtPrecio.getText();
		String tipoProducto = cmbTipoProducto.getValue();

		LocalDate fechaPublicacion = fechaInicial.getValue();
		LocalDate fechaFinalizacion = fechaFinal.getValue();

		if (anuncioSeleccion != null) {

			if (datosValidos(nombreProducto, descripcion, fechaPublicacion, valorInicial, fechaFinalizacion,
					tipoProducto, codigo)) {

				anuncioSeleccion.setCodigo(codigo);
				anuncioSeleccion.setDescripcion(descripcion);
				anuncioSeleccion.setFechaPublicacion(fechaPublicacion);
				anuncioSeleccion.setFechaFinalizacion(fechaFinalizacion);
				anuncioSeleccion.setNombreProducto(nombreProducto);
				anuncioSeleccion.setValorInicial(Double.parseDouble(valorInicial));

				anuncioSeleccion.setTipoProducto(parseToTipoProducto(tipoProducto));
				anuncioSeleccion.setImageByte(arrayImage);

				tablaAnuncios.refresh();

				aplicacion.mostrarMensaje("Información", "Actualizar", "El anuncio ha sido actualizado.",
						AlertType.CONFIRMATION);

				Persistencia.guardaRegistroLog("Anunciante modifico un anuncio", 1, "Acción modificar");
			}

		} else {
			aplicacion.mostrarMensaje("Advertencia", "Actualizar", "No se ha seleccionado un anuncio.",
					AlertType.WARNING);

		}

	}

	/*
	 * Recibe un tipo producto y devuelve un string
	 */
	private String parseTipoProductoToString(TipoProducto tipo) {

		if (tipo == TipoProducto.BIEN_RAIZ) {
			return "BIEN_RAIZ";
		}

		if (tipo == TipoProducto.DEPORTES) {
			return "DEPORTES";
		}
		if (tipo == TipoProducto.HOGAR) {
			return "HOGAR";
		}
		if (tipo == TipoProducto.TECNOLOGIA) {
			return "TECNOLOGIA";
		}
		if (tipo == TipoProducto.VEHICULOS) {
			return "VEHICULOS";
		}

		return null;
	}

	/*
	 * Metodo que permite agregar un Anuncio
	 */
	@FXML
	void btnGuardarAnuncio(ActionEvent event) {

		String nombreProducto = txtNombreProducto.getText();
		String descripcion = txtDescripcion.getText();
		String codigo = txtCodigo.getText();
		String valorInicial = this.txtPrecio.getText();
		String tipoProducto = cmbTipoProducto.getValue();

		LocalDate fechaPublicacion = fechaInicial.getValue();
		LocalDate fechaFinalizacion = fechaFinal.getValue();

		try {
			if (datosValidos(nombreProducto, descripcion, fechaPublicacion, valorInicial, fechaFinalizacion,
					tipoProducto, codigo)) {
				TipoProducto tipoAux = parseToTipoProducto(tipoProducto);

				crearAnuncio(nombreProducto, descripcion, fechaPublicacion, fechaFinalizacion, valorInicial, tipoAux,
						codigo);
				actualizarTabla();

				Persistencia.guardaRegistroLog("Anunciante guardo un anuncio", 1, "Acción guradar");
			}
		} catch (Exception ignored) {
		}



	}

	/*
	 * Metodo que recibe un String de tipo producto y devuelve un tipo producto
	 */
	public TipoProducto parseToTipoProducto(String tipoAux) {
		switch (tipoAux) {
		case "TECNOLOGIA":
			return TipoProducto.TECNOLOGIA;

		case "HOGAR":
			return TipoProducto.HOGAR;

		case "DEPORTES":
			return TipoProducto.DEPORTES;

		case "VEHICULOS":
			return TipoProducto.VEHICULOS;

		case "BIEN_RAIZ":
			return TipoProducto.BIEN_RAIZ;
		default:
			return null;

		}

	}

	/*
	 * Metodo que permite elegir una imagen desde los archivos para subrila
	 */
	@FXML
	void subirImagen(ActionEvent event) {
		// el file chooser permite abrir el explorador
		FileChooser dc = new FileChooser();
		File file = dc.showOpenDialog(new Stage());
		// obtengo el arreglo de bits de la imagen
		byte[] btImagen;
		// cargo los bits de la imagen
		try {
			btImagen = Files.readAllBytes(file.toPath());
			this.arrayImage = btImagen;
			Image imageAux = new Image(new ByteArrayInputStream(btImagen));
			image.setImage(imageAux);
			System.out.println(dc);
		} catch (IOException e) {
			;
		}

	}

	private double precioADouble(String valorInicial) {

		double precioAux = 0;

		try {
			precioAux = Double.parseDouble(valorInicial);
		} catch (Exception e) {

			aplicacion.mostrarMensaje("Advertencia", "La información del anuncio es invalida",
					"Ingrese un valor numerico en el campo de precio", AlertType.WARNING);
		}
		return precioAux;
	}

	/*
	 * Metodo para crear un anuncio
	 */
	private void crearAnuncio(String nombreProducto, String descripcion, LocalDate fechaPublicacion,
			LocalDate fechaFinalizacion, String valorInicial, TipoProducto tipoProducto, String codigo)
			throws IOException {

		double precioAux = precioADouble(valorInicial);
		ArrayList<Puja>listaPujas = new ArrayList<>();
		Anuncio anuncio = ModelFactoryController.crearAnuncio(nombreProducto, descripcion, documento, fechaPublicacion,
				fechaFinalizacion, precioAux, tipoProducto, codigo, arrayImage, listaPujas);

		// Notificar que el anuncio fue creado
		if (anuncio != null) {
			listadoAnuncios.add(0, anuncio);
			listadoAnuncios.add(anuncio);
			aplicacion.mostrarMensaje("Notificación anuncio", "Anuncio guardado",
					"El anuncio " + anuncio.getNombreProducto() + " ha sido guardado", AlertType.INFORMATION);
			actualizarTabla();
		} else {
			aplicacion.mostrarMensaje("Notificación Anuncio", "Anuncio no guardado",
					"El anuncio " + nombreProducto + " no ha sido guardado", AlertType.WARNING);

		}

	}

	/*
	 * Metodo que permite verificar si todos los campos de texto han sido
	 * dilingenciados
	 */
	private boolean datosValidos(String nombreProducto, String descripcion, LocalDate fechaPublicacion,
			String valorInicial, LocalDate fechaFinalizacion, String tipoProducto, String codigo) {

		boolean flag = true;
		String notificacion = "";

		if (arrayImage == null)
			notificacion += "Imagen no cargada\n";

		if (nombreProducto == null || nombreProducto.equals("")) {
			notificacion += "Nombre del producto no tiene información\n";

		}
		if (descripcion == null || descripcion.equals("")) {
			notificacion += "La descripción del producto no tiene información\n";

		}
		if (codigo == null || codigo.equals("")) {
			notificacion += "El codigo del producto no tiene información\n";

		}
		if (fechaPublicacion == null) {
			notificacion += "La fecha inicial del producto no tiene información\n";

		}

		if (fechaFinalizacion == null) {
			notificacion += "La fecha de finalización del producto no tiene información\n";

		}
		if (tipoProducto == null || tipoProducto.equals("")) {
			notificacion += "No ha seleccionado un tipo de producto\n";

		}

		if (valorInicial.equals("")) {
			flag = false;
			notificacion += "El precio del producto no tiene información\n";

		}
		if (flag) {
			try {
				double precioAux = Double.parseDouble(valorInicial);
			} catch (Exception e) {
				notificacion += "El precio debe de contener valores numericos";
			}

		}

		if (notificacion.equals("")) {
			return true;

		}

		aplicacion.mostrarMensaje("Advertencia", "Información del anuncio invalida", notificacion, AlertType.WARNING);
		return false;

	}

	/*
	 * Metodo que permite eliminar un anuncio
	 */
	@FXML
	void btnEliminarAnuncio(ActionEvent event) {

		if (anuncioSeleccion != null) {
			if (ModelFactoryController.eliminarAnuncio(anuncioSeleccion.getCodigo())) {
				aplicacion.mostrarMensaje("Información", "Anuncio  eliminado", "El anuncio ha sido eliminado",
						AlertType.ERROR);

			} else {
				aplicacion.mostrarMensaje("Información", "Anuncio selección", "No se ha seleccionado un anuncio",
						AlertType.INFORMATION);
			}

			listadoAnuncios.remove(anuncioSeleccion);
			Persistencia.guardaRegistroLog("Anunciante elimino un anuncio", 1, "Acción eliminar");
		} else {
			aplicacion.mostrarMensaje("Advertencia", "Anuncio selección",
					"No se ha realizado la seleccion de un Anuncio", AlertType.ERROR);

		}

	}

	/*
	 * Metodo para regresar a la ventana del anunciante
	 */
	@FXML
	void abrirVentanaAnunciante(ActionEvent event) {
		aplicacion.ventanaPrincipalAnunciante(txtNombreAnunciante.getText(), documento);
		Persistencia.guardaRegistroLog("Anunciante volvio a la ventana principal", 1, "Acción volver");
	}

	// ---------------------TABLA-------------------------

	ObservableList<Anuncio> listadoAnuncios = FXCollections.observableArrayList();

	private Anuncio anuncioSeleccion;

	private void mostrarInformacion() {

		if (anuncioSeleccion != null) {

			txtNombreProducto.setText(anuncioSeleccion.getNombreProducto());
			txtDescripcion.setText(anuncioSeleccion.getDescripcion());
			txtCodigo.setText(anuncioSeleccion.getCodigo());
			txtPrecio.setText(anuncioSeleccion.getValorInicial() + "");
			fechaInicial.setValue(anuncioSeleccion.getFechaPublicacion());
			fechaFinal.setValue(anuncioSeleccion.getFechaFinalizacion());
			cmbTipoProducto.setValue(parseTipoProductoToString(anuncioSeleccion.getTipoProducto()));
			image.setImage(new Image(new ByteArrayInputStream(anuncioSeleccion.getImageByte())));
			txtCodigo.setDisable(true);

		}

	}

	@SuppressWarnings("unchecked")
	public void actualizarTabla() {
		tablaAnuncios.getItems().clear();
		listadoAnuncios.clear();

		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) objetoAux;

		listadoAnuncios.addAll(anuncios);
		tablaAnuncios.refresh();
	}

	@FXML
	void initialize() {

		this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
		this.columPrecio.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));

		tablaAnuncios.getSelectionModel().selectedItemProperty().addListener((obs, olSelection, newSelection) -> {

			if (newSelection != null) {
				anuncioSeleccion = newSelection;
				mostrarInformacion();
			}

		});

		cmbTipoProducto.getItems().addAll(tiposProductos);

	}

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
		if(nombre!="" && documento!=""){
    		txtNombreAnunciante.setText(nombre);
			this.documento = documento;
		}
		tablaAnuncios.getItems().clear();
		tablaAnuncios.setItems(getAnuncios());
	}

	@SuppressWarnings("unchecked")
	private ObservableList<Anuncio> getAnuncios() {
		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) objetoAux;

		listadoAnuncios.addAll(anuncios);
		return listadoAnuncios;
	}

}