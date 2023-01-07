package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class DatosVaciosException extends Exception implements Serializable {
	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public DatosVaciosException(String mensaje) {
		super(mensaje);
	}

}
