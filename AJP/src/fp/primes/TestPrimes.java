package fp.primes;

public class TestPrimes {

	public static void main(String[] args) {
		System.out.println("100 Primes from 1: ");
		System.out.println(Primes.primes(1, 100));
		
		System.out.println("1000 Primes from 1000: ");
		Primes.primes(1000, 1000)
		.forEach(System.out::println);
		
	}

}
