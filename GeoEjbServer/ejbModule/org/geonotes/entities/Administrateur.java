package org.geonotes.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * @author Amine 
 * 
 * The persistent class for the administrateur
 * 
 */
@Entity
@DiscriminatorValue("Administrateur")
public class Administrateur extends Users implements Serializable {

    	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1799276875169495645L;

	@OneToMany(mappedBy="administrateur",fetch=FetchType.LAZY)
	private Set<Note> notes;

	@OneToMany(mappedBy="administrateur",fetch=FetchType.LAZY)
	private Set<Parcour> parcours;

    public Administrateur() {
    }

    
    
	/**
	 * @param email
	 * @param nom
	 * @param password
	 * @param prenom
	 */
	public Administrateur(String email, String nom, String password,
			String prenom) {
		super(email, nom, password, prenom);
		
	}




	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
	public Set<Parcour> getParcours() {
		return this.parcours;
	}

	public void setParcours(Set<Parcour> parcours) {
		this.parcours = parcours;
	}
	
	
	
}