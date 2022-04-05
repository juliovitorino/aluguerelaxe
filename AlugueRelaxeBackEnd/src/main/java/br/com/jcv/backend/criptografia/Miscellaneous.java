/**
 * Diretos Reservados (c) 2006-2008 Petrobras - Petr�leo Brasileiro S.A.
 *
 * Este software � confidencial e a informa��o propriet�ria da
 *  Petrobras - Petr�leo Brasileiro S.A.
 *
 * Projeto    : Sistema de Gerenciamento de Empreendimentos
 * Vers�o     : 4.0
 * Cliente    : Petrobras - Petr�leo Brasileiro S.A.
 * Fornecedor : Avanti Engenharia Ltda
 * Natureza   : GED
 * Tecnologia : J2EE 1.4
 * Criado em  : 03/02/2006
 *
 * Historico de Modifica��o:
 * =========================
 * 03/02/2006 - In�cio de tudo, por J�lio Vitorino
 *
 */

package br.com.jcv.backend.criptografia;
/**
 * Classe de Utilit�ria
 * 
 * @author J�lio Cesar Vitorino
 */
public final class Miscellaneous {
	/**
	 * TODO Comentar aqui.
	 * 
	 * @param digestBits
	 * @param len
	 * @return Comentar aqui.
	 */
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