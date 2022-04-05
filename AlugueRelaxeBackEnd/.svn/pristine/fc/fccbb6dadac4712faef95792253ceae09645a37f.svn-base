package br.com.jcv.backend.criptografia;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * Classe com a implementação do algoritmo de encriptação AES
 *
 * @author eii5
 *
 */
public class AES {

	/**
	 * Cria o obejto Cipher especificando o algoritmo de encriptação AES
	 *
	 * Atributo '<code>aesCipher</code>' do tipo Cipher
	 */
	private Cipher aesCipher = null;

	/**
	 * Atributo '<code>secretKey</code>' do tipo SecretKey
	 * "09CC89EA96661E8D34EA3CE2F1928C8E";
	 */
	private SecretKey secretKey = null;

	/**
	 * Construtor para esta classe.
	 *
	 * @throws SigemException -
	 */
	public AES() throws NoSuchAlgorithmException, NoSuchPaddingException {

		this.aesCipher = Cipher.getInstance("AES");

	}

	/**
	 * Retorna a Chave para criptografia AES
	 *
	 * @return - Retorna um objeto do tipo SecretKey
	 * @throws SigemException Comentar aqui.
	 */
	private final SecretKey getChave() {
		String strHexkey = null;

			if (this.secretKey == null) {

				strHexkey = "2F15714947D9B8590C07ADD6AF7F679A";

				// Criando o objeto chave do tipo SecretKey
				this.secretKey = new SecretKeySpec(HexBin.decode(strHexkey),"AES");
			}
			return this.secretKey;
	}

	/**
	 * Gera uma nova chave em hexadecimal
	 *
	 * @return Retorna um objeto do tipo String
	 * @throws NoSuchAlgorithmException -
	 */
	public final String gerarChave() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = null;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			return HexBin.encode(keyGen.generateKey().getEncoded());
		} catch (NoSuchAlgorithmException noSuchAlgo) {
			// System.out.println(" Algoritmo inexistente " + noSuchAlgo);
			throw noSuchAlgo;
		} finally {
			keyGen = null;
		}

	}

	/**
	 * Decriptogra o dado
	 *
	 * @param strDado -
	 *            Parametro é o dado a ser criptografado
	 * @return Retorna um objeto do tipo String
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws SigemException -
	 */
	public final String decriptografar(final String strDado) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		// Inicializando o Cipher para decriptação
			this.aesCipher.init(Cipher.DECRYPT_MODE, this.getChave());

			// Decriptar o Decrypt the cipher bytes using doFinal method
			byte[] byteDataToEncrypt = HexBin.decode(strDado);
			byte[] byteDecryptedText;
			byteDecryptedText = this.aesCipher.doFinal(byteDataToEncrypt);

			return new String(byteDecryptedText);
	}

	/**
	 * Criptografa o dado
	 *
	 * @param strDado -
	 * @return Retorna um objeto do tipo String
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws SigemException -
	 */
	public final String criptografar(final String strDado) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		byte[] byteCipherText = null;

			// Inicializando o Cipher para encriptação
			this.aesCipher.init(Cipher.ENCRYPT_MODE, this.getChave());

			// Converte o dado para vetor de Bytes
			byte[] byteDataToEncrypt = strDado.getBytes();

			// Criptografa o vetor de bytes usando o metofo doFinal
			byteCipherText = this.aesCipher.doFinal(byteDataToEncrypt);

			return HexBin.encode(byteCipherText);

	}

}
