#!/bin/bash

chmod u+x tyhjentaja.sh

cat ./loki.log >> ./vanhatlokit.log
rm ./loki.log
