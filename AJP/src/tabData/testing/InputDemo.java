package tabData.testing;

import tabData.tables.TabDataInput;

public class InputDemo {

	public static void main(String[] args) {
		
		var hover = TabDataInput.compose()
							   .ATF(0.95)
							   .ZF_Weight(15_800)
							   .Fuel_Weight(650)
							   .Temp(-12)
							   .PA(8800)
							   .compute();
		
		var predictedWeight = TabDataInput.compose()
				.HOVER(67.4)
				.Temp(-12)
				.PA(8800)
				.compute();
				
		/*var zf_weight = TabDataInput.compose()
		 		.FUEL(x)
				.HOVER(67.4)
				.Temp(-12)
				.PA(8800)
				.findZFWeight();*/
		
		System.out.println(hover);
		System.out.println(predictedWeight);
		//System.out.println(zf_weight);
		
	}
	
}
