package edu.cis232.WebsiteBuild;

public class Accept extends Choice implements Cancel, GoInto{ //REQ#4

	int choice2;
	
	public Accept(String answer, int choice2) { //REQ#6
		super(answer);
		this.choice2 = choice2;
	}
	
	public void choiceMade(){
		System.out.println("You have made this choice.");
		choice2--;
	}
	@Override
	public int getchoice2(){
		return choice2;
	}
}
