public class LCG_alg {

	/* kod wziê³am bodaj¿e ze strony RossettaCode */
	private static long m = 65355; // to musimy znalezc
	private static long a = 1001;
	private static long c = 7;
	private static long seed = 999;

	// Calculate a*x mod m
	private long mult(long a, long x, long m) {
		long b, n, r;

		r = 0;
		n = 1;
		b = 1;
		while (n <= 64) {
			if ((a & b) != 0)
				r = (r + x) % m;
			x = (x + x) % m;
			b *= 2;
			n++;
		}

		return r;
	}

	public long genRand() {
		seed = (mult(a, seed, m) + c) % m;
		return seed;
	}

}