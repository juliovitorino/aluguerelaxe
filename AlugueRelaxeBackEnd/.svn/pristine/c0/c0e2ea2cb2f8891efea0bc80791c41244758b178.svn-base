#!/bin/bash -x

# delete-pommo-subscriber.sh
# Realiza a decomposicao do arquivo /home/aluguerelaxe/temp/ar-emails-invalidos.log
#
# 17 de Agosto de 2012
# (c) Julio Cesar Vitorino
#

LOG_HOME="/home/aluguerelaxe/temp/ar-emails-invalidos.log"
DATA_STR=`date +%Y%m%d-%H%M%S`
LOG_COMANDOS_SQL="/tmp/delete-subscriber-$DATA_STR.sql"
LOG=`cut -d - -f 4 $LOG_HOME | sed 's/subscriber/pommo_subscribers/g' > $LOG_COMANDOS_SQL`

LOG_MYSQL="/tmp/resultado-mysql-$DATA_STR.log"
mysql -ualuguerelaxe -pfork3t56 aluguerelaxeMailing < $LOG_COMANDOS_SQL 2> $LOG_MYSQL

ERRO_TAG=`cat $LOG_MYSQL | grep -i "You have an error in your SQL syntax"`
if [ ! -z "$ERRO_TAG" ]
then
   #envia e-mail
   tail -5 $LOG_MYSQL | mail -s "[venus] ERRO $LOG_HOME" julio.vitorino@ig.com.br 
   rm -f $LOG_COMANDOS_SQL $LOG_MYSQL
   exit 1
fi

rm -f $LOG_COMANDOS_SQL $LOG_MYSQL
mail -s "[venus] $0 $LOG_HOME" julio.vitorino@ig.com.br << EOF
   script shell: $0 processado com sucesso.
   log origem: $LOG_HOME
   script SQL temporario executado: $LOG_COMANDOS_SQL
EOF

exit 0

