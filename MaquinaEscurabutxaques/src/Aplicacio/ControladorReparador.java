package Aplicacio;

import java.util.LinkedList;

import Domini.Maquina;
import Persistencia.*;

public class ControladorReparador {

	private MaquinaBBDD maquinaBBDD;
	private TecnicBBDD tecnicBBDD;
	private PlacaBBDD placaBBDD;
	private CarcassaBBDD carcassaBBDD;

	//El cas d'ús reparar màquina ho ha estat implementat, aquest mètode de moment no es fa servir en el nostre programa.

	// Mètode que retorna maquines que estan per reperar
	public LinkedList<Integer> maquinesPerReparar() throws Exception {
		try {
			return maquinaBBDD.obtenirMaquines("ESPATLLADA");
		} catch (Exception e) {
			throw new Exception("Error maquinesPerReparar - " + e.getMessage());
		}
	}
}
