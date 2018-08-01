package fp.loaner;

import java.util.function.Consumer;

public class FluentMailer {

	private FluentMailer() {}
	
	public FluentMailer from(final String address) {
		System.out.println(address);
		return this;
	}
	public FluentMailer to(final String address) {
		System.out.println(address);
		return this;
	}
	public FluentMailer subject(final String subject) {
		System.out.println(subject);
		return this;
	}
	
	public FluentMailer body(final String message) {
		System.out.println(message);
		return this;
	}
	
	//Mailer instance lifetime stays within the method.
	public static void send(final Consumer<FluentMailer> block) {
		FluentMailer mailer = new FluentMailer();
		block.accept(mailer);
		System.out.println("Sending...");
	}
	
}
