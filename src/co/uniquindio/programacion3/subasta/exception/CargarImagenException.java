package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class CargarImagenException extends Exception implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CargarImagenException(String mensaje) {
		super(mensaje);
	}

}
