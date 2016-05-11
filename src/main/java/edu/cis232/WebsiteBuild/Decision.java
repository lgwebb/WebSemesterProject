package edu.cis232.WebsiteBuild;

public class Decision extends Choice implements Cancel, GoInto { // REQ#4

	int choice2;
	String answer;
	
	public Decision(String answer) { // REQ#6
		super(answer);

	}

	@Override
	public int getchoice2() {
		return getchoice2();
	}

	@Override
	public void choiceMade() {
		return;
		
	}


}
