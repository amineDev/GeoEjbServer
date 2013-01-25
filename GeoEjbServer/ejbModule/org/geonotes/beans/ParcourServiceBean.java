package org.geonotes.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.geonotes.entities.Note;
import org.geonotes.entities.Parcour;
import org.geonotes.entities.Utilisateur;
import org.geonotes.exceptions.GeoNotesException;
import org.geonotes.interfaces.ParcourServiceLocal;
import org.geonotes.interfaces.ParcourServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;

/**
 * @author Amine 
 * 
 * Session Bean implementation class ParcourServiceBean
 * Permet de créer , modifier , supprimer , lister ,rechercher ,
 * afficher les statistiques d’emploi des parcours par les utilisateurs finaux
 * et récupérer les parcours selon la position géographique d'un utilisateur 
 */


@Stateless(mappedName = "ParcourServiceBean")
@LocalBean
public class ParcourServiceBean implements ParcourServiceRemote, ParcourServiceLocal {

	@PersistenceContext(unitName = "geoJPA")
	private EntityManager entityManager;

	//Logger
	private static final Logger logger = LoggerFactory.getLogger(ParcourServiceBean.class);

	/**
	 *  constructor. 
	 */
	public ParcourServiceBean() {
	}

	@Override
	public Parcour create(Parcour parcours) throws GeoNotesException {

		try{
			logger.info("Création d'un Parcours");
			return entityManager.merge(parcours);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public Parcour update(Parcour parcours) throws GeoNotesException {

		try{
			logger.info("MAJ d'un Parcours");
			return entityManager.merge(parcours);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public void remove(Parcour parcours) throws GeoNotesException {

		try{
			logger.info("Suppression d'un Parcours");
			entityManager.remove(parcours);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public Parcour findById(int id) throws GeoNotesException {

		try{
			logger.info("Recherche d'un Parcours avec l'id : " + id);
			return entityManager.find(Parcour.class, id);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parcour> getAll() throws GeoNotesException {

		logger.info("Récupération de la liste des Parcours");

		try{
			String q = "SELECT parcours from " + Utilisateur.class.getSimpleName() + " parcours";
			Query query = entityManager.createQuery(q);
			List<Parcour> parcours = query.getResultList();
			return parcours;
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public int getStatistiques(int IdParcour) throws GeoNotesException {

		logger.info("Récupération des statistiques d'un Parcours");
		int nb = 0;
		try{

			String q = "SELECT count(*) FROM `parcours_utilisateurs` WHERE id_parcours = " + IdParcour;
			Query query = entityManager.createNativeQuery(q);
			nb = Integer.parseInt(query.getResultList().get(0).toString());
			return nb;
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@Override
	public List<Utilisateur> getStatistiquesUsers(int IdParcour)
	throws GeoNotesException {

		logger.info("Récupération des utilisateurs d'un Parcours");
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		try{
			Query query = entityManager.createQuery(
					"SELECT p FROM " + Parcour.class.getSimpleName()+" p WHERE p.id = :id");
			query.setParameter("id", IdParcour);
			Parcour parcour = (Parcour) query.getSingleResult();

			users.addAll(parcour.getUtilisateurs());

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

		return users;
	}



	@Override
	public List<Parcour> getParcourByLocalisationUser(Utilisateur utilisateur)
	throws GeoNotesException {

		try{
			//On récupère l'adresse de la position de l'utilisateur GPS
			String Adresse = this.getAdress(utilisateur.getLatitude(), utilisateur.getLongitude());

			List<Parcour> resultat = new ArrayList<Parcour>();

			//On récupère la liste de tous les parcours
			List<Parcour> parcours = this.getAll();

			/*Pour chaque parcours on récupère ses Notes et on vérifie 
		   si la ville correspond à l'adresse geolocalisé de l'utilisateur*/
			for (Parcour parcour : parcours) {
				List<Note> notes = new ArrayList<Note>();
				notes.addAll(parcour.getNotes());

				for (Note note : notes) {			
					/*Si la note se trouve dans la meme ville on ajoute 
            	 le parcours au liste de résultats*/
					if(Adresse.contains(note.getVille())){
						resultat.add(parcour);
						break;
					}           	 
				}
			}

			return resultat;

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}




	@Override
	public List<Parcour> getParcourByLocalisation(double latitude,
			double longitude) throws GeoNotesException {

		try{
			//On récupère l'adresse des coordonnées GPS
			String Adresse = this.getAdress(latitude,longitude);

			List<Parcour> resultat = new ArrayList<Parcour>();

			//On récupère la liste de tous les parcours
			List<Parcour> parcours = this.getAll();

			/*Pour chaque parcours on récupère ses Notes et on vérifie 
		   si la ville correspond à l'adresse geolocalisé de l'utilisateur*/
			for (Parcour parcour : parcours) {
				List<Note> notes = new ArrayList<Note>();
				notes.addAll(parcour.getNotes());

				for (Note note : notes) {			
					/*Si la note se trouve dans la meme ville on ajoute 
            	 le parcours au liste de résultats*/
					if(Adresse.contains(note.getVille())){
						resultat.add(parcour);
						break;
					}           	 
				}
			}

			return resultat;

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}



	@Override
	public String getAdress(double latitude,double longitude)throws GeoNotesException{


		try{
			String adresse = "";
			Geocoder geocoder = new Geocoder();
			GeocodeResponse geocoderResponse;

			String lat = String.valueOf(latitude);
			String lon = String.valueOf(longitude);

			LatLng latLng = new LatLng(lat, lon);

			GeocoderRequest geocoderRequest = new GeocoderRequest();
			geocoderRequest.setLocation(latLng);

			geocoderResponse = geocoder.geocode(geocoderRequest);

			if(geocoderResponse.getResults().isEmpty()){
				logger.error("Connot find Adresse of the postion " + latitude +"/"+longitude);
			}else {
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();

				adresse = geocoderResult.getFormattedAddress();

			}

			return adresse;		

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

}
