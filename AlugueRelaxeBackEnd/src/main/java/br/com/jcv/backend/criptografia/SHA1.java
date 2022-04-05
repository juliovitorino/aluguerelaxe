package br.com.jcv.backend.criptografia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe com a implementação do algoritmo de encriptação SHA1
 *
 * @author eii5
 *
 */
public final class SHA1 {

	/**
	 * Retorna a criptografia do dado utilizando o algoritmo SHA1
	 *
	 * @param s -
	 * @return - Retorna um objeto do tipo String
	 * @throws NoSuchAlgorithmException 
	 * @throws SigemException
	 *             Comentar aqui.
	 */
	public String getHashCode(final String s) throws NoSuchAlgorithmException {
		int i;
		MessageDigest md;

		md = MessageDigest.getInstance("SHA1");
			byte[] digestBits = null;

			for (i = 0; i < s.length(); i++) {
				md.update((byte) s.charAt(i));
			}

			digestBits = md.digest();

			return this.bytetoHex(digestBits, 20).toUpperCase();
	}

	/**
	 * Metodo retorna o dado contido no ImputStream criptografado.
	 *
	 * @param is -
	 * @return Retorna um objeto do tipo String
	 */
	public String getHashCode(final InputStream is) {
		return ("not implemented");
	}
	

	public String getHashCode(final FileInputStream fis) throws NoSuchAlgorithmException, IOException {
		
		MessageDigest md = MessageDigest.getInstance("SHA1");
		byte[] digestBits = null;
		
	    byte[] dataBytes = new byte[32768];
		int nread = fis.read(dataBytes);
		while (nread > 0) {
		  md.update(dataBytes, 0, nread);
		  nread = fis.read(dataBytes);
		};		
		
        fis.close();

		digestBits = md.digest();
		
		return Miscellaneous.bytetoHex(digestBits, 20).toUpperCase();

	}
	
	public final static String bytetoHex(final byte[] digestBits, final int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char c1, c2;

			c1 = (char) ((digestBits[i] >>> 4) & 0xf);
			c2 = (char) (digestBits[i] & 0xf);
			c1 = (char) ((c1 > 9) ? 'a' + (c1 - 10) : '0' + c1);
			c2 = (char) ((c2 > 9) ? 'a' + (c2 - 10) : '0' + c2);
			sb.append(c1);
			sb.append(c2);
		}
		return sb.toString();
	}

}
