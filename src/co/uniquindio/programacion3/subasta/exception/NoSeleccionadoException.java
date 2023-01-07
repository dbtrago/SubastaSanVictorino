package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class NoSeleccionadoException extends Exception implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public NoSeleccionadoException(String mensaje) {
		super(mensaje);
	}
}
