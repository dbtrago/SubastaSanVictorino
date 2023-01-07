package co.uniquindio.programacion3.subasta.application;

import java.io.IOException;
import co.uniquindio.programacion3.subasta.controller.AdminController;
import co.uniquindio.programacion3.subasta.controller.ChatCompradorController;
import co.uniquindio.programacion3.subasta.controller.ChatVendedorController;
import co.uniquindio.programacion3.subasta.controller.InicioController;
import co.uniquindio.programacion3.subasta.controller.LoginController;
import co.uniquindio.programacion3.subasta.controller.MisComprasCompradorController;
import co.uniquindio.programacion3.subasta.controller.MisPujasCompradorController;
import co.uniquindio.programacion3.subasta.controller.MisVentasAnuncianteController;
import co.uniquindio.programacion3.subasta.controller.ModelFactoryController;
import co.uniquindio.programacion3.subasta.controller.PrincipalAnuncianteController;
import co.uniquindio.programacion3.subasta.controller.PrincipalCompradorController;
import co.uniquindio.programacion3.subasta.controller.ProductoController;
import co.uniquindio.programacion3.subasta.controller.ProductosPujaController;
import co.uniquindio.programacion3.subasta.controller.RegistroLoginController;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Aplicacion extends Application {

    private Stage primaryStage;
    private Subasta subasta;
    private ModelFactoryController modelFactoryController;

    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;

		Subasta subastaAux = Persistencia.cargarRecursoSubastaBinario();

		subasta = ModelFactoryController.getSubasta();
		subasta.actualizarInformacionBinario(subastaAux);

		subasta.cargarDatos();
		// this.modelFactoryController = modelFactoryController;
		// this.primaryStage.setTitle("Las pujas de San Victorino");
        ventanaPrincipal();
        primaryStage.setOnCloseRequest(event -> {

			try {

				// Persistencia.serializarEmpresaUnificado();

				Persistencia.serializarEmpresaBinario();
				Persistencia.serializarEmpresaTXT();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

    }

    public void ventanaPrincipal() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/Inicio.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            InicioController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//			primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaPrincipalComprador(String nombre, String documento) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/PrincipalComprador.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            PrincipalCompradorController controlador = loader.getController();
            controlador.setAplicacion(this, nombre, documento);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaPrincipalAnunciante(String nombre, String documento) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/PrincipalAnunciante.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            PrincipalAnuncianteController controlador = loader.getController();
            controlador.setAplicacion(this, nombre, documento);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaProducto(String nombre, String documento) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/Producto.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            ProductoController controlador = loader.getController();
            controlador.setAplicacion(this, nombre, documento);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaLogin() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/Login.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            LoginController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaRegistroLogin() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/RegistroLogin.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            RegistroLoginController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//	            primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaAdmin() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/Admin.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            AdminController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
            primaryStage.centerOnScreen();
//             primaryStage.setResizable(false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaProductosPuja(String nombre, String documento) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/ProductosPuja.fxml"));


			AnchorPane anchorPane = (AnchorPane) loader.load();
			ProductosPujaController controlador = loader.getController();
			controlador.setAplicacion(this, nombre, documento);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
			primaryStage.centerOnScreen();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void ventanaMisVentasAnunciante(String nombre, String documento) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/MisVentasAnunciante.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			MisVentasAnuncianteController controlador = loader.getController();
			controlador.setAplicacion(this, nombre, documento);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
			primaryStage.centerOnScreen();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void ventanaMisPujasComprador(String nombre, String documento) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/MisPujasComprador.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			MisPujasCompradorController controlador = loader.getController();
			controlador.setAplicacion(this, nombre, documento);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
			primaryStage.centerOnScreen();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ventanaMisComprasComprador(String nombre, String documento) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/MisComprasComprador.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			MisComprasCompradorController controlador = loader.getController();
			controlador.setAplicacion(this, nombre, documento);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
			primaryStage.centerOnScreen();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void ventanaChatVendedor() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/chatVendedor.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            ChatVendedorController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage ();
            stage.setScene(scene);
            stage.show();
            stage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/botonChat.png"));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setX(1000);
            stage.setTitle("CHAT - Vendedor");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void ventanaChatComprador() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/programacion3/subasta/view/chatComprador.fxml"));

            AnchorPane anchorPane = (AnchorPane) loader.load();
            ChatCompradorController controlador = loader.getController();
            controlador.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage ();
            stage.setScene(scene);
            stage.show();
            stage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/botonChat.png"));
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setX(500);
            stage.setTitle("CHAT - Comprador");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/co/uniquindio/programacion3/subasta/images/LOGO.png"));
        alerta.showAndWait();

    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ModelFactoryController getModelFactoryController() {
        return modelFactoryController;
    }

    public void setModelFactoryController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
    }

    public static void main(String[] args) {
        launch(args);
    }

}