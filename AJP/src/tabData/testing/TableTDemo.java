package tabData.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tabData.tables.TabDataInput;
import tabData.tables.Table;

public class TableTDemo {
	
	@Test
	public void test() {
		
		assertTrue(Files.exists(Paths.get("max_oge_1.0.txt")));
		
		Table table = Table.builder()
				   .withData("max_oge_1.0.txt")
				   .rowMin(0)
				   .rowMax(16_000)
				   .rowInterval(500)
				   .columnMin(-45)
				   .columnMax(55)
				   .columnInterval(5)
				   .build();
		
		assertAll(() -> System.out.println(table));
		
		//top-left
		assertTrue(table.getPrecise(0, -45).get() == 220);

		//top-right
		assertTrue(table.getPrecise(0, 55).get() == 211);
		
		//bottom-left
		assertTrue(table.getPrecise(16_000, -45).get().isNaN());

		//bottom-right
		assertTrue(table.getPrecise(16_000, 55).get().isNaN());	
		
		//random interpolation
		assertTrue(table.interpolate(3_750, 42.5).get() == 197.5);
		
		//same row
		assertTrue(table.interpolate(8_000, -3).get() == 195.8);
		
		//same column
		assertTrue(table.interpolate(8_250, 17.5).get() == 183.25);
		
		//NaN Interpolation
		assertTrue(!table.interpolate(8_750, 35.98).isPresent());
		
		var data = TabDataInput.compose()
								.ATF(1)
								.ZF_Weight(13_500)
								.Fuel_Weight(2_200)
								.Temp(12)
								.PA(1000)
								.calculate();
		
		System.out.println(data);
		
	}	
}
