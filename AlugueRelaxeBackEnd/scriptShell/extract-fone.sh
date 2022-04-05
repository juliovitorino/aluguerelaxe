#!/bin/sh -x
# extrator-telefone.sh
#
# extrair telefones de proprietarios de imoveis de sites concorrentes
#
# formato do arquivo de configuracao.dat
#
# PORTAL::URL::CONTADOR
# MaxTemporada::http://www.maxtemporada.com.br/temporada/Detalhes.aspx?IdAnuncio={##C##}::1464
# TemporadaNacional::http://www.temporadanacional.com.br/mostrar.php?id={##C##}#ad-image-0::1358
#
# Criado por Julio Vitorino, 27 de Novembro de 2012
# (C) AlugueRelaxe, 2012.

DATA_START=`date +%d/%m/%Y-%H:%M:%S`
DATA_ARQ_SAIDA=`date +%Y%m%d`

CONFIGURACAO="/home/aluguerelaxe/scriptShell/configuracao.dat"
CONFIG_BAK="${CONFIGURACAO}.bak"
REGEX="/home/aluguerelaxe/scriptShell/regex-fones.dat"
ARQ_SAIDA="/var/aluguerelaxe/data/telefones/aquisicao/fones-dono-imoveis-${DATA_ARQ_SAIDA}.dat"
CONFIG_TMP="${CONFIGURACAO}.${DATA_ARQ_SAIDA}.tmp"
ARQ_EM_ANDAMENTO="/tmp/extract-fone.working"

if [ -r "$ARQ_EM_ANDAMENTO" ]
then
    echo "processo anterior ainda em andamento."
    exit 1
fi
touch $ARQ_EM_ANDAMENTO

#checar se o arquivo de configuracao existe e pode ser lido
if [ ! -r "$CONFIGURACAO" ]
then
    echo "Arquivo de configuracao $CONFIGURACAO nao encontrado ou acesso negado."
    exit 1
fi

if [ ! -r "$REGEX" ]
then
    echo "Arquivo de configuracao $REGEX nao encontrado ou acesso negado."
    exit 1
fi

# backup do arquivo de configuracao
cp $CONFIGURACAO $CONFIG_BAK
touch $CONFIG_TMP

for linha in `cat $CONFIGURACAO`; do
	PORTAL=`echo $linha | cut -d "#" -f 1`
	URL=`echo $linha | cut -d "#" -f 2`
	CONTADOR=`echo $linha | cut -d "#" -f 3`
	
# remove a string {C} da url 	
#	URL_VALIDO=`echo $URL | sed -e 's/{C}/'${CONTADOR}'/'`
	URL_VALIDO=`echo $URL | awk '{gsub(/{C}/,'"$CONTADOR"')}; 1'`

# extrai os telefones da pagina carregada e alimenta uma base
# o ideal sera passar um SMS diretamente para o celular
    for regexfone in `cat $REGEX`; do
		TELEFONES=`curl "${URL_VALIDO}" | grep -oEi ${regexfone}`
		
		if [ ! -z "$TELEFONES" ]
		then
			for fones in `echo $TELEFONES`; do
				echo "$PORTAL: $fones" >> $ARQ_SAIDA
			done
		fi		
	done
	
# atualiza arquivo de configuracao com o novo contador usando comando sed - Stream EDitor	
	CONTADOR=$((CONTADOR+1))
	LINHA_NOVA=`echo $PORTAL#$URL#$CONTADOR`
	echo $LINHA_NOVA >> $CONFIG_TMP
done

rm $CONFIGURACAO
mv $CONFIG_TMP $CONFIGURACAO
rm $ARQ_EM_ANDAMENTO

DATA_END=`date +%d/%m/%Y-%H:%M:%S`

#envia email
#mail -s "[venus] Extrator de telefones $2" julio.vitorino@ig.com.br << EOF
#   Extrator de telefones executado em $MAILING_LIST
#   Processo iniciado..........: $DATA_START
#   Processo finalizado........: $DATA_END
#   Shell script...............: $0
#   Script SQL processado......: /home/aluguerelaxe/scriptSQL/$1
#   Banco de dados origem......: $DB_HOME_AR
#   Total de linhas processadas: $TOTAL_LINHAS
#EOF

exit 0
