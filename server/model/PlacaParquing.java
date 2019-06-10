package server.model;

/**
 * @author Delta Tracking (Javier Martin, �lex Albal� i Carles Orriols)
 *
 */

public class PlacaParquing implements Comparable<PlacaParquing> {

	int volumParquing;
	int plantaParquing;
	int fila;
	int columna;

	/**
	 * Constructor buit
	 */
	public PlacaParquing() {

	}

	/**
	 * Constructor de la placa de p�rquing utilitzant volum de p�rquing, quantes plantes t�, files i columnes 
	 * @param volumParquing
	 * @param plantaParquing
	 * @param fila
	 * @param columna
	 */
	public PlacaParquing(int volumParquing, int plantaParquing, int fila, int columna) {
		this.volumParquing = volumParquing;
		this.plantaParquing = plantaParquing;
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * Retorna el volum del p�rquing
	 * @return  volumParquing
	 */
	public int getVolumParquing() {
		return volumParquing;
	}

	/** Actualitza el volum del p�rquing
	 * @param volumParquing
	 */
	public void setVolumParquing(int volumParquing) {
		this.volumParquing = volumParquing;
	}

	/** Retorna el n�mero de plantes que t� el p�rquing
	 * @return  plantaParquing
	 */
	public int getPlantaParquing() {
		return plantaParquing;
	}

	/** Actualitza el n�mero de plantes
	 * @param plantaParquing 
	 */
	public void setPlantaParquing(int plantaParquing) {
		this.plantaParquing = plantaParquing;
	}

	/**	 Retorna el n�mero de files
	 * @return  filaPlanta
	 */
	public int getFila() {
		return fila;
	}

	/**	Actualitza el n�mero de files
	 * @param filaPlanta
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**	 Retorna el n�mero de columnes
	 * @return  columnaPlanta
	 */
	public int getColumna() {
		return columna;
	}

	/**	Actualitza el n�mero de columnes
	 * @param columnaPlanta 
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}

	/**
	 * M�tode d'ordenaci� de les places de p�rquing
	 */
	public int compareTo(PlacaParquing placaParquing) {
		if (this.volumParquing == placaParquing.volumParquing) {
			if (this.plantaParquing == placaParquing.plantaParquing) {
				if (this.fila == placaParquing.fila) {
					if (this.columna == placaParquing.columna) {
						return 0;
					} else {
						return this.columna - placaParquing.columna;
					}
				} else {
					return this.fila - placaParquing.fila;
				}
			} else {
				return this.plantaParquing - placaParquing.plantaParquing;
			}
		} else {
			return this.volumParquing - placaParquing.volumParquing;
		}
	}

	/**
	 * Retorna els valors de la placa de p�rquing
	 */
	@Override
	public String toString() {
		return "PlacaParquing [volumParquing=" + volumParquing + ", plantaParquing=" + plantaParquing + ", fila=" + fila
				+ ", columna=" + columna + "]";
	}

}
