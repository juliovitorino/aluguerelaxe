#!/bin/sh
DATA=`date +%Y%m%d%H%M`
#export DATA

PATH_BKP=/var/comum/backup/
PATH_BKP_HIST=/var/comum/backup-hist/
PATH_BD_EVENTLOG=mail.aluguerelaxe.com.br:/var/lib/firebird/2.5/data/producao/eventlog.fdb
ARQ_OUTPUT_EVENTLOG=bkp-prdbd-eventlog.${DATA}.fbk
PATH_OUTPUT_EVENTLOG=${PATH_BKP}${ARQ_OUTPUT_EVENTLOG}

PATH_BD_ALUGUERELAXE=firebird.aluguerelaxe.com.br:/firebird/aluguerelaxe.gdb
ARQ_OUTPUT_ALUGUERELAXE=bkp-prdbd-aluguerelaxe.${DATA}.fbk
PATH_OUTPUT_ALUGUERELAXE=${PATH_BKP}${ARQ_OUTPUT_ALUGUERELAXE}

#export PATH_BD_EVENTLOG
#export PATH_BD_ALUGUERELAXE
#export PATH_OUTPUT_EVENTLOG
#export PATH_OUTPUT_ALUGUERELAXE

cd ${PATH_BKP}

#realiza o backup dos bancos de dados de producao
gbak -B -V -user ALUGUERELAXE -pas fork3t56 $PATH_BD_EVENTLOG $PATH_OUTPUT_EVENTLOG
gbak -B -V -user ALUGUERELAXE -pas fork3t56 $PATH_BD_ALUGUERELAXE $PATH_OUTPUT_ALUGUERELAXE

#realiza o syncronismo com servidor da kinghost usando ssh
rsync -Cavz -e ssh aluguerelaxe@aluguerelaxe.com.br:/home/aluguerelaxe/www/stream/ /var/comum/backup/stream/

#empacota o backup
tar -czvf bkp-prdbd-aluguerelaxe.${DATA}.tar.gz $ARQ_OUTPUT_ALUGUERELAXE $ARQ_OUTPUT_EVENTLOG stream/

#move o pacote pacote para o historico
mv bkp-prdbd-aluguerelaxe.${DATA}.tar.gz $PATH_BKP_HIST

#remove os temporarios gerados
rm $PATH_OUTPUT_EVENTLOG
rm $PATH_OUTPUT_ALUGUERELAXE
