package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class NoEliminadoException extends Exception implements Serializable{

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public NoEliminadoException(String mensaje) {
		super(mensaje);
	}
}
