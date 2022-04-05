#!/bin/sh
# crawler.sh
#
# Processar a fila de href definida em QUEUE_PASTA.
#
# formato do arquivo segue:
# arquivo.href - O arquivo precisa ter uma url definida com seu FQDN. 
# ex: http://www.olx.com.br/aluguel-por-temporada-cat-388-p-2
#
# Criado por Julio Vitorino, 01 de Fevereiro de 2013
# (C) AlugueRelaxe, 2013.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
CRAWLER_HOME="/var/aluguerelaxe/data/crawler"
QUEUE_PASTA="$CRAWLER_HOME/queue"
PROCESSED_PASTA="$CRAWLER_HOME/processed"
CONTROLE="/tmp/crawler.working"
ARQ_FORCAR_PAUSA="/tmp/crawler.lock"
ARQ_FORCAR_KILL="/tmp/crawler.kill"
CEL_ADMIN="2492753073"
HREFTMP="/tmp/hrefs.tmp"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "$0 em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#checar se tem arquivos na fila para processar
QTD=`ls $QUEUE_PASTA | wc -l`
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
for item in `ls $QUEUE_PASTA`; do

	if [ -r $ARQ_FORCAR_KILL ] 
	then
		banner MORTO
		rm $ARQ_FORCAR_PAUSA $ARQ_FORCAR_KILL $CONTROLE
		
		echo "crawler morto em `date +%d/%m/%Y-%H:%M:%S`"
		mail -s "[venus] crawler morto `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $QUEUE_PASTA
Quantidade de arquivos fila: $QTD
Quantidade Emails Enviados.: $CONTADOR
EOF
		exit 1
	fi
	
# verifica se existe arquivo de pausa forcada
	if [ -r $ARQ_FORCAR_PAUSA ] 
	then
		banner PAUSA
		echo "crawler lock: pausado em `date +%d/%m/%Y-%H:%M:%S` aguardando remocao de $ARQ_FORCAR_PAUSA"
		mail -s "[venus] crawler pausado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $QUEUE_PASTA
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
				echo "crawler lock: liberado em `date +%d/%m/%Y-%H:%M:%S`"
				mail -s "[venus] crawler liberado `date +%d/%m/%Y-%H:%M:%S`" julio.vitorino@ig.com.br << EOF
Processo iniciado..........: $DATA_START
Arquivo de controle thread.: $CONTROLE
Shell script...............: $0
Fila processada............: $QUEUE_PASTA
Quantidade de arquivos fila: $QTD
Quantidade Emails Enviados.: $CONTADOR
EOF
			fi
		done
	fi
	
	#o processo consistem em:
	#1) obter a url dentro do arquivo
	#2) ler a URL via internet e filtrar todos os hrefs enviando-os para um arquivo
	#3) tratar cada href e gravar em arquivo com hash um por um
	for hrefitem in `cat $QUEUE_PASTA/$item`; do
		URL="$hrefitem"
	done
	
	echo "executando crawler de tag href na URL $URL ..."
	CMD=`curl $URL | grep -oEi "(http|https)\://[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\-\._\?\,/\\\+&%\$#\=~\-])*" > $HREFTMP`
	
	for href in `cat $HREFTMP`; do
		ARQUIVO=`echo "$href" | md5sum | cut -d " " -f 1`
		echo $href > $QUEUE_PASTA/$ARQUIVO
	done
	
	rm -rf $HREFTMP

	CONTADOR=$((CONTADOR+1))
	
	# move arquivo da fila para pasta de processados
	# liberando a URL para o processo cel-catcher
	mv $QUEUE_PASTA/$item $PROCESSED_PASTA
done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

# Checkpoint final
gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 $CEL_ADMIN << EOF
crawler finalizado: $CONTADOR hrefs processados de $QTD.
EOF

#envia email
mail -s "[venus] crawler finalizado" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Fila processada............: $QUEUE_PASTA
   Quantidade de arquivos fila: $QTD
   Quantidade Emails Enviados.: $CONTADOR
EOF

banner ACABOU
exit 0
