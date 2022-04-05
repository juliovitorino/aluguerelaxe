package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface AssinantesDAO<DTO> extends DAO<DTO> {
	
	DTO loadHash(String hash) throws AlugueRelaxeException;
	void updateStatusPendenteParaAtivo(String hash) throws AlugueRelaxeException;
	DTO load(DTO dto, String campanha) throws AlugueRelaxeException;
	DTO load(DTO dto, String campanha, long parent) throws AlugueRelaxeException;
	List<DTO> list(String campanha, String status) throws AlugueRelaxeException;
}