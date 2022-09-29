package kr.imcf.ott.common.crypt;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.imcf.ott.common.util.CipherUtil;

public class RSACrypt {

	public static KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        return keyGen.genKeyPair();
    }
    
	public static RSAKeyObject generateRsaKeyPair() throws NoSuchAlgorithmException {
		KeyPair keyPair = keyPair();

		byte[] publicKey = keyPair.getPublic().getEncoded();
		byte[] privateKey = keyPair.getPrivate().getEncoded();

		String rsaPublicKeyBase64 = new String(Base64.getEncoder().encode(publicKey));
		String rsaPrivateKeyBase64 = new String(Base64.getEncoder().encode(privateKey));

		return new RSAKeyObject(rsaPublicKeyBase64, rsaPrivateKeyBase64);
	}
	
	public static String encryptRSA(String plainText, RSAKeyObject RsaKeyObject) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
		Cipher cipher = Cipher.getInstance("RSA");
		PublicKey publicKey = CipherUtil.getPublicKeyFromB64(RsaKeyObject.getRsaPublicKey());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytePlain = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(bytePlain);
	}

	public static String decryptRSA(String encrytData, RSAKeyObject RsaKeyObject) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] byteEncrypted = Base64.getDecoder().decode(encrytData.getBytes());
		PrivateKey privateKey = CipherUtil.getPrivateKeyFromB64(RsaKeyObject.getRsaPrivateKey());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytePlain = cipher.doFinal(byteEncrypted);
		return new String(bytePlain, StandardCharsets.UTF_8);
	}

}