package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class LimitePujasException extends Exception implements Serializable{

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	private LimitePujasException(String mensaje) {
		super(mensaje);
	}

}