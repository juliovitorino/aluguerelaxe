#!/bin/sh
# mail-sender.sh
#
# Processar a fila de arquivos que precisam 
# ser enviados por email.
#
# formato do arquivo segue:
# <hash>.msg - <hash>.remetente - <hash>.destino - <hash>.assunto 
#
# Criado por Julio Vitorino, 28 de Janeiro de 2012
# (C) AlugueRelaxe, 2012.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
EMAILS_HOME="/var/aluguerelaxe/data/emails"
SEND_PASTA="$EMAILS_HOME/queue"
CONTROLE="/tmp/mail-sender.working"
ARQ_FORCAR_PAUSA="/tmp/mail-sender.lock"
ARQ_FORCAR_KILL="/tmp/mail-sender.kill"
CEL_ADMIN="2492753073"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "$0 em andamento. Somente e permitido um processo por vez."
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

banner INICIO

# processa toda a fila
for mailing in `ls $SEND_PASTA`; do

# verifica se existe arquivo de KILL forcado
	if [ -r $ARQ_FORCAR_KILL ] 
	then
		banner MORTO
		rm $ARQ_FORCAR_KILL
		rm $ARQ_FORCAR_PAUSA
		rm $CONTROLE
		echo "mail-sender kill: Morto em `date +%d/%m/%Y-%H:%M:%S`"
		mail -s "[venus] mail-sender morto `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $SEND_PASTA
Quantidade de arquivos fila: $QTD
Quantidade Emails Enviados.: $CONTADOR
EOF
		exit 1
	fi

# verifica se existe arquivo de pausa forcada
	if [ -r $ARQ_FORCAR_PAUSA ] 
	then
		banner PAUSA
		echo "email-sender lock: pausado em `date +%d/%m/%Y-%H:%M:%S` aguardando remocao de $ARQ_FORCAR_PAUSA"
		mail -s "[venus] email-sender pausado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $SEND_PASTA
Quantidade de arquivos fila: $QTD
Quantidade Emails Enviados.: $CONTADOR
EOF

		TRAVA=0
		while [ $TRAVA -eq 0 ] ; do
			sleep 60
			if [ ! -r $ARQ_FORCAR_PAUSA ] 
			then
				TRAVA=1
				banner LIBERADO
				echo "email-sender lock: liberado em `date +%d/%m/%Y-%H:%M:%S`"
				mail -s "[venus] email-sender liberado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $SEND_PASTA
Quantidade de arquivos fila: $QTD
Quantidade Emails Enviados.: $CONTADOR
EOF
			fi
		done
	fi

	# obtem o hash do arquivo
	HASH=`echo $mailing | cut -d . -f 1`
	EXTENSAO=`echo $mailing | cut -d . -f 2`
	
	if [ "$EXTENSAO" = "msg" ]
	then
		# obtem as informacoes
		email=`cat $SEND_PASTA/$HASH.remetente`
		destino=`cat $SEND_PASTA/$HASH.destino`
		assunto=`cat $SEND_PASTA/$HASH.assunto`
		
		echo "enviando email $HASH"
		mail -a "Content-type: text/html" -s "$assunto" $destino < $SEND_PASTA/$HASH.msg

		CONTADOR=$((CONTADOR+1))

		# remove arquivo da fila
		rm -rf $SEND_PASTA/$HASH.*

		# aguarda prazo de 60 segundos
		sleep 15
	fi
	
	
	# envia um checkpoint
	if [ `expr $CONTADOR % 1000` -eq 0 ]
	then
		gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
Checkpoint: $CONTADOR email(s) enviados de $QTD.
EOF

	fi

done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

# Checkpoint final
gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
email-sender finalizado: $CONTADOR email(s) enviados de $QTD.
EOF

#envia email
mail -s "[venus] email-sender finalizado" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Fila processada............: $SEND_PASTA
   Quantidade de arquivos fila: $QTD
   Quantidade Emails Enviados.: $CONTADOR
EOF

banner ACABOU
exit 0



