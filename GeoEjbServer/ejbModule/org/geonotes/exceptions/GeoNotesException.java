/**
 * 
 */
package org.geonotes.exceptions;

import javax.ejb.ApplicationException;


/**
 * @author Amine 
 *
 * Class qui permet de gérer des exceptions personnalisé de GeoNotes application
 *
 */
@ApplicationException
public class GeoNotesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3538125586608493183L;

	/**
	 * 
	 */
	public GeoNotesException() {
		super();
	}

	/**
	 * 
	 * @param msg
	 * 
	 */
	public GeoNotesException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param msg
	 * @param e
	 */
	public GeoNotesException(String msg,Exception e) {
		super(msg,e);
	}

}
