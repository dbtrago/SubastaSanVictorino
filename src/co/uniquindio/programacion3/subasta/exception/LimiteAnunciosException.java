package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class LimiteAnunciosException extends Exception implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public LimiteAnunciosException(String mensaje) {
		super(mensaje);
	}

}