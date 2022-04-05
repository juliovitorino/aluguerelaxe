#!/bin/sh
# stop-cluster.sh
#
# Interromper o funcionamento de um determinado node do cluster tomcat
#
# $1 = nome do node
#
# Criado por Julio Vitorino, 10 de Agosto de 2013
# (C) AlugueRelaxe, 2013.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d%H%M%S`
TELEFONES_HOME="/var/aluguerelaxe/data/telefones"
SEND_PASTA="$TELEFONES_HOME/send"
VALIDATE_PASTA="$TELEFONES_HOME/validate"
CONTROLE="/tmp/stop-cluster.working"
PRG="shutdown.sh"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "Processo de shutdown de nodes do tomcat em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#verifica os parametros
if [ -z "$1" ]
then
	banner ERRO
	echo "Sintaxe do comando:"
	echo "# $0 <nome-node>"
	exit 1
fi		

TOMCAT_NODE="/opt/cluster/$1/apache-tomcat-6.0.37/bin"

#checar se o arquivo a ser processado existe e pode ser lido
if [ ! -r "$TOMCAT_NODE/$PRG" ]
then
	banner ERRO
    echo "Arquivo de configuracao $TOMCAT_NODE/$PRG nao encontrado ou acesso negado."
    exit 1
fi

cd $TOMCAT_NODE
./$PRG

#cria o arquivo de processo em andamento
touch $CONTROLE

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

banner ACABOU
exit 0


