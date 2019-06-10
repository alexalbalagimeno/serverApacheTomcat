package server.constants;

/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 * Guarda les sentències SQL utilitzades per administrar la base de dades
 */
public class SentenciesSQL {
	
	//SQL crear de taules
	public final static String SQL_CREAR_TAULA_PLACES =
			"CREATE TABLE IF NOT EXISTS PLACES("
			+ "id_placa INTEGER,"
			+ "numero_fila INTEGER,"
			+ "numero_columna INTEGER,"
			+ "planta INTEGER,"
			+ "edifici INTEGER,"
			+ "coordenada_x DOUBLE(10, 2),"
			+ "coordenada_y DOUBLE(10, 2),"
			+ "coordenada_z DOUBLE(10, 2),"
			+ "PRIMARY KEY(id_placa)"
			+ ")";
	
	public final static String SQL_CREAR_TAULA_NODESMCU =
			"CREATE TABLE IF NOT EXISTS NODESMCU("
			+ "mac_nodeMCU VARCHAR(30),"
			+ "coordenada_x DOUBLE(10, 2),"
			+ "coordenada_y DOUBLE(10, 2),"
			+ "coordenada_z DOUBLE(10, 2),"
			+ "data_ultimPosicionament DATE,"
			+ "PRIMARY KEY(mac_nodeMCU)"
			+ ")";
	
	public final static String SQL_CREAR_TAULA_VEHICLES =
			"CREATE TABLE IF NOT EXISTS VEHICLES("
			+ "numero_bastidor VARCHAR(30),"
			+ "mac_node_emparellat VARCHAR(30),"
			+ "color VARCHAR(30),"
			+ "model VARCHAR(100),"
			+ "marca VARCHAR(30),"
			+ "caracteristiques VARCHAR(200),"
			+ "data_entrada DATE,"
			+ "data_sortida DATE,"
			+ "PRIMARY KEY (numero_bastidor),"
			+ "FOREIGN KEY(mac_node_emparellat) REFERENCES NODESMCU(mac_nodeMCU)"
			+ ")";
	
	public final static String SQL_CREAR_TAULA_PUNTSACCES =
			"CREATE TABLE IF NOT EXISTS PUNTACCES("
			+ "mac_puntacces VARCHAR(30),"
			+ "ssid_puntacces VARCHAR(30),"
			+ "coordenada_x DOUBLE(10, 2),"
			+ "coordenada_y DOUBLE(10, 2),"
			+ "coordenada_z DOUBLE(10, 2),"
			+ "PRIMARY KEY(mac_puntacces)"
			+ ")";
	
	//SQL borrar taules
	public final static String SQL_BORRAR_TAULA_PUNTSACCES = "DROP TABLE IF EXISTS PUNTACCES";
	public final static String SQL_BORRAR_TAULA_VEHICLES = "DROP TABLE IF EXISTS VEHICLES";
	public final static String SQL_BORRAR_TAULA_NODESMCU = "DROP TABLE IF EXISTS NODESMCU";
	public final static String SQL_BORRAR_TAULA_PLACES = "DROP TABLE IF EXISTS PLACES";
	
	//PUNTACCES
	public static final String SQL_LLISTAR_TOTS_PUNTSACCES = "SELECT * FROM PUNTACCES";
//	public static final String BORRAR_ANTENA_AMB_MAC = "DELETE FROM PUNTACCES WHERE mac_antena = ?";
//	public static final String INSERIR_ANTENA = "INSERT INTO PUNTACCES VALUES(?, ?, ?, ?)";
//	public static final String CONTAR_ANTENES_AMB_AQUELLA_MAC = "SELECT COUNT(1) FROM PUNTACCES WHERE mac_antena = ?";
	
	//NODEMCU
	//provisional
	public static final String SQL_ACTUALITZARPOSICIO_NODEMCU = "UPDATE NODESMCU SET coordenada_x = ?, coordenada_y = ?, coordenada_z = ? WHERE mac_nodeMCU = ?";
	
	//PLACES
	
	//VEHICLES
	public static final String SQL_CONTAR_VEHICLES_AMB_NUMEROBASTIDOR = "SELECT COUNT(1) FROM VEHICLES WHERE numero_bastidor = ?";
	public static final String SQL_LOCALITZAR_VEHICLE = "SELECT * FROM VEHICLES V JOIN NODESMCU N ON V.mac_node_emparellat = N.mac_nodeMCU WHERE numero_bastidor = ?";
	public static final String SQL_ELIMINAR_VEHICLE = "DELETE FROM VEHICLES WHERE numero_bastidor = ?";
	//provisional
	public static final String SQL_EMPARELLAR_VEHICLE_AMB_NODEMCU = "UPDATE VEHICLES SET mac_node_emparellat = ? WHERE numero_bastidor = ?";
	public static final String SQL_SABER_SI_EL_VEHICLE_TE_NODEMCU_EMPARELLAT = "SELECT mac_node_emparellat FROM VEHICLES WHERE numero_bastidor = ?";
	public static final String SQL_ACTUALITZAR_DATA_ENTRADA_VEHICLE = "UPDATE VEHICLES SET data_entrada = CURRENT_DATE() WHERE numero_bastidor = ?";
	public static final String SQL_CONSULTA_PLACA_SABENT_POSICIO = "SELECT * FROM PLACES WHERE ABS( ? -coordenada_x)<=0.5 AND ABS( ? -coordenada_y)<=0.5";
}
