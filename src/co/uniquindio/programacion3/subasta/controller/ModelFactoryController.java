package co.uniquindio.programacion3.subasta.controller;

import co.uniquindio.programacion3.subasta.modell.Subasta;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Persona;
import co.uniquindio.programacion3.subasta.modell.Puja;
import co.uniquindio.programacion3.subasta.modell.TipoProducto;
import co.uniquindio.programacion3.subasta.persistence.Persistencia;

//a
public class ModelFactoryController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static Subasta subasta = new Subasta();

	// ---------------------Singleton--------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquï¿½ al ser
		// protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Mï¿½todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {

		// // 1. inicializar datos y luego guardarlo en archivos
		// // iniciarSalvarDatosPrueba();
		// iniciarSalvarDatosPrueba();
		// cargarDatosDesdeArchivos();
		//
		// // 2. Cargar los datos de los archivos
		// // cargarDatosDesdeArchivos();
		//
		// // 3. Guardar y Cargar el recurso serializable binario
		// // guardarResourceBinario();
		// guardarResourceBinario();
		// cargarResourceBinario();
		//
		// // 4. Guardar y Cargar el recurso serializable XML
		// // guardarResourceXML();
		// guardarResourceXML();
		// cargarResourceXML();
		//
		// // Siempre se debe verificar si la raiz del recurso es null
		if (subasta == null) {
			System.out.println("Es null");
			inicializarDatos();
			// guardarResourceSerializable();
			guardarResourceXML();
		}

		// Registrar la accion de incio de sesiï¿½n

		Persistencia.guardaRegistroLog("Inicio de sesiÃ³n del usuario:pedro", 1, "inicioSesion");
	}


	public void cargarResourceBinario() {

		subasta = Persistencia.cargarRecursoSubastaBinario();
	}

	public void guardarResourceBinario() {

		Persistencia.guardarRecursoSubastaBinario(subasta);
	}

	public void cargarResourceXML() {

		subasta = Persistencia.cargarRecursoSubastaXML();
	}

	public void guardarResourceXML() {

		Persistencia.guardarRecursoSubastaXML(subasta);
	}

	public void inicializarDatos() {

		subasta = new Subasta();

		Persona persona = new Persona();
		persona.setClave("0000");
		persona.setDocumento("1234");
		persona.setEdad(20);
		persona.setNombre("Ana");

		subasta.getListaPersonas().add(persona);

		Anuncio anuncio = new Anuncio();
		anuncio.setCodigo("12345");
		anuncio.setDescripcion("Producto para la iluminaicon del hogar");
		// anuncio.setFechaFinalizacion(fechaFinalizacion);
		// anuncio.setFechaPublicacion(fechaPublicacion);
		// anuncio.setImageByte(imageByte);
		anuncio.setNombreProducto("Lampara");
		// anuncio.setTipoProducto("Hogar");
		anuncio.setValorInicial(100.000);

		subasta.getListaAnuncios().add(anuncio);

		try {
			Persistencia.guardarPersonas(subasta.getListaPersonas());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------- GETTERS AND SETTERS---------------

	public static Subasta getSubasta() {
		return subasta;
	}

	public static ArrayList<Anuncio> getListaAnuncios() {
		return subasta.getListaAnuncios();
	}

	// public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
	// this.listaAnuncios = listaAnuncios;
	// }
	//
	// public ArrayList<Persona> getListaPersonas() {
	// return listaPersonas;
	// }
	//
	// public void setListaPersonas(ArrayList<Persona> listaPersonas) {
	// this.listaPersonas = listaPersonas;
	// }

	public static void setSubasta(Subasta subasta) {
		ModelFactoryController.subasta = subasta;
	}

	// public ArrayList<Anunciante> getListaAnunciantes() {
	// return listaAnunciantes;
	// }
	//
	// public void setListaAnunciantes(ArrayList<Anunciante> listaAnunciantes) {
	// this.listaAnunciantes = listaAnunciantes;
	// }

	// -----------------PERSONA------------------

	public static Persona crearPersona(String documento, String nombre, String clave, int edad, String tipoDeUsuario) {

		Persona persona = null;
		try {
			persona = subasta.agregarPersona(documento, nombre, clave, edad, tipoDeUsuario);
		} catch (Exception e) {
			e.getMessage();

		}
		return persona;

	}

	public static Persona buscarPersona(String nombre) {

		Persona persona = null;
		try {
			persona = getSubasta().obtenerUsuario(nombre);
		} catch (Exception e) {
			e.getMessage();

		}
		return persona;

	}

	public static Boolean existeUsuario(String nombre) {

		try {

			if (getSubasta().existeUsuario(nombre)) {
				return true;
			}

		} catch (Exception e) {
			e.getMessage();

		}
		return false;
	}

	// public boolean actualizarPersona(String documentoActual, String
	// documento, String nombre, String clave, int edad) {
	//
	// return getSubasta().actualizarPersona(documento, nombre, clave, edad);
	//
	// }

	public Boolean eliminarPersona(String codigo) {

		boolean flagPersonaExiste = false;

		try {
			flagPersonaExiste = getSubasta().eliminarPersona(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return flagPersonaExiste;
	}

	public Persona obtenerPersona(String codigo) {
		return null;
	}

	public static ArrayList<Persona> obtenerPersona() {
		return subasta.getListaPersonas();
	}

	public static ArrayList<Puja> obtenerPuja() {
		return subasta.getListaPujas();
	}

	// -----------------ANUNCIO------------------

	public static Anuncio crearAnuncio(String nombreProducto, String descripcion, String documento, LocalDate fechaPublicacion,
			LocalDate fechaFinalizacion, Double valorInicial, TipoProducto tipoProducto, String codigo, byte[] array, ArrayList<Puja>listaPujas) {

		Anuncio anuncio = null;
		try {
			anuncio = getSubasta().agregarAnuncio(nombreProducto, descripcion, documento, fechaPublicacion, fechaFinalizacion,
					valorInicial, tipoProducto, codigo, array, listaPujas);
		} catch (Exception e) {
			e.getMessage();

		}
		return anuncio;

	}

	// public boolean actualizarAnuncio(String codigoActual, String
	// nombreProducto, String descripcion, String documentoAnunciante,
	// String fechaPublicacion, String fechaFinalizacion, double valorInicial,
	// double valorSuperior,
	// TipoProducto tipoProducto, Puja listaPujas, String codigo) {
	//
	// return getSubasta().actualizarAnuncio(String nombreProducto, String
	// descripcion, String documentoAnunciante,
	// String fechaPublicacion, String fechaFinalizacion, double valorInicial,
	// double valorSuperior,
	// TipoProducto tipoProducto, Puja listaPujas, String codigo);
	//
	// }

	public static Boolean eliminarAnuncio(String codigo) {

		boolean flagPersonaExiste = false;

		try {
			flagPersonaExiste = getSubasta().eliminarAnuncio(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return flagPersonaExiste;
	}

	public Persona obtenerAnuncio(String codigo) {
		return null;
	}

	public static ArrayList<Anuncio> obtenerAnuncios() {
		return subasta.getListaAnuncios();
	}

	public static boolean crearPuja(Anuncio anuncioSeleccionado, String idComprador, double valorOfertador) {
		return subasta.agregarPuja(anuncioSeleccionado, idComprador, valorOfertador);
	}

	public static void cargarDatos() throws IOException {

		subasta.setListaAnunciantes(Persistencia.cargarAnunciantes());
		subasta.setListaCompradores(Persistencia.cargarCompradores());
		subasta.setListaPujas(Persistencia.cargarPujas());
		subasta.setListaAnuncios(Persistencia.cargarAnuncios());
	}

	public static void guardarAnunciantes() throws IOException {
		Persistencia.guardarAnunciantes(subasta.getListaAnunciantes());
	}

	public static void guardarCompradores() throws IOException {
		Persistencia.guardarCompradores(subasta.getListaCompradores());
	}

	public static void guardarPujas() throws IOException {
		Persistencia.guardarPujas(subasta.getListaPujas());
	}

	public static void guardarAnuncios() throws IOException {
		Persistencia.guardarAnuncios(subasta.getListaAnuncios());
	}

	// --------------------

	public static String getStringUsuarios() {

		return subasta.getStringUsuarios();
	}

	// public static String getStringProductos() {
	//
	// return subasta.getStringProductos();
	// }

	public static String getStringAnuncios() {

		return subasta.getStringAnuncios();
	}

}