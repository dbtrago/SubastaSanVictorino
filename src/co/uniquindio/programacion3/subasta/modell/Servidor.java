package co.uniquindio.programacion3.subasta.modell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable {

	private int puerto;

	public Servidor(int puerto) {
		this.puerto = puerto;
	}

	public void run() {

		ServerSocket servidor = null;
		Socket sc = null;
//		final int PUERTO = 5000;

		DataInputStream entrada;
		DataOutputStream salida;

		try {
			servidor = new ServerSocket(puerto);

			while (true) {

				sc = servidor.accept();

				entrada = new DataInputStream(sc.getInputStream());
//				salida = new DataOutputStream(sc.getOutputStream());

				String mensaje = entrada.readUTF();

				//Esta pendiente a algun cambio
				this.setChanged();
				//lanza el cambio
				this.notifyObservers(mensaje);
				//limpia los cambios
				this.clearChanged();

//				salida.writeUTF("¡Hola!");

				//cierra el servidor
				sc.close();
//				System.out.println("Comprador desconectado");

			}
		} catch (Exception e) {		}
	}
}
