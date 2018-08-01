package fp.defaultInterfaces;

public class SeaPlane extends Vehical implements FastFly, Sail {

	private int altitude = 1;
	
	public void cruise() {
		System.out.println("SeaPlane cruising like: ");
		if(altitude > 0) {
			FastFly.super.cruise();
		}else {
			Sail.super.cruise();
		}
	}
	
}
