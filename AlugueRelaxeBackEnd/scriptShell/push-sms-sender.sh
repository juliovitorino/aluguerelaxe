#!/bin/sh
# push-sms-sender.sh
#
# Gerar os arquivos de campanha que entrarãna fila para envio 
# de SMS. 
#
# $1 = Arquivo com celulares validos para ser processado.
#      E mandatorio este arquivo estar na pasta apontada
#      pela variavel $VALIDATE_PASTA.
# $2 = String da mensagem a ser enviada
#
# Os arquivos serao criados na pasta apontada por $SEND_PASTA
#
# Criado por Julio Vitorino, 14 de Dezembro de 2012
# (C) AlugueRelaxe, 2012.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d%H%M%S`
TELEFONES_HOME="/var/aluguerelaxe/data/telefones"
SEND_PASTA="$TELEFONES_HOME/send"
VALIDATE_PASTA="$TELEFONES_HOME/validate"
CONTROLE="/tmp/push-sms-sender.working"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "Processo de geracao de campanha de celular em andamento. Somente e permitido um processo por vez."
    exit 1
fi

#verifica os parametros
if [ -z "$1" ]
then
	banner ERRO
	echo "Sintaxe do comando:"
	echo "# ./push-sms-sender.sh <arquivo-de-telefones> <mensagem-sms>"
	exit 1
fi		

if [ -z "$2" ]
then
	banner ERRO
	echo "Sintaxe do comando:"
	echo "# ./push-sms-sender.sh <arquivo-de-telefones> <mensagem-sms>"
	exit 1
fi		

#checar se o arquivo a ser processado existe e pode ser lido
if [ ! -r "$VALIDATE_PASTA/$1" ]
then
	banner ERRO
    echo "Arquivo de configuracao $VALIDATE_PASTA/$1 nao encontrado ou acesso negado."
    exit 1
fi

echo "Gerar os arquivos de campanha que entrarãna fila para envio de SMS. "
echo " $1 = Arquivo com celulares validos para ser processado."
echo "      E mandatorio este arquivo estar na pasta apontada"
echo "      pela variavel $VALIDATE_PASTA."
echo " $2 = String da mensagem a ser enviada"
echo " Os arquivos serao criados na pasta apontada por $SEND_PASTA"

#cria o arquivo de processo em andamento
touch $CONTROLE

# processa toda o arquivo com celulares validos
for celular in `cat $VALIDATE_PASTA/$1`; do

	echo "enfileirando mensagem para numero de celular $celular"
	touch $SEND_PASTA/$DATA_SUFIX.$celular
	echo $2 >> $SEND_PASTA/$DATA_SUFIX.$celular

done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

#envia email
mail -s "[venus] Campanha arquivo $1" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Arquivo processado.........: $VALIDATE_PASTA/$1
EOF

banner ACABOU
exit 0


