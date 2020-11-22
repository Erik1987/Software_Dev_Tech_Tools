#!/bin/bash 
#Kirjoita scripti (lokittaja.sh), joka tarvittaessa luo ja sitten 
#kirjoittaa tiedostoon loki.log tämän hetken kellonajan ja tekstin 
#“heippa”. Käytä googlea selvittääksesi miten parhaiten saat aikaleiman 
#tulostettua scriptiin. Ajasta scripti chronilla pyörimään minuutin välein. 
#Kirjoita toinen scripti (tyhjentaja.sh), joka kopioi loki.log-tiedoston 
#sisällön vanhatlogit.log-tiedoston loppuun ja sitten poistaa 
#loki.log-tiedoston. Ajasta tyhjentaja-scripti pyörimään chronissa 
#joka keskiyö. Palauta luomasi kaksi linux-skriptiä (lokittaja.sh ja 
#tyhjentaja.sh) sekä tiedostossa /var/spool/cron/crontabs/kayttajatunnus 
#sijaitseva cron-tiedosto, josta näkyy tekemäsi ajastukset. 
#Vinkki: cron-tiedoston käsittelyyn (palautusta varten) tarvitset 
#todennäköisesti sudo-komentoa.

# Programmer Erik
chmod u+x lokittaja.sh
echo "Syötä etunimesi ja paina [ENTER]: "
read name
name="${name^}"
string="Terve"
printf "\n"
echo "${string} ${name^}!"
printf "\n"
kysymys="Haluatko että kirjaan kellonajan loki.log tiedoston? [y/n]: "
if [[ $name == "Teemu" ]]
then
	printf "Teemu: ${kysymys}"
else
	printf "Ohto: ${kysymys}"
fi 
printf "\n"

read response

printf "\n"

if [[ $response == "y" ]]
then
	aika=$(date +"%H:%M sekä varmuuden vuoksi nanosekunnit %N")
	printf "Kello on: %s\n " "${aika}"
	printf "%s" "heippa " "$aika" >> ./loki.log
else
	printf "Kiitos käyttämästä Erikin bash scriptiä."
fi
printf "\n"
printf "Haluatko että kirjaan lokiin kellonajan minuutin välein? [y/n]: "
printf "\n"
read response2
if [[ $response2 == "y" ]]
then
	printf "watch -n1 -x $aika >> ./loki.log"
else
	printf "Mukavaa päivänjatkoa!"
fi
printf "\n"
printf "Haluatko Matrix elokuvasta tuttua efektiä? [y/n]"
printf "\n"
read response3

if [[ $response3 == "y" ]]
then
	echo -e "\033[2J\033[?25l"; R=`tput lines` C=`tput cols`;: $[R--] ; while true 
	do ( e=echo\ -e s=sleep j=$[RANDOM%C] d=$[RANDOM%R];for i in `eval $e {1..$R}`;
	do c=`printf '\\\\0%o' $[RANDOM%57+33]` 
	$e "\033[$[i-1];${j}H\033[32m$c\033[$i;${j}H\033[37m"$c; $s 0.1;if [ $i -ge $d ]
	then $e "\033[$[i-d];${j}H ";fi;done;for i in `eval $e {$[i-d]..$R}`; #[mat!rix]
	do echo -e "\033[$i;${j}f ";$s 0.1;done)& sleep 0.05;done #(c) 2011 -- [ BruXy ]
else
	printf "Tähän päättyi seikkailu bash scriptaukseen."
fi
