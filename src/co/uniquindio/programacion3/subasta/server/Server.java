package co.uniquindio.programacion3.subasta.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket serverComunicacion;
	int puerto;

	private ObjectInputStream flujoEntradaObjeto;
	private DataInputStream flujoEntradaData;
	private ObjectOutputStream flujoSalidaObjeto;
	String mensajeCliente;


	public Server(int puerto) {
		this.puerto = puerto;
	}


	public void runServer() throws IOException{

		serverComunicacion = new ServerSocket(puerto);

		while(true){

			System.out.println("<------------------------Servidor iniciado------------------------>");
			Socket socketComunicacion = null;

			socketComunicacion = serverComunicacion.accept();

			System.out.println("Conexion establecida");

			flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
			flujoSalidaObjeto  = new ObjectOutputStream(socketComunicacion.getOutputStream());
			flujoEntradaData = new DataInputStream(socketComunicacion.getInputStream());

			iniciarHiloClienteServidor();
		}

	}

	private void iniciarHiloClienteServidor() {

		HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();

		hiloClienteServidor.inicializar(flujoEntradaObjeto, flujoSalidaObjeto, flujoEntradaData, this);

		hiloClienteServidor.start();
	}
}
