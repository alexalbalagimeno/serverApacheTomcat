package server.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import server.constants.SentenciesSQL;
import server.model.PlacaParquing;
import server.model.Punt;
import server.model.PuntAcces;
import server.model.Vehicle;
/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *	Classe per administrar les peticions de la App amb la base de dades
 */
public class PhpMyAdminManager {
	
	
	// Canviar aqui si el separador canvia
	public static final String SEPARADOR_INFORMACIO = "#";

	// Constants peticions antenes
	public static final int PETICIO_LLISTA_PUNTSACCES = 101;
	// public static final int PETICIO_ON_ES_LA_ANTNEA = 102;
	// public static final int PETICIO_AFEGIR_ANTENA = 103;
	// public static final int PETICIO_BORRAR_ANTENA = 104;

	// Constants peticions nodeMCU
	public static final int PETICIO_ACTUALITZARPOSICIO_NODEMCU = 201;
	// public static final int PETICIO_ON_ES_EL_NODEMCU = 202;
	// public static final int PETICIO_LLISTA_NODESMCU = 203;
	// public static final int PETICIO_AFEGIR_NODEMCU = 204;
	// public static final int PETICIO_BORRAR_NODEMCU = 205;

	// Constants peticions places
	// public static final int PETICIO_PLACA_LLIURE = 301;
	// public static final int PETICIO_PLACES_LLIURES = 302;
	// public static final int PETICIO_PLACES_OCUPADES = 303;
	// public static final int PETICIO_LLISTA_PLACES = 304;

	// Constants peticions vehicles
	public static final int PETICIO_ON_ES_EL_VEHICLE = 401;
	public static final int PETICIO_EMPARELLAR_VEHICLE = 402;
	public static final int PETICIO_BAIXA_VEHICLE = 403;
	// public static final int PETICIO_LLISTA_VEHICLES = 404;

	// Consultar M06 UF2 PT22
	// Statement(determina la base de dades a la que estem connectats)
	Connection conn = null;
	
