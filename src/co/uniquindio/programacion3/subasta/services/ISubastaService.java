package co.uniquindio.programacion3.subasta.services;

import java.util.ArrayList;
import co.uniquindio.programacion3.subasta.modell.Anunciante;
import co.uniquindio.programacion3.subasta.modell.Anuncio;
import co.uniquindio.programacion3.subasta.modell.Compradores;
import co.uniquindio.programacion3.subasta.modell.Puja;

public interface ISubastaService {

	public boolean actualizarAnunciante(String nombre, String documento, int edad, ArrayList<Anuncio> listaAnuncios);
	public Boolean eliminarAnunciante(String documento);
	Anunciante crearAnunciante(String nombre, String documento, int edad, ArrayList<Anuncio> listaAnuncios);
	Anunciante obtenerAnunciante(String documento);
	ArrayList<Anunciante> obteneAnunciantes();


	public boolean actualizarComprador(String nombre, String documento,int edad, int numOfertas, ArrayList<Puja> listaPujas);
	public Boolean eliminarComprador(String documento);
	Anunciante crearCcomprador(String nombre, String documento, int edad, int numOfertas, ArrayList<Puja> listaPujas);
	Anunciante obtenerComprador(String documento);
	ArrayList<Compradores> obtenerCompradores();
}
