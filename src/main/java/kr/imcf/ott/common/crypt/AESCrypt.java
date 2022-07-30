/** 
* @fileName: AESCrypt.java 
* @author: rojae 
* @date:2022. 1. 18.
* @description: AES 기능 라이브러리
*/
package kr.imcf.ott.common.crypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {
	
	// AES키 16, 24, 32 바이트만 가능하다
	private final static int keySize = 32;
	private String key;
	private String iv;

	/**
	 * @methodName: AESCrypt 생성
	 * @author: rojae
	 * @date: 2022. 1. 18.
	 * @Description: Ket 랜덤하게 발생되는 32bytes, iv는 입력받자
	 */
	public AESCrypt(String key, String iv) {
		this.key = key;
		this.iv = iv;
	}

	public String getKey() {
		return this.key;
	}

	public String getIv() {
		return this.iv;
	}
	
	public String encrypt(String data) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(this.getIv().getBytes());

		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
		byte[] encrypted = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String decrypt(String encryptData, String aesKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(this.getIv().getBytes());

		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
		byte[] decodeBytes = Base64.getDecoder().decode(encryptData);
		byte[] decrypted = cipher.doFinal(decodeBytes);
		return new String(decrypted, "UTF-8");
	}
	
	public static String decrypt(String encryptData, String aesKey, String iv) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
		IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());

		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
		byte[] decodeBytes = Base64.getDecoder().decode(encryptData);
		byte[] decrypted = cipher.doFinal(decodeBytes);
		return new String(decrypted, StandardCharsets.UTF_8);
	}

}