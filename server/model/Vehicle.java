package server.model;

import java.util.Calendar;
/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 *
 */
public class Vehicle {
	String codiBastidor;
	String marca;
	String model;
	String color;
	String caracteristiques;
	Punt posicio;
	Calendar diaArribada;
	Calendar diaProgramatSortida;

	/**
	 * Constructor buit 
	 */
	public Vehicle() {

	}

	/** Constructor utilitzant paràmetres
	 * @param codiBastidor
	 * @param marca
	 * @param model
	 * @param color
	 * @param caracteristiques
	 * @param posicio
	 * @param diaArribada
	 * @param diaProgramatSortida
	 */
	public Vehicle(String codiBastidor, String marca, String model, String color, String caracteristiques, Punt posicio,
			Calendar diaArribada, Calendar diaProgramatSortida) {
		super();
		this.codiBastidor = codiBastidor;
		this.marca = marca;
		this.model = model;
		this.color = color;
		this.caracteristiques = caracteristiques;
		this.posicio = posicio;
		this.diaArribada = diaArribada;
		this.diaProgramatSortida = diaProgramatSortida;
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

	/** Retorna el codi de bastidor
	 * @return  codiBastidor
	 */
	public String getCodiBastidor() {
		return codiBastidor;
	}

	/** Actualitza el codi de bastidor
	 * @param codiBastidor
	 */
	public void setCodiBastidor(String codiBastidor) {
		this.codiBastidor = codiBastidor;
	}

	/** Retorna la marca
	 * @return  marca
	 */
	public String getMarca() {
		return marca;
	}

	/** Actualitza la marca
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**	Retorna el model
	 * @return  model
	 */
	public String getModel() {
		return model;
	}

	/** Actualitza el model
	 * @param model 
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/** Retorna el color
	 * @return  color
	 */
	public String getColor() {
		return color;
	}

	/**	Actualitza el color
	 * @param color 
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/** Retorna les característiques
	 * @return  caracteristiques
	 */
	public String getCaracteristiques() {
		return caracteristiques;
	}

	/** Actualitza les característiques
	 * @param caracteristiques 
	 */
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	/**	Retorna el dia d'arribada
	 * @return  diaArribada
	 */
	public Calendar getDiaArribada() {
		return diaArribada;
	}

	/** Actualitza el dia d'arribada
	 * @param diaArribada 
	 */
	public void setDiaArribada(Calendar diaArribada) {
		this.diaArribada = diaArribada;
	}

	/** Retorna el dia de sortida
	 * @return the diaProgramatSortida
	 */
	public Calendar getDiaProgramatSortida() {
		return diaProgramatSortida;
	}

	/** Actualitza el dia de sortida
	 * @param diaProgramatSortida the diaProgramatSortida to set
	 */
	public void setDiaProgramatSortida(Calendar diaProgramatSortida) {
		this.diaProgramatSortida = diaProgramatSortida;
	}
	
	

	/**
	 * Retorna els atributs del vehicle
	 */
	@Override
	public String toString() {
		return "Vehicle [codiBastidor=" + codiBastidor + ", marca=" + marca + ", model=" + model + ", color=" + color
				+ ", caracteristiques=" + caracteristiques + ", posicio=" + posicio + ", diaArribada=" + diaArribada
				+ ", diaProgramatSortida=" + diaProgramatSortida + "]";
	}
	
	

}
