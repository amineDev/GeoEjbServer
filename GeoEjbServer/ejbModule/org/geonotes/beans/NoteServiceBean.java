package org.geonotes.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.geonotes.entities.Note;
import org.geonotes.exceptions.GeoNotesException;

import org.geonotes.interfaces.NoteServiceLocal;
import org.geonotes.interfaces.NoteServiceRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Amine 
 * 
 * Session Bean implementation class NoteServiceBean
 * Permet de créer une Note, modifier , supprimer,rechercher par Identifiant ,
 * et lister toutes les Notes
 */


@Stateless(mappedName = "NoteServiceBean")
@LocalBean
public class NoteServiceBean implements NoteServiceRemote, NoteServiceLocal {

	@PersistenceContext(unitName = "geoJPA")
	private EntityManager entityManager;

	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(NoteServiceBean.class);

	/**
	 *  constructor. 
	 */
	public NoteServiceBean() {
	}

	@Override
	public Note create(Note note) throws GeoNotesException {

		try{
			logger.info("Création d'une Note");
			return entityManager.merge(note);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public Note update(Note note) throws GeoNotesException {

		try{
			logger.info("MAJ d'une Note");
			return entityManager.merge(note);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@Override
	public void remove(Note note) throws GeoNotesException {

		try{
			logger.info("Suppression d'une Note");
			entityManager.remove(note);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}

	}

	@Override
	public Note findById(int id) throws GeoNotesException {

		try{
			logger.info("Recherche de la Note avec l'id : " + id);
			return entityManager.find(Note.class, id);
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getAll() throws GeoNotesException {

		logger.info("Récupération de la liste des Notes");
		try{
			String q = "SELECT notes from " + Note.class.getSimpleName() + " notes";
			Query query = entityManager.createQuery(q);
			List<Note> notes = query.getResultList();
			return notes;
		}catch (Throwable e) {
			logger.error("DB Exception",e);
			throw new GeoNotesException("DB Exception ",e);
		}
	}



}
