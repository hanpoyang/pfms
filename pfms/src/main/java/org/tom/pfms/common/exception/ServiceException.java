package org.tom.pfms.common.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException(DaoException ex) {
    	super(ex);
    }
}
