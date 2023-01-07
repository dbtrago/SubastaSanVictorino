package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Puja;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class MisPujasCompradorController {

	private Aplicacion aplicacion;
	String documento;
	ObservableList<Puja> listaPujasData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAtras;

	@FXML
	private Label txtNombreAnunciante;

	@FXML
	private TableColumn<Puja, String> columnNombreMisPuj;

	@FXML
	private TableColumn<Puja, String> columnCodPujaMisPuj;

	@FXML
	private TableView<Puja> tablePujasMisPuj;

	@FXML
	private TableColumn<Puja, Double> columnValorOfertadoMisPuj;

	@FXML
	void abrirVentanaAnunciante(ActionEvent event) {
		aplicacion.ventanaPrincipalComprador(txtNombreAnunciante.getText(), documento);
		Persistencia.guardaRegistroLog("El comprador regreso a la ventana principal de comprador", 1, "Acción volver");
	}

	@FXML
	void initialize() {

		this.columnNombreMisPuj.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
		this.columnCodPujaMisPuj.setCellValueFactory(new PropertyValueFactory<>("codigoPuja"));
		this.columnValorOfertadoMisPuj.setCellValueFactory(new PropertyValueFactory<>("valorOfertado"));
		mostrarPujas();
	}

	public void setAplicacion(Aplicacion aplicacion, String nombre, String documento) {
		this.aplicacion = aplicacion;
		if (nombre != "" && documento != "") {
			this.documento = documento;
			txtNombreAnunciante.setText(nombre);
		}
		tablePujasMisPuj.getItems().clear();
		tablePujasMisPuj.setItems(getPersonas());
	}

    private ObservableList<Puja> getPersonas() {
    	listaPujasData.addAll(ModelFactoryController.obtenerPuja());
        return listaPujasData;
    }

	private ObservableList<Puja> mostrarPujas() {
		ArrayList<Puja> misPujas = new ArrayList<>();
		ArrayList<Puja> pujas = ModelFactoryController.obtenerPuja();
		if (pujas != null) {
			for (Puja puja : pujas) {
				if (puja.getIdComprador().equals(documento)) {
					misPujas.add(puja);
				}
			}
		}
		listaPujasData.addAll(misPujas);
		tablePujasMisPuj.getItems().clear();
		tablePujasMisPuj.setItems(listaPujasData);
		tablePujasMisPuj.refresh();
		return listaPujasData;
	}

}