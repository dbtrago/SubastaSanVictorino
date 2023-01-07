package co.uniquindio.programacion3.subasta.modell;

import java.io.Serializable;

//a
public class Compradores extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Declaracion de atributos
		int numeroOfertas;

	public Compradores(String documento, String nombre, String clave, int edad, String tipoDeUsuario) {
		super(documento, nombre, clave, edad, tipoDeUsuario);
	}

	// Metodos getters and setters
	public int getNumeroOfertas() {
		return numeroOfertas;
	}

	public void setNumeroOfertas(int numeroOfertas) {
		this.numeroOfertas = numeroOfertas;
	}

	// Metodo constructor vacio
	public Compradores() {
		super();
	}

	// Metodo constructor
	public Compradores(int numeroOfertas) {
		super();
		this.numeroOfertas = numeroOfertas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroOfertas;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compradores other = (Compradores) obj;
		if (numeroOfertas != other.numeroOfertas)
			return false;
		return true;
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Compradores [numeroOfertas=" + numeroOfertas + "]";
	}


}