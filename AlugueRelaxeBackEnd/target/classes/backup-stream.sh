#!/bin/sh

#realiza o syncronismo com servidor da kinghost usando ssh
rsync -Cavz -e ssh aluguerelaxe@aluguerelaxe.com.br:/home/aluguerelaxe/www/stream/ /var/comum/backup/stream/

