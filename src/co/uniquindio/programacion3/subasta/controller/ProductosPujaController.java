package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
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
public class ProductosPujaController {

	private Aplicacion aplicacion;
	ObservableList<Anuncio> listaProductosPujaData = FXCollections.observableArrayList();
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
    private TableColumn<Anuncio, String> columnNombrePrP;

    @FXML
    private TableColumn<Anuncio, Double> columnValorSuperiorPrP;

    @FXML
    private TableColumn<Anuncio, String> columnPublicacionPrP;

    @FXML
    private TableView<Anuncio> tableProductosPuja;

    @FXML
    private TableColumn<Anuncio, String> columnDescripcionPrP;

    @FXML
    private TableColumn<Anuncio, TipoProducto> columnTipoProductoPrP;

    @FXML
    private TableColumn<Anuncio, String> columnFinalizacionPrP;

    @FXML
    private TableColumn<Anuncio, Double> columnValorInicialPrP;

    @FXML
    void abrirVentanaAnunciante(ActionEvent event) {
    	aplicacion.ventanaPrincipalAnunciante(nombre, documento);
    	Persistencia.guardaRegistroLog("El anunciante ha regresado a la ventana principal de anunciante", 1, "Acción volver");
    }

    @FXML
    void initialize() {
    	this.columnNombrePrP.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	this.columnTipoProductoPrP.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    	this.columnDescripcionPrP.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	this.columnPublicacionPrP.setCellValueFactory(new PropertyValueFactory<>("fechaPpublicacion"));
    	this.columnFinalizacionPrP.setCellValueFactory(new PropertyValueFactory<>("fechaFinalizacion"));
    	this.columnValorInicialPrP.setCellValueFactory(new PropertyValueFactory<>("valorInicial"));
    	this.columnValorSuperiorPrP.setCellValueFactory(new PropertyValueFactory<>("valorSuperior"));
    }

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
    	if(nombre!="" && documento!=""){
    		this.nombre = nombre;
			this.documento = documento;
		}
		tableProductosPuja.getItems().clear();
		tableProductosPuja.setItems(getListaProductosPujaData());
	}
    
    @SuppressWarnings("unchecked")
	public ObservableList<Anuncio> getListaProductosPujaData() {
    	Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> listaAnuncios = (ArrayList<Anuncio>) objetoAux;
		
    	ArrayList<Anuncio> listaAnunciosAux = new ArrayList<>();
    	if(listaAnuncios != null){
    		for (Anuncio anuncio : listaAnuncios) {
				if(anuncio.getDocumentoAnunciante().equals(documento)){
					listaAnunciosAux.add(anuncio);
				}
			}
    	}
		listaProductosPujaData.addAll(listaAnunciosAux);
		return listaProductosPujaData;
	}

}