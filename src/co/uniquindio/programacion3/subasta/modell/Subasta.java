package co.uniquindio.programacion3.subasta.modell;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import co.uniquindio.programacion3.subasta.server.Cliente;

public class Subasta implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
    String nit;
    String nombre;

    ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
    ArrayList<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
    ArrayList<Puja> listaPujas = new ArrayList<>();
    ArrayList<Anunciante> ListaAnunciantes = new ArrayList<Anunciante>();
    ArrayList<Compradores> ListaCompradores = new ArrayList<Compradores>();

    // Metodo constructor
    public Subasta(String nit, String nombre, ArrayList<Persona> listaPersonas, ArrayList<Anuncio> listaAnuncions,
            ArrayList<Puja> listaPujas) {
        super();
        this.nit = nit;
        this.nombre = nombre;
        this.listaPersonas = listaPersonas;
        this.listaAnuncios = listaAnuncions;
        this.listaPujas = listaPujas;
    }

    // Metodo contructor vacio

    public Subasta() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Subasta [nit=" + nit + ", nombre=" + nombre + ", listaPersonas=" + listaPersonas + ", listaAnuncions="
                + listaAnuncios + ", listaPujas=" + listaPujas + "]";
    }

    // Metodos getters and setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public ArrayList<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(ArrayList<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    public ArrayList<Puja> getListaPujas() {
        return listaPujas;
    }

    public void setListaPujas(ArrayList<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }

    public ArrayList<Anunciante> getListaAnunciantes() {
        return ListaAnunciantes;
    }

    public void setListaAnunciantes(ArrayList<Anunciante> listaAnunciantes) {
        ListaAnunciantes = listaAnunciantes;
    }

    public ArrayList<Compradores> getListaCompradores() {
        return ListaCompradores;
    }

    public void setListaCompradores(ArrayList<Compradores> listaCompradores) {
        ListaCompradores = listaCompradores;
    }

    // -----------------------CREAR PERSONA----------------

    public Persona agregarPersona(String documento, String nombre, String clave, int edad, String tipoDeUsuario) {

        if (existePersona(documento)) {

            return null;

        } else {

            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre(nombre);
            nuevaPersona.setEdad(edad);
            nuevaPersona.setDocumento(documento);
            nuevaPersona.setClave(clave);
            nuevaPersona.setTipoDeUsuario(tipoDeUsuario);
            getListaPersonas().add(nuevaPersona);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaPersonas;
            cliente.iniciarCliente(objeto, "Personas", "Guardar");
            return nuevaPersona;

        }

    }

    private boolean existePersona(String documento) {
        boolean existe = false;
        for (Persona persona : listaPersonas) {
            if (persona.getDocumento().equals(documento)) {
                existe = true;
                return existe;

            }

        }

        return false;
    }

    public boolean existeUsuario(String usuario) {
        boolean existe = false;
        for (Persona persona : listaPersonas) {
            if (persona.getNombre().equals(usuario)) {
                existe = true;
                return existe;

            }

        }

        return false;
    }

    public Boolean eliminarPersona(String documento) {
        Boolean flagEliminado = false;
        Persona persona = obtenerPersona(documento);

        if (persona != null) {
            getListaPersonas().remove(persona);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaPersonas;
            cliente.iniciarCliente(objeto, "Personas", "Guardar");

            flagEliminado = true;
        }
        return flagEliminado;
    }

    public Persona obtenerPersona(String documentoPersona) {

        Persona personaEncontrado = null;

        for (Persona persona : listaPersonas) {
            if (persona.getDocumento().equals(documentoPersona)) {
                personaEncontrado = persona;
                break;

            }
        }
        return personaEncontrado;

    }

    public Persona obtenerUsuario(String usuario) {

        Persona personaEncontrado = null;

        for (Persona persona : listaPersonas) {
            if (persona.getNombre().equals(usuario)) {
                personaEncontrado = persona;
                break;

            }
        }
        return personaEncontrado;

    }

    public void actualizarPersona(String documento, String nombre, String clave, int edad) {
        Persona persona = obtenerPersona(documento);

        if (persona != null) {
            persona.setClave(clave);
            persona.setDocumento(documento);
            persona.setEdad(edad);
            persona.setNombre(nombre);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaPersonas;
            cliente.iniciarCliente(objeto, "Personas", "Guardar");
        }
    }

    // -----------------------CREAR ANUNCIO----------------
    public Anuncio agregarAnuncio(String nombreProducto, String descripcion, String documento,
            LocalDate fechaPublicacion, LocalDate fechaFinalizacion, double valorInicial,
            TipoProducto tipoProducto, String codigo, byte[] array, ArrayList<Puja>listaPujas) {
    	boolean bandera = calcularDias(fechaFinalizacion);
        if (existeAnuncio(codigo) || bandera == false) {
            return null;
        } else {
            Anuncio nuevoAnuncio = new Anuncio();

            nuevoAnuncio.setDescripcion(descripcion);
            nuevoAnuncio.setDocumentoAnunciante(documento);
            nuevoAnuncio.setFechaFinalizacion(fechaFinalizacion);
            nuevoAnuncio.setFechaPublicacion(fechaPublicacion);
            nuevoAnuncio.setNombreProducto(nombreProducto);
            nuevoAnuncio.setTipoProducto(tipoProducto);
            nuevoAnuncio.setValorInicial(valorInicial);
            nuevoAnuncio.setCodigo(codigo);
            nuevoAnuncio.setImageByte(array);
            nuevoAnuncio.setListaPujas(listaPujas);

            getListaAnuncios().add(nuevoAnuncio);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaAnuncios;
            cliente.iniciarCliente(objeto, "Anuncios", "Guardar");

            return nuevoAnuncio;

        }

    }

    private boolean calcularDias(LocalDate fechaFinalizacion) {
		boolean bandera = false;
    	String fechaActual = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
		String fechaF = String.valueOf(fechaFinalizacion);

		int anioA = Integer.parseInt(fechaActual.substring(0, 4));
		int anioF = Integer.parseInt(fechaF.substring(0, 4));
		int mesA = Integer.parseInt(fechaActual.substring(5, 7));
		int mesF = Integer.parseInt(fechaF.substring(5, 7));
		int diaA = Integer.parseInt(fechaActual.substring(8, 10));
		int diaF = Integer.parseInt(fechaF.substring(8, 10));

		if(anioA<=anioF && mesA<=mesF && diaA<=diaF){
			bandera = true;
		}
		return bandera;
	}

    public boolean existeAnuncio(String codigo) {
        boolean existe = false;
        for (Anuncio anuncio : listaAnuncios) {
            if (anuncio.getCodigo().equals(codigo)) {
                existe = true;
                return existe;

            }

        }
        return existe;
    }

    public Boolean eliminarAnuncio(String codigo) {
        Boolean flagEliminado = false;
        Anuncio anuncio = obtenerAnuncio(codigo);

        if (anuncio != null) {
            getListaAnuncios().remove(anuncio);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaAnuncios;
            cliente.iniciarCliente(objeto, "Anuncios", "Guardar");

            flagEliminado = true;
        }
        return flagEliminado;
    }

    public Anuncio obtenerAnuncio(String codigoAnuncio) {
        Anuncio anuncioEncontrado = null;
        for (Anuncio anuncio : listaAnuncios) {
            if (anuncio.getCodigo().equals(codigoAnuncio)) {
                anuncioEncontrado = anuncio;
                break;

            }
        }
        return anuncioEncontrado;

    }

    public void actualizarAnuncio(String nombreProducto, String descripcion, String documentoAnunciante,
            LocalDate fechaPublicacion, LocalDate fechaFinalizacion, double valorInicial,
            TipoProducto tipoProducto, ArrayList<Puja> listaPujas, String codigo) {

        Anuncio anuncio = obtenerAnuncio(codigo);

        if (anuncio != null) {
            anuncio.setDescripcion(descripcion);
            anuncio.setFechaFinalizacion(fechaFinalizacion);
            anuncio.setFechaPublicacion(fechaPublicacion);
            anuncio.setListaPujas(listaPujas);
            anuncio.setDocumentoAnunciante(documentoAnunciante);
            anuncio.setNombreProducto(nombreProducto);
            anuncio.setTipoProducto(tipoProducto);
            anuncio.setValorInicial(valorInicial);
            anuncio.setCodigo(codigo);

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaAnuncios;
            cliente.iniciarCliente(objeto, "Anuncios", "Guardar");
        }

    }



    // -----------------------CREAR PUJA----------------

    public boolean agregarPuja(Anuncio anuncioSeleccionado, String idComprador, double valorOfertado) {
		boolean bandera = false;
		boolean banderaAux = calcularDias(anuncioSeleccionado.getFechaFinalizacion());
		if(!idComprador.isEmpty() && banderaAux == true){
			String codigoPuja = String.valueOf((int)(Math. random()*9999));
			Puja puja = new Puja(anuncioSeleccionado.getNombreProducto(), codigoPuja, null,
					0, 0, idComprador, valorOfertado, null, anuncioSeleccionado.getDocumentoAnunciante());
			listaPujas.add(puja);
			listaAnuncios.remove(anuncioSeleccionado);
			ArrayList<Puja>listaPuja = new ArrayList<Puja>();
			if(anuncioSeleccionado.getListaPujas() == null){
				anuncioSeleccionado.setListaPujas(listaPuja);
			}else{
				listaPuja = anuncioSeleccionado.getListaPujas();
			}
			listaPuja.add(puja);
			anuncioSeleccionado.setListaPujas(listaPuja);
			listaAnuncios.add(anuncioSeleccionado);
			bandera = true;

            Cliente cliente = new Cliente("localhost", 9999);
            Object objeto = listaPujas;
            cliente.iniciarCliente(objeto, "Pujas", "Guardar");
		}
		return bandera;
	}
	public String getStringUsuarios() {

		String mensaje = "";
		for (int i = 0; i < listaPersonas.size(); i++) {

			mensaje += listaPersonas.get(i).toString() + "\n";

		}
		return mensaje;

	}

	// public String getStringProductos() {
	//
	//
	//
	// }

	public String getStringAnuncios() {
		String mensaje = "";
		for (int i = 0; i < listaAnuncios.size(); i++) {

			mensaje += listaAnuncios.get(i).toString() + "\n";

		}
		return mensaje;

	}

	/*
	 *
	 */
	public void actualizarInformacionBinario(Subasta subastaAux) {
		for (int i = 0; i < subastaAux.getListaPersonas().size(); i++) {

			Persona persona = subastaAux.getListaPersonas().get(i);

			if (!listaPersonas.contains(persona)) {
				listaPersonas.add(persona);

			}

		}

		for (int i = 0; i < subastaAux.getListaAnuncios().size(); i++) {

			Anuncio anuncio = subastaAux.getListaAnuncios().get(i);

			if (!listaAnuncios.contains(anuncio)) {
				listaAnuncios.add(anuncio);

			}

		}
		for (int i = 0; i < subastaAux.getListaPujas().size(); i++) {

			Puja puja = subastaAux.getListaPujas().get(i);

			if (!listaPujas.contains(puja)) {
				listaPujas.add(puja);

			}

		}
		for (int i = 0; i < subastaAux.getListaAnunciantes().size(); i++) {

			Anunciante anunciante = subastaAux.getListaAnunciantes().get(i);

			if (!ListaAnunciantes.contains(anunciante)) {
				ListaAnunciantes.add(anunciante);

			}

		}

		for (int i = 0; i < subastaAux.getListaCompradores().size(); i++) {

			Compradores compradores = subastaAux.getListaCompradores().get(i);

			if (!ListaCompradores.contains(compradores)) {
				ListaCompradores.add(compradores);

			}

		}

	}

	public void guardarDatos() {
        Cliente cliente = new Cliente("localhost", 9999);
        Object objeto = listaPersonas;
        cliente.iniciarCliente(objeto, "Personas", "Guardar");

        Cliente cliente2 = new Cliente("localhost", 9999);
        Object objeto2 = ListaAnunciantes;
        cliente2.iniciarCliente(objeto2, "Anunciantes", "Guardar");

        Cliente cliente3 = new Cliente("localhost", 9999);
        Object objeto3 = ListaCompradores;
        cliente3.iniciarCliente(objeto3, "Compradores", "Guardar");

        Cliente cliente4 = new Cliente("localhost", 9999);
        Object objeto4 = listaPujas;
        cliente4.iniciarCliente(objeto4, "Pujas", "Guardar");

        Cliente cliente5 = new Cliente("localhost", 9999);
        Object objeto5 = listaAnuncios;
        cliente5.iniciarCliente(objeto5, "Anuncios", "Guardar");
	}

	@SuppressWarnings("unchecked")
	public void cargarDatos() {
		Cliente cliente = new Cliente("localhost", 9999);
		Object objeto = null;
		Object objetoAux = cliente.iniciarCliente(objeto, "Personas", "Cargar");
		ArrayList<Persona> personas = (ArrayList<Persona>) objetoAux;
		this.listaPersonas = personas;

		Cliente cliente2 = new Cliente("localhost", 9999);
		Object objeto2 = null;
		Object objetoAux2 = cliente2.iniciarCliente(objeto2, "Anunciantes", "Cargar");
		ArrayList<Anunciante> anunciantes = (ArrayList<Anunciante>) objetoAux2;
		this.ListaAnunciantes = anunciantes;

		Cliente cliente3 = new Cliente("localhost", 9999);
		Object objeto3 = null;
		Object objetoAux3 = cliente3.iniciarCliente(objeto3, "Compradores", "Cargar");
		ArrayList<Compradores> compradores = (ArrayList<Compradores>) objetoAux3;
		this.ListaCompradores = compradores;

		Cliente cliente4 = new Cliente("localhost", 9999);
		Object objeto4 = null;
		Object objetoAux4 = cliente4.iniciarCliente(objeto4, "Pujas", "Cargar");
		ArrayList<Puja> pujas = (ArrayList<Puja>) objetoAux4;
		this.listaPujas = pujas;

		Cliente cliente5 = new Cliente("localhost", 9999);
		Object objeto5 = null;
		Object objetoAux5 = cliente5.iniciarCliente(objeto5, "Anuncios", "Cargar");
		ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) objetoAux5;
		this.listaAnuncios = anuncios;

		repartirPujas();
	}

	private void repartirPujas() {
		ArrayList<Puja> pujasAnunciante = new ArrayList<>();
		if(listaAnuncios != null && listaPujas != null){
			for (Anuncio anuncio : listaAnuncios) {
				for (Puja puja : listaPujas) {
					if(anuncio.getDocumentoAnunciante().equals(puja.getIdAnunciante())){
						pujasAnunciante.add(puja);
					}
				}
				anuncio.setListaPujas(pujasAnunciante);
			}
		}
	}

}