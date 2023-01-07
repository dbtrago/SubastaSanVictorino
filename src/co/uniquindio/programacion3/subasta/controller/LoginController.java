package co.uniquindio.programacion3.subasta.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.modell.TipoProducto;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//a

public class LoginController {

    private Aplicacion aplicacion;
    private Persona usuarioEncontrado;
    PrincipalAnuncianteController principalAnuncianteController;

    String usuario;
    String contrasenia;
    String tipoUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> btnTipoUsuario;

    ObservableList<String> options = FXCollections.observableArrayList("Anunciante", "Comprador");

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    void initialize() {
        String[] tipoDeUsuario = { "Anunciante", "Comprador", "ADMIN" };
        btnTipoUsuario.getItems().clear();
        btnTipoUsuario.getItems().addAll(tipoDeUsuario);
//        crearDatosPrueba();

    }



	public void crearDatosPrueba() {
		byte[] arrayImage = null;
		ArrayList<Puja> listaPujas = new ArrayList<>();

		LocalDate date1 = LocalDate.of(2022, 11, 10);
		LocalDate date2 = LocalDate.of(2022, 11, 30);
		ModelFactoryController.crearPersona("154789854", "DANI", "123", 18, "ADMIN");
		ModelFactoryController.crearPersona("123", "1", "1", 20, "Anunciante");
		ModelFactoryController.crearPersona("10101010", "2", "2", 20, "Comprador");
		ModelFactoryController.crearAnuncio("Monitor",
				"Un monitor a la medida Con tu pantalla LED no solo ahorrarás energía, ya que su consumo es bajo, sino que podrás ver colores nítidos y definidos en tus películas o series favoritas.",
				"123", date1, date2, 2500000.00, TipoProducto.TECNOLOGIA, "SA1588ADDE1", arrayImage, listaPujas);

		ModelFactoryController.crearAnuncio("CPU", "Cpu Gamer Intel Core I5 4570 Web Cam", "123", date1, date2, 970000.00,
				TipoProducto.TECNOLOGIA, "DE1238DA155", arrayImage, listaPujas);
	}


//	void imagen(String nombre) {
//
//		File file = new File("./src/co/uniquindio/programacion3/subasta/images/" + nombre + ".jpg");
//		byte[] btImagen;
//
//		try {
//			btImagen = Files.readAllBytes(file.toPath());
//			this.arrayImage = btImagen;
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

    @FXML
    void subirImagen(ActionEvent event) {
        // el file chooser permite abrir el explorador
        FileChooser dc = new FileChooser();
        File file = dc.showOpenDialog(new Stage());
        // obtengo el arreglo de bits de la imagen
        byte[] btImagen;
        // cargo los bits de la imagen

    }


    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    void seleccionarTipoUsuario(ActionEvent event) {

    }

    @FXML
    void abrirVentanaRegistrarse(ActionEvent event) {
        aplicacion.ventanaRegistroLogin();
    }

    @FXML
    void abrirVentanaInicio(ActionEvent event) {
        aplicacion.ventanaPrincipal();
    }

    private Subasta subasta;

    @FXML
	void abrirVentanaIngresar(ActionEvent event) {

		cargarDatos();
		buscarUsuario();

		if (tipoUsuario.equals("ADMIN") && contrasenia.equals("123") && usuario.equals("DANI")) {
			aplicacion.mostrarMensaje(null, "Hola, " + usuario + "!", "Acceso confirmado",
					AlertType.CONFIRMATION);
			aplicacion.ventanaAdmin();
			Persistencia.guardaRegistroLog("El admin ha ingresado al sistema", 1, "Acción ingresar");
		}

		if (verificarDatos(usuarioEncontrado)) {

			if (!usuarioEncontrado.getClave().equals(null)) {

				if (usuario.equals(usuarioEncontrado.getNombre()) && contrasenia.equals(usuarioEncontrado.getClave())
						&& tipoUsuario.equals(usuarioEncontrado.getTipoDeUsuario())) {
					aplicacion.mostrarMensaje(null, "Hola, " + usuario + "!", "Acceso confirmado",
							AlertType.CONFIRMATION);

					if (tipoUsuario.equals("ADMIN")) {
						aplicacion.ventanaAdmin();
						Persistencia.guardaRegistroLog("El admin ha ingresado al sistema", 1, "Acción ingresar");
					}

					if (tipoUsuario.equals("Anunciante")) {
						aplicacion.ventanaPrincipalAnunciante(usuarioEncontrado.getNombre(), usuarioEncontrado.getDocumento());

						Persistencia.guardaRegistroLog("Una anunciante ha ingresado al sistema", 1, "Acción ingresar");
					}

					if (tipoUsuario.equals("Comprador")) {
						aplicacion.ventanaPrincipalComprador(usuarioEncontrado.getNombre(), usuarioEncontrado.getDocumento());
					}

				} else {
					aplicacion.mostrarMensaje(null, "Datos incorrectos",
							"El usuario, la contraseña o el tipo es incorrecto", AlertType.ERROR);
				}

			} else {

				aplicacion.mostrarMensaje(null, "'" + usuario + "'" + ", no existe",
						"El usuario que ingreso no existe, verifique los datos " + "Ã³ haga un registro nuevo.",
						AlertType.ERROR);
				limpiar();
			}

		}

	}
    private void cargarDatos() {

        usuario = txtNombre.getText();
        contrasenia = String.valueOf(txtContrasenia.getText());
        tipoUsuario = btnTipoUsuario.getValue();

    }

    private void buscarUsuario() {
        usuarioEncontrado = ModelFactoryController.buscarPersona(usuario);
    }

    private boolean existeUsuario() {

        if (ModelFactoryController.existeUsuario(usuario)) {
            return true;
        }
        return false;

    }

    private boolean verificarDatos(Persona usuarioEncontrado) {

        String notificacion = "";

        if (usuario == null || usuario.equals("") ||
                contrasenia == null || contrasenia.equals("") ||
                tipoUsuario == null || tipoUsuario.equals("")) {

            if (usuario == null || usuario.equals("")) {
                notificacion += "Debe ingresar un usuario\n";
            }

            if (contrasenia == null || contrasenia.equals("")) {
                notificacion += "Debe ingresar una contraseÃ±a\n";
            }

            if (tipoUsuario == null || tipoUsuario.equals("")) {
                notificacion += "Debe ingresar el tipo de usuario\n";
            }

            aplicacion.mostrarMensaje(null, "Informacion incorrecta", notificacion, AlertType.ERROR);

            return false;

        }

        if (usuarioEncontrado != null) {
            return true;
        }

        return false;

    }

    private void limpiar() {
        txtNombre.setText(null);
        txtContrasenia.setText(null);
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}