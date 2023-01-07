package co.uniquindio.programacion3.subasta.modell;

import java.io.Serializable;
import java.util.ArrayList;
//a
public class Chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String vendedor;
	String usuario;

	ArrayList<String> listaMensajes = new ArrayList<>();

	// Metodos getters and setters

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ArrayList<String> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(ArrayList<String> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	// Constructor vacio

	public Chat() {
		super();
	}

	// Metodo constructor

	public Chat(String vendedor, String usuario, ArrayList<String> listaMensajes) {
		super();
		this.vendedor = vendedor;
		this.usuario = usuario;
		this.listaMensajes = listaMensajes;
	}

	// Metodo toString

	@Override
	public String toString() {
		return "Chat [vendedor=" + vendedor + ", usuario=" + usuario + ", listaMensajes=" + listaMensajes + "]";
	}
}