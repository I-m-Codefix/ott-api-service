package kr.imcf.ott.common.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CipherUtil {

	/**
	 * Base64로 해싱된 RSA PublicKey 변환
	 */
	public static PublicKey getPublicKeyFromB64(final String keyString)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String publicKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
		return keyFactory.generatePublic(keySpecX509);
	}

	
	/**
	 * Base64로 해싱된 RSA PrivateKey 변환
	 */
	public static PrivateKey getPrivateKeyFromB64(final String keyString)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String privateKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
		return keyFactory.generatePrivate(keySpecPKCS8);
	}

}