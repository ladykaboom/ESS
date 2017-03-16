package embedded;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;

public class EllipticCurve {

	private static KeyPair pair;

	private static final String param_p = "A9FB57DBA1EEA9BC3E660A909D838D726E3BF623D52620282013481D1F6E5377";
	private static final String param_q = "A9FB57DBA1EEA9BC3E660A909D838D718C397AA3B561A6F7901E0E82974856A7";
	private static final String param_A = "7D5A0975FC2C3057EEF67530417AFFE7FB8055C126DC5C6CE94A4B44F330B5D9";
	private static final String param_B = "26DC5C6CE94A4B44F330B5D9BBD77CBF958416295CF7E1CE6BCCDC18FF8C07B6";

	private static final String param_x = "8BD2AEB9CB7E57CB2C4B482FFC81B7AFB9DE27E1E3BD23C23A4453BD9ACE3262";
	private static final String param_y = "547EF835C3DAC4FD97F8461A14611DC9C27745132DED8E545C1D54C72F046997";
	private static final String param_h = "1";

	public static void generateKeyPair() {

		ECCurve curve = new ECCurve.Fp(
				new BigInteger(param_p, 16), // p
				new BigInteger(param_A, 16), // a
				new BigInteger(param_B, 16)); // b

		ECParameterSpec ecSpec = new ECParameterSpec(curve,
				curve.createPoint(new BigInteger(param_x,16), new BigInteger(param_y,16)), // G - (x,y)
				new BigInteger(param_q,16), //q
				new BigInteger(param_h)); //h

		KeyPairGenerator g = null;
		try {		
			g = KeyPairGenerator.getInstance("ECDSA", "BC");
			g.initialize(ecSpec, new SecureRandom());
			pair = g.generateKeyPair();

		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		generateKeyPair();
		System.out.println(pair.getPublic());

	}
}
