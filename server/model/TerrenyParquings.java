package server.model;

import java.util.ArrayList;
/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class TerrenyParquings {

	private ArrayList<PuntAcces> antenes;
	private ArrayList<PlacaParquing> aparcaments;

	/** Constructor amb paràmetres
	 * @param antenes
	 * @param aparcaments
	 */
	public TerrenyParquings(ArrayList<PuntAcces> antenes, ArrayList<PlacaParquing> aparcaments) {
		this.antenes = antenes;
		this.aparcaments = aparcaments;
	}
	
	
	/**
	 * Constructor buit
	 */
	public TerrenyParquings() {
		this.antenes = new ArrayList<PuntAcces>();
		this.aparcaments = new ArrayList<PlacaParquing>();
	}

	
	/** 
	 * Llistar el número de routers que hi han al terreny
	 * @return antenes
	 */
	public ArrayList<PuntAcces> llistatAntenes() {
		return antenes;
	}


	
	/**
	 * Busca una placa de pàrquing al terreny 
	 * @param placaParquing
	 * @return true or false
	 */
	public boolean buscaPlacaParquing(PlacaParquing placaParquing) {
		int centro, inf = 0, sup = this.aparcaments.size() - 1;
		if (this.aparcaments.size() == 0) {
			return false;
		}
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			if (this.aparcaments.get(centro).compareTo(placaParquing) == 0)
				return true;
			else if (this.aparcaments.get(centro).compareTo(placaParquing) > 0) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
		return false;

	}

	

}
