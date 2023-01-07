package co.uniquindio.programacion3.subasta.controller;

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
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import co.uniquindio.programacion3.subasta.server.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//a
public class MisVentasAnuncianteController {

	private Aplicacion aplicacion;
	ObservableList<Anuncio> listaAnunciosData = FXCollections.observableArrayList();
	ObservableList<Puja> listaPujasData = FXCollections.observableArrayList();
	Anuncio anuncioSeleccionado;
	String nombre;
	String documento;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtras;

    @FXML
    private TableColumn<Puja, String> columnIdComMisVen;

    @FXML
    private TableColumn<Puja, Double> columnValorOfertadoMisVen;

    @FXML
    private TableColumn<Anuncio, TipoProducto> columnTipoProductoMisVen;

    @FXML
    private TableColumn<Anuncio, Double> columnValorSuperiorMisVen;

    @FXML
    private TableColumn<Anuncio, String> columnNombreMisVen;

    @FXML
    private TableColumn<Anuncio, String> columnFinalizacionMisVen;

    @FXML
    private TableView<Anuncio> tableAnunciosMisVen;

    @FXML
    private TableView<Puja> tablePujasMisVen;

    @FXML
    void abrirVentanaAnunciante(ActionEvent event) {
    	aplicacion.ventanaPrincipalAnunciante(nombre, documento);
    	Persistencia.guardaRegistroLog("El anunciante ha regresado a la ventana principal de anunciante", 1, "Acción volver");
    }

    @FXML
    void initialize() {
    	this.columnNombreMisVen.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	this.columnTipoProductoMisVen.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    	this.columnFinalizacionMisVen.setCellValueFactory(new PropertyValueFactory<>("fechaFinalizacion"));
    	this.columnValorSuperiorMisVen.setCellValueFactory(new PropertyValueFactory<>("valorSuperior"));
    	tableAnunciosMisVen.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
    		anuncioSeleccionado = newSelection;
			mostrarInformacionPujas(anuncioSeleccionado);
		});

    }

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
    	this.aplicacion = aplicacion;
    	if(nombre!="" && documento!=""){
    		this.nombre = nombre;
			this.documento = documento;
		}
		tableAnunciosMisVen.getItems().clear();
		tableAnunciosMisVen.setItems(getListaAnunciosData());
    }

    @SuppressWarnings("unchecked")
	public ObservableList<Anuncio> getListaAnunciosData() {
    	Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> listaAnuncios = (ArrayList<Anuncio>) objetoAux;

    	ArrayList<Anuncio> listaAnunciosAux = new ArrayList<>();
    	if(listaAnuncios != null){
    		for (Anuncio anuncio : listaAnuncios) {
    			boolean bandera = calcularDias(anuncio.getFechaFinalizacion());
				if(anuncio.getDocumentoAnunciante().equals(documento) && bandera == false){
					listaAnunciosAux.add(anuncio);
				}
			}
    	}
		listaAnunciosData.addAll(listaAnunciosAux);
		return listaAnunciosData;
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

	private ObservableList<Puja> getListaPujasData() {
    	if(anuncioSeleccionado.getListaPujas() != null){
    		listaPujasData.addAll(anuncioSeleccionado.getListaPujas());
    	}
    	return listaPujasData;
	}

    private void mostrarInformacionPujas(Anuncio anuncioSeleccionado2) {
    	this.columnIdComMisVen.setCellValueFactory(new PropertyValueFactory<>("idComprador"));
    	this.columnValorOfertadoMisVen.setCellValueFactory(new PropertyValueFactory<>("valorOfertado"));
    	
    	tablePujasMisVen.getItems().clear();
    	tablePujasMisVen.setItems(getListaPujasData());
 	}



}
