package br.com.jcv.backend.chain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.factory.DAOFactory;

public abstract class AbstractChain {
	
	private DAOFactory daofactory;
	
	public void setContextDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	public DAOFactory getContextDAO(){
		return this.daofactory;
	}
	
	public static String geraSHA1(String str) {
		// Aplica algoritimo SHA1 na string informada
		String strsha1 = "";
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			strsha1 = bfs.criptografar(str);
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}	
		return strsha1;
	}

	
}