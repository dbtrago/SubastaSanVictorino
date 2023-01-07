package co.uniquindio.programacion3.subasta.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import co.uniquindio.programacion3.subasta.modell.Anunciante;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Compradores;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;

public class HiloClienteServidor extends Thread{
	
	private ObjectInputStream flujoEntradaObjeto;
	private DataInputStream flujoEntradaData;
	private ObjectOutputStream flujoSalidaOjeto;

	Server server;
	


	public void inicializar(ObjectInputStream flujoEntradaObjecto, ObjectOutputStream flujoSalidaObjeto,
			DataInputStream flujoEntradaData, Server server) {

		this.flujoEntradaObjeto = flujoEntradaObjecto;
		this.flujoSalidaOjeto = flujoSalidaObjeto;
		this.flujoEntradaData = flujoEntradaData;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			String mensaje = recibirMensaje();
			String tipoObjeto = mensaje.split("-")[0];
			String accion = mensaje.split("-")[1];
			
			if(accion.equals("Cargar")){
				enviarObjeto(tipoObjeto);
			}else{
				if(accion.equals("Guardar")){
					recibirObjeto(tipoObjeto);
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	private String recibirMensaje() {
		String mensaje = "";
		try {
			mensaje = flujoEntradaData.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}

	private void enviarObjeto(String tipoObjeto) {
		Object listaObjetos = null;
		try {
		if(tipoObjeto.equals("Personas")){
			listaObjetos = Persistencia.cargarPersonas();
		}else{
			if(tipoObjeto.equals("Anunciantes")){
				listaObjetos = Persistencia.cargarAnunciantes();
			}else{
				if(tipoObjeto.equals("Compradores")){
					listaObjetos = Persistencia.cargarCompradores();
				}else{
					if(tipoObjeto.equals("Pujas")){
						listaObjetos = Persistencia.cargarPujas();
					}else{
						if(tipoObjeto.equals("Anuncios")){
							listaObjetos = Persistencia.cargarAnuncios();
						}
					}
				}
			}
		}
		flujoSalidaOjeto.writeObject(listaObjetos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@SuppressWarnings("unchecked")
	private void recibirObjeto(String tipoObjeto) throws ClassNotFoundException, IOException {
		Object objeto = flujoEntradaObjeto.readObject();
		if(objeto != null){
			if(tipoObjeto.equals("Personas")){
				ArrayList<Persona> listaPersonas = (ArrayList<Persona>) objeto;
				Persistencia.guardarPersonas(listaPersonas);
			}else{
				if(tipoObjeto.equals("Anunciantes")){
					ArrayList<Anunciante> listaAnunciantes = (ArrayList<Anunciante>) objeto;
					Persistencia.guardarAnunciantes(listaAnunciantes);
				}else{
					if(tipoObjeto.equals("Compradores")){
						ArrayList<Compradores> listaCompradores = (ArrayList<Compradores>) objeto;
						Persistencia.guardarCompradores(listaCompradores);
					}else{
						if(tipoObjeto.equals("Pujas")){
							ArrayList<Puja> listaPujas = (ArrayList<Puja>) objeto;
							Persistencia.guardarPujas(listaPujas);
						}else{
							if(tipoObjeto.equals("Anuncios")){
								ArrayList<Anuncio> listaAnuncios = (ArrayList<Anuncio>) objeto;
								Persistencia.guardarAnuncios(listaAnuncios);
							}
						}
					}
				}
			}
		}
	}
}