	/**
	 * Constructor de la classe d'administració de la base de dades que utilitza la url de conexió
	 * el usuari i la contrasenya
	 * @param URL
	 * @param user
	 * @param password
	 * @throws ClassNotFoundException
	 */
	public PhpMyAdminManager(String URL, String user, String password) throws ClassNotFoundException {

		// No es necessari del tot
		Class.forName("com.mysql.jdbc.Driver");
		
		//Fa la conexió
		try {
			conn = DriverManager.getConnection(URL, user, password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Métode principal que administra les peticions a la base de dades i respon a la App
	 * 
	 * @param opcio 
	 * @param informacio o dades necessaries per completar la consulta
	 * @return
	 */
	public String administra(int opcio, String informacio) {

		String[] infoSeparada;
		String macNodeMCU, numBastidor;
		Double posX, posY, posZ;
		switch (opcio) {
		case PETICIO_ACTUALITZARPOSICIO_NODEMCU:
			infoSeparada = informacio.split(SEPARADOR_INFORMACIO);

			macNodeMCU = infoSeparada[0];
			posX = Double.parseDouble(infoSeparada[1]);
			posY = Double.parseDouble(infoSeparada[2]);
			posZ = Double.parseDouble(infoSeparada[3]);

			return "" + actualitzarPosicioNodeMCU(macNodeMCU, posX, posY, posZ);

		case PETICIO_ON_ES_EL_VEHICLE:
			System.out.println(informacio);
			return vehicleAXML(0, localitzarVehicle(informacio));

		case PETICIO_EMPARELLAR_VEHICLE:
			infoSeparada = informacio.split(SEPARADOR_INFORMACIO);
			System.out.println(informacio);
			numBastidor = infoSeparada[0];
			macNodeMCU = infoSeparada[1];
			System.out.println(numBastidor+"  "+macNodeMCU);
			return "" + emparellarVehicleAmbNodeMCU(numBastidor, macNodeMCU);

		case PETICIO_BAIXA_VEHICLE:
			return "" + eliminarVehicle(informacio);

		default:
			return null;
		}
	}

	//
	//
	//
	//
	// BASE DE DADES
	/**	Reseteja la base de dades
	 * @return true si funciona, false si no
	 */
	public boolean resetBaseDeDades() {
		boolean aux = borrarTaules();
		if (aux == false) {
			return aux;
		}
		aux = creacioTaules();
		if (aux == false) {
			return aux;
		}
		return aux;
	}

	/** Borra totes les taules de la base de dades
	 * @return true si funciona, false si no
	 */
	public boolean borrarTaules() {
		try {
			Statement estatement = conn.createStatement();
			estatement.executeUpdate(SentenciesSQL.SQL_BORRAR_TAULA_PUNTSACCES);
			estatement.executeUpdate(SentenciesSQL.SQL_BORRAR_TAULA_VEHICLES);
			estatement.executeUpdate(SentenciesSQL.SQL_BORRAR_TAULA_NODESMCU);
			estatement.executeUpdate(SentenciesSQL.SQL_BORRAR_TAULA_PLACES);
			estatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/** Crea les taules a la base de dades
	 * @return true si funciona, false si no
	 */
	public boolean creacioTaules() {
		try {
			Statement estatement = conn.createStatement();
			estatement.executeUpdate(SentenciesSQL.SQL_CREAR_TAULA_PLACES);
			estatement.executeUpdate(SentenciesSQL.SQL_CREAR_TAULA_NODESMCU);
			estatement.executeUpdate(SentenciesSQL.SQL_CREAR_TAULA_VEHICLES);
			estatement.executeUpdate(SentenciesSQL.SQL_CREAR_TAULA_PUNTSACCES);
			estatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//
	//
	//
	//
	// PUNTS ACCES
	/** Llista els routers guardats a la base de dades
	 * @return true si funciona, false si no
	 */
	public ArrayList<PuntAcces> llistarPuntsAcces() {
		ArrayList<PuntAcces> puntsAcces = new ArrayList<PuntAcces>();
		try {
			Statement estatement = conn.createStatement();
			ResultSet resultset = estatement.executeQuery(SentenciesSQL.SQL_LLISTAR_TOTS_PUNTSACCES);
			while (resultset.next()) {
				PuntAcces puntAcces = new PuntAcces();
				puntAcces.setMac(resultset.getString("mac_puntacces"));
				puntAcces.setPos(new Punt(resultset.getDouble("coordenada_x"), resultset.getDouble("coordenada_y"),
						resultset.getDouble("coordenada_z")));
				puntsAcces.add(puntAcces);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return puntsAcces;
	}

	//
	//
	//
	//
	// VEHICLES
	
	/**	Retorna les dades d'un vehicle en concret de la base de dades
	 * @param Número bastidor del vehicle a buscar
	 * @return Vehicle
	 */
	public Vehicle localitzarVehicle(String numeroBastidor) {
		String sql = SentenciesSQL.SQL_LOCALITZAR_VEHICLE;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, numeroBastidor);
			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();

			Calendar calendarEnt = new GregorianCalendar();
			calendarEnt.setTime(resultset.getDate("data_entrada"));
			Calendar calendarSor = new GregorianCalendar();
			
			if(resultset.getDate("data_sortida") == null) {
				System.out.println(resultset.getDate("data_sortida"));
				//System.out.println(calendarSor);
			}else {
				calendarSor.setTime(resultset.getDate("data_sortida"));
			}
			// Si no hi ha cap entrada amb aquesta mac, retorna false
			Vehicle vehicle1 = new Vehicle(numeroBastidor, resultset.getString("marca"), resultset.getString("model"),
					resultset.getString("color"), resultset.getString("caracteristiques"),
					new Punt(resultset.getDouble("coordenada_x"), resultset.getDouble("coordenada_y"),
							resultset.getDouble("coordenada_z")),
					calendarEnt, calendarSor);
			return vehicle1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	/** Elimina un vehicle de la base de dades
	 * @param numeroBastidor del vehicle a borrar
	 * @return true si funciona, false si no
	 */
	public Boolean eliminarVehicle(String numeroBastidor) {
		String sql = SentenciesSQL.SQL_CONTAR_VEHICLES_AMB_NUMEROBASTIDOR;
		PreparedStatement preparedStatement;
		// comprovar si hi ha algu vehcile amb aquell nom per poder retornar false o
		// true

		sql = SentenciesSQL.SQL_ELIMINAR_VEHICLE;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, numeroBastidor);
			preparedStatement.executeUpdate();

			// Si no hi ha cap entrada amb aquesta mac, retorna false
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	/** Emparella el vehicle amb el nodeMCU en la base de dades
	 * @param numeroBastidor
	 * @param macNodeMCU
	 * @return true si funciona, false si no
	 */
	public Boolean emparellarVehicleAmbNodeMCU(String numeroBastidor, String macNodeMCU) {
		// si la mac fins ara del vehicle era null, actualitzar data entrada a la base
		// de dades, sino deixarho igual
		String sql = SentenciesSQL.SQL_EMPARELLAR_VEHICLE_AMB_NODEMCU;

		PreparedStatement preparedStatement;
		// comprovar si hi ha algu vehcile amb aquell num i node amb mac per poder
		// retornar false o true

		String strMacNodeMCUAntiga = macNodeMCUEnUnVehicle(numeroBastidor);

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, macNodeMCU);
			preparedStatement.setString(2, numeroBastidor);
			preparedStatement.executeUpdate();

			if (strMacNodeMCUAntiga == null) {
				actualitzaDataEntradaVehicle(numeroBastidor);
			}

			// Si no hi ha cap entrada amb aquesta mac, retorna false
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}

	}

	/** Actualitza la data d'entrada del vehicle a la base de dades
	 * @param numeroBastidor
	 * @return true si funciona, false si no
	 */
	public Boolean actualitzaDataEntradaVehicle(String numeroBastidor) {
		String sql = SentenciesSQL.SQL_ACTUALITZAR_DATA_ENTRADA_VEHICLE;

		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, numeroBastidor);
			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	/** Comprova si el nodeMCU està emparellat amb cap vehicle
	 * @param numeroBastidor
	 * @return true si funciona, false si no
	 */
	public String macNodeMCUEnUnVehicle(String numeroBastidor) {
		String sql = SentenciesSQL.SQL_SABER_SI_EL_VEHICLE_TE_NODEMCU_EMPARELLAT;
		PreparedStatement preparedStatement;
		// comprovar si hi ha algu vehcile amb aquell num i node amb mac per poder
		// retornar false o true
		String strResult="";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, numeroBastidor);

			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				strResult = resultset.getString("mac_node_emparellat");
			}		
			System.out.println("");
			return strResult;

		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	//
	//
	//
	//
	// NODE_MCU
	/** Actualitza la posicio del nodeMCU a la base de dades
	 * @param macNodeMCU
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @return true si funciona, false si no
	 */
	public Boolean actualitzarPosicioNodeMCU(String macNodeMCU, double posX, double posY, double posZ) {
		String sql = SentenciesSQL.SQL_ACTUALITZARPOSICIO_NODEMCU;

		PreparedStatement preparedStatement;

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDouble(1, posX);
			preparedStatement.setDouble(2, posY);
			preparedStatement.setDouble(3, posZ);
			preparedStatement.setString(4, macNodeMCU);
			preparedStatement.executeUpdate();

			// Si no hi ha cap entrada amb aquesta mac, retorna false
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	//
	//
	//
	//
	// PLACES
	/** Retorna la placa de pàrquing que està aprop d'aquest punt
	 * @param Posicio nova
	 * @return Placa de pàrquing
	 */
	public PlacaParquing placaAPartirDePosicio(Punt pos) {

		String sql = SentenciesSQL.SQL_CONSULTA_PLACA_SABENT_POSICIO;
		PreparedStatement preparedStatement;
		// comprovar si hi ha algu vehcile amb aquell num i node amb mac per poder
		// retornar false o true

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDouble(1, pos.getX());
			preparedStatement.setDouble(2, pos.getY());

			ResultSet resultset = preparedStatement.executeQuery();
			resultset.next();

			int fila = resultset.getInt("numero_fila");
			int columna = resultset.getInt("numero_columna");
			int planta = resultset.getInt("planta");
			int edifici = resultset.getInt("edifici");
			PlacaParquing placa = new PlacaParquing(edifici, planta, fila, columna);

			return placa;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	//
	//
	//
	//
	// FORMATEJAR ABANS D'ENVIAR (XML)
	
	/** Transforma un array de senyals en un text XML
	 * @param numTabs
	 * @param puntsAcces
	 * @return Text en xml
	 */
	public String arrayPuntAccesAXML(int numTabs, ArrayList<PuntAcces> puntsAcces) {
		String stringXML = afegirTabuladors(numTabs) + "<punts_acces>\n";
		for (int i = 0; i < puntsAcces.size(); i++) {
			stringXML += puntAccesAXML(numTabs + 1, puntsAcces.get(i));
		}
		stringXML += afegirTabuladors(numTabs) + "</punts_acces>\n";
		return stringXML;
	}

	/** Transforma un punt d'accés en un text XML
	 * @param numTabs
	 * @param puntAcces
	 * @return text en XML
	 */
	public String puntAccesAXML(int numTabs, PuntAcces puntAcces) {
		String stringXML = afegirTabuladors(numTabs) + "<punt_acces>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<nom>" + puntAcces.getSsid() + "</nom>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<macPunt_acces>" + puntAcces.getMac() + "</macPunt_acces>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<posicio>\n";
		stringXML += posicioAXML(numTabs + 2, puntAcces.getPos());
		stringXML += afegirTabuladors(numTabs) + "\t</posicio>\n";
		stringXML += afegirTabuladors(numTabs) + "</punt_acces>\n";

		return stringXML;
	}

	/** Transforma un punt en un text XML
	 * @param numTabs
	 * @param posicio
	 * @return text en XML
	 */
	public String posicioAXML(int numTabs, Punt posicio) {
		String stringXML = afegirTabuladors(numTabs) + "<posX>" + posicio.getX() + "</posX>\n";
		stringXML += afegirTabuladors(numTabs) + "<posY>" + posicio.getY() + "</posY>\n";
		stringXML += afegirTabuladors(numTabs) + "<posZ>" + posicio.getZ() + "</posZ>\n";
		return stringXML;
	}

	/** Transforma un vehicle en un text XML
	 * @param numTabs
	 * @param veh
	 * @return text en XML
	 */
	public String vehicleAXML(int numTabs, Vehicle veh) {
		PlacaParquing pla = this.placaAPartirDePosicio(veh.getPosicio());
		String stringXML = afegirTabuladors(numTabs) + "<vehicle>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<bastidor>" + veh.getCodiBastidor() + "</bastidor>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<color>" + veh.getColor() + "</color>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<model>" + veh.getModel() + "</model>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<marca>" + veh.getMarca() + "</marca>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<caract>" + veh.getCaracteristiques() + "</caract>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<placa>\n";
		stringXML += placaAXML(numTabs + 2, pla);
		stringXML += afegirTabuladors(numTabs) + "\t</placa>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<posicio>\n";
		stringXML += posicioAXML(numTabs + 2, veh.getPosicio());
		stringXML += afegirTabuladors(numTabs) + "\t</posicio>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<planta>" + "0" + "</planta>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<edifici>" + "0" + "</edifici>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<dataentrada>" + calendarAString(veh.getDiaArribada())
				+ "</dataentrada>\n";
		stringXML += afegirTabuladors(numTabs) + "\t<datasortida>" + calendarAString(veh.getDiaProgramatSortida())
				+ "</datasortida>\n";
		stringXML += afegirTabuladors(numTabs) + "</vehicle>\n";
		
		
		return stringXML;

	}
	
//	public String vehicleAXML(int numTabs, Vehicle veh) {
//		PlacaParquing pla = this.placaAPartirDePosicio(veh.getPosicio());
//		String stringXML = afegirTabuladors(numTabs) + "<vehicle>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<bastidor>" + veh.getCodiBastidor() + "</bastidor>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<color>" + veh.getColor() + "</color>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<model>" + veh.getModel() + "</model>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<marca>" + veh.getMarca() + "</marca>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<caract>" + veh.getCaracteristiques() + "</caract>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<placa>\n";
//		stringXML += placaAXML(numTabs + 2, pla);
//		stringXML += afegirTabuladors(numTabs) + "\t</placa>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<posicio>\n";
//		stringXML += posicioAXML(numTabs + 2, veh.getPosicio());
//		stringXML += afegirTabuladors(numTabs) + "\t</posicio>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<planta>" + "0" + "</planta>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<edifici>" + "0" + "</edifici>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<dataentrada>" + calendarAString(veh.getDiaArribada())
//				+ "</dataentrada>\n";
//		stringXML += afegirTabuladors(numTabs) + "\t<datasortida>" + calendarAString(veh.getDiaProgramatSortida())
//				+ "</datasortida>\n";
//		stringXML += afegirTabuladors(numTabs) + "</vehicle>\n";
//		
//		
//		return stringXML;
//
//	}

	@SuppressWarnings("deprecation")
	public String calendarAString(Calendar cal) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(cal.getTime().getHours());
		stringBuilder.append("h ");
		stringBuilder.append(cal.getTime().getMinutes());
		stringBuilder.append("m ");
		stringBuilder.append(cal.getTime().getSeconds());
		stringBuilder.append("s ");
		String strCalendar = stringBuilder.toString();
		strCalendar += " " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH)+1) + "/"
				+ cal.get(Calendar.YEAR);

		return strCalendar;
	}

	/** Transforma una placa de pàrquing en un text XML
	 * @param numTabs
	 * @param placa
	 * @return text en XML
	 */
	public String placaAXML(int numTabs, PlacaParquing placa) {
		String stringXML = afegirTabuladors(numTabs) + "<fila>" + placa.getFila() + "</fila>\n";
		stringXML += afegirTabuladors(numTabs) + "<columna>" + placa.getColumna() + "</columna>\n";
		return stringXML;
	}

	/** Afegeix tabulats perquè sigui llegible
	 * @param numTabuladors
	 * @return text tabulat
	 */
	public String afegirTabuladors(int numTabuladors) {
		String stringTabs = "";
		for (int i = 0; i < numTabuladors; i++) {
			stringTabs += "\t";
		}
		return stringTabs;
	}

	// public boolean afegirNodeMCU(NodeMCU nodeMCUIntroduir) {
	//
	// // SQL insert antena
	// String sql = "INSERT INTO ANTENES VALUES('" +
	// nodeMCUIntroduir.getMacAntena() + "', "
	// + nodeMCUIntroduir.getPosicio().getPosX() + ", " +
	// nodeMCUIntroduir.getPosicio().getPosY() + ", "
	// + nodeMCUIntroduir.getPosicio().getPosZ() + ")";
	// try {
	// estatement.executeUpdate(sql);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	// }

	// public ArrayList<Antena> llistarNodeMCU() {
	// ArrayList<Antena> antenes = new ArrayList<Antena>();
	// try {
	// ResultSet resultset =
	// estatement.executeQuery(SentenciesSQL.MOSTRAR_TOTES_LES_ANTENES);
	// while (resultset.next()) {
	// Antena ant = new Antena(resultset.getString("mac_antena"));
	// ant.setPosicio(new Posicio(resultset.getDouble("coordenada_x"),
	// resultset.getDouble("coordenada_y"),
	// resultset.getDouble("coordenada_z")));
	// antenes.add(ant);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return antenes;
	// }

}
