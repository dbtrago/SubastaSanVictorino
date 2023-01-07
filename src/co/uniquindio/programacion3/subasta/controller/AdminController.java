package co.uniquindio.programacion3.subasta.controller;
import java.net.URL;
import java.util.ResourceBundle;
import co.uniquindio.programacion3.subasta.application.Aplicacion;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController implements Initializable{

    private Aplicacion aplicacion;
    Subasta subasta;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtras;

    @FXML
    private TableView<Persona> tablaAdmin;

    @FXML
    private TableColumn<Persona, String> columnaTipo;

    @FXML
    private TableColumn<Persona, String> columnaUsuario;

    @FXML
    private TableColumn<Persona, String> columnaContrasenia;

    public ObservableList<Persona> listaUsuarios = FXCollections.observableArrayList();


    @FXML
    void abrirVentanaLogin(ActionEvent event) {
        aplicacion.ventanaLogin();
        Persistencia.guardaRegistroLog("El admin ha regresado a la ventana principal", 1, "Acción volver");
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //this.tablaAdmin.setItems(listaUsuarios);

        this.columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoDeUsuario"));
        this.columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaContrasenia.setCellValueFactory(new PropertyValueFactory<>("clave"));

    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
        this.subasta = aplicacion.getSubasta();

        tablaAdmin.getItems().clear();
        tablaAdmin.setItems(getPersonas());

    }
    private ObservableList<Persona> getPersonas() {
        listaUsuarios.addAll(ModelFactoryController.obtenerPersona());
        return listaUsuarios;
    }
}