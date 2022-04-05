/*
 * Created on 03/02/2006
 *
 */
package br.com.jcv.backend.criptografia;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author elmt
 *
 */
public class SHA1Factory extends BaseFactorySecurity {


	/**
	 * Método sobrescrito.
	 * @throws NoSuchAlgorithmException 
	 * @see br.com.petrobras.sigem.security.BaseFactorySecurity#criptografar(java.lang.String)
	 */
	public String criptografar(String keyblend) throws NoSuchAlgorithmException {
		SHA1 sha1 = new SHA1();
		return sha1.getHashCode(keyblend);
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.petrobras.sigem.security.BaseFactorySecurity#criptografar(java.lang.String)
	 */
	public String criptografar(FileInputStream file) {
		SHA1 sha1 = new SHA1();
		String result = new String();
		try {
			result = sha1.getHashCode(file);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.petrobras.sigem.security.BaseFactorySecurity#decriptografar(java.lang.String)
	 */
	public String decriptografar(String dado) {
		return null;
	}

}
