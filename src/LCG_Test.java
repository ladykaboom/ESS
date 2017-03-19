/**
 * 
 * @author Raven
 *
 */

public class LCG_Test {

	private static LCG_alg lcg_alg;

	private static int n = 18;

	private static long guess_a = 0;
	private static long guess_c = 0;
	private static long guess_m = 0;

	public static void main(String[] args) {

		lcg_alg = new LCG_alg();

		/*
		 * tablica wszystkich wyliczonych 'u', na koncu bêdziemy liczyæ
		 * NWD(u_array)
		 */
		long[] u_array = new long[n];

		/* generujemy pierwsze losowe liczby */
		long result_0 = lcg_alg.genRand(); // n
		long result_1 = lcg_alg.genRand(); // n+1
		long result_2 = lcg_alg.genRand(); // n+2
		
		print(result_0, result_1, result_2);

		for (int i = 0; i < n; i++) {
			
			if (i > 0) {
				 long guessValue = guess(result_2, result_0, result_1, result_2);
				 System.out.println("Guess Result3 as: " + guessValue);
			}

			long result_3 = lcg_alg.genRand(); // n+3

			System.out.println("Generated new random (Result3): " + result_3);

			/* try to find 'm' */
			long t_n = result_1 - result_0;
			long t_n1 = result_2 - result_1;
			long t_n2 = result_3 - result_2;

			long u_n = Math.abs(t_n2 * t_n - t_n1 * t_n1);
			u_array[i] = u_n;

			guess_m = NWD(u_array);
			System.out.println("[i:" + i + "] calculated m = " + guess_m);

			result_0 = result_1;
			result_1 = result_2;
			result_2 = result_3;
			
			System.out.println("------------------");
		}
	}

	private static long guess(long x, long x_1, long x_2, long x_3) {
		solve(x_1, x_2, x_3);
		return lcg_alg.guessRand(guess_a, x, guess_c, guess_m);
	}

	private static void solve(long x_1, long x_2, long x_3) {
		/*
		 * ...x_2, x_1, x <- looking for 'x' x_2 = x_1 * a + c mod m x_3 = x_2 *
		 * a + c mod m find: a and c
		 * 
		 * | x_2 - x_3 | = a*() mod m
		 * 
		 * 1080*j = 1 (mod 7).
		 */

		/* calculate 'a' */
		long i = 0;
		if (guess_m <= 70000) {
			for ( i = 0; i < guess_m; i++) {
				if ((i * (x_1 - x_2)) % guess_m == (x_2 - x_3)) {
					guess_a = i;
					
					/*calculate 'c' */
					for ( int k = 0; k < guess_m; k++) {
						if ((x_1 * guess_a + k) % guess_m == x_2) {
							guess_c = k;							
							break;
						}
					}
					break;
				}
			}
			System.out.println("Guess a = " + guess_a + " ; guess c = " + guess_c);
		}
	}

	private static void print(long res1, long res2, long res3) {
		System.out.println("Result_0 =" + res1);
		System.out.println("Result_1 =" + res2);
		System.out.println("Result_2 =" + res3);
	}

	/* Calculate gcd (NWD) from array */
	private static long NWD(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++)
			result = NWD(result, input[i]);
		return result;
	}

	private static long NWD(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}
}
