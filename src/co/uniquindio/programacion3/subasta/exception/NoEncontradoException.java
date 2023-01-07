package co.uniquindio.programacion3.subasta.exception;

import java.io.Serializable;

public class NoEncontradoException extends Exception implements Serializable{

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public NoEncontradoException(String mensaje) {
		super(mensaje);
	}

}