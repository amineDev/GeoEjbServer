package org.geonotes.entities;


import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Amine 
 * 
 * The persistent class abstract for the Users 
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Users implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8787609208563482207L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(unique=true)
	private String email;

	private String nom;

	private String password;

	private String prenom;



	public Users() {
	}


	/**
	 * @param email
	 * @param nom
	 * @param password
	 * @param prenom
	 */
	public Users(String email, String nom, String password, String prenom) {
		super();
		this.email = email;
		this.nom = nom;
		this.password = password;
		this.prenom = prenom;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPass() {
		return this.password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		if (!(obj instanceof Users)) {
			return false;
		}
		Users other = (Users) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}





}