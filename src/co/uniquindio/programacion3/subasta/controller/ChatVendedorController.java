package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.ChatComprador;
import co.uniquindio.programacion3.subasta.modell.Servidor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatVendedorController implements Observer  {

	private Aplicacion aplicacion;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEnviar;

    @FXML
    private TextArea areaChat;

    @FXML
    private TextField txtMensaje;

    @FXML
    void initialize() {
    	areaChat.setEditable(false);
    }

	public ChatVendedorController() {
		Servidor s = new Servidor(600);
		s.addObserver(this);
		Thread t = new Thread(s);
		t.start();
	}

	public void enviarMensaje() {

		if (!this.txtMensaje.getText().equals(null)) {

		String mensaje = "Vendedor: " + this.txtMensaje.getText() + "\n";

		this.areaChat.appendText(mensaje);
		this.txtMensaje.setText("");

		ChatComprador c = new ChatComprador (7000, mensaje);
		Thread t = new Thread (c);
		t.start();

		}
	}

	public void update(Observable o, Object arg) {
		this.areaChat.appendText((String) arg);
	}

	public void setAplicacion(Aplicacion aplicacion) {
		// TODO Auto-generated method stub
		this.aplicacion = aplicacion;
	}
}
