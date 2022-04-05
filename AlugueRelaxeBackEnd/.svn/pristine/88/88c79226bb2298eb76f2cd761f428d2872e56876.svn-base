#!/bin/sh
# deploy-arweb-cluster.sh
#
# realizar o deploy do arquivo arweb dentro dos nodes ativos do cluster.
#
# Criado por Julio Vitorino, 10 de Agosto de 2013
# (C) AlugueRelaxe, 2013.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
CLUSTER_HOME="/opt/cluster"
TOMCAT_CLUSTER="apache-tomcat-6.0.37"
NODES_ATVOS="$CLUSTER_HOME/nodes-ativos.dat"
TARGET_WAR="arweb.war"
SOURCE_WAR="/var/comum/"
STOP_NODE_CLUSTER="/home/aluguerelaxe/scriptShell/stop-cluster.sh"
START_NODE_CLUSTER="/home/aluguerelaxe/scriptShell/start-cluster.sh"

CONTROLE="/tmp/deploy-arweb-cluster.working"
ARQ_FORCAR_PAUSA="/tmp/deploy-arweb-cluster.lock"
ARQ_FORCAR_KILL="/tmp/deploy-arweb-cluster.kill"
CEL_ADMIN="2492753073"
HREFTMP="/tmp/hrefs.tmp"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "$0 em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#verifica os parametros
if [ -z "$1" ]
then
	banner ERRO
	echo "Sintaxe do comando:"
	echo "# $0 <nome-arquivo-war>"
	exit 1
fi		

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ ! -r "$SOURCE_WAR/$1" ]
then
	banner ERRO
    echo "Arquivo $SOURCE_WAR/$1 nao encontrado para deploy nos nodes do cluster."
    exit 1
fi

#checar se o arquivo de nodes se encontra no diretorio
if [ ! -r "$NODES_ATVOS" ]
then
	banner ERRO
    echo "Arquivo com a lista de nodes nao encontrado em $NODES_ATVOS."
    exit 1
fi

echo "catalogo com nodes ativos em $NODES_ATVOS"

#checar se tem arquivos na fila para processar
QTD=`cat $NODES_ATVOS | wc -l`
if [ $QTD -eq 0 ]
then
	  banner VAZIO
    echo "Fila de nodes vazia. Nada para fazer."
    exit 1
fi


#cria o arquivo de processo em andamento
touch $CONTROLE

CONTADOR=0

banner INICIO

echo "paralizando nodes ..."
for node in `cat $NODES_ATVOS`; do
    echo "parando $node ..."
		CMD=`$STOP_NODE_CLUSTER $node`
done

for node in `cat $NODES_ATVOS`; do
    echo "realizando deploy em $node ..."
		WEBAPPS_NODE="$CLUSTER_HOME/$node/$TOMCAT_CLUSTER/webapps"
		
		#checar se o contexto arweb existe
    if [ -r "$WEBAPPS_NODE/arweb" ]
    then
      rm -rf $WEBAPPS_NODE/arweb
    fi

		rm $WEBAPPS_NODE/$TARGET_WAR
		cp $SOURCE_WAR/$1 $WEBAPPS_NODE/$TARGET_WAR
done

echo "Iniciando nodes ..."
for node in `cat $NODES_ATVOS`; do
    echo "dando partida em $node ..."
		CMD=`$START_NODE_CLUSTER $node`
done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

banner ACABOU
exit 0
