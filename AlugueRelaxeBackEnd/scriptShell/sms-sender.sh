#!/bin/sh
# sms-sender.sh
#
# Processar a fila de arquivos que precisam 
# ser enviados por SMS.
#
# formato do arquivo segue:
# <numero-do-celular>.<identifidor>
#
# conteudo do arquivo segue:
# texto a ser enviado por SMS
#
# Criado por Julio Vitorino, 14 de Dezembro de 2012
# (C) AlugueRelaxe, 2012.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
TELEFONES_HOME="/var/aluguerelaxe/data/telefones"
SEND_PASTA="$TELEFONES_HOME/send"
CONTROLE="/tmp/sms-sender.working"
ARQ_FORCAR_PAUSA="/tmp/sms-sender.lock"
CEL_ADMIN="2492753073"
MODEM="/dev/ttyUSB0"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "sms-sender em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#checar se tem arquivos na fila para processar
QTD=`ls $SEND_PASTA | wc -l`
if [ $QTD -eq 0 ]
then
	banner VAZIO
    echo "Fila de processamento vazia. Nada para fazer."
    exit 1
fi

#cria o arquivo de processo em andamento
touch $CONTROLE

CONTADOR=0
SMS_CONTROLE=0

banner INICIO

# processa toda a fila
for celular in `ls $SEND_PASTA`; do

	# se o processo ultrapassar as 7:00 p.m. ele sera interrompido
	HORA_EXPIRAR=`date +%H%M%S`
	if [ "$HORA_EXPIRAR" -gt "200000" ] 
	then 
		banner EXPIRADO
		echo "prazo de execucao expirou. Continua no proximo dia."
		rm -rf $CONTROLE

		# Checkpoint final
		gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
sms-sender interrompido. horario de execucao expirou em `date +%H:%M:%S`. Total de $CONTADOR SMS enviados de $QTD SMS.
EOF

	#envia email
	mail -s "[venus] sms-sender expirado" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Fila processada............: $SEND_PASTA
   Quantidade de arquivos fila: $QTD
   Quantidade de SMS Enviados.: $CONTADOR
EOF

		exit 1
	fi

# verifica se existe arquivo de pausa forcada
	if [ -r $ARQ_FORCAR_PAUSA ] 
	then
		banner PAUSA
		echo "sms-sender lock: pausado em `date +%d/%m/%Y-%H:%M:%S` aguardando remocao de $ARQ_FORCAR_PAUSA"
		mail -s "[venus] sms-sender pausado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $SEND_PASTA
Quantidade de arquivos fila: $QTD
Quantidade de SMS Enviados.: $CONTADOR
EOF

		TRAVA=0
		while [ $TRAVA -eq 0 ] ; do
			sleep 60
			if [ ! -r $ARQ_FORCAR_PAUSA ] 
			then
				TRAVA=1
				banner LIBERADO
				echo "sms-sender lock: liberado em `date +%d/%m/%Y-%H:%M:%S`"
				mail -s "[venus] sms-sender liberado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $SEND_PASTA
Quantidade de arquivos fila: $QTD
Quantidade de SMS Enviados.: $CONTADOR
EOF
			fi
		done
	fi

	NUMERO_CELULAR=`echo $celular | cut -d . -f 2`
	
#algoritmo de round-robin para SMS Center Number
	if [ $SMS_CONTROLE -eq 0 ] 
	then
		#SMS Center Number #1 da TIM
		SMSC="+553191938200"
		MODEM="/dev/ttyUSB0"
	fi
	if [ $SMS_CONTROLE -eq 1 ] 
	then
		#SMS Center Number #2 da TIM
		SMSC="+552181138200"
		MODEM="/dev/ttyUSB0"
	fi
	if [ $SMS_CONTROLE -eq 2 ] 
	then
		#SMS Center Number #3 da TIM
		SMSC="+551181138200"
		MODEM="/dev/ttyUSB0"
	fi

	echo "enviando sms celular $NUMERO_CELULAR"
	CMDENVIAR="`cat $SEND_PASTA/$celular | gsmsendsms -d $MODEM -C $SMSC -b 19200 $NUMERO_CELULAR`"

	CONTADOR=$((CONTADOR+1))
	
	# atualiza controlador do algoritmo de round robin
	SMS_CONTROLE=$((SMS_CONTROLE+1))
	if [ $SMS_CONTROLE -gt 2 ]
	then
		SMS_CONTROLE=0
	fi
	
	# envia um checkpoint
	if [ `expr $CONTADOR % 30` -eq 0 ]
	then
		gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
Checkpoint: $CONTADOR SMS enviados de $QTD.
EOF

	fi

	# remove arquivo da fila
	rm -rf $SEND_PASTA/$celular
	
	# aguarda prazo de 60 segundos
	sleep 60
	
done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

# Checkpoint final
gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
sms-sender finalizado: $CONTADOR SMS enviados de $QTD.
EOF

#envia email
mail -s "[venus] sms-sender finalizado" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Fila processada............: $SEND_PASTA
   Quantidade de arquivos fila: $QTD
   Quantidade de SMS Enviados.: $CONTADOR
EOF

banner ACABOU
exit 0



