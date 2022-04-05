#!/bin/sh
PATH_LIB=/var/aluguerelaxe/lib
export PATH_LIB
DATA=`date +%Y-%m-%d-%H.%M`
export DATA
java  -Xms512m -Xmx512m -Dfile.encoding=Cp1252 -cp /var/aluguerelaxe/lib/AlugueRelaxeWorkers.jar:${PATH_LIB}/log4j-1.2.15.jar:${PATH_LIB}/activation.jar:${PATH_LIB}/antlr-2.7.6.jar:${PATH_LIB}/commons-collections-3.1.jar:${PATH_LIB}/dom4j-1.6.1.jar:${PATH_LIB}/hibernate3.jar:${PATH_LIB}/javassist-3.4.GA.jar:${PATH_LIB}/jaybird-full-2.1.5.jar:${PATH_LIB}/jta-1.1.jar:${PATH_LIB}/slf4j-api-1.5.6.jar:${PATH_LIB}/slf4j-jdk14-1.5.6.jar:${PATH_LIB}/AlugueRelaxeBackEndServices.jar:${PATH_LIB}/eventlog.jar:${PATH_LIB}/mail.jar br.com.jcv.backend.workers.main.ClientRenovadorSorteioPublicidadePD > /home/aluguerelaxe/temp/log_renew_pd_${DATA}

