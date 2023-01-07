package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//a
public class InicioController {

	private Aplicacion aplicacion;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnAnunciante;

	@FXML
	private Button btnComprador;


    @FXML
    void abrirPrincipalAnunciante(ActionEvent event) {
    	aplicacion.ventanaPrincipalAnunciante("", "");
    }

    @FXML
    void abrirPrincipalComprador(ActionEvent event) {
    	aplicacion.ventanaPrincipalComprador("", "");
    }

	@FXML
	void abrirLogin(ActionEvent event) {
		aplicacion.ventanaLogin();
		Persistencia.guardaRegistroLog("Una persona ha ingresado al sistema", 1, "Acción Ingresar");
	}

	@FXML
	void initialize() {


	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
}