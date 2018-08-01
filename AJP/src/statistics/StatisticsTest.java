package statistics;

public class StatisticsTest {

	public static void main(String[] args) {
		
		BayesRule test = BayesRule.Builder()
									.setPrior(0.1f)
									.setSensitivity(0.9f)
									.setSpecificity(0.5f)
									.nameEvents("At Red", "At Green")
									.nameTests("Pos", "Neg")
									.create();
		
		test.printPosterior();
		
		}

}