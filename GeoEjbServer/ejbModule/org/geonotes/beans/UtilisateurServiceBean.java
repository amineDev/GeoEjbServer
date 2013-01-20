package org.geonotes.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.geonotes.entities.Utilisateur;
import org.geonotes.exceptions.GeoNotesException;
import org.geonotes.interfaces.UtilisateurServiceLocal;
import org.geonotes.interfaces.UtilisateurServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Amine TIFAK
 *  
 * Session Bean implementation class UtilsateurServiceBean
 * Permet de créer un Utilisateur, modifier , supprimer , lister ,rechercher par Identifiant/Email ,
 * verifier un Email en Base et  de vérifier un login(email,password)
 */


@Stateless(mappedName = "UtilisateurServiceBean")
public class UtilisateurServiceBean implements UtilisateurServiceRemote, UtilisateurServiceLocal {


	@PersistenceContext(unitName = "geoJPA")
	private EntityManager entityManager;

	//Logger
	private static final Logger logger = LoggerFactory.getLogger(UtilisateurServiceBean.class);

	/**
	 * constructor. 
	 */
	public UtilisateurServiceBean() {
	}

	@Override
	public Utilisateur create(Utilisateur user) throws GeoNotesException {

		try {
			logger.info("Création d'un Utilisateur");
			return entityManager.merge(user);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public Utilisateur update(Utilisateur user) throws GeoNotesException {

		try{
			logger.info("MAJ d'un Utilisateur");
			return entityManager.merge(user);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public void remove(Utilisateur user) throws GeoNotesException {

		try{
			logger.info("Suppression d'un Utilisateur");
			entityManager.remove(user);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}

	@Override
	public Utilisateur findById(int id) throws GeoNotesException {

		try{
			logger.info("Recherche de Utilisateur avec l'id : " + id);
			return entityManager.find(Utilisateur.class, id);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getAll() throws GeoNotesException {

		logger.info("Récupération de la liste des Utilisateur");

		try{
			String q = "SELECT users from " + Utilisateur.class.getSimpleName() + " users";
			Query query = entityManager.createQuery(q);
			List<Utilisateur> users = query.getResultList();
			return users;
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}


	}

	@Override
	public Utilisateur findByEmail(String email) throws GeoNotesException {

		logger.info("Recherche d'un Utilisateur par son EMAIL : " + email);
		try{
			TypedQuery<Utilisateur> query = entityManager.createQuery(
					"SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class);
			return query.setParameter("email", email).getSingleResult();
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@Override
	public boolean login(String email, String password) throws GeoNotesException {

		boolean value = false;
		logger.info("Vérification d'un Login Utilisateur");
		try{
			TypedQuery<Utilisateur> query = entityManager.createQuery(
					"SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class);
			Utilisateur user =  query.setParameter("email", email).getSingleResult();

			if(user.getEmail().equals(email) && user.getPass().equals(password)){
				value = true;
			}
			return value;
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}

	@Override
	public boolean mailExist(String email) throws GeoNotesException {
		boolean value = false;
		logger.info("Vérification d'un email");
		try{
			TypedQuery<Utilisateur> query = entityManager.createQuery(
					"SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class);
			Utilisateur user =  query.setParameter("email", email).getSingleResult();

			if(user != null && user.getEmail().equals(email)){
				value = true;
			}
			return value;
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}



	@Override
	public String getTypeByEmail(String email) throws GeoNotesException {

		String type = "";

		try{
			String q = "SELECT DTYPE FROM `users` WHERE email = '" + email+ "'";
			Query query = entityManager.createNativeQuery(q);
			type = query.getSingleResult().toString();		
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

		return type;
	}



}
