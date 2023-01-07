package co.uniquindio.programacion3.subasta.controller;

import java.net.URL;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegistroLoginController {

    private Aplicacion aplicacion;

    private Persona usuarioCreado;

    String usuario;
    String contrasenia;
    String edad;
    String documento;
    String tipoDeUsuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtEdad;

    @FXML
    private ComboBox<String> btnTipoUsuario;

    @FXML
    private TextField txtDocumento;

    @FXML
    void initialize() {
        String[] tipoDeUsuario = {"Anunciante", "Comprador"};
        btnTipoUsuario.getItems().clear();
        btnTipoUsuario.getItems().addAll(tipoDeUsuario);
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    void seleccionarTipoUsuario(ActionEvent event) {

    }

    @FXML
    void abrirVentanaLogin(ActionEvent event) {
        aplicacion.ventanaLogin();
        Persistencia.guardaRegistroLog("La persona ha regresado a la ventana principal", 1, "Acción volver");
    }

    @FXML
    void guardarUsuarioNuevo(ActionEvent event) {

        cargarDatos();

        if (datosValidos(usuario, contrasenia, edad, documento, 18, tipoDeUsuario)) {

            int edadINT = Integer.parseInt(edad);

            if (datosValidos(usuario, contrasenia, edad, documento, edadINT, tipoDeUsuario)) {

                usuarioCreado = ModelFactoryController.crearPersona(documento, usuario, contrasenia, edadINT, tipoDeUsuario);

                aplicacion.mostrarMensaje("Confirmación", "Hola, " + usuarioCreado.getNombre() + "!",
                        "El usuario se ha creado correctamente", AlertType.CONFIRMATION);

                aplicacion.ventanaLogin();

                Persistencia.guardaRegistroLog("Una persona nueva se ha registrado en el sistema", 1, "Acción registrar");
            }
        }
    }

    private void cargarDatos() {

        usuario = txtNombre.getText();
        contrasenia = String.valueOf(txtContrasenia.getText());
        edad = txtEdad.getText();
        documento = txtDocumento.getText();
        tipoDeUsuario = btnTipoUsuario.getValue();

    }

    private boolean datosValidos(String usuario, String contrasenia, String edad1, String documento, int edad, String tipoUsuario) {

        String notificacion = "";

        if (usuario == null || usuario.equals("")) {
            notificacion += "Debes ingresar un nombre de usuario\n";

        }
        if (contrasenia == null || contrasenia.equals("")) {
            notificacion += "Debes ingresar una contraseña de usuario\n";

        }
        if (edad1 == null || edad1.equals("")) {
            notificacion += "Debes ingresar una edad para usuario\n";

        }
        if (documento == null || documento.equals("")) {
            notificacion += "Debes ingresar un documento de usuario\n";

        }
        if (tipoUsuario == null || tipoUsuario.equals("")) {
            notificacion += "Debes ingresar el tipo de usuario\n";

        }
        if (edad < 18) {
            notificacion += "El usuario debe ser mayor de edad\n";

        }

        if (notificacion.equals("")) {
            return true;

        }

        aplicacion.mostrarMensaje("Advertencia", "Informacion incorrecta", notificacion, AlertType.WARNING);

        return false;

    }
}