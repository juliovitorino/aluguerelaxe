#!/bin/sh
PATH_SVN=/var/svnroot
PATH_OUTPUT=/var/comum
export PATH_SVN
export PATH_OUTPUT
DATA=`date +%Y%m%d%H%M`
export DATA
cd ${PATH_SVN}
svnadmin dump $1 > ${PATH_OUTPUT}/$1.${DATA}.dmp


