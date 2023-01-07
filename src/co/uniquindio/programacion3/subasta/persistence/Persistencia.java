package co.uniquindio.programacion3.subasta.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import co.uniquindio.programacion3.subasta.controller.ModelFactoryController;
import co.uniquindio.programacion3.subasta.exception.NoCreadoException;
import co.uniquindio.programacion3.subasta.modell.Anunciante;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Compradores;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.modell.Subasta;
import co.uniquindio.programacion3.subasta.modell.TipoProducto;

public class Persistencia {

	// Toda la subasta serializada en binario en el c
	public static final String RUTA_C = "C:/Users/Daniel Buitrago/Desktop/SubastaToda.txt";
	public static final String RUTA_ARCHIVO_PERSONAS = "./Persistencia/Archivos/Personas.txt";
	public static final String RUTA_ARCHIVO_ANUNCIANTES = "./Persistencia/Archivos/Anunciantes.txt";
	public static final String RUTA_ARCHIVO_COMPRADORES = "./Persistencia/Archivos/Compradores.txt";
	public static final String RUTA_ARCHIVO_PUJAS = "./Persistencia/Archivos/Pujas.txt";
	public static final String RUTA_ARCHIVO_ANUNCIOS = "./Persistencia/Archivos/Anuncios.txt";

	// Auxiliares para convertir a binario
	public static final String RUTA_AUX_ANUNCIANTES = "./Persistencia/Archivos/AnunciantesAux.txt";
	public static final String RUTA_AUX_ANUNCIOS = "./Persistencia/Archivos/AnunciosAux.txt";
	public static final String RUTA_AUX_PUJAS = "./Persistencia/Archivos/PujasAux.txt";

	// Covertir a binario
	public static final String RUTA_ARCHIVO_SUBASTA_BINARIO = "./Persistencia/model.dat";

	public static final String RUTA_ARCHIVO_SUBASTA_XML = "C:/Subasta/Persistencia/model.xml";

	public static final String RUTA_ARCHIVO_PRODUCTOS = "C:/td/Persistencia/Archivos/Productos.txt";

	// Guardar registro de acciones
	public static final String RUTA_ARCHIVO_LOG = "./Persistencia/Archivos/SubastaLog.txt";

	public static void cargarDatosArchivos(Subasta subasta) throws FileNotFoundException, IOException {

		// Cargar archivo anunciantes
		ArrayList<Anunciante> anunciantesCargados = cargarAnunciantes();
		if (anunciantesCargados.size() > 0) {
			subasta.getListaAnunciantes().addAll(anunciantesCargados);

		}

		// Cargar archivo compradores
		ArrayList<Compradores> compradoresCargador = cargarCompradores();
		if (compradoresCargador.size() > 0) {
			subasta.getListaCompradores().addAll(compradoresCargador);

		}

	}

