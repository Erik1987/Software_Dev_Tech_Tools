#!/bin/bash 
chmod u+x lokittaja.sh
# Aja mieluiten mainohjelma.sh tiedosto viihteeksi
# Tämä toimii apuohjelmana sen lisäksi
# Palautan myös mainohjelma.sh koska siinä on paljon muutakin kivaa

aika=$(date +"%H:%M sekä varmuuden vuoksi nanosekunnit %N")
printf "%s" "heippa " "$aika" >> ./loki.log
printf "\n" >> ./loki.log
