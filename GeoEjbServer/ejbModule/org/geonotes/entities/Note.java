package org.geonotes.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * @author Amine 
 * 
 * The persistent class for the Note
 * 
 */
@Entity
@Table(name="note")
public class Note implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6187885404270842347L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String commentaire;

	@Temporal( TemporalType.DATE)
	private Date dateCreation;

	private double latitude;

	private double longitude;

	private String ville;

	private String nom;

	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name="id_admin")
	private Administrateur administrateur;

	@ManyToMany(mappedBy="notes")
	private Set<Parcour> parcours;

	public Note() {
	}



	/**
	 * @param commentaire
	 * @param dateCreation
	 * @param latitude
	 * @param longitude
	 * @param nom
	 * @param categorie
	 */
	public Note(String commentaire, Date dateCreation, double latitude,
			double longitude, String nom, String ville,Categorie categorie) {
		super();
		this.commentaire = commentaire;
		this.dateCreation = dateCreation;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nom = nom;
		this.categorie = categorie;
		this.ville = ville;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Administrateur getAdministrateur() {
		return this.administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public Set<Parcour> getParcours() {
		return this.parcours;
	}

	public void setParcours(Set<Parcour> parcours) {
		this.parcours = parcours;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Note)) {
			return false;
		}
		Note other = (Note) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}








}