	/**
	 * Guarda en un archivo de texto todos la informaci n de las personas
	 * almacenadas en el ArrayList
	 *
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarPersonas(ArrayList<Persona> listaPersonas) throws IOException {
		String contenido = "";

		for (Persona persona : listaPersonas) {
			contenido += persona.getNombre() + "@@" + persona.getDocumento() + "@@" + persona.getEdad() + "@@"
					+ persona.getClave() + "@@" + persona.getTipoDeUsuario() + "\r\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PERSONAS, contenido, false);

	}

	public static void guardarAnunciantes(ArrayList<Anunciante> listaAnunciantes) throws IOException {

		String contenido = "";

		for (Anunciante anunciante : listaAnunciantes) {
			contenido += anunciante.getNombre() + "@@" + anunciante.getDocumento() + "@@" + anunciante.getEdad() + "@@"
					+ anunciante.getClave() + "@@" + anunciante.getTipoDeUsuario() + "\r\n";
		}

		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIANTES, contenido, false);

	}

	public static void guardarCompradores(ArrayList<Compradores> listaCompradores) throws IOException {

		String contenido = "";

		for (Compradores comprador : listaCompradores) {
			contenido += comprador.getNombre() + "@@" + comprador.getDocumento() + "@@" + comprador.getEdad() + "@@"
					+ comprador.getClave() + "@@" + comprador.getTipoDeUsuario() + comprador.getNumeroOfertas()
					+ "\r\n";
		}

		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMPRADORES, contenido, false);

	}

	public static void guardarPujas(ArrayList<Puja> listaPujas) throws IOException {

		String contenido = "";

		for (Puja puja : listaPujas) {
			contenido += puja.getIdComprador() + "@@" + puja.getValorOfertado() + "@@" + puja.getCodigoPuja() + "@@"
					+ puja.getFinPuja() + "@@" + puja.getInicioPuja() + "@@" + puja.getNombreProducto() + "@@"
					+ puja.getIdAnunciante() + puja.getNombreProducto() + "@@" + puja.getIdComprador() +"\r\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUJAS, contenido, false);
	}

	public static void guardarAnuncios(ArrayList<Anuncio> listaAnuncios) throws IOException {

		String contenido = "";

		for (Anuncio anuncio : listaAnuncios) {

			String tipoProducto = obtenerStringTipoProducto(anuncio.getTipoProducto());
			String fechaPublicacion = String.valueOf(anuncio.getFechaPublicacion());
			String fechaFinalizacion = String.valueOf(anuncio.getFechaFinalizacion());
			String valorInicial = String.valueOf(anuncio.getValorInicial());
			String valorSuperior = String.valueOf(anuncio.getValorSuperior());

			contenido += anuncio.getNombreProducto() + "@@" + anuncio.getDescripcion() + "@@"
					+ anuncio.getDocumentoAnunciante() + "@@" + fechaPublicacion + "@@" + fechaFinalizacion + "@@"
					+ valorInicial + "@@" + valorSuperior + "@@" + tipoProducto + "@@" + anuncio.getCodigo() + "\r\n";
		}

		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ANUNCIOS, contenido, false);

	}

	private static String obtenerStringTipoProducto(TipoProducto tipoProducto) {
		if (tipoProducto.equals(TipoProducto.TECNOLOGIA)) {
			return "TECNOLOGIA";
		} else {
			if (tipoProducto.equals(TipoProducto.HOGAR)) {
				return "HOGAR";
			} else {
				if (tipoProducto.equals(TipoProducto.DEPORTES)) {
					return "DEPORTES";
				} else {
					if (tipoProducto.equals(TipoProducto.VEHICULOS)) {
						return "VEHICULOS";
					} else {
						if (tipoProducto.equals(TipoProducto.BIEN_RAIZ)) {
							return "BIEN_RAIZ";
						}
					}
				}
			}
		}
		return null;
	}

	// ----------------------LOADS------------------------

	/**
	 *
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de
	 *         texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public static ArrayList<Persona> cargarPersonas() throws IOException {

		ArrayList<Persona> personas = new ArrayList<Persona>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PERSONAS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);

			Persona persona = new Persona();
			persona.setClave(linea.split("@@")[0]);
			persona.setDocumento(linea.split("@@")[1]);
			persona.setEdad(Integer.valueOf((linea.split("@@")[2])));
			persona.setNombre(linea.split("@@")[3]);
			persona.setTipoDeUsuario(linea.split("@@")[4]);
			personas.add(persona);
		}
		return personas;

	}

	public static ArrayList<Anunciante> cargarAnunciantes() throws IOException {

		ArrayList<Anunciante> anunciantes = new ArrayList<Anunciante>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIANTES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anunciante anunciante = new Anunciante();
			anunciante.setNombre(linea.split("@@")[0]);
			anunciante.setDocumento(linea.split("@@")[1]);
			anunciante.setEdad(Integer.valueOf((linea.split("@@")[2])));
			anunciante.setClave(linea.split("@@")[3]);
			anunciante.setTipoDeUsuario(linea.split("@@")[4]);
			anunciantes.add(anunciante);

		}
		return anunciantes;
	}

	public static ArrayList<Compradores> cargarCompradores() throws IOException {

		ArrayList<Compradores> compradores = new ArrayList<Compradores>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMPRADORES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Compradores comprador = new Compradores();
			comprador.setNombre(linea.split("@@")[0]);
			comprador.setDocumento(linea.split("@@")[1]);
			comprador.setEdad(Integer.valueOf((linea.split("@@")[2])));
			comprador.setClave(linea.split("@@")[3]);
			comprador.setTipoDeUsuario(linea.split("@@")[4]);
			comprador.setNumeroOfertas(Integer.valueOf((linea.split("@@")[5])));
			compradores.add(comprador);

		}
		return compradores;
	}

	public static ArrayList<Puja> cargarPujas() throws IOException {
		ArrayList<Puja> pujas = new ArrayList<>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUJAS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Puja puja = new Puja();
			puja.setIdComprador(linea.split("@@")[0]);
			puja.setValorOfertado(Double.valueOf((linea.split("@@")[1])));
			puja.setCodigoPuja(linea.split("@@")[2]);
			puja.setFinPuja(Double.valueOf((linea.split("@@")[3])));
			puja.setInicioPuja(Double.valueOf((linea.split("@@")[4])));
			puja.setNombreProducto(linea.split("@@")[5]);
			puja.setIdAnunciante(linea.split("@@")[6]);
			puja.setIdComprador(linea.split("@@")[7]);

			pujas.add(puja);
		}

		return pujas;
	}


	public static ArrayList<Anuncio> cargarAnuncios() throws IOException {

		ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ANUNCIOS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Anuncio anuncio = new Anuncio();
			anuncio.setNombreProducto(linea.split("@@")[0]);
			anuncio.setDescripcion(linea.split("@@")[1]);
			anuncio.setDocumentoAnunciante(linea.split("@@")[2]);
			LocalDate fechaPublicacion = LocalDate.parse(linea.split("@@")[3]);
			anuncio.setFechaPublicacion(fechaPublicacion);
			LocalDate fechaFinalizacion = LocalDate.parse(linea.split("@@")[4]);
			anuncio.setFechaFinalizacion(fechaFinalizacion);
			anuncio.setValorInicial(Double.valueOf((linea.split("@@")[5])));
			anuncio.setValorSuperior(Double.valueOf((linea.split("@@")[6])));
			TipoProducto tipoProducto = obtenerTipoProducto(linea.split("@@")[7]);
			anuncio.setTipoProducto(tipoProducto);
			anuncio.setCodigo(linea.split("@@")[8]);
			anuncios.add(anuncio);
		}

		return anuncios;
	}

	private static TipoProducto obtenerTipoProducto(String string) {
		if (string.equals("TECNOLOGIA")) {
			return TipoProducto.TECNOLOGIA;
		} else {
			if (string.equals("HOGAR")) {
				return TipoProducto.HOGAR;
			} else {
				if (string.equals("DEPORTES")) {
					return TipoProducto.DEPORTES;
				} else {
					if (string.equals("VEHICULOS")) {
						return TipoProducto.VEHICULOS;
					} else {
						if (string.equals("BIEN_RAIZ")) {
							return TipoProducto.BIEN_RAIZ;
						} else {
							return null;
						}
					}
				}
			}
		}
	}

	// -----------------------REGISTRO LOG------------------
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);

	}

	// ----------------------EXCEPCIONES-----------------

	public static boolean iniciarSesion(String nombre, String clave)
			throws FileNotFoundException, IOException, NoCreadoException {

		if (validarUsuario(nombre, clave)) {
			return true;
		} else {
			throw new NoCreadoException("Usuario no existe");
		}

	}

	private static boolean validarUsuario(String nombre, String clave) throws FileNotFoundException, IOException {

		ArrayList<Persona> personas = Persistencia.cargarPersonas();

		for (int indicePersona = 0; indicePersona < personas.size(); indicePersona++) {
			Persona personaAux = personas.get(indicePersona);
			if (personaAux.getNombre().equalsIgnoreCase(nombre) && personaAux.getClave().equalsIgnoreCase(clave)) {
				return true;
			}
		}
		return false;
	}

	// ----------------------SAVES------------------------

	// /**
	// * Guarda en un archivo de texto todos la informaci n de las personas
	// * almacenadas en el ArrayList
	// *
	// * @param objetos
	// * @param ruta
	// * @throws IOException
	// */
	//
	// public static void guardarObjetos(ArrayList<Cliente> listaClientes,
	// String ruta) throws IOException {
	// String contenido = "";
	//
	// for (Cliente clienteAux : listaClientes) {
	// contenido += clienteAux.getNombre() + "," + clienteAux.getApellido() +
	// "," + clienteAux.getCedula()
	// + clienteAux.getDireccion() + "," + clienteAux.getCorreo() + "," +
	// clienteAux.getFechaNacimiento()
	// + "," + clienteAux.getTelefono() + "\n";
	// }
	// ArchivoUtil.guardarArchivo(ruta, contenido, true);
	// }

