/*
 * Diretos Reservados (c) 2006-2008 Petrobras - Petr�leo Brasileiro S.A.
 *
 * Este software � confidencial e a informa��o propriet�ria da
 * Petrobras - Petr�leo Brasileiro S.A.
 *
 * Projeto    : Sistema de Gerenciamento de Empreendimentos
 * Vers�o     : 4.0
 * Cliente    : Petrobras - Petr�leo Brasileiro S.A.
 * Fornecedor : Avanti Engenharia Ltda
 * Natureza   : GED
 * Tecnologia : Java
 * Criado em  : Jun 1, 2009
 *
 * Historico de Modifica��o:
 * =========================
 * Jun 1, 2009 - In�cio de tudo, por ELMT
 *
 */
package br.com.jcv.backend.criptografia;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESClient {

	/**
	 * TODO Comentar aqui.
	 *
	 * @param args Comentar aqui.
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		BaseFactorySecurity aes = BaseFactorySecurity.getInstance(1);
		BaseFactorySecurity sha1 = BaseFactorySecurity.getInstance(0);
		String token1 = aes.criptografar("Sabe que voce � muito linda !");
		String token2 = sha1.criptografar("Sabe que voce � muito linda !");
		String token3 = sha1.criptografar("EU te AMO");
		String token4 = sha1.criptografar("EU TE AMO");
		String token5 = sha1.criptografar("EU TE AMO");
		System.out.println("--- AES ---");
		System.out.println(token1);
		System.out.println("--- SHA-1 ---");
		System.out.println(token2);
		System.out.println(token3);
		System.out.println(token4);
		System.out.println(token5);
		
	}

}

