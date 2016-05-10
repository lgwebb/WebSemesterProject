package edu.cis232.WebsiteBuild;



public class InvalidFavAdded extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidFavAdded() {
		super("Nothing was added. Type new URL.");
	}

	public InvalidFavAdded(String message) {
		super(message);
	}
}
