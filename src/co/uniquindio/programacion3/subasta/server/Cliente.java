package co.uniquindio.programacion3.subasta.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	// puerto y host

		int puerto;
		String host;

		// socket cliente
		Socket socketComunicacion;

		// flujos de entrada y salida
		ObjectOutputStream flujoSalidaObjeto;
		DataOutputStream flujoSalidaData;
		ObjectInputStream flujoEntradaObjeto;

		public Cliente(String host, int puerto) {
			this.puerto = puerto;
			this.host = host;
		}


		public Object iniciarCliente(Object objeto, String tipoObjeto, String accion) {
			Object objetoAux = null;
			try {
				crearConexion();

				flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());
				flujoSalidaData = new DataOutputStream(socketComunicacion.getOutputStream());
				flujoEntradaObjeto = new ObjectInputStream(socketComunicacion.getInputStream());
				
				if(accion.equals("Cargar")){
					String mensaje = tipoObjeto+"-"+accion+"-";
					enviarMensaje(mensaje);
					objetoAux = recibirObjeto();
				}else{
					if(accion.equals("Guardar")){
						String mensaje = tipoObjeto+"-"+accion+"-";
						enviarMensaje(mensaje);
						enviarObjeto(objeto);
					}
				}
				
				flujoSalidaObjeto.close();
				flujoSalidaData.close();
				flujoEntradaObjeto.close();
				socketComunicacion.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return objetoAux;

		}

		private void enviarMensaje(String mensaje) {
			try {
				flujoSalidaData.writeUTF(mensaje);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		private void enviarObjeto(Object objeto) {
			try {
				flujoSalidaObjeto.writeObject(objeto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private Object recibirObjeto() throws IOException {
			Object listaObjetos = null;
			try {
				listaObjetos = flujoEntradaObjeto.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaObjetos;
		}
		

		private void crearConexion() throws IOException {
			// TODO Auto-generated method stub
			socketComunicacion = new Socket(host,puerto);

		}

}
