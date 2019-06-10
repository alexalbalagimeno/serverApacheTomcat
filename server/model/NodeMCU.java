package server.model;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class NodeMCU {
	String macNodeMCU;
	Punt posicio;

	
	/** Constructor amb paràmetre
	 * @param MAC
	 */
	public NodeMCU(String macNodeMCU) {
		this.macNodeMCU = macNodeMCU;
	}

	/**	Retorna la MAC 
	 * @return  MAC
	 */
	public String getMacNodeMCU() {
		return macNodeMCU;
	}

	/** Actualització de la MAC
	 * @param MAC 
	 */
	public void setMacNodeMCU(String macNodeMCU) {
		this.macNodeMCU = macNodeMCU;
	}

	/** Retorna la posició 
	 * @return  posicio
	 */
	public Punt getPosicio() {
		return posicio;
	}

	/** Actualitza la posició
	 * @param posicio 
	 */
	public void setPosicio(Punt posicio) {
		this.posicio = posicio;
	}

	/**
	 * Retorna els valors del nodeMCU
	 */
	@Override
	public String toString() {
		return "NodeMCU [macNodeMCU=" + macNodeMCU + ", posicio=" + posicio + "]";
	}
	
	

}