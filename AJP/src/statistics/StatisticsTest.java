package statistics;

public class StatisticsTest {

	public static void main(String[] args) {
		/*
		 * TODO:
		 * Might use loaner or EAM pattern... need to reassess patterns' pro/con/use cases.
		 */
		BayesRule test = BayesRule.Builder()
									.setPrior(0.01f)
									.setSensitivity(0.8f)
									.setSpecificity(0.5f)
									.nameEvents("Have Heart Disease", "Don't have Heart Disease")
									.nameTests("Pos", "Neg")
									.create();
		test.printPosterior();
		}
}
