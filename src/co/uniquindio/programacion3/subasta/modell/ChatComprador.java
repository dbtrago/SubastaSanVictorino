package co.uniquindio.programacion3.subasta.modell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatComprador implements Runnable {

	private int puerto;
	private String mensaje;

	public ChatComprador(int puerto, String mensaje) {
		this.puerto = puerto;
		this.mensaje = mensaje;
	}

	public void run() {
//		host del servidor
		final String HOST = "127.0.0.1";
//		final int PUERTO = 5000;

		DataInputStream entrada;
		DataOutputStream salida;

		try {

//			Creo el socket para conectarme con el cliente
			Socket sc = new Socket(HOST, puerto);

//			entrada = new DataInputStream(sc.getInputStream());
			salida = new DataOutputStream(sc.getOutputStream());

			salida.writeUTF(mensaje);

//			String mensaje = entrada.readUTF();
//
//			System.out.println(mensaje);

			sc.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
