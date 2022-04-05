/******************************************************************/
/* Autor: Julio Cesar Vitorino                                    */
/* criado em 09 de Agosto de 2012                                 */
/*                                                                */
/******************************************************************/
/* SQL Manager 2008 Lite for InterBase and Firebird 5.1.0.5       */
/* -------------------------------------------------------------- */
/* Database : eventlog.fdb                                         */
/******************************************************************/


CONNECT "/var/aluguerelaxe/data/fb/eventlog.fdb" user 'aluguerelaxe' password 'fork3t56';

/* EMITE UMA LISTA DE PROCESSOS QUE ESTAO RODANDO NESTE MOMENTO */
select EVRT_CD_TAREFA "pid",
	EVRT_NM_ROBO "Robot",
	EVRT_TX_TAREFA "Tarefa",
	EVRT_DT_EVENTO "Iniciou",
	EVRT_DT_ATUALIZACAO "Atualizacao",
	EVRT_TX_MSG "Msg"
from EVENTOS_ROBOTS_TASKLIST
where EVRT_IN_STATUS = 'W';

commit;
exit;

