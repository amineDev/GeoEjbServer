package org.geonotes.interfaces;
import java.util.List;

import javax.ejb.Local;

import org.geonotes.entities.Administrateur;
import org.geonotes.exceptions.GeoNotesException;


/**
 * 
 * @author Amine 
 *
 */
@Local
public interface AdminServiceLocal {



	/**
	 * Permet de créer un nouveau administrateur
	 * @param admin
	 * @return
	 */
	Administrateur create(Administrateur admin)throws GeoNotesException;

	/**
	 * Permet de mettre à jour un administrateur
	 * @param admin
	 * @return
	 */
	Administrateur update(Administrateur admin)throws GeoNotesException;

	/**
	 * Permet de supprimer un Administrateur
	 * @param user
	 */
	void remove(Administrateur admin)throws GeoNotesException;

	/**
	 * Retourne un Administrateur par son identifiant
	 * @param id
	 * @return
	 */
	Administrateur findById(int id)throws GeoNotesException;

	/**
	 * Retourne la liste de tous les Administrateurs
	 * @return
	 */
	List<Administrateur> getAll()throws GeoNotesException;


	/**
	 * Retourne un administrateur par son email
	 * @param email
	 * @return
	 */
	Administrateur findByEmail(String email)throws GeoNotesException;



	/**
	 * Permet de vérifier si le login d'un administrateur est correcte ou non
	 * Return True si email et password correct Sinn Return False 
	 * @param email
	 * @param password
	 * @return
	 */
	boolean login(String email,String password)throws GeoNotesException;


	/**
	 * Permet de vérifier si l'email appartient deja à un Administrateur
	 * @param email
	 * @return
	 * @throws GeoNotesException
	 */
	boolean mailExist(String email)throws GeoNotesException;


}
