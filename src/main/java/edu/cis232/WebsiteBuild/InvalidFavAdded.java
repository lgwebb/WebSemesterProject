package edu.cis232.WebsiteBuild;



public class InvalidFavAdded extends Exception {
	private static final long serialVersionUID = 1L;
	
	
	public InvalidFavAdded() { //REQ#5
		super();
	}

	public InvalidFavAdded(String message) {
		super(message);
	}
	
}
