package fp.loaner;

public class TestFluentMailer {

	public static void main(String[] args) {

		//You get the mailer, you do stuff with it, then you return it.
		//Works with builder.
		FluentMailer.send(mailer -> mailer.from("alex@gmail.com")
										  .to("aly@gmail.com")
										  .subject("Hello World")
										  .body("Spartan Race?"));
	
	}

}
