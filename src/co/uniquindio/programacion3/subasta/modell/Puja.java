package co.uniquindio.programacion3.subasta.modell;
import java.io.Serializable;
//a
import java.util.ArrayList;

public class Puja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombreProducto;
	String codigoPuja;
	ArrayList<String> listaPersonas = new ArrayList<>();
	double inicioPuja;
	double finPuja;
	String idComprador;
	String idAnunciante;
	double valorOfertado;
	ArrayList<Double> listaOfertas = new ArrayList<>();

	// Metodos getters and setters

    public String getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(String idComprador) {
        this.idComprador = idComprador;
    }

    public Double getValorOfertado() {
        return valorOfertado;
    }

    public void setValorOfertado(Double valorOfertad) {
        this.valorOfertado = valorOfertad;
    }

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCodigoPuja() {
		return codigoPuja;
	}

	public void setCodigoPuja(String codigoPuja) {
		this.codigoPuja = codigoPuja;
	}

	public ArrayList<String> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(ArrayList<String> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public double getInicioPuja() {
		return inicioPuja;
	}

	public void setInicioPuja(double inicioPuja) {
		this.inicioPuja = inicioPuja;
	}

	public double getFinPuja() {
		return finPuja;
	}

	public void setFinPuja(double finPuja) {
		this.finPuja = finPuja;
	}

	public ArrayList<Double> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(ArrayList<Double> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}
	

	public String getIdAnunciante() {
		return idAnunciante;
	}

	public void setIdAnunciante(String idAnunciante) {
		this.idAnunciante = idAnunciante;
	}

	// Metodo constructor vacio
	public Puja() {
		super();
	}

	// Metodo constructor
	public Puja(String nombreProducto, String codigoPuja, ArrayList<String> listaPersonas, double inicioPuja,
			double finPuja, String idComprador, double valorOfertado, ArrayList<Double> listaOfertas, 
			String idAnunciante) {
		super();
		this.nombreProducto = nombreProducto;
		this.codigoPuja = codigoPuja;
		this.listaPersonas = listaPersonas;
		this.inicioPuja = inicioPuja;
		this.finPuja = finPuja;
		this.idComprador = idComprador;
		this.valorOfertado = valorOfertado;
		this.listaOfertas = listaOfertas;
		this.idAnunciante = idAnunciante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPuja == null) ? 0 : codigoPuja.hashCode());
		long temp;
		temp = Double.doubleToLongBits(finPuja);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(inicioPuja);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listaOfertas == null) ? 0 : listaOfertas.hashCode());
		result = prime * result + ((listaPersonas == null) ? 0 : listaPersonas.hashCode());
		result = prime * result + ((nombreProducto == null) ? 0 : nombreProducto.hashCode());
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
		Puja other = (Puja) obj;
		if (codigoPuja == null) {
			if (other.codigoPuja != null)
				return false;
		} else if (!codigoPuja.equals(other.codigoPuja))
			return false;
		if (Double.doubleToLongBits(finPuja) != Double.doubleToLongBits(other.finPuja))
			return false;
		if (Double.doubleToLongBits(inicioPuja) != Double.doubleToLongBits(other.inicioPuja))
			return false;
		if (listaOfertas == null) {
			if (other.listaOfertas != null)
				return false;
		} else if (!listaOfertas.equals(other.listaOfertas))
			return false;
		if (listaPersonas == null) {
			if (other.listaPersonas != null)
				return false;
		} else if (!listaPersonas.equals(other.listaPersonas))
			return false;
		if (nombreProducto == null) {
			if (other.nombreProducto != null)
				return false;
		} else if (!nombreProducto.equals(other.nombreProducto))
			return false;
		return true;
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Pujas [nombreProducto=" + nombreProducto + ", codigoPuja=" + codigoPuja + ", listaPersonas="
				+ listaPersonas + ", inicioPuja=" + inicioPuja + ", finPuja=" + finPuja + ", listaOfertas="
				+ listaOfertas + "]";
	}

}