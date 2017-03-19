public class LCG_alg {

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

	public long guessRand(long a2, long seed2, long c2, long m2) {
		return (mult(a2, seed2, m2) + c2) % m2;
	}
}