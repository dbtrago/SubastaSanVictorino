package co.uniquindio.programacion3.subasta.modell;

import java.io.Serializable;

//a
public class Anunciante extends Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Anunciante() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Anunciante(String documento, String nombre, String clave, int edad, String tipoDeUsuario) {
			super(documento, nombre, clave, edad, tipoDeUsuario);
			// TODO Auto-generated constructor stub
		}

}