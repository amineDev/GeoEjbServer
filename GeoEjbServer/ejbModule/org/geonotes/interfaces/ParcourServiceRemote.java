package org.geonotes.interfaces;
import java.util.List;

import javax.ejb.Remote;

import org.geonotes.entities.Parcour;
import org.geonotes.entities.Utilisateur;
import org.geonotes.exceptions.GeoNotesException;

/**
 * 
 * @author Amine 
 *
 */

@Remote
public interface ParcourServiceRemote {


	/**
	 * Permet de créer un nouveau Parcours
	 * @param parcours
	 * @return
	 */
	Parcour create(Parcour parcours)throws GeoNotesException;

	/**
	 * Permet de mettre à jour un Parcours
	 * @param parcours
	 * @return
	 */
	Parcour update(Parcour parcours)throws GeoNotesException;

	/**
	 * Permet de supprimer un Parcours
	 * @param parcours
	 */
	void remove(Parcour parcours)throws GeoNotesException;

	/**
	 * Retourne un Parcours par son identifiant
	 * @param id
	 * @return
	 */
	Parcour findById(int id)throws GeoNotesException;

	/**
	 * Retourne la liste de tous les Parcours
	 * @return
	 */
	List<Parcour> getAll()throws GeoNotesException;

	/**
	 * Retourne les statistiques de l'utilisation d'un parcours
	 * @param IdParcours
	 * @return
	 * @throws GeoNotesException
	 */
	int getStatistiques(int IdParcours)throws GeoNotesException;


	/**
	 * Retourne la liste des utilisateurs qui ont utilisés le Parcours
	 * @param IdParcours
	 * @return
	 * @throws GeoNotesException
	 */
	List<Utilisateur> getStatistiquesUsers(int IdParcours) throws GeoNotesException;
	
	
	/**
	 * Retourne liste des parcours qui sont près  de l'emplacement géographique de l'Utilisateur
	 * @param utilisateur
	 * @return
	 * @throws GeoNotesException
	 */
	List<Parcour> getParcourByLocalisationUser(Utilisateur utilisateur)throws GeoNotesException;


	/**
	 * Retourne liste des parcours qui sont près d'un l'emplacement géographique (latitude,longitude)
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws GeoNotesException
	 */
	List<Parcour> getParcourByLocalisation(double latitude,double longitude )throws GeoNotesException;

	
	/**
	 * Retourne l'adresse complète d'une position
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	String getAdress(double latitude,double longitude)throws GeoNotesException;
}