	// ------------------------------------SERIALIZACI N y XML

	public static Subasta cargarRecursoSubastaBinario() {

		Subasta subasta = null;

		try {
			subasta = (Subasta) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_SUBASTA_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subasta;
	}

	public static void guardarRecursoSubastaBinario(Subasta subasta) {

		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_SUBASTA_BINARIO, subasta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Subasta cargarRecursoSubastaXML() {

		Subasta subasta = null;

		try {
			subasta = (Subasta) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subasta;

	}

	public static void guardarRecursoSubastaXML(Subasta subasta) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_SUBASTA_XML, subasta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------METODOS PARA SERIALIZAR EN BINARIO-------------
	public static void serializarEmpresaBinario() {
		/**
		 * Metodo que permite serializar la empresa en un archivo binario se
		 * pasa la ruta donde se va a guardar el archivo, esta se halla en
		 * archivo util
		 */
		ArchivoUtil.serializarBinario(RUTA_ARCHIVO_SUBASTA_BINARIO, ModelFactoryController.getSubasta());
		ArchivoUtil.serializarBinario(RUTA_C, ModelFactoryController.getSubasta());
	}

	public static void serializarEmpresaTXT() {
		/**
		 * Este metodo permite serializar la empresa en un txt, cada
		 * implementación retorna un string con los datos contenidos, se pasa la
		 * ruta donde se va a guardar el archivo, esta se halla en archivo util
		 */
		// obtengo el string de los usuarios
		String usuariosTxt = ModelFactoryController.getStringUsuarios();
		try {
			ArchivoUtil.guardarArchivo(RUTA_AUX_ANUNCIANTES, usuariosTxt, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// obtengo el string de los anuncios
		String anunciosTxt = ModelFactoryController.getStringAnuncios();

		try {
			ArchivoUtil.guardarArchivo(RUTA_AUX_ANUNCIOS, anunciosTxt, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// obtengo el string de las pujas
		String pujasTxt = ModelFactoryController.getStringAnuncios();

		try {
			ArchivoUtil.guardarArchivo(RUTA_AUX_PUJAS, pujasTxt, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// //obtengo el string de los productos
		// String productosTxt = ModelFactoryController.getStringProductos();
		// ArchivoUtil.serializarTxt(RUTA_ARCHIVO_PRODUCTOS, productosTxt);
		//
		// //obtengo el string de las transacciones
		// //String transaccionesTxt =
		// ModelFactoryController.getStringTransacciones();
		// //ArchivoUtil.serializarTxt(Utils.RUTA_TRANSACCIONES_TXT,
		// transaccionesTxt);
		//

	}

	public static Subasta deserializarBinario() {

		return ArchivoUtil.deserializarBinario(RUTA_ARCHIVO_SUBASTA_BINARIO);
	}

}