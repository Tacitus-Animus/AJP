package fp.virtualProxy;

public class TestVirtualProxyPattern {

	public static void main(String[] args) {

		Holder proxy = new Holder();
		
		//First call is to a lambda that calls a synchronized method to get the object. 
		System.out.println("Asking for heavy object:");
		System.out.println(proxy.getHeavy() + "\n");
		//The second call is on the newly re-assigned proxy that doesn't use a synchronized method.
		System.out.println("Asking for heavy object:");
		System.out.println(proxy.getHeavy());
		
	}

}
