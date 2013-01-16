package org.geonotes.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * @author Amine 
 * 
 * The persistent class for the Utilisateur
 * 
 */
@Entity
@DiscriminatorValue("Utilisateur")
public class Utilisateur extends Users implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8625207776576782100L;

	private double latitude;

	private double longitude;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="parcours_utilisateurs"
				, joinColumns={
					@JoinColumn(name="id_utilisateur")
			}
			, inverseJoinColumns={
					@JoinColumn(name="id_parcours")
			}
	)
	private Set<Parcour> parcours;

	public Utilisateur() {
	}



	/**
	 * @param email
	 * @param nom
	 * @param password
	 * @param prenom
	 */
	public Utilisateur(String email, String nom, String password, String prenom) {
		super(email, nom, password, prenom);

	}



	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public Set<Parcour> getParcours() {
		return this.parcours;
	}

	public void setParcours(Set<Parcour> parcours) {
		this.parcours = parcours;
	}

}