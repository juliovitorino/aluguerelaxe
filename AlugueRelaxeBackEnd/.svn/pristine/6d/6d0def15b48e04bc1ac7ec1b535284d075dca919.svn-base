#!/bin/bash

#
# Script para criar a mailing list para um determinado ClientSpammer atraves de uma SQL.
#
# banco de dados a sofrer a pesquisa /var/aluguerelaxe/data/fb/aluguerelaxe.fdb
# diretorio destino do arquivo de mailing /home/aluguerelaxe/spammer/mailing/
#
# Parametros:
# $1 = nome do script SQL que sera executado no firebird
# $2 = nome do arquivo mailing de saida para ser usado no processo do ClientSpammer. Sem extensao ".xml"
#
#
# Exemplo de configuracao na cron 
# 10 7 * 3 * /home/aluguerelaxe/Robots/criarMailingList.sh aniversariantes-mar.sql mailing-niver-mar
# 10 7 * 8 * /home/aluguerelaxe/Robots/criarMailingList.sh aniversariantes-ago.sql mailing-niver-ago
# 10 7 * * * /home/aluguerelaxe/Robots/criarMailingList.sh clientes-ativos-ar.sql  mailing-anunciantes-ativos
#
# 16 de Agosto de 2012
# (c) Julio Cesar Vitorino
#

DATA_START=`date +%d/%m/%Y-%H:%M:%S`

#checar se parametro foi informado vazio
if [ -z "$1" ]
then
    echo "$0: sintaxe: $0 <scriptSQL> <mailing-saida>"
    exit 1
fi

if [ -z "$2" ]
then
    echo "$0: sintaxe: $0 <scriptSQL> <mailing-saida>"
    exit 1
fi

SCRIPT_SQL="/home/aluguerelaxe/scriptSQL/$1"

#checar se o arquivo SQL existe e pode ser lido
if [ ! -r "$SCRIPT_SQL" ]
then
    echo "Script SQL $SCRIPT_SQL nao encontrado ou acesso negado."
    exit 1
fi

#mailing list de saida
MAILING_LIST="/home/aluguerelaxe/spammer/mailing/$2.xml"

#variaveis
DATA_STR=`date +%Y%m%d-%H%M%S`
TEMP_FILE="/tmp/qr-$2-$DATA_STR"
HEADER_XML='<?xml version="1.0" encoding="ISO-8859-1"?>'
MAILING_START_TAG="<mailing-list>"
MAILING_END_TAG="</mailing-list>"

#executa a query de forma interativa por linha de comando
isql-fb -q -o $TEMP_FILE -i $SCRIPT_SQL

#monta arquivo final definido em ${MAILING_LIST}
echo $HEADER_XML > ${MAILING_LIST}
echo $MAILING_START_TAG >> ${MAILING_LIST}
cat $TEMP_FILE | grep "mailing-record" >> ${MAILING_LIST}
echo $MAILING_END_TAG >> ${MAILING_LIST}

#obtem o total de linhas processadas na SQL
TOTAL_LINHAS=`cat $TEMP_FILE | grep "<mailing-record>" | wc -l`

#remove temporario
rm -f $TEMP_FILE

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

#envia email
mail -s "[venus] Criacao de mailing $2" julio.vitorino@ig.com.br << EOF
   Mailing criado com sucesso em $MAILING_LIST
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Shell script...............: $0
   Script SQL processado......: /home/aluguerelaxe/scriptSQL/$1
   Total de linhas processadas: $TOTAL_LINHAS
EOF

exit 0

