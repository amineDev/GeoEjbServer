package org.geonotes.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * @author Amine 
 * 
 * The persistent class for the Parcour
 * 
 */
@Entity
@Table(name="parcours")
public class Parcour implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5741384262824857330L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String commentaire;

    @Temporal( TemporalType.DATE)
	private Date dateCreation;

	private double denivele;

	private double distance;

	private String nom;

    @ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="id_admin")
	private Administrateur administrateur;

	@ManyToMany(mappedBy="parcours" ,fetch=FetchType.EAGER)
	private Set<Utilisateur> utilisateurs;

    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="parcoursnotes"
		, joinColumns={
			@JoinColumn(name="id_parcours")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_note")
			}
		)
	private Set<Note> notes;

    public Parcour() {
    }

        
	/**
	 * @param commentaire
	 * @param dateCreation
	 * @param denivele
	 * @param distance
	 * @param nom
	 * @param administrateur
	 */
	public Parcour(String commentaire, Date dateCreation, double denivele,
			double distance, String nom, Administrateur administrateur) {
		super();
		this.commentaire = commentaire;
		this.dateCreation = dateCreation;
		this.denivele = denivele;
		this.distance = distance;
		this.nom = nom;
		this.administrateur = administrateur;
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

	public double getDenivele() {
		return this.denivele;
	}

	public void setDenivele(double denivele) {
		this.denivele = denivele;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Administrateur getAdministrateur() {
		return this.administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
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
		if (!(obj instanceof Parcour)) {
			return false;
		}
		Parcour other = (Parcour) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
	
	
}