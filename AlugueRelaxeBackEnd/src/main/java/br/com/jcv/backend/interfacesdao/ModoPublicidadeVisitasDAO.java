package br.com.jcv.backend.interfacesdao;

import java.util.Date;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ModoPublicidadeVisitasDAO<DTO> extends DAO<DTO> {
	void updateModoPublicidadeVisita(Date d, long idModoPublicidade) throws AlugueRelaxeException;
	DTO load(Date d, long idModoPublicidade) throws AlugueRelaxeException;
}
