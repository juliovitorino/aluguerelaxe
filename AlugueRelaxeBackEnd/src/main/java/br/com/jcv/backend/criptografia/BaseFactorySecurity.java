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
public abstract class BaseFactorySecurity {

	public static final int AES = 1;
	public static final int SHA1 = 0;
	
	/**
	 * Construtor para esta classe.
	 *
	 */
	public BaseFactorySecurity() {
	}

	/**
	 * Método utilizado para criptografar a String informada.
	 *
	 * @param dado String a ser criptografa.
	 * @return a String criptografada.
	 * @throws SigemException Caso ocorra algum problema durante a operação.
	 */
	public abstract String criptografar(String dado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * Método utilizado para criptografar um arquivo.
	 *
	 * @param file Arquivo a ser criptografo.
	 * @return a String criptografada.
	 * @throws SigemException Caso ocorra algum problema durante a operação.
	 */
	public abstract String criptografar(FileInputStream file);

	/**
	 * Método utilizado para decriptografar a String informada.
	 *
	 * @param dado String a ser decriptada.
	 * @return A String decriptada
	 * @throws SigemException Caso ocorra algum problema durante a operação.
	 */
	public abstract String decriptografar(String dado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * @param factorytype Tipo de criptografia a ser utilizada.
	 * @return Caso ocorra algum problema durante a operação
	 */
	public static BaseFactorySecurity getInstance(int idFactoryCriptografia) {
		if (idFactoryCriptografia == AES) {
			return new AESFactory();
		} else {
			return new SHA1Factory();
		}
	}

}

