package eu.trentorise.smartcampus.moderatoservice.exception;

import eu.trentorise.smartcampus.network.RemoteException;

public class ModeratorServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733742984256898788L;

	public ModeratorServiceException(RemoteException e) {
		super(e);
	}

}
