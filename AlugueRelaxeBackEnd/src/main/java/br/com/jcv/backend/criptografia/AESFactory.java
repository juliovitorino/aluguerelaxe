/*
 * Created on 03/02/2006
 *
 */
package br.com.jcv.backend.criptografia;

import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author elmt
 *
 */
public class AESFactory extends BaseFactorySecurity {


	/**
	 * Método sobrescrito.
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @see br.com.petrobras.sigem.security.BaseFactorySecurity#criptografar(java.lang.String)
	 */
	public final String criptografar(final String dado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		AES aes = new AES();
		return aes.criptografar(dado);
	}

	/**
	 * Método sobrescrito.
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @see br.com.petrobras.sigem.security.BaseFactorySecurity#decriptografar(java.lang.String)
	 */
	public String decriptografar(String dado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		AES aes = new AES();
		return aes.decriptografar(dado);
	}

	@Override
	public String criptografar(FileInputStream file) {
		// TODO Auto-generated method stub
		return null;
	}

}
