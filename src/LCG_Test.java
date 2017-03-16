/**
 * Opis rozwi¹zania znalaz³am tutaj:
 * https://security.stackexchange.com/questions/4268/cracking-a-linear-congruential-generator
 * 
 * Ogólnie chodzi o to, ¿eby najpierw znalezæ liczbê 'm' ze wzoru random =  a*x mod m
 * ¯eby to zrobiæ trzeba wygenerowaæ kilka kolejnych liczb (oznaczonych tutaj 's') i obliczyæ:
 *  tn = sn+1 - sn 
 *  a potem:
 *  un = |tn+2 tn - t2n+1|
 *  a na koñcu:
 *  x = NWD(ze wszystkich obliczonych un)
 *  
 *  To 'x' powinno byæ szukanym 'm', ale z jakimœ tam prawdopodobieñstwem. Im wiêcej razy przeprowadzisz
 *  test, tym wiêksza szansa, ¿e znajdziesz 'm'.
 *  
 *  Jak masz 'm', to wystarczy chyba rozwi¹zaæ uk³ad równañ, ¿eby znalezc 'a' i 'b'. 
 *  Wtedy mo¿esz przewidzieæ, jaka bêdzie kolejna liczba.
 *  
 * Mam nadziejê :D
 * 
 * @author Raven
 *
 */

public class LCG_Test {

	private static int n = 11;

	public static void main(String[] args) {

		LCG_alg lcg_alg = new LCG_alg();

		/* tablica wszystkich wyliczonych 'u', 
		 * na koncu bêdziemy liczyæ NWD(u_array) */
		long[] u_array = new long[11];
		
		/* generujemy pierwsze losowe liczby*/
		long result_0 = lcg_alg.genRand(); // n
		long result_1 = lcg_alg.genRand(); // n+1
		long result_2 = lcg_alg.genRand(); // n+2

		for (int i = 0; i < n; i++) {
			
			long result_3 = lcg_alg.genRand(); // n+3
			
			System.out.println("Result_0 =" + result_0);
			System.out.println("Result_1 =" + result_1);
			System.out.println("Result_2 =" + result_2);
			System.out.println("Result_3 =" + result_3);

			/* to ten algorytm opisany wy¿ej */
			long t_n = result_1 - result_0;
			long t_n1 = result_2 - result_1;
			long t_n2 = result_3 - result_2;
			
			long u_n = Math.abs(t_n2 * t_n - t_n1 * t_n1);
			u_array[i] = u_n;
			
			System.out.println("[i:" + i + "] calculated m = " + NWD(u_array));
			
			result_0 = result_1;
			result_1 = result_2;
			result_2 = result_3;
		}
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
