package co.uniquindio.programacion3.subasta.modell;

import java.io.Serializable;

//a
public class Persona implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String documento;
    String nombre;
    String clave;
    String tipoDeUsuario;
    int edad;

    // Metodos getters and setters
    
    public String getDocumento() {
		return documento;
	}
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

	// Metodo contructor vacio
    public Persona() {
        super();
    }

    // Metodo constructor
    public Persona(String documento, String nombre, String clave, int edad, String tipoDeUsuario) {
        super();
        this.documento = documento;
        this.nombre = nombre;
        this.clave = clave;
        this.edad = edad;
        this.tipoDeUsuario = tipoDeUsuario;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clave == null) ? 0 : clave.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
        result = prime * result + edad;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tipoDeUsuario == null) ? 0 : tipoDeUsuario.hashCode());
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
        Persona other = (Persona) obj;
        if (clave == null) {
            if (other.clave != null)
                return false;
        } else if (!clave.equals(other.clave))
            return false;
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        if (edad != other.edad)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (tipoDeUsuario == null) {
            if (other.tipoDeUsuario != null)
                return false;
        } else if (!tipoDeUsuario.equals(other.tipoDeUsuario))
            return false;
        return true;
    }

    // Metodo toString
    @Override
    public String toString() {
        return documento + "@@" + nombre + "@@" + edad + "@@" + clave + "@@" + tipoDeUsuario;
    }

    public void setNumeroOfertas(Integer valueOf) {
        // TODO Auto-generated method stub

    }

}