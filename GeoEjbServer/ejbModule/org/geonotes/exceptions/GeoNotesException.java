/**
 * 
 */
package org.geonotes.exceptions;

import javax.ejb.ApplicationException;


/**
 * @author Amine 
 *
 * Class qui permet de g�rer des exceptions personnalis� de GeoNotes application
 *
 */
@ApplicationException(rollback = true)
public class GeoNotesException extends RuntimeException {

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
	public GeoNotesException(String msg,Throwable cause) {
		super(msg,cause);
	}

}
