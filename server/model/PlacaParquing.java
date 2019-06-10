package server.model;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
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
	 * Constructor de la placa de pàrquing utilitzant volum de pàrquing, quantes plantes té, files i columnes 
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
	 * Retorna el volum del pàrquing
	 * @return  volumParquing
	 */
	public int getVolumParquing() {
		return volumParquing;
	}

	/** Actualitza el volum del pàrquing
	 * @param volumParquing
	 */
	public void setVolumParquing(int volumParquing) {
		this.volumParquing = volumParquing;
	}

	/** Retorna el número de plantes que té el pàrquing
	 * @return  plantaParquing
	 */
	public int getPlantaParquing() {
		return plantaParquing;
	}

	/** Actualitza el número de plantes
	 * @param plantaParquing 
	 */
	public void setPlantaParquing(int plantaParquing) {
		this.plantaParquing = plantaParquing;
	}

	/**	 Retorna el número de files
	 * @return  filaPlanta
	 */
	public int getFila() {
		return fila;
	}

	/**	Actualitza el número de files
	 * @param filaPlanta
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**	 Retorna el número de columnes
	 * @return  columnaPlanta
	 */
	public int getColumna() {
		return columna;
	}

	/**	Actualitza el número de columnes
	 * @param columnaPlanta 
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}

	/**
	 * Métode d'ordenació de les places de pàrquing
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
	 * Retorna els valors de la placa de pàrquing
	 */
	@Override
	public String toString() {
		return "PlacaParquing [volumParquing=" + volumParquing + ", plantaParquing=" + plantaParquing + ", fila=" + fila
				+ ", columna=" + columna + "]";
	}

}
