package edu.cis232.WebsiteBuild;

public abstract class Choice {
	String answer;
	
	public Choice(String answer){
		this.answer = answer;
	}
	
	public String getanswer(){
		return answer;
	}
	
	public void setanswer(String answer){
		this.answer = answer;
	}
	
	@Override
	public String toString(){
		return answer;
	}
}
