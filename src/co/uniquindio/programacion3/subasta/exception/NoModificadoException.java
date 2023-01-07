package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class NoModificadoException extends Exception implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public NoModificadoException(String mensaje) {
		super(mensaje);
	}
}