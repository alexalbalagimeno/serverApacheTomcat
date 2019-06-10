package server.model;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class SenyalPuntAcces implements Comparable<SenyalPuntAcces> {
	String mac;
	String ssid;
	int rssi;

	/**
	 * Constructor per paràmetres MAC, nom de la senyal i intensitat d'aquesta
	 * @param mac
	 * @param ssid
	 * @param rssi
	 */
	public SenyalPuntAcces(String mac, String ssid, int rssi) {
		this.mac = mac;
		this.ssid = ssid;
		this.rssi = rssi;
	}
	
	/**
	 * Constructor buit
	 */
	public SenyalPuntAcces() {
		// TODO Auto-generated constructor stub
	}

	/**	Retorna la MAC
	 * @return MAC
	 */
	public String getMac() {
		return mac;
	}
	/** Actualitza la MAC
	 * @param MAC
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**	Retorna el nom de la senyal
	 * @return ssid
	 */
	public String getSsid() {
		return ssid;
	}
	/** Actualitza el nom de la senyal
	 * @param ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	/** Retorna la intensitat de la senyal
	 * @return rssi
	 */
	public int getRssi() {
		return rssi;
	}
	/** Actualització de la senyal 
	 * @param rssi
	 */
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	/** Métode per mostrar els atributs de la senyal
	 */
	@Override
	public String toString() {
		return "SenyalPuntAcces [mac=" + mac + ", ssid=" + ssid + ", rssi=" + rssi + "]";
	}

	/** 
	 * Métode d'ordenació de menor a major utilitzant la intensitat
	 */
	@Override
	public int compareTo(SenyalPuntAcces spa) {
		if (this.rssi < spa.rssi) {
            return 1;
        }
        if (this.rssi > spa.rssi) {
            return -1;
        }
        return 0;		
	}	
	

}
