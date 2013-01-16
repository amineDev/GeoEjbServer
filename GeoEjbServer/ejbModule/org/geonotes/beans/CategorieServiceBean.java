package org.geonotes.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.geonotes.entities.Categorie;
import org.geonotes.exceptions.GeoNotesException;
import org.geonotes.interfaces.CategorieServiceLocal;
import org.geonotes.interfaces.CategorieServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Amine 
 * 
 * Session Bean implementation class CategorieServiceBean
 * Permet de créer une Catégorie, modifier , supprimer , lister 
 * et rechercher par Identifiant
 * 
 */


@Stateless(mappedName = "CategorieServiceBean")
public class CategorieServiceBean implements CategorieServiceRemote, CategorieServiceLocal {



	@PersistenceContext(unitName = "geoJPA")
	private EntityManager entityManager;

	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(CategorieServiceBean.class);


	/**
	 * constructor
	 */
	public CategorieServiceBean() {
	}


	@Override
	public Categorie create(Categorie categorie) throws GeoNotesException {

		try{
			logger.info("Création d'une Categorie");
			return entityManager.merge(categorie);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@Override
	public Categorie update(Categorie categorie) throws GeoNotesException {

		try{
			logger.info("MAJ d'une Categorie");
			return entityManager.merge(categorie);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@Override
	public void remove(Categorie categorie) throws GeoNotesException {

		try{
			logger.info("Suppression d'une Categorie");
			entityManager.remove(categorie);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}


	@Override
	public Categorie findById(int id) throws GeoNotesException {

		try{
			logger.info("Recherche de la Catégorie avec l'id : " + id);
			return entityManager.find(Categorie.class, id);
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> getAll() throws GeoNotesException {

		logger.info("Récupération de la liste des Categories");
		try{
			String q = "SELECT g from " + Categorie.class.getSimpleName() + " g";
			Query query = entityManager.createQuery(q);
			List<Categorie> cat = query.getResultList();
			return cat;
		}catch (PersistenceException e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}




}
