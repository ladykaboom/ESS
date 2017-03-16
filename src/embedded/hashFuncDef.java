package embedded;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;

public class hashFuncDef {

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private final static int sha1 = 0x01;
	private final static int sha256 = 0x04;
	private final static int sha512 = 0x06;

	public static byte[] hash(byte[] in, int fun) {

		Digest alg;
		if (fun == sha1) {
			alg = new SHA1Digest();
		} else if (fun == sha256) {
			alg = new SHA256Digest();
		} else if (fun == sha512) {
			alg = new SHA512Digest();
		} else {
			System.out.println("Wrong parameter - exit");
			return null;
		}

		alg.update(in, 0, in.length);
		byte[] result = new byte[alg.getDigestSize()];
		alg.doFinal(result, 0);

		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		int fun = 0x04;

		byte[] input = "alamakota".getBytes("UTF-8");
		byte[] output = hash(input, fun);
		
		System.out.print(bytesToHex(output));

	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
