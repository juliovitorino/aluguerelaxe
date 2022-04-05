#!/bin/sh -x
# validate-celular.sh
#
# Validar numeros de telefones celular de acordo com as regras abaixo
# estes telefones estarao na pasta 
#
# a) tamanho da string entre 10 e 11 caracteres;
#
# b) primeiro digito do celular deve ser 7, 8 ou 9;
#
# c) regra especifica para DDD 11 - Cumpridos os items a e b; deve-se acrescentar um digito "9"
# na frente do numero do telefone SE E SOMENTE SE o tamanho da string do telefone for igual a 8;
#
# d) DDD somente éido se os numeros estiverem no intervalo 1 >= x <= 9
#
# Criado por Julio Vitorino, 13 de Dezembro de 2012
# (C) AlugueRelaxe, 2012.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_SUFIX=`date +%Y%m%d`
TELEFONES_HOME="/var/aluguerelaxe/data/telefones"
AQUISICAO_PASTA="$TELEFONES_HOME/aquisicao"
CLEAN_PASTA="$TELEFONES_HOME/clean"
QUEUE_PASTA="$TELEFONES_HOME/queue"
VALIDATE_PASTA="$TELEFONES_HOME/validate"
PURGED_PASTA="$TELEFONES_HOME/purged"
ARQ_SAIDA_VALIDATE="$VALIDATE_PASTA/validate-$DATA_SUFIX.dat"
ARQ_SAIDA_PURGED="$PURGED_PASTA/purged-$DATA_SUFIX.dat"
CONTROLE="/tmp/validate.working"

#checar se o arquivo de controle de processo em andamento existe e pode ser lido
if [ -r "$CONTROLE" ]
then
	banner WORKING
    echo "Processo de validacao de celular em andamento. Somente e permitido um processo por vez."
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

#cria os arquivos de resultados
if [ ! -r "$ARQ_SAIDA_VALIDATE" ]
then
	echo "criando arquivo $ARQ_SAIDA_VALIDATE"
	touch $ARQ_SAIDA_VALIDATE
fi

if [ ! -r "$ARQ_SAIDA_PURGED" ]
then
	echo "criando arquivo $ARQ_SAIDA_PURGED"
	touch $ARQ_SAIDA_PURGED
fi

banner INICIO

#contadores
CEL_INVALIDOS=0
CEL_VALIDOS=0

echo "processando a fila $QUEUE_PASTA"

# processa toda a fila
for linha in `ls $QUEUE_PASTA`; do

	echo "processando arquivo $linha"

# processa cada telefone
	for celular in `cat $QUEUE_PASTA/$linha`; do
		
		DDD=`echo $celular | cut -b 1-2`
		FONE=`echo $celular | cut -b 3-11 | sed 's/[ \t\r\n]*$//'`
		
		echo "Analisando $celular ..." 
		
# contador de erros
		ERROS=0
		
# executa procedimentos de analise do numero do celular
		echo "Fase 1 ... 3o. digito entre 6 e 9"
		DIGITO3=`echo $celular | cut -b 3-3`
		if [ $DIGITO3 -lt 6 ]
		then
			ERROS=$((ERROS+1))
		fi

		echo "Fase 2 ... tamanho da string do celular entre 10 e 11 caracteres"
		TAMSTR=`echo $celular | sed 's/[ \t\r\n]*$//' | wc -c`
		TAMSTR=$((TAMSTR-1))
		if [ $TAMSTR -lt 10 ]
		then
			ERROS=$((ERROS+1))
		fi 
		
		if [ $TAMSTR -gt 11 ]
		then
			ERROS=$((ERROS+1))
		fi 

		echo "Fase 3 ... identificacao do DDD <> 11 tamanho do telefone deve ser igual a 8. "
		if [ $DDD -gt 11 ]
		then
			if [ `echo $FONE | wc -c` -gt 9 ]
			then
				ERROS=$((ERROS+1))
			fi
		fi

		echo "Fase 4 ... identificacao do DDD 11 - Sao Paulo (Capital). Adiciona Dig. 9"
		if [ $DDD -eq 11 ]
		then
			if [ `echo $FONE | wc -c` -eq 9 ]
			then
				FONE="9${FONE}"
			fi
		fi

# adiciona o numero do telefone no local destino correto
		if [ $ERROS -gt 0 ]
		then
			echo "$ERROS encontrados. Expurgando celular $celular"
			echo "$celular" >> $ARQ_SAIDA_PURGED
			CEL_INVALIDOS=$((CEL_INVALIDOS+1))
		else
			echo "celular $celular validado com sucesso."
			echo "${DDD}${FONE}" >> $ARQ_SAIDA_VALIDATE
			CEL_VALIDOS=$((CEL_VALIDOS+1))
		fi
		
	done
	
	# remove arquivo da fila
	rm -rf $QUEUE_PASTA/$linha

done

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

# elimina arquivo de controle
rm -rf $CONTROLE

gsmsendsms -d /dev/ttyUSB0 -C +553191938200 -b 19200 2492753073  << EOF
Shell script: $0 inicio:$DATA_START fim:$DATA_END
EOF


#envia email
mail -s "[venus] Validar fila de celular $DATA_SUFIX" julio.vitorino@ig.com.br << EOF
   Processo iniciado..........: $DATA_START
   Processo finalizado........: $DATA_END
   Arquivo de controle thread.: $CONTROLE
   Shell script...............: $0
   Fila processada............: $QUEUE_PASTA
   Quantidade de arquivos fila: $QTD
   Arquivos de saida VALIDATE.: $ARQ_SAIDA_VALIDATE ($CEL_VALIDOS validos)
   Arquivos de saida PURGED...: $ARQ_SAIDA_PURGED ($CEL_INVALIDOS invalidos)
EOF

banner ACABOU
exit 0


