package fr.blavin.oui.sncf.technicaltest;

/**
 * The WrongBatchException is raised in case of a wrong batch.
 */
public class WrongBatchException extends RuntimeException {

	private static final long serialVersionUID = 4118285437269559136L;

	public WrongBatchException() {
		super();
	}

	public WrongBatchException(String message) {
		super(message);
	}

	public WrongBatchException(String message, Throwable e) {
		super(message, e);
	}

	public WrongBatchException(Throwable e) {
		super(e);
	}

}
