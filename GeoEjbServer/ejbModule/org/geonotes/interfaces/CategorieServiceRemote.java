package org.geonotes.interfaces;
import java.util.List;

import javax.ejb.Remote;

import org.geonotes.entities.Categorie;
import org.geonotes.exceptions.GeoNotesException;

/**
 * 
 * @author Amine 
 *
 */
@Remote
public interface CategorieServiceRemote {


	/**
	 * Permet de créer une Categorie
	 * @param categorie
	 * @return
	 */
	Categorie create(Categorie categorie)throws GeoNotesException;

	/**
	 * Permet de mettre à jour une Categorie
	 * @param categorie
	 * @return
	 */
	Categorie update(Categorie categorie)throws GeoNotesException;

	/**
	 * Permet de supprimer une Categorie
	 * @param categorie
	 */
	void remove(Categorie categorie)throws GeoNotesException;

	/**
	 * Retourne une Categorie par son identifiant
	 * @param id
	 * @return
	 */
	Categorie findById(int id)throws GeoNotesException;

	/**
	 * Retourne la liste de tous les Categories
	 * @return
	 */
	List<Categorie> getAll()throws GeoNotesException;


}