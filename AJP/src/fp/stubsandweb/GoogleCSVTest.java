package fp.stubsandweb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

public class GoogleCSVTest {

	public static void main(String[] args) {


		try {
			final URL url = new URL("http://www.google.com/finance/historical?q=GOOGL&output=csv");
			
			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			final String data = reader.lines().skip(1).findFirst().get();
			
			final String[] dataItems = data.split(",");
			
			System.out.println(new BigDecimal(dataItems[dataItems.length - 1]));
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}

}
