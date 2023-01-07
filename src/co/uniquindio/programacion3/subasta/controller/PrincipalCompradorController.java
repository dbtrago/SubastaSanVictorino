package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import co.uniquindio.programacion3.subasta.server.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//a
public class PrincipalCompradorController {

	private Aplicacion aplicacion;
	ObservableList<Anuncio> listaAnunciosData = FXCollections.observableArrayList();
	FilteredList<Anuncio> filteredDataAnuncio;
	Anuncio anuncioSeleccionado;
	String nombre;
	String documento;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnSalir;

	@FXML
	private TableColumn<Anuncio, String> columnNombreProductoCom;

	@FXML
	private TableColumn<Anuncio, Double> columnPrecioProductoCom;

	@FXML
	private TextField txtBuscarProducto;

	@FXML
	private Button btnMisPujas;

    @FXML
    private Button btnActualizar;

	@FXML
	private Button btnMisCompras1;

	@FXML
	private TableView<Anuncio> tablaComprador;

	@FXML
	private Button btnMisCompras;

	@FXML
	private TableColumn<Anuncio, Integer> columnNumPujanresProductoCom;

	@FXML
	private Button btnOfrecer;

	@FXML
	private TableColumn<Anuncio, String> columnDescripcionProductoCom;

    @FXML
    private Button btnChat;

    private Stage primaryStage;

    @FXML
    void actualizarTabla(ActionEvent event) {
//    	initialize();
    }

    @FXML
    void abrirChat(ActionEvent event) {
    	aplicacion.ventanaChatComprador();
    	aplicacion.ventanaChatVendedor();
    	Persistencia.guardaRegistroLog("El comprador ha abierto el chat", 1, "Acción ver");
    }

    @FXML
    void ventanaInicio(ActionEvent event) {
    	aplicacion.ventanaPrincipal();
    	Persistencia.guardaRegistroLog("El comprador ha cerrado sesion", 1, "Acción salir");
    }

    @FXML
    void ventanaMisPujas(ActionEvent event) {
    	aplicacion.ventanaMisPujasComprador(nombre, documento);
    	Persistencia.guardaRegistroLog("El comprador ingreso a ver sus pujas", 1, "Acción ver");
    }

    @FXML
    void ventanaMisCompras(ActionEvent event) {
    	aplicacion.ventanaMisComprasComprador(nombre, documento);
    	Persistencia.guardaRegistroLog("El comprador ingreso ha ver sus compras", 1, "Acción ver");
    }

    @FXML
    void tablaProductosComprador(ActionEvent event) {

    }

    @FXML
    void hacerOferta(ActionEvent event) {
    	crearPuja();
    }

	@FXML
    void e8dd9b(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	this.columnNombreProductoCom.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	this.columnDescripcionProductoCom.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	this.columnPrecioProductoCom.setCellValueFactory(new PropertyValueFactory<>("valorSuperior"));
    	tablaComprador.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
			anuncioSeleccionado = newSelection;
		});
    	// 1. Wrap the ObservableList in a FilteredList (initially display all data).
    	filteredDataAnuncio = new FilteredList<>(listaAnunciosData, p -> true);
    	// 2. Set the filter Predicate whenever the filter changes.
    	txtBuscarProducto.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredDataAnuncio.setPredicate(anuncio-> {
				// If filter text is empty, display all products.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare name of every product with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(anuncio.getNombreProducto()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches name.
				}
				return false; // Does not match.
			});
		});
    }

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
		if(nombre!="" && documento!=""){
			this.nombre = nombre;
			this.documento = documento;
		}

		tablaComprador.getItems().clear();
		tablaComprador.setItems(getListaAnunciosData());
		// 3. Wrap the FilteredList in a SortedList.
    	SortedList<Anuncio> sortedData = new SortedList<>(filteredDataAnuncio);
    	// 4. Bind the SortedList comparator to the TableView comparator.
    	sortedData.comparatorProperty().bind(tablaComprador.comparatorProperty());
    	// 5. Add sorted (and filtered) data to the table.
    	tablaComprador.setItems(sortedData);
    	tablaComprador.refresh();
	}

	@SuppressWarnings("unchecked")
	public ObservableList<Anuncio> getListaAnunciosData() {
		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Anuncios", "Cargar");
		ArrayList<Anuncio> listaAnuncios = (ArrayList<Anuncio>) objetoAux;
			listaAnunciosData.addAll(listaAnuncios);
			return listaAnunciosData;
	}


	private void crearPuja() {
		double valorOfertado = Double.parseDouble(JOptionPane.showInputDialog("Digite el precio a pujar"));
		if(anuncioSeleccionado != null){
			boolean bandera = ModelFactoryController.crearPuja(anuncioSeleccionado, documento, valorOfertado);
			if(bandera == true){
				JOptionPane.showMessageDialog(null, "Se ha creado la puja correctamente", "Dialogo de Informacion" ,
						JOptionPane.INFORMATION_MESSAGE);
				Persistencia.guardaRegistroLog("El comprador ha iniciado una puja", 1, "Acción iniciar");
			}else{
				JOptionPane.showMessageDialog(null, "No se ha creado la puja correctamente", "Dialogo de Error" ,
						JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No se ha seleccionado un anuncio", "Dialogo de Error" ,
					JOptionPane.WARNING_MESSAGE);
		}

	}


}