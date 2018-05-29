package statistics;

public class ProbObject {

	String name, chance1, chance2;
	
	float prob1, prob2;
	
	public ProbObject(String name, String chance1, float prob, String chance2) {
		this.name = name;
		this.chance1 = chance1;
		this.chance2 = chance2;
		prob1 = prob;
		prob2 = 1f - prob1;
	}
	
	@Override
	public String toString() {
		return name + " - " + "[" + chance1 + ":" + prob1 + "] [" + chance2 + ":" + prob2 + "]";
	}
	
}
