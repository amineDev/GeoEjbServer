package org.geonotes.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.geonotes.entities.Administrateur;
import org.geonotes.exceptions.GeoNotesException;

import org.geonotes.interfaces.AdminServiceLocal;
import org.geonotes.interfaces.AdminServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Amine 
 * 
 * Session Bean implementation class AdminServiceBean
 * Permet de créer un Administrateur, modifier , supprimer , lister ,rechercher par Identifiant/Email ,
 * verifier un Email en Base et  de vérifier un login(email,password)
 */


@Stateless(mappedName = "AdminServiceBean")
@LocalBean
public class AdminServiceBean implements AdminServiceRemote, AdminServiceLocal {

	@PersistenceContext(unitName = "geoJPA")
	private EntityManager entityManager;


	//Logger
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceBean.class);

	/**
	 *  constructor. 
	 */
	public AdminServiceBean() {
	}

	@Override
	public Administrateur create(Administrateur admin) throws GeoNotesException{

		try{
			logger.info("Création d'un administrateur");
			return  entityManager.merge(admin);

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}

	
	@Override
	public Administrateur update(Administrateur admin) throws GeoNotesException {

		try{
			logger.info("MAJ d'un administrateur");
			return entityManager.merge(admin);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public void remove(Administrateur admin) throws GeoNotesException {
		try{
			logger.info("Suppression  d'un administrateur");
			entityManager.remove(admin);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public Administrateur findById(int id) throws GeoNotesException {

		try{
			logger.info("Recherche de l'administrateur avec id : " + id);
			return entityManager.find(Administrateur.class, id);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrateur> getAll() throws GeoNotesException {

		try{		
			logger.info("Récupération de la liste des administrateurs");

			String q = "SELECT admins from " + Administrateur.class.getSimpleName() + " admins";
			Query query = entityManager.createQuery(q);
			List<Administrateur> admins = query.getResultList();
			return admins;

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}

	@Override
	public Administrateur findByEmail(String email) throws GeoNotesException {

		try{
			logger.info("Recherche d'un Administrateur par son EMAIL : " + email);

			TypedQuery<Administrateur> query = entityManager.createQuery(
					"SELECT a FROM Administrateur a WHERE a.email = :email", Administrateur.class);
			return query.setParameter("email", email).getSingleResult();

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public boolean login(String email, String password) throws GeoNotesException {

		boolean value = false;
		logger.info("Vérification d'un Login Administrateur");

		try{
			TypedQuery<Administrateur> query = entityManager.createQuery(
					"SELECT ad FROM Administrateur ad WHERE ad.email = :email", Administrateur.class);
			Administrateur admin =  query.setParameter("email", email).getSingleResult();

			if(admin.getEmail().equals(email) && admin.getPass().equals(password)){
				value = true;
			}
			return value;

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public boolean mailExist(String email) throws GeoNotesException {
		boolean value = false;
		logger.info("Vérification d'email");

		try{
			TypedQuery<Administrateur> query = entityManager.createQuery(
					"SELECT ad FROM Administrateur ad WHERE ad.email = :email", Administrateur.class);
			Administrateur admin =  query.setParameter("email", email).getSingleResult();

			if(admin != null && admin.getEmail().equals(email)){
				value = true;
			}
			return value;

		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}




}
