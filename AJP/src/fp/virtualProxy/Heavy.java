package fp.virtualProxy;

public class Heavy {

	public Heavy() {
		System.out.println("Creating Heavy...");

		simulateHeavyObjectCreation();
		
		System.out.println("Heavy Created...");
	}

	
	@Override
	public String toString() {
		return "Quite Heavy";
	}
	
	private void simulateHeavyObjectCreation() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException ex) {
			//nothing...
		}
	}
	
}
