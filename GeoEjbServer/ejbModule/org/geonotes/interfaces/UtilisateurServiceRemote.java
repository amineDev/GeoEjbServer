package org.geonotes.interfaces;
import java.util.List;

import javax.ejb.Remote;

import org.geonotes.entities.Utilisateur;
import org.geonotes.exceptions.GeoNotesException;

/**
 * @author Amine 
 *
 */

@Remote
public interface UtilisateurServiceRemote {

	/**
	 * Permet de créer un nouveau utilisateur
	 * @param user
	 * @return
	 */
	Utilisateur create(Utilisateur user)throws GeoNotesException;

	/**
	 * Permet de mettre à jour un utilisateur
	 * @param user
	 * @return
	 */
	Utilisateur update(Utilisateur user)throws GeoNotesException;

	/**
	 * Permet de supprimer un utilisateur
	 * @param user
	 */
	void remove(Utilisateur user)throws GeoNotesException;

	/**
	 * Retourne un utilisateur par son identifiant
	 * @param id
	 * @return
	 */
	Utilisateur findById(int id)throws GeoNotesException;

	/**
	 * Retourne la liste de tous les utilisateurs
	 * @return
	 */
	List<Utilisateur> getAll()throws GeoNotesException;

	/**
	 * Retourne un utilisateur par son email
	 * @param email
	 * @return
	 */
	Utilisateur findByEmail(String email)throws GeoNotesException;


	/**
	 * Permet de vérifier si le login d'un utilisateur est correcte ou non
	 * Return True si email et password correct Sinn Return False 
	 * @param email
	 * @param password
	 * @return
	 */
	boolean login(String email,String password)throws GeoNotesException;

	/**
	 * Permet de vérifier si l'email appartient deja à un Utilisateur
	 * @param email
	 * @return
	 * @throws GeoNotesException
	 */
	boolean mailExist(String email)throws GeoNotesException;
}
