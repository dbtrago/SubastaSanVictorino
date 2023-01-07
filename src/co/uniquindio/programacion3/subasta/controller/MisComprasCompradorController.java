package co.uniquindio.programacion3.subasta.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.modell.TipoProducto;
import co.uniquindio.programacion3.subasta.persistence.ArchivoUtil;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import co.uniquindio.programacion3.subasta.server.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MisComprasCompradorController {

	private Aplicacion aplicacion;
	String documento;
	ObservableList<Anuncio> listaAnunciosData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnExportar;

    @FXML
    private TableColumn<Anuncio, String> columnNombreAnuncianteMC;

    @FXML
    private Label txtNombreAnunciante;

    @FXML
    private TableColumn<Anuncio, TipoProducto> columnTipoProductoMC;

    @FXML
    private TableColumn<Anuncio, Double> columnValorSuperiorMC;

    @FXML
    private TableView<Anuncio> tableComprasMC;

    @FXML
    private TableColumn<Anuncio, String> columnDescripcionMC;

    @FXML
    private TableColumn<Anuncio, String> columnNombreProductoMC;

    @FXML
    void abrirVentanaAnunciante(ActionEvent event) {
    	aplicacion.ventanaPrincipalComprador(txtNombreAnunciante.getText(), documento);
    	Persistencia.guardaRegistroLog("El comprador regreso a la ventana principal de comprador", 1, "Acción volver");
    }

    @FXML
    void initialize() {
    	mostrarAnuncios();
    }

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
		if(nombre!="" && documento!=""){
			this.documento = documento;
			txtNombreAnunciante.setText(nombre);
		}
	}

	@FXML
	void exportarCSV(ActionEvent event) {

			FileChooser seleccionarArchivo = new FileChooser();

			seleccionarArchivo.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv"));

			File file = seleccionarArchivo.showSaveDialog(new Stage());

	}

	@SuppressWarnings("unchecked")
	private void mostrarAnuncios() {
		ArrayList<Anuncio> misCompras = new ArrayList<>();

		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) objetoAux;

		if(anuncios != null){
			for (Anuncio anuncio : anuncios) {
				boolean bandera = calcularDias(anuncio.getFechaFinalizacion());
				ArrayList<Puja> pujas = anuncio.getListaPujas();
				if(pujas != null && bandera == false){
					for (Puja puja : pujas) {
						if(puja.getIdComprador().equals(documento) &&
								puja.getValorOfertado().equals(anuncio.getValorSuperior())){
							misCompras.add(anuncio);
						}
					}
				}
			}
			listaAnunciosData.addAll(misCompras);

			tableComprasMC.getItems().clear();
			tableComprasMC.setItems(listaAnunciosData);
			tableComprasMC.refresh();
		}
	}

	 private boolean calcularDias(LocalDate fechaFinalizacion) {
			boolean bandera = false;
	    	String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
			String fechaF = String.valueOf(fechaFinalizacion);

			int anioA = Integer.parseInt(fechaActual.substring(0, 4));
			int anioF = Integer.parseInt(fechaF.substring(0, 4));
			int mesA = Integer.parseInt(fechaActual.substring(5, 7));
			int mesF = Integer.parseInt(fechaF.substring(5, 7));
			int diaA = Integer.parseInt(fechaActual.substring(8, 10));
			int diaF = Integer.parseInt(fechaF.substring(8, 10));

			if(anioA<=anioF && mesA<=mesF && diaA<=diaF){
				bandera = true;
			}
			return bandera;
		}

}
