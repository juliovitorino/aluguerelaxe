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
 * Criado em  : Mar 23, 2010
 *
 * Historico de Modifica��o:
 * =========================
 * Mar 23, 2010 - In�cio de tudo, por elmt
 *
 */
package br.com.jcv.backend.robot;

public class GenericRobot extends Robot {

	public GenericRobot() {
		super("Geny");
	}
	
	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		for (int i = 0; i < 1000; i++){
			if (this.getStatus().equals(CicloVidaRobot.ROBOT_ABORTADO)) {
				System.out.println(this.getNome() + " :: ABORTADO!");
				break;
			}
			System.out.println(this.getNome() + " - " + i);
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

