#!/bin/sh -x
# disk-free.sh
#
# Verifica o espaco em disco de cada filesystem
#
# Criado por Julio Vitorino, 14 de Novembro de 2014
# (C) AlugueRelaxe, 2014.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
CONTROLE="/tmp/disk-free.working"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "disk-free em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#cria o arquivo de processo em andamento
touch $CONTROLE

CONTADOR=0
SMS_CONTROLE=0

banner INICIO

# processa toda pontos de montagem a partir de 10%

for celular in `df -kh | grep -e "[0-9][0-9]%" | tr -s ' ' | sed -e 's/ /:/g'`; do

  ESPACO_USADO=`echo $celular | cut -d "%" -f1 | cut -d ":" -f5`
  DEVICE=`echo $celular | cut -d ":" -f1`
  PM=`echo $celular | cut -d ":" -f6`

	# se o processo ultrapassar as 7:00 p.m. ele sera interrompido
	HORA_EXPIRAR=`date +%H%M%S`
	if [ "$ESPACO_USADO" -gt "80" ] 
	then 
		banner PERIGO

	#envia email
	mail -s "[venus] disk-free ALERTA em $DEVICE ($DATA_START)" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Device.....................: $DEVICE
   Ponto de Montagem..........: $PM
   Espaco ja utilizado........: $ESPACO_USADO%
   df -kh resultado completo..: $celular
EOF

	fi
	
done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

banner ACABOU
exit 0



