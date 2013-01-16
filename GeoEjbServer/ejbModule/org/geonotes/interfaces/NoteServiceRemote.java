package org.geonotes.interfaces;
import java.util.List;

import javax.ejb.Remote;

import org.geonotes.entities.Note;
import org.geonotes.exceptions.GeoNotesException;

/**
 * 
 * @author Amine 
 *
 */
@Remote
public interface NoteServiceRemote {
	
	
	
	/**
	 * Permet de créer une Note
	 * @param note
	 * @return
	 */
	Note create(Note note)throws GeoNotesException;

	/**
	 * Permet de mettre à jour une Note
	 * @param admin
	 * @return
	 */
	Note update(Note note)throws GeoNotesException;

	/**
	 * Permet de supprimer une Note
	 * @param user
	 */
	void remove(Note note)throws GeoNotesException;

	/**
	 * Retourne une Note par son identifiant
	 * @param id
	 * @return
	 */
	Note findById(int id)throws GeoNotesException;

	/**
	 * Retourne la liste de tous les Notes
	 * @return
	 */
	List<Note> getAll()throws GeoNotesException;


}
