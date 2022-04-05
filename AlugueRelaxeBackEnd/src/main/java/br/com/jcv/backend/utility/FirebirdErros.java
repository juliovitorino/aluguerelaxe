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
 * Criado em  : Mar 29, 2010
 *
 * Historico de Modifica��o:
 * =========================
 * Mar 29, 2010 - In�cio de tudo, por elmt
 *
 */
package br.com.jcv.backend.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe com erros do Firebird 2.1
 *
 * @author elmt
 *
 */
public class FirebirdErros {
	
	private final Map<Integer,FirebirdRecord> mpFBErros; 

	private static FirebirdErros instance = null;
	
	private FirebirdErros(){
		mpFBErros = new HashMap<Integer, FirebirdRecord>();
		
		
	}
	
	public static FirebirdErros getInstance(){
		if (instance == null){
			synchronized (FirebirdErros.class) {
				if (instance == null) {
					instance = new FirebirdErros();
				}
			}
		}
		return instance;
	}
}

