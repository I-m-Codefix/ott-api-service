package kr.imcf.ott.common.crypt;

public class RSAKeyObject {
	
	private final String rsaPublicKey;
	private final String rsaPrivateKey;
	
	public RSAKeyObject(String rsaPublicKey, String rsaPrivateKey) {
		this.rsaPublicKey = rsaPublicKey;
		this.rsaPrivateKey = rsaPrivateKey;
	}
	
	public String getRsaPublicKey() {
		return rsaPublicKey;
	}
	public String getRsaPrivateKey() {
		return rsaPrivateKey;
	}

}