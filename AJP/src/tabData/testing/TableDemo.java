package tabData.testing;

import tabData.tables.Tables;

public class TableDemo {

	public static void main(String[] args) {
		
						
		System.out.println(Tables.TRQ_IGE_GOOD.interpolate(8_800, -12).get());
		System.out.println(Tables.TRQ_IGE_GOOD.interpolate(8_800, -12).get());
		
		Tables.MAX_OGE_BAD.showInterpolation(12_250, 22);
		
		//System.out.println("\nAnswer: " + table.interpolate(8_250, 17.5).get());
		
	}

}
