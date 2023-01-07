package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class NoCreadoException extends Exception implements Serializable{

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public NoCreadoException(String mensaje) {
		super(mensaje);
	}

}