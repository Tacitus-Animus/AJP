package statistics;

public class BayesRule {
	
	public static final char NEGATE = '¬';
	
	private PosteriorState positive = new PosteriorState("Positive", "C", "Pos");
	
	private PosteriorState negative = new PosteriorState("Negative", "¬C", "Neg");
	
	private class PosteriorState {

		float prior, sensitivity, specificity;
		final String name;
		String event, test;
		
		public PosteriorState(String name, String condition, String test) {
			this.name =  name;
			this.event = condition;
			this.test = test;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
	}
	
	public BayesRule(Builder b) {
		setPrior(b.prior);
		setSensitivity(b.sensitivity);
		setSpecificity(b.specificity);
		if(b.hasCustomEvents()) {
			setEvents(b.eventA, b.eventB);
		}
		if(b.hasCustomTests()) {
			setTests(b.testA, b.testB);
		}
	}

	/**
	 * What seems to be the outcome of a tested Event; Example: Test could be 'positive' or 'negative'
	 * @param posTest - custom name for a positive test.
	 * @param negTest - custom name for a negative test.
	 */
	private void setTests(String posTest, String negTest) {
		positive.test = posTest;
		negative.test = negTest;
	}

	/**
	 * What are the Events to be tested; Example: Events could be 'Has Cancer' and 'No Cancer'
	 * @param trueEvent - name of event being true.
	 * @param falseEvent - name of event being false.
	 */
	private void setEvents(String trueEvent, String falseEvent) {
		positive.event = trueEvent;
		negative.event = falseEvent;
	}

	/**
	 * True Positive; In other words, being tested positive for Event turning out true. Mathematical: P(Pos|C)
	 * @param probability - value between 0.0f and 1.0f (inclusive).
	 */
	public void setSensitivity(float probability) {
		positive.sensitivity = probability;
		negative.sensitivity = 1f - probability;
	}

	/**
	 * True Negative; In other words, being tested negative for Event turning out false. Mathematical: P(Neg|¬C)
	 * <p><b>
	 * Note: Since the positive posterior uses specificity as P(Pos|¬C), 
	 * the positive specificity is calculated by 1f - probability value to chance the Neg to Pos when used to find the joint P(¬C|Pos)
	 * <b><p>
	 * @param probability - value between 0.0f and 1.0f (inclusive).
	 */
	private void setSpecificity(float probability) {
		positive.specificity = 1f - probability;
		negative.specificity = probability;
	}

	/**
	 * Prior knowledge of the probability of the Event being True. Mathematical: P(C) 
	 * @param probability - value between 0.0f and 1.0f (inclusive).
	 */
	public void setPrior(float probability) {
		positive.prior = probability;
		negative.prior = 1f - probability;
	}

	public void printPosterior() {
		print(positive);
		System.out.println();
		print(negative);
	}
	
	public void print(PosteriorState varying) {		
		System.out.println(varying + " Posterior:\n");
		
		System.out.println("Joint:");
		
		float cPosNeg = positive.prior * varying.sensitivity; 
		
		System.out.printf("P(%s) * P(%s|%s) = P(%s|%s)\n", positive.event, varying.test, positive.event, positive.event, varying.test);
		System.out.printf("%.3f * %.3f = %.3f\n\n", positive.prior, varying.sensitivity, cPosNeg);
		
		float notCPosNeg = negative.prior * varying.specificity; 	
		
		System.out.printf("P(%s) * P(%s|%s) = P(%s|%s)\n", negative.event, varying.test, negative.event, negative.event, varying.test);
		System.out.printf("%.3f * %.3f = %.3f\n\n", negative.prior, varying.specificity, notCPosNeg);
				
		System.out.println("Normalizer:");
		
		float posNeg = cPosNeg + notCPosNeg;
		
		System.out.printf("P(%s|%s) + P(%s|%s) = P(%s)\n", positive.event, varying.test, negative.event, varying.test, varying.test);
		System.out.printf("%.3f + %.3f = %.3f\n\n", cPosNeg, notCPosNeg, posNeg);
				
		System.out.println("Posterior:");
		
		System.out.printf("P(%s|%s) / P(%s) = P(%s|%s)\n", positive.event, varying.test, varying.test, positive.event, varying.test);
		System.out.printf("%.3f / %.3f = %.3f\n\n", cPosNeg, posNeg, cPosNeg / posNeg);
		
		System.out.printf("P(%s|%s) / P(%s) = P(%s|%s)\n", negative.event, varying.test, varying.test, negative.event, varying.test);
		System.out.printf("%.3f / %.3f = %.3f\n\n", notCPosNeg, posNeg, notCPosNeg / posNeg);

		System.out.println("Conclusion:");
		
		System.out.printf("%.2f%% Chance of %s if test is %s.\n", cPosNeg / posNeg * 100, positive.event, varying.test);
		System.out.printf("%.2f%% Chance of %s if test is %s.\n", notCPosNeg / posNeg * 100, negative.event, varying.test, varying.test, negative.event, varying.test);
	}
	
	public static class Builder {
		
		private String eventA, eventB, testA, testB;
		
		private float prior, sensitivity, specificity;

		public boolean hasCustomEvents() {
			return eventA != null && !eventA.isEmpty();
		}

		public boolean hasCustomTests() {
			return testA != null && !eventA.isEmpty();
		}

		/**
		 * Prior knowledge of the probability of the Condition/Event being True. Mathematical: P(C) 
		 * @param probability - value between 0.0f and 1.0f (inclusive).
		 */
		public Builder setPrior(float probability) {
			prior = probability;
			return this;
		}

		/**
		 * True Positive; In other words, being tested positive for event turning out true. Mathematical: P(Pos|C)
		 * @param probability - value between 0.0f and 1.0f (inclusive).
		 */
		public Builder setSensitivity(float probability) {
			sensitivity = probability;
			return this;
		}

		/**
		 * True Negative; In other words, being tested negative for event turning out false. Mathematical: P(Neg|¬C)
		 * <p><b>
		 * Note: Since the positive posterior uses specificity as P(Pos|¬C), 
		 * the positive specificity is calculated by 1f - probability value to change the Neg to Pos when used to find the joint P(¬C|Pos)
		 * <b><p>
		 * @param probability - value between 0.0f and 1.0f (inclusive).
		 */
		public Builder setSpecificity(float probability) {
			specificity = probability;
			return this;
		}
		
		/**
		 * Name of the true and false state of event. Example: "Pick Coin 1" "Pick Coin 2" Mathematical: P(C) P(¬C)
		 * @param cStateA - Name of true state of event.
		 * @param cStateB - Name of false state of event.
		 * @return this builder.
		 */
		public Builder nameEvents(String cStateA, String cStateB) {
			eventA = cStateA;
			eventB = cStateB;
			return this;
		}

		/**
		 * Name of the positive and negative tests of the conditions Example: "Flip Heads" "Flip Tails" Mathematical: P(Pos) P(Neg)
		 * @param ConditionA - Name of Positive Test.
		 * @param ConditionB - Name of Negative Test.
		 * @return this builder.
		 */
		public Builder nameTests(String s1, String s2) {
			testA = s1;
			testB = s2;
			return this;
		}

		public BayesRule create() {
			return new BayesRule(this);
		}

		
	}
	
	public static Builder Builder() {
		return new Builder();
	}

}
