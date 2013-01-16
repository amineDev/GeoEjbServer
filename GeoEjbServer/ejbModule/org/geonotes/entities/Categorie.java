package org.geonotes.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * @author Amine 
 * 
 * The persistent class for the Categorie
 * 
 */
@Entity
@Table(name="categorie")
public class Categorie implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2694994106114338624L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Description")
	private String description;

	@Column(name="Intitule")
	private String intitule;

	
	@OneToMany(mappedBy="categorie",fetch=FetchType.LAZY)
	private Set<Note> notes;

    public Categorie() {
    }

    
    
	/**
	 * @param description
	 * @param intitule
	 */
	public Categorie(String description, String intitule) {
		super();
		this.description = description;
		this.intitule = intitule;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
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
		if (!(obj instanceof Categorie)) {
			return false;
		}
		Categorie other = (Categorie) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
	
}