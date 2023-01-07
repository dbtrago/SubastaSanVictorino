package co.uniquindio.programacion3.subasta.server;

import java.io.IOException;

public class AplicacionServer {
	public static void main(String[] args) {

		Server server = new Server(9999);
		try {
			server.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
