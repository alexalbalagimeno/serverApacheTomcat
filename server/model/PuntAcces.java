package server.model;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class PuntAcces {
	String ssid;
	Punt pos;
	String mac;
	
	/** Constructor del punt d'accés utilitzant MAC, SSID i punt on es troba físicament
	 * @param mac
	 * @param ssid
	 * @param pos
	 */
	public PuntAcces(String mac,String ssid, Punt pos) {
		this.pos = pos;
		this.ssid = ssid;
		this.mac=mac;
	}
	
	/** 
	 * Constructor del punt d'accés buit
	 */
	public PuntAcces() {
		// TODO Auto-generated constructor stub
	}
	
	/** Retorna la MAC
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}

	/** Actualitza la MAC
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**	Retorna el nom del punt d'accés
	 * @return ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/** Actualitza el nom del punt d'accés 
	 * @param ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	/**	Retorna la posició d'on està el punt d'accés 
	 * @return posicio
	 */
	public Punt getPos() {
		return pos;
	}

	/** Actualitza la posició del punt d'accés.
	 * @param pos
	 */
	public void setPos(Punt pos) {
		this.pos = pos;
	}
	
	/**
	 * Retorna els valors del punt d'accés
	 */
	@Override
	public String toString() {
		return "PuntAcces [ssid=" + ssid + ", pos=" + pos + ", mac=" + mac + "]";
	}

	

}
